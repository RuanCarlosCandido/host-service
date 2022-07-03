package restaurant.com.hostService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurant.com.hostService.model.Client;
import restaurant.com.hostService.model.Hall;
import restaurant.com.hostService.model.Table;
import restaurant.com.hostService.service.ClientService;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@RestController
@RequestMapping("/")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("client/add")
    public List<Table> addClient(@RequestBody Client client) {
        return clientService.add(client);
    }

    @GetMapping("hall")
    public Hall addClient() {
        return clientService.getHall();
    }

    @PostMapping("/table/remove/{tableID}")
    public void unBookTable(@PathVariable (name = "tableID", required = true) Long tableID) {
        clientService.unBook(tableID);
    }

    @GetMapping("ping")
    public String ping() throws IOException {
        try (java.net.DatagramSocket socket = new java.net.DatagramSocket()) {
            String publicIP = new java.io.BufferedReader(
                    new java.io.InputStreamReader(new java.net.URL("http://checkip.amazonaws.com/").openStream()))
                    .readLine();
            InetAddress localIP = java.net.InetAddress.getLocalHost();

            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ext = socket.getLocalAddress().getHostAddress();

            return "HostService running at: Public IP: " + publicIP + " Local IP: " + localIP + " ext: " + ext;
        }
    }

}
