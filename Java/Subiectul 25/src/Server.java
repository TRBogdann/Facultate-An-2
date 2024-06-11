import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends Thread{

    private class Worker extends Thread
    {
        Socket client;
        public Worker(Socket client)
        {
            super();
            this.client = client;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
                String request = in.readLine();
                if(request.equals("exit"))
                {
                    closed = true;
                    return;
                }
                int cod = Integer.parseInt(request);
                Santiere s = null;
                for(Santiere temp:list)
                {
                    if(temp.getCod() == cod)
                        s = temp;
                }
                if(s == null)
                {
                    out.println("Not Found");
                    return;
                }
                out.println("Obiectiv:"+s.getObiectiv()+" Valoare:"+s.getValoare());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    List<Santiere> list;
    public Server(List<Santiere> list)
    {
        super();
        this.list = list;
    }
    boolean closed  =  false;

    @Override
    public void run() {
        closed = false;
        try(ServerSocket socket = new ServerSocket(2020);) {
            System.out.println("Server Started");
            socket.setSoTimeout(1000);

                while (!closed)
                {
                    try {
                    Socket client = socket.accept();
                    Worker worker = new Worker(client);
                    worker.start();
                    }
                    catch (Exception e)
                    {
                        if(closed)
                            System.out.println("Server Closing");
                    }
                }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
