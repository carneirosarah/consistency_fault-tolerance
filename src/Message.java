import java.io.Serializable;

public class Message implements Serializable {

    private Integer sender; // remetente
    private Integer type; // tipo da operação: 0 -> escrita, 1 -> leitura, 3 -> libera o balance
    private Integer number; // número aleatório enviado nas requisições de escrita

    public Message(Integer sender, Integer type, Integer number) {
        this.sender = sender;
        this.type = type;
        this.number = number;
    }

    public Message(Integer sender, Integer type) {
        this.sender = sender;
        this.type = type;
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

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", type=" + type +
                ", number=" + number +
                '}';
    }
}
