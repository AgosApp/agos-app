package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.FormationRepository;
import UFC.Agos.repositories.StudentRepository;
import UFC.Agos.repositories.StudentThesisRepository;
import UFC.Agos.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addStudent(Student student, Long formationId) {
        Formation formation = formationRepository.getById(formationId);
        student.setFormation(formation);
        studentRepository.save(student);

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
    public void updateStudent(Long studentId, String firstName, String lastName,String login, Long formationId) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("The student with id " + studentId + " does not exist")
        );

        if(firstName != null && firstName.length()>0 && !Objects.equals(firstName, student.getFirstName())){
            student.setFirstName(firstName);
        }

        if( lastName!= null && lastName.length()>0 && !Objects.equals(lastName, student.getLastName())){
            student.setLastName(lastName);
        }

        if( login != null && login.length()>0 && !Objects.equals(login, student.getLogin())){
            student.setLogin(login);
        }

        if(formationId != null && formationId != student.getFormation().getId() ){
            Formation formation = formationRepository.getById(formationId);
            student.setFormation(formation);
        }
    }
}
