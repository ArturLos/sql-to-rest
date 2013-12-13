
package def.controller;

import def.pojo.Kolumna;
import def.pojo.Struktura;
import def.pojo.Tabela;
import def.service.StrukturaService;
import def.service.impl.ZrodloDanychServiceImpl;
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
public class BibliotekaController {
    private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(BibliotekaController.class.getName());
    
    @Autowired(required = true)
    StrukturaService strukturaService;
    
    @Autowired(required = true)
    ZrodloDanychServiceImpl zrodloDanychService;
    
    @ModelAttribute("struktury")
    public List<Struktura> setListaStruktur(){
        LOG.info("Pobieranie listy struktur");
        return strukturaService.getAll();
    }
    
    @RequestMapping({"/","/struktura"})
    public String index(ModelMap model){
        return "struktura";
    }
    
    
    @RequestMapping(value = {"/struktura/dodaj"}, method = RequestMethod.GET)
    public String strukturaDodaj(@ModelAttribute Struktura struktura, ModelMap model){
        return "dodaj_strukture";
    }
    @RequestMapping(value = {"/struktura/dodaj"}, method = RequestMethod.POST)
    public String strukturaDodajPost(@ModelAttribute @Valid Struktura struktura, BindingResult bindingResult, ModelMap model){
        try{
            strukturaService.add(struktura);
            return "redirect:/struktura";
        }catch(RuntimeException e){
            LOG.log(Level.WARNING, "Wystapil blad podczas dodawania struktury");
            return "dodaj_strukture";
        }
    }
    
    @RequestMapping("/struktura/{idStruktura}")
    public String strukturaSzczegoly(@ModelAttribute @PathVariable String idStruktura,ModelMap model){
        Struktura cur_str = strukturaService.getStruktura(idStruktura);
        model.put("curStr", cur_str);
        return "struktura";
    }
    @RequestMapping("/struktura/{idStruktura}/del")
    public String strukturaUsun(@PathVariable String idStruktura, ModelMap model){
        strukturaService.remove(idStruktura);
        return "redirect:/struktura";
    }
    
    @RequestMapping(value = {"/struktura/{idStruktura}/dodajTabela"}, method = RequestMethod.GET)
    public String dodajTabele(@ModelAttribute @PathVariable String idStruktura, ModelMap model){
        model.put("tabelaList", zrodloDanychService.getTabele());
        model.put("tab", new Tabela(""));
        return "dodaj_tabele";
    }
    @RequestMapping(value = {"/struktura/{idStruktura}/dodajTabela/{idTabela}"}, method = RequestMethod.GET)
    public String dodajTabeleT(@ModelAttribute @PathVariable String idStruktura, @PathVariable String idTabela, ModelMap model){
        model.put("tabelaList", zrodloDanychService.getTabele());
        if(!idTabela.equals("")){
            model.put("tab",new Tabela(idTabela));
            model.put("kolumnaList", zrodloDanychService.getKolumnyTabeli(idTabela));
        }
        return "dodaj_tabele";
    }
    @RequestMapping(value = {"/struktura/{idStruktura}/dodajTabela/{idTabela}"}, method = RequestMethod.POST)
    public String dodajTabeleTPost(@ModelAttribute @PathVariable String idStruktura, @PathVariable String idTabela,
            @ModelAttribute(value = "tab") Tabela utworzonaTabela) {
        utworzonaTabela.setStruktura(strukturaService.getStruktura(idStruktura));
        for(Kolumna k: utworzonaTabela.getKolumny()){k.setTabela(utworzonaTabela);}
        strukturaService.add(utworzonaTabela);
        LOG.log(Level.INFO, utworzonaTabela.toString());
        return "redirect:/struktura/"+idStruktura;
    }
    @RequestMapping(value = {"/struktura/{idStruktura}/tabela/{idTabela}/del"})
    public String usunTabeleT(@ModelAttribute @PathVariable String idStruktura, @PathVariable String idTabela){
        Set<Tabela> tabele = strukturaService.getStruktura(idStruktura).getTabele();
        Iterator<Tabela> it = tabele.iterator();
        while( it.hasNext() ){
            Tabela el = it.next();
            if(el.getNazwa().equalsIgnoreCase(idTabela)){
                strukturaService.remove(el);
                break;
            }
        }
        return "redirect:/struktura/"+idStruktura;
    }
    @RequestMapping(value = {"/struktura/{idStruktura}/tabela/{idTabela}"}, method = RequestMethod.GET)
    public String edytujTabele(@PathVariable String idStruktura, @PathVariable String idTabela, ModelMap model){
        model.put("tabelaList", zrodloDanychService.getTabele());
        if(!idTabela.equals("")){
            model.put("kolumnaList", zrodloDanychService.getKolumnyTabeli(idTabela));
        }
        Tabela t = strukturaService.getStruktura(idStruktura).getTabela(idTabela);
        model.put("tab", t);
        return "dodaj_tabele";
    }
    @RequestMapping(value = {"/struktura/{idStruktura}/tabela/{idTabela}"}, method = RequestMethod.POST)
    public String edytujTabelePost(@PathVariable String idStruktura, @PathVariable String idTabela, 
                                    @ModelAttribute Tabela tab, ModelMap model){
        Tabela t = strukturaService.getStruktura(idStruktura).getTabela(idTabela);
        strukturaService.updateKolumny(t, tab.getKolumny());
        return "redirect:/struktura/"+idStruktura;
    }
    
    @RequestMapping("/tabele")
    public String dodajStrone(ModelMap model){
        model.put("tabele", zrodloDanychService.getTabele());
        model.put("kolumny", zrodloDanychService.getKolumnyTabeli("faktura"));
//        bibliotekaService.addBiblioteka(new Biblioteka("NEW<s>_ID_14789"));
        return "tabele";
    }
    
    
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        
        /* Konwersja między Set<String> a Set<Kolumna> */
         dataBinder.registerCustomEditor(Set.class, "kolumny", new CustomCollectionEditor(Set.class){
             @Override
             protected Object convertElement(Object element) {
                 if(element instanceof String){
                     LOG.log(Level.INFO, "konwersja pola 'kolumny' na obiekt Kolumna ["+element+"]");
                     return new Kolumna((String) element, Boolean.FALSE);
                 }else if(element instanceof Kolumna){
                     LOG.log(Level.INFO, "konwersja obiektu Kolumna na obiekt Kolumna ["+((Kolumna)element).getNazwa()+"]");
                     return element;
                 }else{
                     LOG.log(Level.WARNING, "nie obsłużony typ elementu konwersji custom editora");
                 }
                 return null;
             }
         
         });
     }
}
