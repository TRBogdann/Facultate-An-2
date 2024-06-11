import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListWriter {
        private static float exist(Candidat c,int cod)
        {
            for(Optiune opt:c.getOptiuni())
            {
                if((opt.cod == cod))
                    return opt.punctaj;
            }
            return -1;
        }

        public static void writeList(PrintWriter print,Program p,List<Candidat> c)
        {
            List<Candidat> newList = c.stream().filter(candidat -> exist(candidat,p.getCodProgram())!=-1).toList();
            Map<String,Float>  map = newList.stream().collect(Collectors.toMap(Candidat::getNume,candidat ->exist(candidat,p.getCodProgram())));
            for(var e:map.entrySet())
            {
                print.println(p.getDenumire()+","+e.getKey()+","+e.getValue());
            }
        }
}
