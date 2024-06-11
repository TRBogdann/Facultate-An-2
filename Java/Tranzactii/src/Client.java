import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client()
    {

    }
    public boolean closed =false;
    public void start()
    {

            while(!closed)
            {
                try {
                Socket socket = new Socket("localhost",2020);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                Scanner s = new Scanner(System.in);
                System.out.println("Cisteste numarul:");
                String read = s.nextLine();
                int num = Integer.parseInt(read);
                out.println(num);
                String response  = in.readLine();
                if(response.equals("NaN"))
                {
                    System.out.println("Produsul nu a realizat tranzactii");
                }
                else {
                    double sum = Double.parseDouble(response);
                    System.out.println("Suma este "+sum);
                    closed = true;
                }
                }
                catch(Exception e)
                {

                }
            }

    }
}
