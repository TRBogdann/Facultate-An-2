import javax.xml.crypto.Data;
import java.sql.*;

public class DataModel {
    Statement s;
    public DataModel(Connection c) throws SQLException {
        DatabaseMetaData meta = c.getMetaData();
        ResultSet rs = meta.getTables(null,null,"Optiuni",new String[]{"TABLE"});
        s = c.createStatement();
        if(!rs.next())
        {
            String query = "CREATE TABLE Optiuni("+
                    "nume varchar2(50),"
                    +"codProgram int,"
                    +"punctaj NUMBER (4,2))";
            s.execute(query);

        }

    }
    public void insert(Candidat c) throws SQLException {
        for(var opt:c.getOptiuni().entrySet())
        {
            String query = "INSERT INTO Optiuni values("
                    +"'"+c.getNume()+"',"+opt.getKey()+","+opt.getValue()+")";
            s.execute(query);
        }
    }

}
