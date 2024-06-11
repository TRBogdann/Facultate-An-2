import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static String send(String request)
    {
          try(Socket server = new Socket("localhost",2020))
          {
              BufferedReader in =  new BufferedReader(new InputStreamReader(server.getInputStream()));
              PrintWriter out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()),true);
              out.println(request);
              return in.readLine();
          }
          catch (Exception e)
          {
              System.out.println("Server could not be reached");
          }
          return "";
    }
    public static void main(String[] args) {
        boolean closed = false;

        while(!closed)
        {
            System.out.println("Citeste Codul Santierului:");
            Scanner s = new Scanner(System.in);
            String request =  s.nextLine();
            if(request.equals("Exit Client"))
            {
                closed = true;
            }
            else
            {
                String response  = send(request);
                System.out.println(response);
            }

        }
    }
}
