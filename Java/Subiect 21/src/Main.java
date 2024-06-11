import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //1
        System.out.println("Cerinta 1:");
        List<Carte> carti = TXTDeserializer.deserialize("carti.txt");
        List<Carte> filtered = new java.util.ArrayList<>(carti.stream().filter(carte -> carte.getAn() < 1940).toList());
        filtered.sort((c1,c2)->c1.getNume().compareTo(c2.getNume()));
        filtered.forEach(System.out::println);

        //2
        System.out.println("Cerinta 2:");
        List<Imprumut> imprumuturi = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:biblioteca.db");
            DataModel model = new DataModel(c);
            imprumuturi = model.select();
             map = imprumuturi.stream().collect(Collectors.groupingBy(Imprumut::getNume,Collectors.summingInt(Imprumut::getZile)));
             map.forEach((k,v)->
            {
                System.out.println(k);
            });
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //3
        System.out.println("Cerinta 3:");
        System.out.println("Fisierul 'output.txt' a fost generat");
        TXTSerializer.serialize(map,"output.txt");

        //4
        System.out.println("Cerinta 4");
        Server server = new Server(carti);
        Client client = new Client();

        server.start();
        client.start();
    }
}