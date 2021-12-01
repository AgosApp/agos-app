package UFC.Agos.configurations;

import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import UFC.Agos.models.Professor;
import UFC.Agos.models.Student;
import UFC.Agos.repositories.ProfessorRepository;
import UFC.Agos.repositories.StudentRepository;
import UFC.Agos.services.imp.ProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository studentRepository, ProfessorRepository professorRepository, ProfessorService professorService){
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
            Professor professor = new Professor(
                    "Maurilleaud",
                    "Nicolas",
                    "mnicolas",
                    "NM",
                    false,
                    ufrST
            );
            Professor professor1 = new Professor(
                    "Jean François",
                    "Couchot",
                    "fcouchot",
                    "JFC",
                    true,
                    ufrST
            );

            studentRepository.saveAll(List.of(mourtada,nossair));
            //professorRepository.saveAll(List.of(professor,professor1));
            //professorService.addProfessor(professor,ufrST.getId());
        };

    }
}
