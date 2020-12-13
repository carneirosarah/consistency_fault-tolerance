
public class ServerReplicationHandler implements Runnable{

    private File database;
    private Message message;

    public ServerReplicationHandler (File database, Message message) {
        this.database = database;
        this.message = message;
    }

    @Override
    public void run() {
        database.fileWriter(message.getMessage());
    }
}
