import seminar.seminar2.g1065.MijlocFix;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class MainClient {
    public static void cerere1(BufferedReader cin)
    {
        try(Socket socket = new Socket("localhost",2020)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("cerere 1");
            System.out.println("Nr inventar");
            long nrInventar = Long.parseLong(cin.readLine().trim());
            out.writeObject(nrInventar);
            MijlocFix m = (MijlocFix)in.readObject();
            System.out.println(m.toString());
        }
        catch(Exception e)
        {

        }
    }

    public static void cerere2(BufferedReader cin)
    {
        try(Socket socket = new Socket("localhost",2020)) {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject("cerere 2");
            System.out.println("Locatie");
            String locatie = cin.readLine().trim();
            out.writeObject(locatie);
            List<MijlocFix> lista = (List<MijlocFix>)in.readObject();
            lista.forEach(System.out::println);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Client c = new Client();
        try (BufferedReader cin = new BufferedReader(new InputStreamReader(System.in))) {
            int optiune = 1;
            while (optiune != 10) {
                optiune = c.readOptions(cin);
                switch (optiune)
                {
                    case 5:
                        c.stopServer();
                        break;
                    case 1:
                        cerere1(cin);

                        break;
                    case 2:
                        cerere2(cin);
                }

            }
        } catch (Exception e) {

        }
    }
}
