package def.service.impl;

import def.pojo.Kolumna;
import def.pojo.Struktura;
import def.pojo.Tabela;
import def.service.StrukturaService;
import def.service.dao.StrukturaDAO;
import def.service.dao.TabelaDAO;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StrukturaServiceImpl implements StrukturaService{
    
    int changeFired = 1;
    List<Struktura> strukturaList;
    
    @Autowired
    private StrukturaDAO strukturaDao;
    @Autowired
    private TabelaDAO tabelaDao;
    
    public StrukturaServiceImpl() {
    }
    
    @Transactional
    @Override
    public List<Struktura> getAll(){
        if(changeFired==1){
            strukturaList = strukturaDao.getAll();
            changeFired = 0;
        }
        return strukturaList;
        
    }
    
    @Transactional
    @Override
    public void add(Struktura b){
        Integer result = strukturaDao.save(b);
        if(result!=null)
            fireChange();
        System.out.println("Nowej bibliotece przydziolono ID: "+result);
    }
    
    @Transactional
    @Override
    public void remove(String idStruktura){
        strukturaDao.remove(getStruktura(idStruktura));
        fireChange();
    }

    @Transactional
    @Override
    public void add(Tabela t) {
        tabelaDao.save(t);
        
    }
    @Transactional
    @Override
    public void remove(Tabela t){
        tabelaDao.remove(t);
    }
    
    @Transactional
    @Override
    public void updateKolumny(Tabela t, Set<Kolumna> set){
        tabelaDao.updateKolumny(t, set);
    }

    @Transactional
    @Override
    public Struktura getStruktura(String idStruktura) {
        Struktura result = strukturaDao.getByNazwa(idStruktura);
        Hibernate.initialize(result.getTabele());
        for(Tabela t: result.getTabele()){
            Hibernate.initialize(t.getKolumny());
        }
//        result.getTabele().size(); // Mało oczywiste rozwiązanie ale skuteczne
//        System.out.println("Wzrócono elementów: "+result.getTabele().size());
//        for(Tabela t: result.getTabele()){
//            t.getId();
//            t.getNazwa();
//            System.out.println(t.getId()+"-"+t.getNazwa());
//        }
//        result.setTabele(result.getTabele()); // jakoś wgraż to pole
        return result;
    }
    
    public void fireChange(){
        changeFired = 1;
        getAll();
    }
}
