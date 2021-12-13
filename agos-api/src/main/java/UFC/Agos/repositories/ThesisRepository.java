package UFC.Agos.repositories;

import UFC.Agos.models.Session;
import UFC.Agos.models.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {

     List<Thesis> getThesesBySession(Session session);

     Thesis getThesisByIdAndSession(Long thesisId, Session session);

    @Query(value = "SELECT * FROM Thesis t WHERE id IN  (SELECT thesis_id FROM student_thesis WHERE student_id = (SELECT id from Student s WEHRE s.id =?))", nativeQuery = true)
    List<Thesis> findThesesByStudent(Long studentId);

    @Query(value = "SELECT * FROM Thesis t WHERE id = ?1 AND id  IN  (SELECT thesis_id FROM student_thesis WHERE student_id = (SELECT id from Student s WEHRE s.id =?2))", nativeQuery = true)
    Thesis findThesisByIdAndStudent(Long thesisId,Long studentId);

    @Query(value = "SELECT * FROM Thesis t WHERE id IN  (SELECT thesis_id FROM evaluation WHERE professor_id = (SELECT id from professor  WEHRE p.id =?))", nativeQuery = true)
    List<Thesis> findThesesByProfessor(Long professorId);

    @Query(value = "SELECT * FROM Thesis t WHERE id = ?1 AND id  IN  (SELECT thesis_id FROM evaluation  WHERE professor_id = (SELECT id from professor p WEHRE p.id =?2))", nativeQuery = true)
    Thesis findThesisByIdAndProfessor(Long thesisId,Long professorId);


}
