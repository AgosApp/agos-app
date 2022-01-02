package UFC.Agos.repositories;

import UFC.Agos.models.Evaluation;
import UFC.Agos.models.Professor;
import UFC.Agos.models.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Evaluation getEvaluationByProfessor(Professor professor);

    List<Evaluation> getEvaluationsByThesis(Thesis thesis);

    @Modifying
    @Query(value = "DELETE FROM evaluation  WHERE thesis_id = ?", nativeQuery = true)
    void deleteByThesis(Long thesisId);

}
