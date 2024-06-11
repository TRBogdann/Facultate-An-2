import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) throws IOException {

            while(true)
            {
                Socket socket = new Socket("localhost",2020);
                System.out.println("Citeste String");
                BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                Scanner s = new Scanner(System.in);
                String read =  s.nextLine();
                if(read.equals("exit Client"))
                    break;
                else
                    out.println(read);

            }
        }
    }
