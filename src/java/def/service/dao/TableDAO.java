package def.service.dao;

import def.pojo.Column;
import def.pojo.Table;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author artur
 */
@Repository
public class TableDAO extends GenericDAO<Table, Integer>{

    public void updateColumns(Table t, Set<Column> set){
        
//        getSessionFactory().getCurrentSession().flush();
//        getSessionFactory().getCurrentSession().clear();
        getSessionFactory().getCurrentSession().createQuery("delete from Column where table=:tab").setParameter("tab", t).executeUpdate();
//        t.getKolumny().clear();
//        getSessionFactory().getCurrentSession().update(t);
        for(Column k : set){ 
            k.setTable(t);
            getSessionFactory().getCurrentSession().save(k);
        }
//        
    }
}
