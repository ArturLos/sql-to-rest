package def.service.dao;

import def.pojo.Column;
import def.pojo.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SourceDbStructureDAO extends NamedParameterJdbcDaoSupport {

    private final String q_table_list = "SHOW TABLES";
    private final String q_table_columns = "DESC ";
    
    @Autowired
    public SourceDbStructureDAO(DataSource ds) {
        super();
        setDataSource(ds);
    }

    public List getTableList(){
        return getNamedParameterJdbcTemplate().query(q_table_list, new HashMap(), new RowMapper<Table>() {
            @Override
            public Table mapRow(ResultSet rs, int i) throws SQLException {
                return new Table(rs.getString(1));
            }
        });
    }
    
    public List<Column> getColumnList(String table_name){
        HashMap parametry = new HashMap();
        return getNamedParameterJdbcTemplate().query(q_table_columns+table_name, parametry, new RowMapper<Column>() {
            @Override
            public Column mapRow(ResultSet rs, int i) throws SQLException {
                return new Column(rs.getString("Field"), rs.getString("Key").equalsIgnoreCase("PRI")? Boolean.TRUE:Boolean.FALSE);
            }
        });
    }
    
}

/**
 * show tables; - lista wszystkich tabel w danej bazie danych
 * desc <tablename/>; - opis poszczególnych kolumn tabeli: Field(nazwa); Type; Null; Key(primary); Default; Extra;
 */