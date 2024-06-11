import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {

            //1
            List<Produs> p = TXTDeserializer.deserialize("produse.txt");
            System.out.println("Cerinta 1:\n\nNumar de produse:"+ p.size()+"\n\nCerinta2:\n\n");

            //2
            p.sort((a,b)->b.getDenumire().compareTo(a.getDenumire()));
            p.forEach(System.out::println);

            //3
            List<Tranzactii> t =JSONDeserializer.deserialize("tranzactii.json");
            Map<String,Integer> map = p.stream().collect(Collectors.toMap(Produs::getDenumire,
                    prod->t.stream().filter(tr->tr.cod==prod.getCod()).toList().size()));
            List<Map.Entry<String, Integer>> newlist = new ArrayList<>(map.entrySet());
            newlist.sort((t1, t2) ->t2.getValue().compareTo(t1.getValue()) );
            PrintWriter writer = new PrintWriter(new FileWriter("final.txt"));
            for(var e1:newlist)
            {
                writer.println("Denumire:" +e1.getKey()+",Numar: "+e1.getValue());
            }
            writer.close();

            //4
            Map<String,List<Tranzactii>> info = t.stream().collect(Collectors.groupingBy(Tranzactii::getTip));

            double intrari = info.get("intrare").stream().mapToDouble(tr -> ((double) tr.cod) *
                    p.stream().filter(prod -> prod.getCod() == tr.cod).mapToDouble(Produs::getPret).sum()).sum();
            double iesiri = info.get("iesire").stream().mapToDouble(tr -> ((double) tr.cod) *
                    p.stream().filter(prod -> prod.getCod() == tr.cod).mapToDouble(Produs::getPret).sum()).sum();
            System.out.println(intrari-iesiri);

            Client client = new Client();
            Server s = new Server(p,t);
            s.start();
            client.start();


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}