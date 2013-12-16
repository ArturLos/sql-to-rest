package def.service.impl;

import def.pojo.Column;
import def.pojo.Structure;
import def.pojo.Table;
import def.service.StructureService;
import def.service.dao.StructureDAO;
import def.service.dao.TableDAO;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StructureServiceImpl implements StructureService{
    
    int changeFired = 1;
    List<Structure> strukturaList;
    
    @Autowired
    private StructureDAO strukturaDao;
    @Autowired
    private TableDAO tableDao;
    
    public StructureServiceImpl() {
    }
    
    @Transactional
    @Override
    public List<Structure> getAll(){
        if(changeFired==1){
            strukturaList = strukturaDao.getAll();
            changeFired = 0;
        }
        return strukturaList;
        
    }
    
    @Transactional
    @Override
    public void add(Structure b){
        Integer result = strukturaDao.save(b);
        if(result!=null)
            fireChange();
        System.out.println("Nowej bibliotece przydziolono ID: "+result);
    }
    
    @Transactional
    @Override
    public void remove(String idStructure){
        strukturaDao.remove(getStructure(idStructure));
        fireChange();
    }

    @Transactional
    @Override
    public void add(Table t) {
        tableDao.save(t);
        
    }
    @Transactional
    @Override
    public void remove(Table t){
        tableDao.remove(t);
    }
    
    @Transactional
    @Override
    public void updateColumns(Table t, Set<Column> set){
        tableDao.updateColumns(t, set);
    }

    @Transactional
    @Override
    public Structure getStructure(String idStructure) {
        Structure result = strukturaDao.getByNazwa(idStructure);
        Hibernate.initialize(result.getTables());
        for(Table t: result.getTables()){
            Hibernate.initialize(t.getColumns());
        }
//        result.getTables().size(); // Mało oczywiste rozwiązanie ale skuteczne
//        System.out.println("Wzrócono elementów: "+result.getTables().size());
//        for(Table t: result.getTables()){
//            t.getId();
//            t.getNazwa();
//            System.out.println(t.getId()+"-"+t.getNazwa());
//        }
//        result.setTables(result.getTables()); // jakoś wgraż to pole
        return result;
    }
    
    public void fireChange(){
        changeFired = 1;
        getAll();
    }
}
