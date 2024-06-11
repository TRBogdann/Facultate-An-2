import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DateModel {
    Statement s;
    public DateModel(Connection c) throws SQLException {
        s = c.createStatement();
    }
    public List<Capitol> select()
    {
        List<Capitol> list = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM CAPITOLE";
            ResultSet rs = s.executeQuery(query);
            while(rs.next())
            {
                Capitol c = new Capitol();
                c.setCod(rs.getInt(1));
                c.setCodSantier(rs.getInt(2));
                c.setDenumire(rs.getString(3));
                c.setUnitate(rs.getString(4));
                c.setCantitate(rs.getFloat(5));
                c.setPret(rs.getFloat(6));
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
