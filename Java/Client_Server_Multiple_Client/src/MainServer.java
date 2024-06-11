import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class MainServer {

    public static class WorkingThread extends Thread
    {
        Socket client;
        public WorkingThread(Socket client)
        {
            super();
            this.client = client;
        }

        @Override
        public void run() {
            try {
                BufferedReader read = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter print = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
                String request = read.readLine();
                if(request.equals("exit"))
                {
                    stop=true;
                }
                else {
                    System.out.println("Clinet:" + request);
                    String response = "Server:Salut";
                    print.println(response);
                }
            }
            catch (Exception e)
            {
                System.out.println("Client Deconectat");
            }
        }
    }

    static boolean stop  = false;

    public static void main(String[] args) throws IOException {
        ServerSocket socket  = new ServerSocket(2020);
        socket.setSoTimeout(5000);
        while(!stop)
        {
            try {
                Socket client = socket.accept();
                WorkingThread worker = new WorkingThread(client);
                worker.start();
            }
            catch (Exception e)
            {

            }
        }
    }
}
