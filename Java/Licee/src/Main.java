import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static List<Candidat> deserialize()
    {
        List<Candidat> list =  new ArrayList<>();
        try
        {
            JSONTokener tokener = new JSONTokener(new FileReader("candidati.json"));
            JSONArray arr = new JSONArray(tokener);
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                int cod = obj.getInt("cod_candidat");
                String nume = obj.getString("nume_candidat");
                float medie = obj.getFloat("media");
                JSONArray optiuni = obj.getJSONArray("optiuni");
                Map<Integer,Integer> opt = new HashMap<>();
                for(int j=0;j<optiuni.length();j++)
                {
                    JSONObject optiune = optiuni.getJSONObject(j);
                    //System.out.println(optiune.toString());
                    int cod_liceu = optiune.getInt("cod_liceu");
                    int specializare = optiune.getInt("cod_specializare");
                    opt.put(cod_liceu,specializare);
                }
                Candidat c = new Candidat(cod,medie,nume,opt);
                list.add(c);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static List<Liceu> citereTXT()
    {
        List<Liceu> list = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("licee.txt"));
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            while(line2!=null)
            {
                String[] formatted = line1.split(",");

                int cod = Integer.parseInt(formatted[0]);
                String nume = formatted[1];
                int numar = Integer.parseInt(formatted[2]);

                Map<Integer,Integer> map = new HashMap<>();
                String[] mapLine = line2.split(",");
                for(int i = 0;i<mapLine.length;i+=2)
                {
                    map.put(Integer.parseInt(mapLine[i]),
                            Integer.parseInt(mapLine[i+1]));
                }

                Liceu liceu = new Liceu(cod,nume,numar,map);
                list.add(liceu);

                line1 = reader.readLine();
                line2 = reader.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static int nrLocuri(Liceu l)
    {
        int sum = 0;
        for(var e:l.getSpecializari().entrySet())
        {
            sum += e.getValue();
        }
        return sum;
    }


    public static void main(String[] args) {
        //1
        System.out.println("Exercitiul 1:");
        List<Candidat> candidati = deserialize();
        List<Candidat> lista = candidati.stream().filter(c->c.getMedie()>=9).toList();
        System.out.println("Numar de candidati: "+lista.size());

        //2
        System.out.println("Exercitiul 2:");
        List<Liceu> list = citereTXT();
        list.forEach(System.out::println);
        Map<Liceu,Integer> map = list.stream().collect(Collectors.toMap(Function.identity(), Main::nrLocuri));
        var list2 = map.entrySet().stream().sorted((e1,e2)->Integer.compare(e2.getValue(),e1.getValue())).toList();
        list2.forEach(e->System.out.println("Cod Liceu: "+e.getKey().getCod()+" Nume: "+e.getKey().getNume()
                +" Numar Locuri:"+e.getValue()));
    }
}