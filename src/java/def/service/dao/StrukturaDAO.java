package def.service.dao;

import def.pojo.Struktura;
import org.springframework.stereotype.Repository;

/**
 *
 * @author artur
 */
@Repository
public class StrukturaDAO extends GenericDAO<Struktura, Integer>{

    public Struktura getByNazwa(String idStruktura){
        return (Struktura) getSessionFactory().getCurrentSession().createQuery("from Struktura AS s where s.nazwa=:nazwa").setParameter("nazwa", idStruktura).uniqueResult();
    }
}
