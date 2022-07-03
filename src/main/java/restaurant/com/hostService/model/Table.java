package restaurant.com.hostService.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    Long ID;
    Boolean isAvailable = true;

    private List<Client> clients = new ArrayList<Client>();

    public Table() {
    }

    public Table(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", isAvailable=" + isAvailable +
                ", clients=" + clients;
    }
}
