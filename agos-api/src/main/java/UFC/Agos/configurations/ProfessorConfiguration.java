package UFC.Agos.configurations;


import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import UFC.Agos.models.Professor;
import UFC.Agos.repositories.ProfessorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProfessorConfiguration {

    @Bean
    CommandLineRunner professorCommandLineRunner(ProfessorRepository professorRepository){

        Department ufrL = new Department("Lettres", "Département en UFR Lettres");
        return args -> {
            Professor professor = new Professor(
                    "Maria",
                    "Alberti",
                    "malberti",
                    "mAl",
                    false,
                    ufrL
            );
            Professor professor1 = new Professor(
                    "Jean François",
                    "Couchot",
                    "fcouchot",
                    "JFC",
                    true,
                    ufrL
            );
            professorRepository.saveAll(List.of(professor1,professor));
        };
    }
}
