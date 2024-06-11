import java.io.*;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class Server extends Thread{
    List<Produs> p;
    List<Tranzactii> t;
    private class Worker extends Thread
    {
        Socket socket;
        public Worker(Socket socket)
        {
            super();
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                int produs = Integer.parseInt(in.readLine());
                System.out.println("Server:"+produs);
                List<Tranzactii> temp = t.stream().filter(t->t.cod==produs).toList();
                if (!temp.isEmpty()) {
                    double sum = temp.stream().mapToDouble(t -> t.cantitate * (t.tip.equals("intrare") ? 1 : -1)).sum();
                    out.println(sum);
                    closed = true;
                }
                out.println("NaN");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public Server(List<Produs> p ,List<Tranzactii>t )
    {
        super();
        this.p=p;
        this.t=t;
    }
    private boolean closed = false;
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(2020);
            serverSocket.setSoTimeout(5000);
            try{
            while(!closed)
            {
                Socket client = serverSocket.accept();
                System.out.println("Recieved");
                Worker worker = new Worker(client);
                worker.start();
            }}
            catch (Exception e)
            {
                System.out.println("Server was closed");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
