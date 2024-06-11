import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(2020);

        while(true)
        {
            Socket clientSocket = socket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out  = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String result = in.readLine();
            if(result.equals("exit"))
            {
                break;
            }
            System.out.println(result);
        }
    }
}
