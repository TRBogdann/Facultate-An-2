import org.json.JSONArray;
import org.json.JSONObject;
import seminar.seminar2.g1065.MijlocFix;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client {
    public static void salvareJSON(String filename, List<MijlocFix> lista)
    {
        try(PrintWriter out = new PrintWriter(filename))
        {
            JSONArray jsa = new JSONArray();
            for(MijlocFix mf:lista)
            {
                JSONObject obj = new JSONObject();
                obj.put("Denumire",mf.getDenumire());
                obj.put("Nr Inventar",mf.getNrInventar());

                jsa.put(obj);
            }
            jsa.write(out,8,0);
        }
        catch(Exception e)
        {

        }
    }

    public static void stopServer()
    {
        try(Socket socket = new Socket("localhost",2020))
        {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("stop");
        }
        catch (Exception e)
        {

        }
    }
    public static int readOptions(BufferedReader cin) {
        System.out.println("Optiuni:");
        System.out.println("1- Returnare mijloc fix dupa numar inventar");
        System.out.println("2- Returnare mijloace fixe dupa locatie");
        System.out.println("5 Inchide Server");
        System.out.println("10 - Inchide Client");
        System.out.println("Optiuni:");
        int optiune = 1;
        try {
            optiune = Integer.parseInt(cin.readLine().trim());
        } catch (Exception e) {

        }
        return optiune;
    }

}
