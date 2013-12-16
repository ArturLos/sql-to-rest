package def.pojo;

import java.util.LinkedHashSet;
import java.util.Set;

public class Table {
    
    private Integer id;
    private String nazwa;
    private Structure structure;
    private Set<Column> columns;

    public Table() {
    }

    public Table(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure struktura) {
        this.structure = struktura;
    }

    public Set<Column> getColumns() {
        if(columns != null){
            return columns;
        }else{
            columns=new LinkedHashSet<Column>();
            return columns;
        }
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Table{" + "id=" + id + ", nazwa=" + nazwa + '}';
    }
    
}
