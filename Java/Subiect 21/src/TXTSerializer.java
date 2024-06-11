import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

public class TXTSerializer {
    public static void serialize(Map<String,Integer> map,String filename)
    {
        try {

            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            var list = new ArrayList<>(map.entrySet());
            list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
            for (var element : list)
            {
                writer.println(element.getKey()+" "+ element.getValue());
            }
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
