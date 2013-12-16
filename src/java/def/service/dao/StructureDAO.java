package def.service.dao;

import def.pojo.Structure;
import org.springframework.stereotype.Repository;

/**
 *
 * @author artur
 */
@Repository
public class StructureDAO extends GenericDAO<Structure, Integer>{

    public Structure getByNazwa(String idStructure){
        return (Structure) getSessionFactory().getCurrentSession().createQuery("from Structure AS s where s.nazwa=:nazwa").setParameter("nazwa", idStructure).uniqueResult();
    }
}
