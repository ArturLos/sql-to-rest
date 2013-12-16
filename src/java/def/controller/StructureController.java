
package def.controller;

import def.pojo.Column;
import def.pojo.Structure;
import def.pojo.Table;
import def.service.StructureService;
import def.service.impl.DataSourceServiceImpl;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StructureController {
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(StructureController.class.getName());
    
    @Autowired(required = true)
    StructureService structureService;
    
    @Autowired(required = true)
    DataSourceServiceImpl dataSourceService;
    
    @ModelAttribute("struktury")
    public List<Structure> setListaStruktur(){
        LOG.info("Pobieranie listy struktur");
        return structureService.getAll();
    }
    
    @RequestMapping({"/","/struktura"})
    public String index(ModelMap model){
        return "struktura";
    }
    
    
    @RequestMapping(value = {"/struktura/dodaj"}, method = RequestMethod.GET)
    public String strukturaDodaj(@ModelAttribute Structure struktura, ModelMap model){
        return "dodaj_strukture";
    }
    @RequestMapping(value = {"/struktura/dodaj"}, method = RequestMethod.POST)
    public String strukturaDodajPost(@ModelAttribute @Valid Structure struktura, BindingResult bindingResult, ModelMap model){
        try{
            structureService.add(struktura);
            return "redirect:/struktura";
        }catch(RuntimeException e){
            LOG.log(Level.WARNING, "Wystapil blad podczas dodawania struktury");
            return "dodaj_strukture";
        }
    }
    
    @RequestMapping("/struktura/{idStructure}")
    public String strukturaSzczegoly(@ModelAttribute @PathVariable String idStructure,ModelMap model){
        Structure cur_str = structureService.getStructure(idStructure);
        model.put("curStr", cur_str);
        return "struktura";
    }
    @RequestMapping("/struktura/{idStructure}/del")
    public String strukturaUsun(@PathVariable String idStructure, ModelMap model){
        structureService.remove(idStructure);
        return "redirect:/struktura";
    }
    
    @RequestMapping(value = {"/struktura/{idStructure}/dodajTabela"}, method = RequestMethod.GET)
    public String dodajTabele(@ModelAttribute @PathVariable String idStructure, ModelMap model){
        model.put("tabelaList", dataSourceService.getTables());
        model.put("tab", new Table(""));
        return "dodaj_tabele";
    }
    @RequestMapping(value = {"/struktura/{idStructure}/dodajTabela/{idTable}"}, method = RequestMethod.GET)
    public String dodajTabeleT(@ModelAttribute @PathVariable String idStructure, @PathVariable String idTable, ModelMap model){
        model.put("tabelaList", dataSourceService.getTables());
        if(!idTable.equals("")){
            model.put("tab",new Table(idTable));
            model.put("kolumnaList", dataSourceService.getTableColumns(idTable));
        }
        return "dodaj_tabele";
    }
    @RequestMapping(value = {"/struktura/{idStructure}/dodajTabela/{idTable}"}, method = RequestMethod.POST)
    public String dodajTabeleTPost(@ModelAttribute @PathVariable String idStructure, @PathVariable String idTable,
            @ModelAttribute(value = "tab") Table createdTable) {
        createdTable.setStructure(structureService.getStructure(idStructure));
        for(Column k: createdTable.getColumns()){k.setTable(createdTable);}
        structureService.add(createdTable);
        LOG.log(Level.INFO, createdTable.toString());
        return "redirect:/struktura/"+idStructure;
    }
    @RequestMapping(value = {"/struktura/{idStructure}/tabela/{idTable}/del"})
    public String usunTabeleT(@ModelAttribute @PathVariable String idStructure, @PathVariable String idTable){
        Set<Table> tabele = structureService.getStructure(idStructure).getTables();
        Iterator<Table> it = tabele.iterator();
        while( it.hasNext() ){
            Table el = it.next();
            if(el.getNazwa().equalsIgnoreCase(idTable)){
                structureService.remove(el);
                break;
            }
        }
        return "redirect:/struktura/"+idStructure;
    }
    @RequestMapping(value = {"/struktura/{idStructure}/tabela/{idTable}"}, method = RequestMethod.GET)
    public String edytujTabele(@PathVariable String idStructure, @PathVariable String idTable, ModelMap model){
        model.put("tabelaList", dataSourceService.getTables());
        if(!idTable.equals("")){
            model.put("kolumnaList", dataSourceService.getTableColumns(idTable));
        }
        Table t = structureService.getStructure(idStructure).getTable(idTable);
        model.put("tab", t);
        return "dodaj_tabele";
    }
    @RequestMapping(value = {"/struktura/{idStructure}/tabela/{idTable}"}, method = RequestMethod.POST)
    public String edytujTabelePost(@PathVariable String idStructure, @PathVariable String idTable, 
                                    @ModelAttribute Table tab, ModelMap model){
        Table t = structureService.getStructure(idStructure).getTable(idTable);
        structureService.updateColumns(t, tab.getColumns());
        return "redirect:/struktura/"+idStructure;
    }
    
    @RequestMapping("/tabele")
    public String dodajStrone(ModelMap model){
        model.put("tabele", dataSourceService.getTables());
        model.put("kolumny", dataSourceService.getTableColumns("faktura"));
//        bibliotekaService.addBiblioteka(new Biblioteka("NEW<s>_ID_14789"));
        return "tabele";
    }
    
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        
        /* Konwersja między Set<String> a Set<Column> */
         dataBinder.registerCustomEditor(Set.class, "kolumny", new CustomCollectionEditor(Set.class){
             @Override
             protected Object convertElement(Object element) {
                 if(element instanceof String){
                     LOG.log(Level.INFO, "konwersja pola 'kolumny' na obiekt Column ["+element+"]");
                     return new Column((String) element, Boolean.FALSE);
                 }else if(element instanceof Column){
                     LOG.log(Level.INFO, "konwersja obiektu Column na obiekt Column ["+((Column)element).getNazwa()+"]");
                     return element;
                 }else{
                     LOG.log(Level.WARNING, "nie obsłużony typ elementu konwersji custom editora");
                 }
                 return null;
             }
         
         });
     }
}
