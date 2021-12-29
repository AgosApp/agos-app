package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class ProfessorService implements IProfessorService, UserDetailsService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    CriteriaEvaluationRepository criteriaEvaluationRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Professor professor = professorRepository.findByUsername(username);
        if(professor == null) {
            throw new UsernameNotFoundException("username not found !");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        professor.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(professor.getUsername(), professor.getPassword(), authorities);
    }

    @Override
    public void addRoleToProfessor(String username, String roleName) {
        Professor professor = professorRepository.findByUsername(username);
        System.out.println(professor);
        Role role = roleRepository.findByName(roleName);

        System.out.println(role.toString());
        //professor.setRole(new Role("ROLE_PROFESSOR"));
        professor.getRoles().add(role);

    }

    @Override
    public List<Professor> getProfessorsByDepartment(Long departmentId) {
        Department department = departmentRepository.getById(departmentId);
        return professorRepository.getProfessorsByDepartment(department);
    }

    @Override
    public Professor getProfessorByDepartment(Long professorId, Long departmentId) {
        Department department = departmentRepository.getById(departmentId);
        return professorRepository.getProfessorByIdAndDepartment(professorId, department);
    }

    @Override
    public void addProfessor(Professor professor, Long departmentId) {
        Department department = departmentRepository.getById(departmentId);
        professor.setDepartment(department);
        professorRepository.save(professor);
    }

    @Override
    public void deleteProfessor(Long professorId) throws Exception {
        boolean exists = professorRepository.existsById(professorId);
        if(!exists){
            throw new IllegalStateException("The professor with id "+ professorId + " does not exist");
        }
        Professor professor = professorRepository.getById(professorId);
        List<CriteriaEvaluation> criteriaEvaluations =  criteriaEvaluationRepository.getCriteriaEvaluationsByProfessor(professor);
        Evaluation evaluation = evaluationRepository.getEvaluationByProfessor(professor);
        if(!criteriaEvaluations.isEmpty() || evaluation != null){
            throw new Exception("th professor with id "+ professorId +" can't be removed because he has evaluation or criteriaEvaluations ");
        }
        professorRepository.deleteById(professorId);
    }

    @Override
    @Transactional
    public void updateProfessor(Long professorId, String firstName, String lastName, String username, String password, boolean isAdmin, Long departmentId) {

        Professor professor = professorRepository.findById(professorId).orElseThrow(
                () -> new IllegalStateException("The professor with id " + professorId + " does not exist")
        );

        if(lastName != null && lastName.length()>0 && !Objects.equals(lastName, professor.getLastName())){
            professor.setLastName(lastName);
        }

        if(firstName != null && firstName.length()>0 && !Objects.equals(firstName, professor.getFirstName())){
            professor.setFirstName(firstName);
        }

        if(username != null && username.length()>0 && !Objects.equals(username, professor.getUsername())){
            professor.setUsername(username);
        }

        if(!Objects.equals(isAdmin, professor.isAdmin())){
            professor.setAdmin(isAdmin);
        }

        if(departmentId != null && departmentId != professor.getDepartment().getId() ){
            Department department = departmentRepository.getById(departmentId);
            professor.setDepartment(department);
        }
    }

}
