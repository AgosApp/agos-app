package UFC.Agos.services.imp;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Department;
import UFC.Agos.models.Notation;
import UFC.Agos.repositories.CriteriaEvaluationRepository;
import UFC.Agos.repositories.CriteriaRepository;
import UFC.Agos.repositories.NotationRepository;
import UFC.Agos.services.ICriteriaEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriaEvaluationService implements ICriteriaEvaluationService {

    @Autowired
    CriteriaService criteriaService;

    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    CriteriaEvaluationRepository criteriaEvaluationRepository;



    @Override
    public List<CriteriaEvaluation> getCriteriaEvaluationsByCriteria(Long criteriaId) {
        Criteria criteria = criteriaService.getCriteria(criteriaId);
        return criteriaEvaluationRepository.getCriteriaEvaluationsByCriteria(criteria);
    }
}
