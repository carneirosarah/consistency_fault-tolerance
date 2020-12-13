import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerWriteHandler implements Runnable {

    private File database;
    private Message message;
    private Integer sender;

    public ServerWriteHandler(File database, Message message, Integer sender) {
        this.database = database;
        this.message = message;
        this.sender = sender;
    }

    @Override
    public void run() {

        try {
            Socket socket;
            String str;
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

                str = "O Valor " + message.getNumber() + " é primo.";
            } else {

                str = "O Valor " + message.getNumber() + " não é primo.";
            }

            System.out.println(str);
            database.fileWriter(str); // escreve a mensagem no servidor atual

            // Replica a mensagem nos demais servidores
            for (int i = 0; i < message.getServers().size(); i++) {

                if (!message.getServers().get(i).equals(sender)) {

                    socket = new Socket("localhost", message.getServers().get(i));
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                    Message message = new Message(sender, 4, str);

                    output.writeObject(message);
                    output.flush();
                    output.close();
                }
            }

            // Libera o balance após a operação de consitencia
            socket = new Socket("localhost", 5000);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Message message = new Message(sender, 3);

            output.writeObject(message);
            output.flush();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
