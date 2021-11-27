package UFC.Agos.repositories;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    public List<Student> getStudentByFormation(Formation formation);
}
