package UFC.Agos.configurations;


import UFC.Agos.models.*;
import UFC.Agos.repositories.CrenelRepository;
import UFC.Agos.repositories.SessionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class CrenelConfiguration {

    @Bean
    CommandLineRunner crenelCommandLineRunner(CrenelRepository crenelRepository){
        Department ufrST = new Department("Mécanique", "Département en UFR ST");

        Formation formation = new Formation(
                "M2 informatique ISL",
                "Master 2 informatique Ingénierie sys et logiciels",
                ufrST);
        NotationGroup notationGroup = new NotationGroup("notation group 2");

        Session session = new Session(
                "Projets PAM",
                Duration.ofMinutes(20),
                Duration.ofMinutes(15),
                Duration.ofMinutes(5),
                LocalDate.of(2022, 01, 21),
                formation,
                notationGroup
        );

        Session sessionM2 = new Session(
                "Soutenances M2 ISL",
                Duration.ofMinutes(20),
                Duration.ofMinutes(15),
                Duration.ofMinutes(5),
                LocalDate.of(2022, 07, 25),
                formation,
                notationGroup
        );


        Room roomC = new Room("401C-B","null");
        Room roomB = new Room("201B","null");


        return args -> {

            Crenel crenelPAM = new Crenel(LocalDate.of(2022, 01, 21), LocalTime.of(8, 00), LocalTime.of(12, 30),session, List.of(roomB));
            Crenel crenelM2 = new Crenel(LocalDate.of(2021, 07, 25), LocalTime.of(8, 00), LocalTime.of(18, 00),session, List.of(roomB, roomC));

            crenelRepository.saveAll(List.of(crenelPAM,crenelM2));
        };
    }
}
