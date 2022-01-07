package UFC.Agos.configurations;


import UFC.Agos.models.Department;
import UFC.Agos.models.Professor;
import UFC.Agos.models.Role;
import UFC.Agos.repositories.ProfessorRepository;
import UFC.Agos.repositories.RoleRepository;
import UFC.Agos.services.imp.ProfessorService;
import UFC.Agos.services.imp.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProfessorConfiguration {

    @Bean
    CommandLineRunner professorCommandLineRunner(ProfessorRepository professorRepository, RoleService roleService, ProfessorService professorService, RoleRepository roleRepository){

        Department ufrL = new Department("Lettres", "Département en UFR Lettres");

        return args -> {

            Role PROF_ROLE=new Role("PROF_ROLE");
            Role ADMIN_ROLE = new Role("ADMIN_ROLE");
           //roleService.addRol(PROF_ROLE);
            //roleService.addRole(new Role("ADMIN_ROLE"));
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Professor professor = new Professor(
                    "Maria",
                    "Alberti",
                    "malberti",
                    passwordEncoder.encode("1234"),
                    false,
                    ufrL,
                    new ArrayList<Role>()
            );
            professor.setRole(PROF_ROLE);

            Professor admin = new Professor(
                    "Jean François",
                    "Couchot",
                    "fcouchot",
                    passwordEncoder.encode("1234"),
                    true,
                    ufrL,
                    new ArrayList<Role>()
            );
            admin.setRole(PROF_ROLE);
            admin.setRole(ADMIN_ROLE);
            professorRepository.saveAll(List.of(admin,professor));

           // professorService.addRoleToProfessor("malberti", "PROF_ROLE");
           // professorService.addRoleToProfessor("fcouchot", "PROF_ROLE");
            // professorService.addRoleToProfessor("fcouchot", "ADMIN_ROLE");

        };
    }
}
