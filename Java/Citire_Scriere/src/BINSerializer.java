import java.io.*;
import java.util.List;

public class BINSerializer {
    public static void serialize(String filename, List<Masina> list) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filename));
        stream.writeObject(list);
        stream.close();
    }
}
