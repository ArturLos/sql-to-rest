package def.service.dao;

import def.pojo.Column;
import def.pojo.Structure;
import def.pojo.Table;
import def.rest.OutputHelperService;
import java.util.Map;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RestDataDAO extends NamedParameterJdbcDaoSupport{
    private static final Logger LOG = Logger.getLogger(RestDataDAO.class.getName());

    @Autowired
    public RestDataDAO(DataSource dataSource) {
        super();
        setDataSource(dataSource);
    }
    
    public void loadData(Structure struktura, Map<String, String> parametry, final OutputHelperService template){
        String query = createQuery(struktura);
        LOG.info(new StringBuilder().append("RestDataDAO query: ").append(query).toString());
        getNamedParameterJdbcTemplate().query(query, parametry, template);
    }
    
    protected String createQuery(Structure struktura){
        if(struktura==null) throw new NullPointerException("Struktura nie może być null");
        StringBuilder sb = new StringBuilder();
        if(struktura.getTables().size()>0){
            Table tabela = struktura.getTables().iterator().next();
            sb.append("SELECT ");
            for(Column kolumna : tabela.getColumns()){
                sb.append(kolumna.getNazwa());
                sb.append(", ");
            }
            sb.deleteCharAt(sb.length()-2);
            sb.append("FROM ");
            sb.append(tabela.getNazwa());
        }
        return sb.toString();
    }
}
