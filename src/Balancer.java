import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Balancer extends Server{

    private ArrayList<Integer> dataServers = new ArrayList<Integer>(); // guarda as portas dos servidores de dados
    private Boolean onHold = false;
    private ArrayList<Message> buffer = new ArrayList<Message>();

    public Balancer(int port) throws IOException {

        super(port);
    }

    public static void main(String[] args) {

        try{
            // Escuta as requsições dos clientes na porta 5000
            Balancer balancer = new Balancer (5000);
            System.out.println("Balancer - Aguardando Conexão na porta 5000 ...");

            // Adiciona os servidores de dados
            balancer.dataServers.add(Integer.parseInt(args[0]));
            balancer.dataServers.add(Integer.parseInt(args[1]));
            balancer.dataServers.add(Integer.parseInt(args[2]));

            while (true) {

                Socket socket = balancer.acceptConnection();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) input.readObject();

                Server.closeSocket(socket);

                System.out.println(message);

                // Mensagem enviada por um dos servidores sinalizando o fim de um procedimento de consistência
                if (message.getType() == 3) {

                    balancer.onHold = false;
                } else {

                    balancer.buffer.add(message);
                }

                // Enquanto existirem requisições no buffer e requisições de escrita não estiverem sendo processadas
                while ((balancer.buffer.size() != 0) && (!balancer.onHold)) {

                    message = balancer.buffer.get(0);
                    balancer.buffer.remove(0);

                    if (message.getType() == 0) {

                        balancer.onHold = true;
                    }

                    Thread worker = new Thread(new BalancerWorker(message, balancer.dataServers));
                    worker.start();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
