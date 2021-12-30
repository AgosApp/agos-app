package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.INotationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class NotationGroupService implements INotationGroupService {

    @Autowired
    NotationGroupRepository notationGroupRepository;

    @Autowired
    NotationRepository notationRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    NotationService notationService;

    @Autowired
    NotationGroupService notationGroupService;


    @Autowired
    CriteriaEvaluationRepository criteriaEvaluationRepository;



    @Override
    public List<NotationGroup> getNotationGroups() {
        return notationGroupRepository.findAll();
    }

    @Override
    public NotationGroup getNotationGroup(Long notationGroupId) {
        return notationGroupRepository.findById(notationGroupId).orElseThrow(
                ()-> new IllegalStateException("Notation group with Id"+ notationGroupId + " does not exist")
        );
    }

    @Override
    @Transactional
    public void addNotationGroup(NotationGroup notationGroup) {
         //for mapping Criteria in Notation make it cascade.REMOVE -> EVITE cascade.ALL

        List<Notation> notations = new ArrayList<>();

        for (Notation s : notationGroup.getNotations()) {
            Notation notation = new Notation();
            notation.setBareme(s.getBareme());
            System.out.println("s is : "+s);
            notations.add(notation);
            notation.setNotationGroup(notationGroup);
            notation.setCriteria(s.getCriteria());


        }

        notationGroup.setNotations(notations);
        notationGroup = notationGroupRepository.save(notationGroup);

        System.out.println(notations);

    }


    @Override
    public void deleteNotationGroup(Long notationGroupId) throws Exception {
        boolean exists = notationGroupRepository.existsById(notationGroupId);
        if(!exists){
            throw new IllegalStateException("The notation group with id "+ notationGroupId + " does not exist");
        }

        NotationGroup notationGroup = notationGroupRepository.getById(notationGroupId);
        List<Notation> notations = notationRepository.getNotationsByNotationGroup(notationGroup);
        List<Session> sessions = sessionRepository.getSessionsByNotationGroup(notationGroup);


        if(!notations.isEmpty() || !sessions.isEmpty()){
            throw new Exception("the notation group with id "+ notationGroupId +" can't be removed because it contains relationships");
        }

        notationGroupRepository.deleteById(notationGroupId);
    }

    @Override
    @Transactional
    public void updateNotationGroup(NotationGroup notationGroup, Long id) {
        NotationGroup notationGroupToUpdate = notationGroupRepository.findById(id).orElseThrow(
                ()-> new IllegalStateException("The notation group with id "+id+" does not exist")
        );
        System.out.println("notationGroup updated is"+notationGroup);
        System.out.println("notationGroup to update is"+notationGroupToUpdate);

        notationGroupToUpdate.setNotationGroupTitle(notationGroup.getNotationGroupTitle());

        System.out.println("notationGroup title updated"+notationGroup.getNotationGroupTitle());

        //List<Notation> notations = notationGroup.getNotations();
            List<Notation> notations = notationRepository.getNotationsByNotationGroup(notationGroupToUpdate);
        System.out.println("notations list grom database"+notations);

        for (Notation s : notationGroup.getNotations()) {
                Notation notation = notationRepository.findById(s.getId()).orElseThrow(
                        ()-> new IllegalStateException("The notation  with id "+s.getId()+" does not exist")
                );

            System.out.println("notation to update is"+notation);
                System.out.println("notation updated is"+s);

                if(s.getCriteria() != null && s.getCriteria() != notation.getCriteria() ){
                    notation.setCriteria(s.getCriteria());
                }
                System.out.println("criteria to update is"+notation.getCriteria());
                System.out.println("criteria updated is"+s.getCriteria());

                notation.setNotationGroup(notationGroupToUpdate);

                notation.setBareme(s.getBareme());

                System.out.println("s is : " + s);

            }

            notationGroupToUpdate.setNotations(notations);
            System.out.println(notations);
        }




}
