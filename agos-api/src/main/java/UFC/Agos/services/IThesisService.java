package UFC.Agos.services;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Thesis;

import java.util.List;
import java.util.Map;

public interface IThesisService {

    public List<Thesis> getThesesBySession(Long sessionId);

    public Thesis getThesisBySession(Long thesisId, Long sessionId);

    public List<Thesis> getThesesByStudent(Long studentId);

    public Thesis getThesisByStudent(Long thesisId, Long studentId);

    public List<Thesis> getThesesByProfessor(Long professorId);

    public Thesis getThesisByProfessor(Long thesisId, Long professorId);

    public void addThesis(Thesis thesis, Long sessionId, Long roomId, List<Long> professorIds, List<Long> StudentIds);

    public void deleteThesis(Long thesisId) throws Exception;

    /*public void updateThesis(Long thesisId,
                                String title,
                                String type,
                                String time,
                                Float finalNote,
                                String summary,
                                Long sessionId,
                                Long studentThesisId
    );*/

    public void updateThesis(Long thesisId, String title, String type, String time, Float finalNote, String summary, Long sessionId, Long roomId, List<Long> professorIds, List<Long> studentIds);

    public void update(Long thesisId, Map<String, Object> request, Long sessionId, Long roomId, List<Long> professorIds, List<Long> studentIds);

    }
