package hello.Services;

import hello.Repositories.TrailerRepository;
import hello.Trucks.Trailer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests functionality of TrailerService class.
 * We do not test the methods listAllTailers(), findTrailer() and save() since they only depend on the TrailerRepository
 * to work and do not have a complex logic.
 */
@RunWith(SpringRunner.class)
public class TrailerServiceTest {

    ArrayList<Trailer> freeTrailers;
    ArrayList<Trailer> usedTrailers;
    ArrayList<Trailer> allTrailers;

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
        freeTrailers = new ArrayList<>();
        usedTrailers = new ArrayList<>();
        allTrailers = new ArrayList<>();

        Trailer trai1 = new Trailer();
        trai1.setFree(1);
        trailerRepository.save(trai1);
        freeTrailers.add(trai1);
        allTrailers.add(trai1);

        Trailer trai2 = new Trailer();
        trai2.setFree(0);
        trailerRepository.save(trai2);
        usedTrailers.add(trai2);
        allTrailers.add(trai2);

        Mockito.when(trailerRepository.findAll()).thenReturn(allTrailers);
    }


    @Test
    public void listTrailers(){
        assertThat(trailerService.listTrailers()).isEqualTo(freeTrailers);
    }

    @Test
    public void listUsedTrailers(){
        assertThat(trailerService.listUsedTrailers()).isEqualTo(usedTrailers);
    }
}
