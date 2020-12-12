import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerWriteHandler implements Runnable {

    private String database;
    private Message message;
    private Integer sender;

    public ServerWriteHandler(String database, Message message, Integer sender) {
        this.database = database;
        this.message = message;
        this.sender = sender;
    }

    @Override
    public void run() {

        try {

            int count = 0;
            Boolean flag = true;

            for (int i = 1; i < message.getNumber(); i ++) {

                if (count > 2) {

                    flag = false;
                    break;

                } else if (message.getNumber() % i == 0) {

                    count++;
                }
            }

            if (flag) {

                System.out.println("O Valor " + message.getNumber() + " é primo.");
            } else {

                System.out.println("O Valor " + message.getNumber() + " não é primo.");
            }

            Socket socket = new Socket("localhost", 5000);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message message = new Message(sender, 3);

            // envia a requisição ao balancer
            output.writeObject(message);
            output.flush();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
