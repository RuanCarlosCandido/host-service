package restaurant.com.hostService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restaurant.com.hostService.model.Client;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    Queue<Client> queue = new PriorityBlockingQueue<Client>();

    public void addToQueue(Client client) {
        LOGGER.info("adding client {} to queue",client);
        queue.add(client);
    }

    public Client removeFromQueue() {
        return queue.poll();
    }

    public boolean isQueueEmpty(){
        return queue.isEmpty();
    }



}
