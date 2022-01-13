package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.CriteriaEvaluationRepository;
import UFC.Agos.repositories.CriteriaRepository;
import UFC.Agos.repositories.NotationRepository;
import UFC.Agos.repositories.ThesisRepository;
import UFC.Agos.services.ICriteriaEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CriteriaEvaluationService implements ICriteriaEvaluationService {

    @Autowired
    CriteriaService criteriaService;

    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    CriteriaEvaluationRepository criteriaEvaluationRepository;

    @Autowired
    ThesisRepository thesisRepository;



    @Override
    public List<CriteriaEvaluation> getCriteriaEvaluationsByCriteria(Long criteriaId) {
        Criteria criteria = criteriaService.getCriteria(criteriaId);
        return criteriaEvaluationRepository.getCriteriaEvaluationsByCriteria(criteria);
    }

    @Override
    public List<CriteriaEvaluation> getCriteriaEvaluationsByThesis(Long thesisId) {
        Thesis thesis = thesisRepository.getById(thesisId);
        return criteriaEvaluationRepository.getCriteriaEvaluationsByThesis(thesis);
    }

    @Transactional
    @Override
    public void updateNote(List<Float> notes, Long thesisId, Long professorId, List<Long> criteriaIds) {

        for (Long criteriaId : criteriaIds
             ) {
            criteriaEvaluationRepository.updateNote(
                    notes.get(criteriaIds.indexOf(criteriaId)),
                    thesisId,
                    professorId,
                    criteriaId);
        }
    }
}
