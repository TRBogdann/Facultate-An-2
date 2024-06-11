import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONDeserializer {
    public static List<Santiere> deserialize(String path)
    {
        List<Santiere> list = new ArrayList<>();
        try {
            JSONTokener tokener = new JSONTokener(new FileReader(path));
            JSONArray arr = new JSONArray(tokener);
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                Santiere s = new Santiere();
                s.setCod(obj.getInt("Cod Santier"));
                s.setLocalitate(obj.getString("Localitate"));
                s.setObiectiv(obj.getString("Obiectiv"));
                s.setValoare(obj.getFloat("Valoare"));
                s.setStrada(obj.getString("Strada"));
                list.add(s);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
