package def.service;

import def.pojo.Column;
import def.pojo.Table;
import java.util.List;

public interface DataSourceService {
    public List<Column> getTableColumns(String tabela);
    public List<Table> getTables();
}
