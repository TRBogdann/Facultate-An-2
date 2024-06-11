import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) throws IOException {
        boolean connected = true;
        while(connected)
        {
            try {
                Socket socket = new Socket("localhost", 2020);
                BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter print = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                Scanner scan = new Scanner(System.in);
                String request = scan.nextLine();
                print.println(request);
                String response = read.readLine();
                System.out.println(response);
            }
            catch (Exception e)
            {
                System.out.println("Server was shutdown. Press y to reconnect or any other key to exit");
                Scanner scan = new Scanner(System.in);
                String msg = scan.nextLine();
                if(!msg.equals("y"))
                   connected = false;
            }
        }
    }
}