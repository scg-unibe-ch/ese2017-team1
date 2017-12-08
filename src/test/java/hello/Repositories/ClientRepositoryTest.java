package hello.Repositories;

import hello.Application;
import hello.Client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import hello.Application;
import hello.Client.Client;
import hello.Repositories.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Very stupid test but maybe we can work on it later.
 * repository.save() does not work
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ClientRepositoryTest {


    @Autowired
    private ClientRepository repository;


    /**
     * Tests whether client is saved correctly in Repository.
     * Maybe stupid?
     */
    @Test
    public void saveClient() throws Exception {

        Long id = Long.valueOf(20);

        Client client = new Client();

        client.setId(id);
        client.setCity("Langenthal");
        client.setEmail("test@example.com");
        client.setLand("Indien");
        client.setName("Pandorra");
        client.setStreet("Mannystreet 21");
        client.setPhone("079 840 33 22");

        this.repository.save(client);

        assertThat(repository.findByName("Pandorra").getCity()).isEqualTo("Langenthal");
        assertThat(repository.findByName("Pandorra").getEmail()).isEqualTo("test@example.com");
        assertThat(repository.findByName("Pandorra").getId()).isEqualTo(id);
        assertThat(repository.findByName("Pandorra").getLand()).isEqualTo("Indien");
        assertThat(repository.findByName("Pandorra").getPhone()).isEqualTo("079 840 33 22");
        assertThat(repository.findByName("Pandorra").getStreet()).isEqualTo("Mannystreet 21");

    }
}