import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {

        this.serverSocket = new ServerSocket(port);
    }

    public Socket acceptConnection () throws IOException {

        return this.serverSocket.accept();
    }

    public static void closeSocket (Socket socket) {

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
