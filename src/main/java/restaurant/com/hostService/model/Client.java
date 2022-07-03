package restaurant.com.hostService.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * implements Comparable because is required to use PriorityBlockingQueue<Client> in QueueService
 */
public class Client implements Serializable, Comparable<Client>{
    @Serial
    private static final long serialVersionUID = 1L;
    private Long ID;
    private String name;

    public Client() {
    }

    public Client(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Long getID() {
        return ID;
    }

    public Client setID(Long ID) {
        this.ID = ID;
        return this;
    }

    public String getName() {
        return name;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return ID.equals(client.ID) && name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name);
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", name=" + name;
    }

    @Override
    public int compareTo(Client o) {
        return Long.compare(this.ID, o.getID());
    }
}
