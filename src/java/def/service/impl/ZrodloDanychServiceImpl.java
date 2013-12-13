package def.service.impl;

import def.pojo.Kolumna;
import def.pojo.Tabela;
import def.service.ZrodloDanychService;
import def.service.dao.SourceDbStructureDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZrodloDanychServiceImpl implements ZrodloDanychService {
    
    @Autowired
    private SourceDbStructureDAO sourceDb;
    
    @Override
    public List<Tabela> getTabele(){
        return sourceDb.getTabelaList();
    }
    
    @Override
    public List<Kolumna> getKolumnyTabeli(String tabela){
        return  sourceDb.getKolumnaList(tabela);
    }
}
