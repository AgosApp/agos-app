package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.IProfessorService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Professor professor = professorRepository.findByUsername(username);
        Student student = studentRepository.findByUsername(username);

        if(professor == null && student == null) {
            throw new UsernameNotFoundException("username not found !");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(professor == null){
            student.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new User(student.getUsername(), student.getPassword(), authorities);

        } else if(student == null) {
            professor.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));

            });
            return new User(professor.getUsername(), professor.getPassword(), authorities);

        } else return  null;
    }

    @Override
    public void addRoleToProfessor(String username, String roleName) {
        Professor professor = professorRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        professor.getRoles().add(role);

    }

    @Override
    public Professor findProfessorByUsername(String username) {
        return professorRepository.findByUsername(username);
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
    public void addProfessor(Professor professor, Long departmentId) throws Exception {
        Department department = departmentRepository.getById(departmentId);
        professor.setDepartment(department);
        professor.setRole(roleRepository.findByName("PROF_ROLE"));
        if (professor.isAdmin()){
            professor.setRole(roleRepository.findByName("ADMIN_ROLE"));
        }
        professor.setPassword(passwordEncoder.encode(professor.getPassword()));
        //check if username already exists
        Student student = studentRepository.findByUsername(professor.getUsername());
        if(student == null)
        professorRepository.save(professor);
        else throw new Exception("username taken");
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
    public void updateProfessor(Long professorId, String firstName, String lastName, String username, String password, boolean isAdmin, Long departmentId) throws Exception {

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
            //check if username already exists
            Student student = studentRepository.findByUsername(professor.getUsername());
            if(student == null)
                professor.setUsername(username);
            else throw new Exception("username taken");
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
