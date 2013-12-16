package def.service;

import def.pojo.Structure;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface RestDataService {
    
    public void loadData(
            Structure struktura, // wystarczy wypełnić ID albo nazwę
            Map<String, String> parametry, // albo zamienić na Properties, zależy co lżejsze) throws SQLException;
            HttpServletResponse responce
    ) throws Exception; // Zaimplementować jakąś ciekawszą strukturę wyjątków
    
}
