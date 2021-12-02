package UFC.Agos.services;

import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Notation;

import java.util.List;

public interface ICriteriaEvaluationService {
    public List<CriteriaEvaluation> getCriteriaEvaluationsByCriteria(Long criteriaId);

}
