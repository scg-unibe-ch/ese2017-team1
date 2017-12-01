package hello.Services;

import hello.Client.Client;
import hello.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling requests concerning clients
 */
@Service("clientService")
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    /**
     * returns client with Id clientId
     */
    public Client findClient(Long clientId) { return clientRepository.findOne(clientId); }

    /**
     * @return Iterable of all Clients in clientRepository
     */
    public Iterable<Client> listClients() { return clientRepository.findAll(); }

    /**
     * saves client in clientRepository
     */
    public void save(Client client) { clientRepository.save(client); }
}
