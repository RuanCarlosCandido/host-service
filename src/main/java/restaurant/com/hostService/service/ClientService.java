package restaurant.com.hostService.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import restaurant.com.hostService.model.Client;
import restaurant.com.hostService.model.Hall;
import restaurant.com.hostService.model.Table;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClientService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    // schedule configuration (ss mm hh dd mm yy), cron = "30 * * * * * is
    // configured to run every minute
    private static final String SCHEDULING_INTERVAL_EVERY_MINUTE = "30 * * * * *";

    private static final int TOTAL_NUMBER_OF_TABLES = 2;
    private List<Client> clients = new ArrayList<Client>();
    private Hall hall = new Hall(TOTAL_NUMBER_OF_TABLES);

    QueueService queue = new QueueService();

    public List<Table> add(@RequestBody Client client) {
        if (!hasAvailableDiningTables())
            queue.addToQueue(client);
        else
            allowEntry(client);

        return hall.getTables();
    }

    private void allowEntry(Client client) {
        LOGGER.info("entry allowed for customer {}", client);
        Table table = hall.getTables().stream().filter(iterableTable -> iterableTable.isAvailable()).findFirst().get();
        table.setAvailable(false);
        table.getClients().add(client);
    }

    private boolean hasAvailableDiningTables() {
        return hall.getTables().stream().anyMatch(table -> table.isAvailable());
    }

    /**
     * if there's someone in queue, verify if there's a available table and allow entry
     */
    @Scheduled(cron = SCHEDULING_INTERVAL_EVERY_MINUTE)
    public void relocate() {
        if (hasAvailableDiningTables() && !queue.isQueueEmpty()) {
            LOGGER.debug("relocating");
            allowEntry(queue.removeFromQueue());
        }
    }

    public Hall getHall() {
        return hall;
    }

    public void unBook(Long tableID) {
        for (Table table : hall.getTables()) {
            if (table.getID() == tableID) {
                LOGGER.debug("unbooking table {}", table.getID());
                table.getClients().clear();
                table.setAvailable(true);
            }
        }
    }
}
