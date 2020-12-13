import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class BalancerWorker implements Runnable {

    private Message message;
    private ArrayList<Integer> servers;

    public BalancerWorker (Message message, ArrayList<Integer> servers) {
        this.message = message;
        this.servers = servers;
    }

    @Override
    public void run() {

        try {

            // Round-robin Policy, seleciona o servidor de dados de maneira aleatoria
            Random random = new Random();
            Integer selectedServer = random.nextInt(servers.size());

            // Envia a mensagem ao servidor selecionado
            Socket socket = new Socket("localhost", servers.get(selectedServer));
            ObjectOutput output = new ObjectOutputStream(socket.getOutputStream());

            if (message.getType() == 0) {
                message.setServers(servers);
            }

            output.writeObject(message);
            output.flush();
            output.close();

            if (message.getType() == 0) {

                System.out.println("Valor " + message.getNumber() + " recebido do cliente " + message.getSender() +
                    " e direcionado para o servidor de dados " + servers.get(selectedServer));
            } else {

                System.out.println("Leitura encaminhada pelo cliente " + message.getSender() +
                        " e direcionada para o servidor de dados " + servers.get(selectedServer));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
