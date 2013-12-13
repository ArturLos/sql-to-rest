package def.service.dao;

import def.pojo.Kolumna;
import def.pojo.Tabela;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author artur
 */
@Repository
public class TabelaDAO extends GenericDAO<Tabela, Integer>{

    public void updateKolumny(Tabela t, Set<Kolumna> set){
        
//        getSessionFactory().getCurrentSession().flush();
//        getSessionFactory().getCurrentSession().clear();
        getSessionFactory().getCurrentSession().createQuery("delete from Kolumna where tabela=:tab").setParameter("tab", t).executeUpdate();
//        t.getKolumny().clear();
//        getSessionFactory().getCurrentSession().update(t);
        for(Kolumna k : set){ 
            k.setTabela(t);
            getSessionFactory().getCurrentSession().save(k);
        }
//        
    }
}
