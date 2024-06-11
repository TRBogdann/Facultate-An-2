import org.json.JSONArray;
import org.json.JSONObject;

import javax.management.Query;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:data.db");
            ModelPrograme model = new ModelPrograme(c);

            //1
            List<Program> list = model.select("");
            list.sort((a, b) -> -Integer.compare(a.getNumarLocuri(), b.getNumarLocuri()));
            for (Program p : list) {
                System.out.println(p);
            }
            //2

            File f = new File("candidati.json");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            List<Candidat> candidati = new ArrayList<>();
            JSONArray arr = new JSONArray(content.toString());
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String nume = obj.getString("nume");
                JSONArray opt = obj.getJSONArray("optiuni");
                List<Optiune> lista = new ArrayList<>();
                for (int j = 0; j < opt.length(); j++) {
                    JSONObject obj2 = opt.getJSONObject(j);
                    Optiune o = new Optiune(obj2.getInt("codProgram"), obj2.getFloat("punctaj"));
                    lista.add(o);
                }
                candidati.add(new Candidat(nume, lista));

            }

            PrintWriter print = new PrintWriter("output.txt");
            for (Program p : list)
            {
                ListWriter.writeList(print,p,candidati);
            }
            print.close();

            candidati = candidati.stream().filter(candidat -> candidat.getNrOptiuni() >= 3).toList();

            for (Candidat candidat : candidati) {
                System.out.println(candidat.getNume() + " are " + candidat.getNrOptiuni());
            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}