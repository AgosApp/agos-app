package UFC.Agos.services.imp;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Department;
import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;
import UFC.Agos.repositories.*;
import UFC.Agos.services.INotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    NotationGroupService notationGroupService;

    @Autowired
    CriteriaService criteriaService;

    @Autowired
    NotationGroupRepository notationGroupRepository;

    @Override
    public List<Notation> getNotations() {
        return notationRepository.findAll();
    }

    @Override
    public Notation getNotation(Long notationId) {
        return notationRepository.findById(notationId).orElseThrow(
                ()-> new IllegalStateException("Notation with Id"+ notationId + " does not exist")
        );
    }

    @Override
    @Transactional
    public void addNotation(Notation notation
                            //Long criteriaId,
                            //Long notationGroupId
                                    ) {
       /* Criteria criteria = criteriaService.getCriteria(criteriaId);
        notation.setCriteria(criteria);*/

        //NotationGroup notationGroup = notationGroupService.getNotationGroup(notationGroupId);
        //notation.setNotationGroup(notationGroup);
         /*criteria = criteriaRepository.findById(notation.getCriteria().getId()).orElse(null);
        notation.setCriteria(criteria);*/

        /*NotationGroup notationGroup = new NotationGroup();
        notationGroup.setNotation_group_title(notation.getNotationGroup().getNotation_group_title());;
        notation.setNotationGroup(notationGroup);*/
        //notation.setNotationGroup(this);
        //notations.add(notation) ;

        notationRepository.save(notation);
    }

    @Override
    public void deleteNotation(Long notationId) throws Exception {

        boolean exists = notationRepository.existsById(notationId);
        if(!exists){
            throw new IllegalStateException("The notation with id "+ notationId + " does not exist");
        }
        Notation notation = notationRepository.getById(notationId);

        notationRepository.delete(notation);
    }

    @Override
    public void updateNotation(Long notationId, int bareme, Long criteriaId, Long notationGroupId) {

    }

    @Override
    public List<Notation> getNotationsByCriteria(Long criteriaId) {
        Criteria criteria = criteriaService.getCriteria(criteriaId);
        return notationRepository.getNotationsByCriteria(criteria);
    }

    @Override
    public List<Notation> getNotationsByNotationGroup(Long notationGroupId) {
        NotationGroup notationGroup = notationGroupService.getNotationGroup(notationGroupId);
        return notationRepository.getNotationsByNotationGroup(notationGroup);
    }
}
