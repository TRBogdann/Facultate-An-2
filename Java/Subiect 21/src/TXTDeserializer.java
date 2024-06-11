import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TXTDeserializer {
    public static List<Carte> deserialize(String filepath)
    {
        List<Carte> list = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while((line=reader.readLine())!=null)
            {
                String[] parsed = line.split("\t");
                Carte c = new Carte(
                        Integer.parseInt(parsed[3]),
                        parsed[2],
                        parsed[1],
                        parsed[0]
                );
                list.add(c);
            }
        }
        catch (Exception e)
        {

        }
        return list;
    }
}
