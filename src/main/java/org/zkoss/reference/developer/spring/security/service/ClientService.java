package org.zkoss.reference.developer.spring.security.service;


import org.zkoss.reference.developer.spring.security.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAllClient();

    Client saveClient(Client client);

    Client findByClientName(String name);
}
