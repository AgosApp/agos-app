package UFC.Agos.services;

import UFC.Agos.models.Session;

import java.util.List;

public interface ISessionService {

    public List<Session> getSessionsByFormation(Long formationId);

    public Session getSessionByFormation(Long sessionId, Long formationId);

    public void addSession(Session session, Long formationId,Long notationGroupId);

    public void deleteSession(Long sessionId) throws Exception;

    public void updateSession(Long sessionId,
                              String title,
                              Integer duration,
                              String alertDelay,
                              Long notationGroupId,
                              Long formationId
    );
}
