import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    private Integer sender; // remetente
    private Integer type; // tipo da operação: 0 -> escrita, 1 -> leitura, 3 -> libera o balance, 4 -> replicacao
    private Integer number; // número aleatório enviado nas requisições de escrita
    private String message; // String enviada na replicação
    private ArrayList<Integer> servers; // servidores de dados

    public Message(Integer sender, Integer type, Integer number) {
        this.sender = sender;
        this.type = type;
        this.number = number;
    }

    public Message(Integer sender, Integer type) {
        this.sender = sender;
        this.type = type;
    }

    public Message(Integer sender, Integer type, String message) {
        this.sender = sender;
        this.type = type;
        this.message = message;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Integer> getServers() {
        return servers;
    }

    public void setServers(ArrayList<Integer> servers) {
        this.servers = servers;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", type=" + type +
                ", number=" + number +
                '}';
    }
}
