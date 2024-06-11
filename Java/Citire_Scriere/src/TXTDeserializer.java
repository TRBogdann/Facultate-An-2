import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TXTDeserializer {
    static List<Masina> deserialize(String path) throws IOException {
        List<Masina> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String unfformatted;
        while((unfformatted = reader.readLine())!=null)
        {
            String[] formated = unfformatted.trim().split(",");
            Masina m = new Masina(Integer.parseInt(formated[0]),
                    formated[1],Float.parseFloat(formated[2]),formated[3]);
            list.add(m);
        }
        return list;
    }
}
