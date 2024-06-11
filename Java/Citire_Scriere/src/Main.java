import javax.sql.rowset.spi.SyncResolver;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Citire Text
        try {
            //Obs 1:Obiectele care au "Deserializer/Serializer" in nume nu sunt din java, implemetarile sunt in fisierele cu acelasi nume
            //Obs 2:Schimba din input/ in input\\ daca n ai Linux.La fel pentru output/

            //text
            System.out.println("Test Txt");
            List<Masina> list = TXTDeserializer.deserialize("input/masini.txt");
            list.forEach(System.out::println);
            TXTSerializer.serialize("output/masini.txt",list);

            //binar
            System.out.println("Test Binar");
            BINSerializer.serialize("output/masina.dat",list);
            list = BINDeserializer.deserialize("input/masina.dat");
            list.forEach(System.out::println);

            //json
            System.out.println("Test JSON");
            JSONSerializer.serialize("output/masini.json",list);
            list = JSONDeserializer.deserialize("input/masini.json");
            list.forEach(System.out::println);

            //xml
            System.out.println("Test XML");
            XMLSerializer serializer = new XMLSerializer();
            serializer.serialize(list);
            serializer.save("output/masina.xml");
            XMLDeserializer deserializer = new XMLDeserializer("input/masina.xml");
            list = deserializer.deserialize();
            list.forEach(System.out::println);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}