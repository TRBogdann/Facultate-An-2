import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JSONDeserializer {
    public static List<Masina> deserialize(String filepath) throws FileNotFoundException {
        JSONTokener tokener = new JSONTokener(new FileReader(filepath));
        List<Masina> list = new ArrayList<>();
        JSONArray arr = new JSONArray(tokener);
        for(int i = 0;i<arr.length();i++)
        {
            JSONObject obj = arr.getJSONObject(i);
            Masina m = new Masina();
            m.setSerie(obj.getInt("serie"));
            m.setCost(obj.getFloat("cost"));
            m.setNumar(obj.getString("numar"));
            m.setNume(obj.getString("nume"));
            list.add(m);
        }
        return list;
    }
}
