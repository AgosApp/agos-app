package UFC.Agos.configurations;

import UFC.Agos.models.*;
import UFC.Agos.repositories.ThesisRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class ThesisConfiguration {


    @Bean
    CommandLineRunner thesisCommandLineRunner(ThesisRepository thesisRepository){
        Department ufrST = new Department("Arts", "Département en UFR Culture");

        Formation formation = new Formation(
                "L1 SVT",
                "Licence 1 Sciences vie et terre",
                ufrST);

        Formation formationL3 = new Formation(
                "L3 Info",
                "Licence 3 Informatique",
                ufrST);
        NotationGroup notationGroup = new NotationGroup("notation group for internship L1 theses");


        Session stageL1 = new Session("Soutenance L1",
                Duration.ofMinutes(20),
                LocalDate.of(2022, 02, 10),
                formation,
                notationGroup);

        Session stageL3 = new Session("Soutenance L3 info",
                Duration.ofMinutes(20),
                LocalDate.of(2022, 02, 10),
                formation,
                notationGroup);

        Room room1 = new Room("501C", null);
        Room room2 = new Room("008B", null);

        Crenel crenelSoutenanceL1 = new Crenel(LocalDate.of(2021, 12, 02), LocalTime.of(8, 00), LocalTime.of(12, 30),stageL1, List.of(room1, room2));
        Crenel crenelStageL3 = new Crenel(LocalDate.of(2022, 01, 21), LocalTime.of(8, 00), LocalTime.of(12, 30),stageL3, List.of(room1));



        return args -> {
            Thesis thesis = new Thesis(
                    "ADN des animaux",
                    "projet",
                    LocalDateTime.of(2022,02,21,8, 22 ),
                    null,
                    "ADN des animaux",
                    crenelSoutenanceL1,
                    room2
            );
            Thesis thesisL2 = new Thesis(
                    "Réseaux de Neuronnes",
                    "stage",
                    LocalDateTime.of(2022,03,02,10, 00 ),
                    null,
                    "Intélligence artificielle",
                    crenelStageL3,
                    room1
            );
            thesisRepository.saveAll(List.of(thesis,thesisL2));
        };
    }
}
