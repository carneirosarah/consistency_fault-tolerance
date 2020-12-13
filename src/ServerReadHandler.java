
public class ServerReadHandler implements Runnable {

    private File database;

    public ServerReadHandler(File database) {
        this.database = database;
    }

    @Override
    public void run() {

        System.out.println(database.fileReader());

    }
}
