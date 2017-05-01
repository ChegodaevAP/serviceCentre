package ServiceCentre.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ServiceCentre.dao.ClientDao;
import ServiceCentre.model.Client;
import ServiceCentre.service.ClientService;

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
