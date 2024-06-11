import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ModelPrograme {
    private final Statement s;
    public ModelPrograme(Connection c) throws SQLException {
        DatabaseMetaData md = c.getMetaData();
        s = c.createStatement();
        ResultSet rs = md.getTables(null,null,"PROGRAME",new String[]{"TABLE"});
        if(!rs.next())
        {
            String query = " CREATE TABLE PROGRAME("
                    +"CodProgram INT PRIMARY KEY,"
                    +"CodFacultate VARCHAR(10),"
                    +"Denumire VARCHAR(100),"
                    +"NumarLocuri INT)";
            s.execute(query);
        }

    }
    public List<Program> select(String filter) throws SQLException {
        List<Program> list = new ArrayList<>();
        String query = "SELECT * FROM PROGRAME";
        if(!filter.isEmpty())
        {
            query += " WHERE "+filter;
        }
        ResultSet rs = s.executeQuery(query);
        while(rs.next())
        {
            Program p = new Program();
            p.CodProgram = rs.getInt(1);
            p.CodFacultate = rs.getString(2);
            p.Denumire = rs.getString(3);
            p.NumarLocuri  = rs.getInt(4);
            list.add(p);
        }
        return list;
    }
    public void insert(Program p) throws SQLException {
        String query = "INSERT INTO PROGRAME VALUES("+
                p.getCodProgram()+",'"+p.getCodFacultate()+"','"+
                p.getDenumire()+"',"+p.NumarLocuri+")";
        s.execute(query);
    }
}
