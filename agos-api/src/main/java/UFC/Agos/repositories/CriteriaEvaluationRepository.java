package UFC.Agos.repositories;

import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaEvaluationRepository extends JpaRepository<CriteriaEvaluation, Long> {

    public List<CriteriaEvaluation> getCriteriaEvaluationsByProfessor(Professor professor);
}
