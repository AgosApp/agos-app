package UFC.Agos.services;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Thesis;

import java.util.List;

public interface IThesisService {

    public List<Thesis> getThesesBySession(Long sessionId);

    public Thesis getThesisBySession(Long thesisId, Long sessionId);

    public List<Thesis> getThesesByStudent(Long studentId);

    public Thesis getThesisByStudent(Long thesisId, Long studentThesisId);

    public void addThesis(Thesis thesis, Long sessionId, Long roomId, Long professorId);

    public void deleteThesis(Long thesisId) throws Exception;

    public void updateThesis(Long thesisId,
                                String title,
                                String time,
                                Float finalNote,
                                String summary,
                                Long sessionId,
                                Long studentThesisId
    );
}
