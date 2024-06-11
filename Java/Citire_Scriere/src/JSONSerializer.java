import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class JSONSerializer {
    public static void serialize(String filename, List<Masina> list) throws FileNotFoundException {
        PrintWriter print = new PrintWriter(filename);
        print.println("[");
        for(int i = 0;i<list.size();i++)
        {
            Masina m = list.get(i);
            JSONObject object = new JSONObject();
            object.put("serie",m.getSerie());
            object.put("nume",m.getNume());
            object.put("cost",m.getCost());
            object.put("numar",m.getNumar());
            String str = ",";
            if(i==list.size()-1)
                str = "";
            print.println(object.toString()+str);

        }
        print.println("]");
        print.close();
    }
}
