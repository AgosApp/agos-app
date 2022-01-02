package UFC.Agos.repositories;

import UFC.Agos.models.Crenel;
import UFC.Agos.models.Session;
import UFC.Agos.models.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {

     List<Thesis> getThesesByCrenel(Crenel crenel);

     Thesis getThesisByIdAndCrenel(Long thesisId, Crenel crenel);

    @Query(value = "SELECT * FROM Thesis t WHERE id IN  (SELECT thesis_id FROM student_thesis WHERE student_id =?)", nativeQuery = true)
    List<Thesis> findThesesByStudent(Long studentId);

    @Query(value = "SELECT * FROM Thesis t WHERE id = ?1 AND id  IN  (SELECT thesis_id FROM student_thesis WHERE student_id =?2)", nativeQuery = true)
    Thesis findThesisByIdAndStudent(Long thesisId,Long studentId);

    @Query(value = "SELECT * FROM Thesis t WHERE id IN  (SELECT thesis_id FROM evaluation WHERE professor_id =?)", nativeQuery = true)
    List<Thesis> findThesesByProfessor(Long professorId);

    @Query(value = "SELECT * FROM Thesis t WHERE id = ?1 AND id  IN  (SELECT thesis_id FROM evaluation  WHERE professor_id  =?2)", nativeQuery = true)
    Thesis findThesisByIdAndProfessor(Long thesisId,Long professorId);


}
