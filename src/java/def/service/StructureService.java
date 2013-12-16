package def.service;

import def.pojo.Column;
import def.pojo.Structure;
import def.pojo.Table;
import java.util.List;
import java.util.Set;

public interface StructureService {
    public List<Structure> getAll();    
    public void add(Structure s);
    public void remove(String nazwa);
    public void add(Table t);
    public void remove(Table t);
    public void updateColumns(Table t, Set<Column> set);
    public Structure getStructure(String idStructure);
}
