import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private final Statement s;
    public DataModel(Connection c) throws SQLException {
        s = c.createStatement();
        DatabaseMetaData md = c.getMetaData();
        ResultSet rs = md.getTables(null,null,"MateriiPrime",new String[]{"TABLE"});
        if(!rs.next())
        {
            String query = "CREATE TABLE MateriiPrime("
                    +"COD INT PRIMARY KEY,"
                    +"DENUMIRE VARCHAR(50),"
                    +"CANTITATE NUMBER(6,2),"
                    +"PRET NUMBER(6,2),"
                    +"UNITATE VARCHAR(10))";
            s.execute(query);
        }
    }
    public void insert(MateriiPrime p) throws SQLException {
        String query = "INSERT INTO MateriiPrime VALUES("
                +p.getCod()+",'"+p.getDenumire()+"',"+p.getCantitate()+","
                +p.getPret()+",'"+p.getUnitate()+"')";
        s.execute(query);
    }

    public List<MateriiPrime> select() throws SQLException {
        List<MateriiPrime> list = new ArrayList<>();
        String query = "SELECT * FROM MateriiPrime";
        ResultSet rs = s.executeQuery(query);
        while(rs.next())
        {
            MateriiPrime p = new MateriiPrime();
            p.setCod(rs.getInt(1));
            p.setDenumire(rs.getString(2));
            p.setCantitate(rs.getFloat(3));
            p.setPret(rs.getFloat(4));
            p.setUnitate(rs.getString(5));
            list.add(p);
        }
        return list;
    }

}
