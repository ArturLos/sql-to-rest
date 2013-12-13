package def.rest;

import org.springframework.jdbc.core.RowCallbackHandler;

public interface OutputHelperService extends RowCallbackHandler{
    
    public void pre() throws Exception;
    public void post() throws Exception;
    
}
