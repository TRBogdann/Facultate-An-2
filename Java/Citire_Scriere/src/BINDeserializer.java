import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class BINDeserializer {
    public static List<Masina> deserialize(String filepath) throws IOException, ClassNotFoundException {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filepath));
        List<Masina> list = (List<Masina>)stream.readObject();
        return list;
    }
}
