import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends Thread {
    List<Proiect> list;
    public Server(List<Proiect> list)
    {
        super();
        this.list = list;
    }

    public class Worker extends  Thread
    {
        Socket client;
        public Worker(Socket client)
        {
            this.client =  client;
        }


        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out  = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
                String request = in.readLine();
                System.out.println("Client:"+request);
                if(request.equals("exit"))
                {
                    out.println("Server exiting");
                    closed = true;
                    return;
                }
                int cod = Integer.parseInt(request);
                Proiect p = null;
                for(Proiect proj:list)
                {
                    if(cod == proj.getCod())
                        p=proj;
                }
                if(p==null)
                {
                    out.println("Not Found");
                    return;
                }
                out.println("Denumire: "+p.getDenumiure()+" Buget: "+p.getBuget());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
    }

    boolean closed;
    @Override
    public void run() {
        try(ServerSocket socket  =  new ServerSocket(2020);) {
            socket.setSoTimeout(1000);
            System.out.println("Server has started");
            while (!closed)
            {
                    try
                    {
                        Socket client = socket.accept();
                        Worker worker = new Worker(client);
                        worker.start();
                    }
                    catch (Exception e)
                    {
                        if(closed)
                            System.out.println("Server is closing");
                    }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
