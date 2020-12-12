import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientReadHandler implements Runnable {

    private Socket socket;
    private Integer sender;

    public ClientReadHandler(Socket socket, Integer sender) {
        this.socket = socket;
        this.sender = sender;
    }

    @Override
    public void run() {
        try {

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message message = new Message(sender, 1);

            // envia a requisição ao balancer
            output.writeObject(message);
            output.flush();
            output.close();

            System.out.println("Leitura enviada");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
