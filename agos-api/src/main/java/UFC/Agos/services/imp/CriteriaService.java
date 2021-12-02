package UFC.Agos.services.imp;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import UFC.Agos.repositories.CriteriaRepository;
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

