import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //1
        System.out.println("Cerinta 1:");
        List<Candidat> candidati = JSONDeserializer.deserialize("S07_candidati.json");
        candidati.forEach(c->System.out.println(c.getNume()+" "+c.getOptiuni().size()+" optiuni"));

        //2
        System.out.println("Cerinta 2:");
        List<Program> programe = new ArrayList<>();
        try {
            XMLDeserializer deserializer = new XMLDeserializer("S07_programe.xml");
            programe = deserializer.deserialize();
            programe.sort((c1,c2)->Integer.compare(c2.getNrLocuri(),c1.getNrLocuri()));
            programe.forEach(c->System.out.println(c.getDenumire()+": "+c.getNrLocuri()+" locuri"));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //3
        System.out.println("Cerinta 3:");
        try
        {
            Connection c = DriverManager.getConnection("jdbc:sqlite:admitere.db");
            DataModel model = new DataModel(c);
            for(Candidat cand:candidati)
            {
                model.insert(cand);
            }
            Map<Integer,String> map = programe.stream().collect(Collectors.toMap(Program::getCod,Program::getDenumire));
            Map<Integer,Double> map2 = new HashMap<>();
            for(Candidat cand:candidati)
            {
                map2.putAll(cand.getOptiuni());
            }
            map2 = map2.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getKey,Collectors.averagingDouble(Map.Entry::getValue)));
            for(var element:map2.entrySet())
            {
                System.out.println(map.get(element.getKey())+" "+element.getValue());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}