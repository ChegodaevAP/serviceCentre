package org.zkoss.reference.developer.spring.security.sericeImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.reference.developer.spring.security.dao.ClientDao;
import org.zkoss.reference.developer.spring.security.model.Client;
import org.zkoss.reference.developer.spring.security.service.ClientService;

import java.util.List;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientDao clientDao;

    @Override
    public List<Client> getAllClient() {
        return clientDao.getAll();
    }

    @Override
    public Client saveClient(Client client) {
        return findByClientName(client.getName()) == null ? clientDao.insertClient(client) : null;
    }

    @Override
    public Client findByClientName(String name) {
        return clientDao.findClientByName(name);
    }
}
