package def.controller;

import def.pojo.Structure;
import def.service.RestDataService;
import def.service.StructureService;
import def.service.DataSourceService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/rest**")
public class RestController {
    
    @Autowired
    private DataSourceService dataSourceService;
    
    @Autowired
    private StructureService structureService;
    
    @Autowired
    private RestDataService restDataService;
    
    @RequestMapping(value = {"/struktura/{idStruktury}"}, method = RequestMethod.GET)
    public void get(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String idStruktury) throws Exception{
        Structure struktura = structureService.getStructure(idStruktury);
        restDataService.loadData(struktura, null, response);
        response.flushBuffer();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response){
        
    }
    
//    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
    public String info(){
        return "info";
    }
    
//    @RequestMapping(value = "/tabela", method = RequestMethod.GET)
    public String t(ModelMap model){ 
        model.addAttribute("name", "tabela");
        return "info";}

}