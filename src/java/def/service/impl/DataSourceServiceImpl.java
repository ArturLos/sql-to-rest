package def.service.impl;

import def.pojo.Column;
import def.pojo.Table;
import def.service.DataSourceService;
import def.service.dao.SourceDbStructureDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DataSourceServiceImpl implements DataSourceService {
    
    @Autowired
    private SourceDbStructureDAO sourceDb;
    
    @Override
    public List<Table> getTables(){
        return sourceDb.getTableList();
    }
    
    @Override
    public List<Column> getTableColumns(String tabela){
        return  sourceDb.getColumnList(tabela);
    }
}
