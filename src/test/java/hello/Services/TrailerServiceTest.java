package hello.Services;

import hello.Repositories.TrailerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests functionality of TrailerService class.
 * We do not test the methods listAllTailers(), findTrailer() and save() since they only depend on the TrailerRepository
 * to work and do not have a complex logic.
 */
@RunWith(SpringRunner.class)
public class TrailerServiceTest {

    @TestConfiguration
    static class TrailerServiceTestContextConfiguration {
        @Bean
        public TrailerService trailerService() {
            return new TrailerService();
        }
    }

    @Autowired
    private TrailerService trailerService;

    @MockBean
    private TrailerRepository trailerRepository;


    @Before
    public void setup(){

    }


    @Test
    public void listTrailers(){

    }

    @Test
    public void listUsedTrailers(){

    }
}
