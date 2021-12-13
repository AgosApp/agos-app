package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.FormationRepository;
import UFC.Agos.repositories.NotationGroupRepository;
import UFC.Agos.repositories.SessionRepository;
import UFC.Agos.repositories.ThesisRepository;
import UFC.Agos.services.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class SessionService implements ISessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    ThesisRepository thesisRepository;

    @Autowired
    NotationGroupRepository notationGroupRepository;


    @Override
    public List<Session> getSessionsByFormation(Long formationId) {
        Formation formation= formationRepository.getById(formationId);
        return sessionRepository.getSessionsByFormation(formation);
    }

    @Override
    public Session getSessionByFormation(Long sessionId, Long formationId) {
        Formation formation= formationRepository.getById(formationId);
        return sessionRepository.getSessionByIdAndFormation(sessionId, formation);
    }

    @Override
    public void addSession(Session session, Long formationId, Long notationGroupId) {
        Formation formation= formationRepository.getById(formationId);
        NotationGroup notationGroup = notationGroupRepository.getById(notationGroupId);
        session.setFormation(formation);
        session.setNotationGroup(notationGroup);
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Long sessionId) throws Exception {
        boolean exists = sessionRepository.existsById(sessionId);
        if(!exists){
            throw new IllegalStateException("The session with id "+ sessionId + " does not exist");
        }
        Session session = sessionRepository.getById(sessionId);
        List<Thesis> theses =  thesisRepository.getThesesBySession(session);
        if(!theses.isEmpty()){
            throw new Exception("the session with id "+ sessionId +" can't be removed because it contains theses");
        }
        sessionRepository.deleteById(sessionId);
    }

    @Override
    @Transactional
    public void updateSession(Long sessionId, String title, Integer duration, String alertDelay, Long notationGroupId, Long formationId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(
                () -> new IllegalStateException("The session with id " + sessionId + " does not exist")
        );

        if(title != null && title.length()>0 && !Objects.equals(title, session.getTitle())){
            session.setTitle(title);
        }

        if(duration != null && duration != 0 && !Objects.equals(duration, session.getDuration())){
            System.out.println("service "+duration);

            session.setDuration(duration);
        }

        if(alertDelay != null && !Objects.equals(alertDelay, session.getAlertDelay())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate alertDate = LocalDate.parse(alertDelay, formatter);
            if(!alertDate.isBefore(LocalDate.now()))     session.setAlertDelay(alertDate);
        }

        if(notationGroupId != null && notationGroupId != session.getNotationGroup().getId() ){
            NotationGroup notationGroup = notationGroupRepository.getById(notationGroupId);
            session.setNotationGroup(notationGroup);
        }

        if(formationId != null && formationId != session.getFormation().getId() ){
            Formation formation = formationRepository.getById(formationId);
            session.setFormation(formation);
        }
    }
}
