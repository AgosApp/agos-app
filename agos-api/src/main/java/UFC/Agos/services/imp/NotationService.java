package UFC.Agos.services.imp;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Department;
import UFC.Agos.models.Notation;
import UFC.Agos.repositories.CriteriaRepository;
import UFC.Agos.repositories.FormationRepository;
import UFC.Agos.repositories.NotationRepository;
import UFC.Agos.repositories.StudentRepository;
import UFC.Agos.services.INotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotationService implements INotationService {


    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    NotationRepository notationRepository;

    @Autowired
    NotationService notationService;

    @Autowired
    CriteriaService criteriaService;

    @Override
    public List<Notation> getNotationsByCriteria(Long criteriaId) {
        Criteria criteria = criteriaService.getCriteria(criteriaId);
        return notationRepository.getNotationsByCriteria(criteria);
    }
}
