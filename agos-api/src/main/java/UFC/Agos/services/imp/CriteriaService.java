package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.CriteriaEvaluationRepository;
import UFC.Agos.repositories.CriteriaRepository;
import UFC.Agos.repositories.NotationRepository;
import UFC.Agos.services.ICriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CriteriaService implements ICriteriaService {

    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    NotationRepository notationRepository;

    @Autowired
    CriteriaEvaluationRepository criteriaEvaluationRepository;

    @Override
    public List<Criteria> getCriterias() {
        return criteriaRepository.findAll();
    }

    @Override
    public Criteria getCriteria(Long criteriaId) {
        return criteriaRepository.findById(criteriaId).orElseThrow(
                ()-> new IllegalStateException("Criteria"+ criteriaId + " does not exist")
        );
    }

    @Override
    public void addCriteria(Criteria criteria) {
        criteriaRepository.save(criteria);
    }

    @Override
    public void deleteCriteria(Long criteriaId) throws Exception {
        boolean exists = criteriaRepository.existsById(criteriaId);
        if(!exists){
            throw new IllegalStateException("The department with id "+ criteriaId + " does not exist");
        }

        Criteria criteria = criteriaRepository.getById(criteriaId);
        List<Notation> notations = notationRepository.getNotationsByCriteria(criteria);
        List<CriteriaEvaluation> criteriaEvaluations = criteriaEvaluationRepository.getCriteriaEvaluationsByCriteria(criteria);

        if(!notations.isEmpty() || !criteriaEvaluations.isEmpty()){
            throw new Exception("the criteria with id "+ criteriaId +" can't be removed because it contains relationships");
        }

        criteriaRepository.deleteById(criteriaId);
    }

    @Override
    @Transactional
    public void updateCriteria(Long criteriaId, String title) {
        Criteria criteria = criteriaRepository.findById(criteriaId).orElseThrow(
                ()-> new IllegalStateException("The criteria with id "+ criteriaId + " does not exist")
        );

        if(title != null && title.length()>0 && !Objects.equals(title, criteria.getTitle())){
            criteria.setTitle(title);
        }

        }
    }

