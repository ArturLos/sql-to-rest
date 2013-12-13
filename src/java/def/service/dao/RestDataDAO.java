package def.service.dao;

import def.pojo.Kolumna;
import def.pojo.Struktura;
import def.pojo.Tabela;
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
    
    public void loadData(Struktura struktura, Map<String, String> parametry, final OutputHelperService template){
        String query = createQuery(struktura);
        LOG.info(new StringBuilder().append("RestDataDAO query: ").append(query).toString());
        getNamedParameterJdbcTemplate().query(query, parametry, template);
    }
    
    protected String createQuery(Struktura struktura){
        if(struktura==null) throw new NullPointerException("Struktura nie może być null");
        StringBuilder sb = new StringBuilder();
        if(struktura.getTabele().size()>0){
            Tabela tabela = struktura.getTabele().iterator().next();
            sb.append("SELECT ");
            for(Kolumna kolumna : tabela.getKolumny()){
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
