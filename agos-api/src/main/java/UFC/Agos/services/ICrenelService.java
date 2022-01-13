package UFC.Agos.services;

import UFC.Agos.models.Crenel;

import java.util.List;
import java.util.Map;

public interface ICrenelService {

    public List<Crenel> getCreneauxBySession(Long sessionId);

    public Crenel getCrenelBySession(Long crenelId, Long sessionIf);

    public void addCrenel(Crenel crenel, Long sessionId, List<Long> roomsIds);

    public void deleteCrenel(Long crenelId) throws Exception;

    public void updateCrenel(Long crenelId, Map<String, Object> request, Long sessionId, List<Long> roomsIds);

    }
