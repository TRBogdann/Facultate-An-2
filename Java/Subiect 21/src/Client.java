import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread{
    public Client()
    {

    }
    boolean closed = false;
    @Override
    public void run() {
        while(!closed)
        {
            System.out.println("Citeste cota:");
            Scanner s = new Scanner(System.in);
            String request = s.nextLine();
            try(Socket server = new Socket("localhost", 2020);)
            {
                BufferedReader in  = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()),true);
                out.println(request);
                String response  = in.readLine();
                System.out.println(response);
                if(!response.equals("Not Found"))
                    closed = true;

            }
            catch(Exception e)
            {
                System.out.println("Server not responding");
            }
        }
    }
}
