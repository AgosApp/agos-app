package UFC.Agos.configurations;

import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import UFC.Agos.models.Student;
import UFC.Agos.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
       Formation formation1 = new Formation("M2 Big data", "Master 2 Info", ufrST);

        return args -> {
            Student mourtada = new Student(
                    "Mourtada",
                    "BOUFELJA",
                    "mboufelj",
                    formation
                    );
            Student nossair = new Student(
                    "Nossair",
                    "Sbaibi",
                    "nsbaibi",
                    formation1
            );

            studentRepository.saveAll(List.of(mourtada,nossair));
        };

    }
}
