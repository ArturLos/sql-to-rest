package def.service.impl;

import def.pojo.Structure;
import def.rest.OutputHelperService;
import def.rest.XmlOutputHelperService;
import def.service.RestDataService;
import def.service.dao.RestDataDAO;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RestDataServiceImpl implements RestDataService {
    
    // TODO: Zrobić transakcje dla pobierania danych. Tak żeby zmiana konfiguracji nie zakłucała działania już istniejących selectów.
    Transaction tx;
    
    @Autowired
    private RestDataDAO restDataDAO;
    
    @Override
    public void loadData(Structure struktura, Map<String, String> parametry, HttpServletResponse response) throws Exception {
        XmlOutputHelperService templateHandler = new XmlOutputHelperService(response); // pobierać z kontekstu po nazwie np "templateHandler"
        templateHandler.setStructure(struktura);
        templateHandler.pre();
        restDataDAO.loadData(struktura, parametry, templateHandler);
        templateHandler.post();
    }
    
}
