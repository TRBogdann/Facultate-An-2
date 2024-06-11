import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TXTSerializer {
    public static void serialize(String filename, List<Masina> list) throws FileNotFoundException {
        PrintWriter print = new PrintWriter(filename);
        for(Masina m:list)
        {
            String line = m.getSerie()+","+m.getNume()+","+m.getCost()+","+m.getNume();
            print.println(line);
        }
        print.close();
    }
}
