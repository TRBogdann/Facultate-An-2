import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            //1

            Connection c = DriverManager.getConnection("jdbc:sqlite:date.db");
            DataModel model = new DataModel(c);
            //model.insert(new MateriiPrime(1,"Faina",12.5f,1.25f,"kg"));
            //model.insert(new MateriiPrime(2,"Apa",2.55f,3.25f,"litri"));
            //model.insert(new MateriiPrime(3,"Drojdie",5.55f,0.75f,"Grame"));
            //model.insert(new MateriiPrime(4,"Zahar",1.25f,3.45f,"kg"));
            List<MateriiPrime> list = model.select();
            double sum = list.stream().mapToDouble(materiiPrime -> materiiPrime.getCantitate() * materiiPrime.getPret()).sum();
            System.out.println("Exercitiul 1:");
            System.out.println("Valoare Totala: "+sum+"\n\nExercitiul 2:");

            BufferedReader reader = new BufferedReader(new FileReader("produse.json"));
            String line;
            StringBuilder unformatted = new StringBuilder();
            while((line = reader.readLine())!=null)
            {
                unformatted.append(line);
            }
            JSONArray arr = new JSONArray(unformatted.toString());
            List<Produs> produse = new ArrayList<>();
            for(int i=0;i<arr.length();i++)
            {
                JSONObject obj = arr.getJSONObject(i);
                Produs p = new Produs();
                p.setCod(obj.getInt("Cod produs"));
                p.setDenumire(obj.getString("Denumire produs"));
                p.setCantitate(obj.getFloat("Cantitate"));
                p.setUnitate(obj.getString("Unitate masura"));
                JSONArray consumuri = obj.getJSONArray("Consumuri");
                for(int j=0;j<consumuri.length();j++)
                {
                    JSONObject cObj = consumuri.getJSONObject(j);
                    Consumuri consum = new Consumuri(cObj.getInt("Cod materie prima"),cObj.getFloat("Cantitate"));
                    p.getC().add(consum);
                }
                produse.add(p);

            }

            produse.forEach(System.out::println);

            System.out.println("\nExercitiul 3:\n");
            XMLSerializer serializer = new XMLSerializer();
            serializer.serialize(list);
            serializer.saveFile("materii.xml");
            System.out.println("Fisierul a fost salvat");

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}