package def.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author artur
 */
public abstract class GenericDAO<T, ID extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> genClass;
    
    public ID save(T entity){
        return (ID)sessionFactory.getCurrentSession().save(entity);
    }
    
    public List<T> getAll(){
        if(getGenClass()==null) setGenClass();
        return sessionFactory.getCurrentSession().createQuery("from "+ genClass.getName()).list();
    }
    
    public void remove(T entity){
        sessionFactory.getCurrentSession().delete(entity);
    }

    public Class<T> getGenClass() {
        return genClass;
    }

    public void setGenClass() {
        this.genClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}