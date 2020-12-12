import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientWriteHandler implements Runnable {

    private Socket socket;
    private Integer sender;

    public ClientWriteHandler(Socket socket, Integer sender) {
        this.socket = socket;
        this.sender = sender;
    }

    @Override
    public void run() {

        try {

            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // gera um número inteiro no intervalo [1, 1000000]
            Random random = new Random();
            Integer number = random.nextInt(1000000) + 1;

            Message message = new Message(sender, 0, number);

            // envia a requisição ao balancer
            output.writeObject(message);
            output.flush();
            output.close();

            System.out.println("Valor " + number + " enviado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
