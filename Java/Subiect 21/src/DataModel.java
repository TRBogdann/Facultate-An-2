import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataModel {
    Statement s;
    public DataModel(Connection c) throws SQLException {
        s = c.createStatement();
    }
    public List<Imprumut> select()
    {
        List<Imprumut> list = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM Imprumuturi";
            ResultSet rs = s.executeQuery(query);
            while(rs.next())
            {
                Imprumut p = new Imprumut(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
                list.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
