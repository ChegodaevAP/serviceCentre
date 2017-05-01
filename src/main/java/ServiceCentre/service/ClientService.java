package ServiceCentre.service;


import ServiceCentre.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClient();

    Client saveClient(Client client);

    Client findByClientName(String name);
}
