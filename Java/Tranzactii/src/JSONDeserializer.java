import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONDeserializer {
    public static List<Tranzactii> deserialize(String path)
    {

        List<Tranzactii> list = new ArrayList<>();
        try{
            JSONTokener tokener = new JSONTokener(new FileReader(path));
            JSONArray arr = new JSONArray(tokener);
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                Tranzactii t = new Tranzactii();
                t.cod = obj.getInt("cod");
                t.tip = obj.getString("tip");
                t.cantitate = obj.getFloat("cantitate");
                list.add(t);
            }
        }
        catch (Exception e)
        {
            System.out.println("File not found");
        }

        return list;
    }
}
