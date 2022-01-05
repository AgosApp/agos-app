package UFC.Agos.services;

import UFC.Agos.models.Session;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface ISessionService {

    public List<Session> getSessionsByFormation(Long formationId);

    public Session getSessionByFormation(Long sessionId, Long formationId);

    public void addSession(Session session, Long formationId,Long notationGroupId);

    public void deleteSession(Long sessionId) throws Exception;

    public void updateSession(Long sessionId,
                              String title,
                              Integer duration,
                              Integer thesisDuration,
                              Integer deliberationDuration,
                              String alertDelay,
                              Long notationGroupId,
                              Long formationId
    );

    public void updateSession(Long sessionId, Map<String, Object> request, Long formationId, Long notationGroupId);
}
