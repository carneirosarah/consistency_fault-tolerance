import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class DataServer extends Server {

    private File database;

    public DataServer(int port) throws IOException {
        super(port);
    }

    public static void main(String[] args) {

        try {

            DataServer server = new DataServer(Integer.parseInt(args[0]));
            System.out.println("Data Server - Aguardando Conexão na porta " + args[0] + " ...");
            server.database = new File("Database_" + args[0] + ".txt");

            while (true) {

                Socket socket = server.acceptConnection();
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) input.readObject();

                Server.closeSocket(socket);

                if (message.getType() == 4) {

                    Thread replicationHandler = new Thread(new ServerReplicationHandler(server.database, message));
                    replicationHandler.start();

                } else if (message.getType() == 0) {

                    Thread writeHandler = new Thread(new ServerWriteHandler(server.database, message, Integer.parseInt(args[0])));
                    writeHandler.start();

                } else {

                    Thread readHandler = new Thread(new ServerReadHandler(server.database));
                    readHandler.start();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
