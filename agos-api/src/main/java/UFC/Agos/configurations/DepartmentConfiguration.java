package UFC.Agos.configurations;

import UFC.Agos.models.Department;
import UFC.Agos.repositories.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DepartmentConfiguration {

    @Bean
    CommandLineRunner departmentCommandLineRunner(DepartmentRepository departmentRepository){

        return args -> {
            Department info = new Department(
                    "UFR ST INFO",
                    "Departement Informatique"
            );
            Department lettre = new Department(
                    "UFR Lettre Litterature Française",
                    "Department Lettres"
            );

            departmentRepository.saveAll(List.of(info,lettre));
        };

    }
}
