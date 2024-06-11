import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONDeserializer {
    public static List<Proiect> deserialize(String filename)
    {
        List<Proiect> list = new ArrayList<>();
        try{
            JSONTokener tokener = new JSONTokener(new FileReader(filename));
            JSONArray arr = new JSONArray(tokener);
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj  = arr.getJSONObject(i);
                Proiect p = new Proiect(
                        obj.getInt("cod_proiect"),
                        obj.getString("nume"),
                        obj.getString("descriere"),
                        obj.getString("manager"),
                        obj.getFloat("buget")
                );
                list.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
