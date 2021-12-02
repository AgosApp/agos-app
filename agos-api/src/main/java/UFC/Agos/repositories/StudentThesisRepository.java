package UFC.Agos.repositories;

import UFC.Agos.models.Student;
import UFC.Agos.models.StudentThesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentThesisRepository extends JpaRepository<StudentThesis, Long> {

    public List<StudentThesis> getStudentThesisByStudent(Student student);
}
