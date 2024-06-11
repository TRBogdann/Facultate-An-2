import javax.xml.catalog.Catalog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends Thread{
    List<Carte> list;
    public Server(List<Carte> list)
    {
        this.list = list;
    }

    boolean closed;

    @Override
    public void run() {
        try(ServerSocket socket = new ServerSocket(2020);)
        {
            socket.setSoTimeout(1000);
            while(!closed)
            {
                try{
                    Socket client  = socket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(new PrintWriter(new OutputStreamWriter(client.getOutputStream())),true);
                    String recieved = in.readLine();
                    System.out.println("Server has recieved the message");
                    Carte found = null;
                    for(Carte c:list)
                    {
                        if(c.getCota().equals(recieved))
                        {
                            found = c;
                        }
                    }
                    if(found == null)
                    {
                        out.println("Not Found");
                    }
                    else
                    {
                        out.println(found.toString());
                        closed = true;
                    }
                }
                catch (Exception e)
                {
                    if(closed)
                        System.out.println("Server is Closing");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
