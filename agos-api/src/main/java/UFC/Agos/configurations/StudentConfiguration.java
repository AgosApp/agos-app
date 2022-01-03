package UFC.Agos.configurations;

import UFC.Agos.models.*;
import UFC.Agos.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository studentRepository){
        Department ufrST = new Department("Info", "Département en UFR ST");
              Formation formation = new Formation(
                       "M2 ISL",
                       "Master 2 Info",
                       ufrST);
              Formation formation1 = new Formation(
                      "M2 Big data",
                      "Master 2 Info",
                      ufrST);


        return args -> {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Role role = new Role("STUDENT_ROLE");
            Student mourtada = new Student(
                    "Mourtada",
                    "BOUFELJA",
                    "mboufelj",
                    passwordEncoder.encode("1234"),
                    formation
                    );
            mourtada.setRole(role);
            Student nossair = new Student(
                    "Nossair",
                    "Sbaibi",
                    "nsbaibi",
                    passwordEncoder.encode("1234"),
                    formation1
            );
            nossair.setRole(role);
            Professor professor = new Professor(
                    "Maurilleaud",
                    "Nicolas",
                    "mnicolas",
                    passwordEncoder.encode("1234"),
                    false,
                    ufrST,
                    List.of(new Role("PROFESSOR_ROLE"))
            );
            studentRepository.saveAll(List.of(mourtada,nossair));
        };

    }
}
