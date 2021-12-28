package UFC.Agos.repositories;

import UFC.Agos.models.Student;
import UFC.Agos.models.StudentThesis;
import UFC.Agos.models.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentThesisRepository extends JpaRepository<StudentThesis, Long> {

    List<StudentThesis> getStudentThesisByStudent(Student student);

    List<StudentThesis> getStudentThesesByThesis(Thesis thesis);

    @Modifying
    @Query(value = "DELETE from student_thesis WHERE thesis_id = ?", nativeQuery = true)
    void deleteByThesis(Long thesisId);
}
