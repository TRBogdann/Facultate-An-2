import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.*;

public class JSONDeserializer {
    public static List<Candidat> deserialize(String filename)
    {
        List<Candidat> list = new ArrayList<>();
        try{
            JSONTokener tokener =  new JSONTokener(new FileReader(filename));
            JSONArray arr = new JSONArray(tokener);
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                String name = obj.getString("nume");
                JSONArray opt = obj.getJSONArray("optiuni");
                Map<Integer,Double> map = new HashMap<>();
                for(int j=0;j<opt.length();j++)
                {
                    JSONObject o = opt.getJSONObject(j);
                    map.put(o.getInt("codProgram"),o.getDouble("punctaj"));
                }
                Candidat c = new Candidat(name,map);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
