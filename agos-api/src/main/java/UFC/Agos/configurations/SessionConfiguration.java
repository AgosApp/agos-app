package UFC.Agos.configurations;

import UFC.Agos.models.*;
import UFC.Agos.repositories.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class SessionConfiguration {


    @Bean
    CommandLineRunner sessionCommandLineRunner(SessionRepository sessionRepository){
        Department ufrST = new Department("Mécanique", "Département en UFR ST");

        Formation formation = new Formation(
                "L3 Mécanique",
                "Licence 3 Méca",
                ufrST);
        NotationGroup notationGroup = new NotationGroup("notation group for theses");

        return args -> {
            Session session = new Session(
                    "Soutenance Fin d'études",
                    Duration.ofMinutes(20),
                    LocalDate.of(2022, 02, 10),
                    formation,
                    notationGroup
            );
            Session sessionProjet = new Session(
                    "Soutenance Projet mécanique de solide",
                    Duration.ofMinutes(15),
                    LocalDate.of(2021, 12, 13),
                    formation,
                    notationGroup
            );
            sessionRepository.saveAll(List.of(session,sessionProjet));
        };
    }
}
