import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TXTDeserializer {
    public static List<Produs> deserialize(String path) throws IOException {
        List<Produs> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while((line = reader.readLine())!=null) {
            String[] formatted = line.split(",");
            Produs p = new Produs(Integer.parseInt(formatted[0]),formatted[1],Float.parseFloat(formatted[2]));
            list.add(p);
        }
        return list;
    }
}
