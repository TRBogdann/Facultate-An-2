import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args)
    {
        boolean closed = false;
        Scanner s = new Scanner(System.in);
        while(!closed)
        {
            System.out.println("Cisteste codul de proiect sau exit pentru a inchide:");
            String request = s.nextLine();
            if(request.equals("exit client"))
                closed = true;
            else
                try(Socket server = new Socket("localhost",2020);) {
                BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(server.getOutputStream()),true);
                out.println(request);
                String response = in.readLine();
                System.out.println(response);
            } catch (Exception e)
            {
                System.out.println("Server could not be reached.Do you want to retry?(y(Yes)/anything else(No)");
                String msg = s.nextLine();
                if(!msg.equals("y"))
                {
                    closed = true;
                }
            }



        }
        System.out.println("Client is exiting");
    }
}
