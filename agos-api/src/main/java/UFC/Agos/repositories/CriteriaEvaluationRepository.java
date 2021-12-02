package UFC.Agos.repositories;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriteriaEvaluationRepository extends JpaRepository<CriteriaEvaluation, Long> {

    public List<CriteriaEvaluation> getCriteriaEvaluationsByCriteria(Criteria criteria);
}
