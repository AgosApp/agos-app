package UFC.Agos.services;

import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Notation;
import UFC.Agos.models.Thesis;

import java.util.List;

public interface ICriteriaEvaluationService {
    public List<CriteriaEvaluation> getCriteriaEvaluationsByCriteria(Long criteriaId);

    List<CriteriaEvaluation> getCriteriaEvaluationsByThesis(Long thesisId);

}
