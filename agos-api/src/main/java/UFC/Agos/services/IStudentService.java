package UFC.Agos.services;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Professor;
import UFC.Agos.models.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> getStudentsByFormation(Long formationId);

    public Student getStudentByFormation(Long studentId, Long formationId);

    public void addStudent(Student student, Long formationId) throws Exception;

    public void deleteStudent(Long studentId) throws Exception;

    public void updateStudent(Long studentId,
                                String firstName,
                                String lastName,
                                String username,
                                String password,
                                Long formationId
    ) throws Exception;

    Student findStudentByUsername(String username);

}
