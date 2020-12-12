import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Client {

    private static Integer id;

    public static void main(String[] args) {

        Client.id = Integer.parseInt(args[0]);

        try {

            while (true) {

                Socket socket = new Socket("localhost", 5000);

                /* Gera um número aleatório que representa o tipo de operação
                    0 -> escrita, 1 -> leitura */
                Random random = new Random();
                Integer operationType = random.nextInt(2);

                if (operationType == 0) {

                    // trata a requisição de escrita
                    Thread writeHandler = new Thread(new ClientWriteHandler(socket, Client.id));
                    writeHandler.start();
                } else {

                    // trata a requisição de leitura
                    Thread readHandler = new Thread(new ClientReadHandler(socket, Client.id));
                    readHandler.start();
                }

                // Gera aleatóriamente o tempo [50, 200] que o cliente irá dormir após uma requisição
                Integer sleepTime = random.nextInt(151) + 50;
                Thread.sleep(sleepTime);
            }
        } catch (IOException | InterruptedException e) {

            e.printStackTrace();
        }
    }
}
