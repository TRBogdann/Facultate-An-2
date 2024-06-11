import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //1
        System.out.println("Cerinta 1:");
        List<Santiere> list = JSONDeserializer.deserialize("santiere.json");
        double sum = list.stream().mapToDouble(Santiere::getValoare).sum()/list.size();
        list.forEach(System.out::println);
        System.out.println("Media este:"+sum+"\n");


        //2
        System.out.println("Cerinta 2:");
        List<Capitol> capitole = new ArrayList<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:devize.db");
            DateModel model = new DateModel(c);
            capitole = model.select();
            Map<Integer,Double> map = new HashMap<>();
            for(Capitol cap:capitole)
            {
                if(!map.containsKey(cap.getCod())) {
                    double total = capitole.stream().filter(t->t.getCod()==cap.getCod()).mapToDouble(Capitol::getCantitate).sum();
                    map.put(cap.getCod(),total);
                }
            }
            map.forEach((key, value) -> System.out.println(key + "," + value));

            TXTSerializer.serialize(capitole,list,"output.txt");

            Server server = new Server(list);
            server.start();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}