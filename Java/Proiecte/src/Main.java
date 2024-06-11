import org.w3c.dom.ls.LSInput;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
         //1
         System.out.println("Cerinta 1:");
         List<Proiect> proiecte = JSONDeserializer.deserialize("proiecte.json");
         Map<String,Double> map = proiecte.stream().collect(Collectors.groupingBy(Proiect::getManager,Collectors.summingDouble(Proiect::getBuget)));
         map.forEach((k,v)->System.out.println(k+" "+v));

         //2
        System.out.println("Cerinta 2:");
        List<Cost> list = new ArrayList<>();
        try
        {
            XMLDeserializer deserializer = new XMLDeserializer("costuri.xml");
            list = deserializer.deserialize();
            Map<Integer,Double> costMap = list.stream().collect(Collectors.groupingBy(Cost::getCod,Collectors.summingDouble(Cost::getCantitate)));
            costMap.forEach((k,v)->System.out.println(k+" "+v));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        //3
        System.out.println("Cerinta 3:");
        try{
            XMLSerializer serializer = new XMLSerializer();
            serializer.serialize(list);
            serializer.save("bugete.xml");
            System.out.println("'bugete.xml'S a fost creat");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        //4
        System.out.println("Cerinta 4:");
        Server server = new Server(proiecte);
        server.start();

    }
}