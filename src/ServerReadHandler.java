
public class ServerReadHandler implements Runnable {

    private String database;

    public ServerReadHandler(String database) {
        this.database = database;
    }

    @Override
    public void run() {

        System.out.println("Dados do arquivo ...");

    }
}
