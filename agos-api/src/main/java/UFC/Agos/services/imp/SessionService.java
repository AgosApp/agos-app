package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SessionService implements ISessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    NotationGroupRepository notationGroupRepository;

    @Autowired
    CrenelRepository crenelRepository;


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
        session.setFormation(formation);
        if(notationGroupId != null){
            NotationGroup notationGroup = notationGroupRepository.getById(notationGroupId);
            session.setNotationGroup(notationGroup);
        }
        session.setDuration(Duration.ofSeconds(session.getThesisDuration().getSeconds() + session.getDeliberationDuration().getSeconds()));
        sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Long sessionId) throws Exception {
        boolean exists = sessionRepository.existsById(sessionId);
        if(!exists){
            throw new IllegalStateException("The session with id "+ sessionId + " does not exist");
        }
        Session session = sessionRepository.getById(sessionId);
        List<Crenel> creneaux =  crenelRepository.getCrenelsBySession(session);
        if(!creneaux.isEmpty()){
            throw new Exception("the session with id "+ sessionId +" can't be removed because it contains crenaux");
        }
        sessionRepository.deleteById(sessionId);
    }

    @Override
    @Transactional
    public void updateSession(Long sessionId, String title, Integer duration, Integer thesisDuration, Integer deliberationDuration, String alertDelay, Long notationGroupId, Long formationId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow(
                () -> new IllegalStateException("The session with id " + sessionId + " does not exist")
        );

        if(title != null && title.length()>0 && !Objects.equals(title, session.getTitle())){
            session.setTitle(title);
        }

        if(thesisDuration != null  && !Objects.equals(thesisDuration, session.getThesisDuration())){
            Duration d = Duration.ofSeconds(thesisDuration);
            session.setDuration(d);
        }
        if(deliberationDuration != null  && !Objects.equals(deliberationDuration, session.getDeliberationDuration())){
            Duration d = Duration.ofSeconds(deliberationDuration);
            session.setDuration(d);
        }
        if(!Objects.equals(duration, session.getDuration()) && session.getThesisDuration() != null && session.getDeliberationDuration() != null ){
            session.setDuration(Duration.ofSeconds(session.getThesisDuration().getSeconds() + session.getDeliberationDuration().getSeconds()));
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

    @Override
    @Transactional
    public void updateSession(Long sessionId, Map<String, Object> request, Long formationId, Long notationGroupId) {

        Session session = sessionRepository.findById(sessionId).orElseThrow(
                () -> new IllegalStateException("The session with id " + sessionId + " does not exist")
        );

        request.forEach((key, value) -> {
            if(key =="alertDelay"){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse((CharSequence) value, formatter);
                value = date;
            }

            if(key =="deliberationDuration" || key =="thesisDuration" ){
               Duration  duration = Duration.ofSeconds((Integer) value);
                value = duration;
            }

            Field field = ReflectionUtils.findField(Session.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, session, value);
        });

        Duration totalDuration = Duration.ofSeconds((Integer) request.get("thesisDuration") + (Integer) request.get("deliberationDuration"));
        session.setDuration(totalDuration);

        if(notationGroupId != null){
            NotationGroup notationGroup = notationGroupRepository.getById(notationGroupId);
            session.setNotationGroup(notationGroup);
        }

        if(formationId != null && formationId != session.getFormation().getId() ){
            Formation formation = formationRepository.getById(formationId);
            session.setFormation(formation);
        }
    }
}
