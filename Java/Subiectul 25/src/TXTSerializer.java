import java.awt.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TXTSerializer {
    public static void serialize(List<Capitol> capitole, List<Santiere> santiere,String filename)
    {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            Set<Integer> set = new HashSet<>();
            Map<Integer,Santiere> map = santiere.stream().collect(Collectors.toMap(Santiere::getCod, Function.identity()));
            for (Capitol c : capitole) {
                set.add(c.getCod());
            }
            for (Integer cod : set)
            {
                List<Capitol> filtered = capitole.stream().filter(cap->cap.getCod()==cod).toList();
                writer.println(cod);
                for(Capitol cap:filtered)
                {
                    Santiere s = map.get(cap.getCodSantier());
                    writer.println(s.getCod()+","+cap.getPret()*cap.getCantitate());
                }
            }
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Fisierul nu a fost gasit");
        }
    }

}
