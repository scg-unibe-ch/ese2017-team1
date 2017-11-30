package hello.Services;

import hello.Client.Client;
import hello.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clientService")
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findClient(Long clientId) { return clientRepository.findOne(clientId); }

    public Iterable<Client> listClients() { return clientRepository.findAll(); }

    public void save(Client client) { clientRepository.save(client); }
}
