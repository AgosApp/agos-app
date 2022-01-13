package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService implements IStudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    StudentThesisRepository studentThesisRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Student findStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByFormation(Long formationId) {
        Formation formation = formationRepository.getById(formationId);
        return studentRepository.getStudentsByFormation(formation);
    }

    @Override
    public Student getStudentByFormation(Long studentId, Long formationId) {
        Formation formation = formationRepository.getById(formationId);
        return studentRepository.getStudentByIdAndFormation(studentId, formation) ;
    }

    @Override
    public void addStudent(Student student, Long formationId) throws Exception {
        Formation formation = formationRepository.getById(formationId);
        student.setFormation(formation);
        student.setRole(roleRepository.findByName("STUDENT_ROLE"));

        //check if username already exists
        Professor professor = professorRepository.findByUsername(student.getUsername());

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        if(professor == null)
            studentRepository.save(student);
        else throw new Exception("username taken");
    }

    @Override
    public void deleteStudent(Long studentId) throws Exception {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("The student with id "+ studentId + " does not exist");
        }
        Student student = studentRepository.getById(studentId);
        List<StudentThesis> studentThesisList =  studentThesisRepository.getStudentThesisByStudent(student);
        if(!studentThesisList.isEmpty()){
            throw new Exception("th student with id "+ studentId +" can't be removed because he has theses");
        }
        studentRepository.deleteById(studentId);
    }

    @Override
    @Transactional
    public void updateStudent(Long studentId, String firstName, String lastName,String username, String password, Long formationId) throws Exception {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("The student with id " + studentId + " does not exist")
        );

        if(firstName != null && firstName.length()>0 && !Objects.equals(firstName, student.getFirstName())){
            student.setFirstName(firstName);
        }

        if( lastName!= null && lastName.length()>0 && !Objects.equals(lastName, student.getLastName())){
            student.setLastName(lastName);
        }

        if( username != null && username.length()>0 && !Objects.equals(username, student.getUsername())){
            //check if username already exists
            Professor professor = professorRepository.findByUsername(student.getUsername());
            if(professor == null)
                student.setUsername(username);
            else throw new Exception("username taken");
        }

        if( password != null && password.length()>0){
            student.setUsername(passwordEncoder.encode(password));
        }

        if(formationId != null && formationId != student.getFormation().getId() ){
            Formation formation = formationRepository.getById(formationId);
            student.setFormation(formation);
        }
    }
}
