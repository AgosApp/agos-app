package UFC.Agos.services;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Thesis;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;

public interface IThesisService {

    public List<Thesis> getThesesByCrenel(Long crenelId);

    public Thesis getThesisByCrenel(Long thesisId, Long crenelId);

    public List<Thesis> getThesesByStudent(Long studentId);

    public Thesis getThesisByStudent(Long thesisId, Long studentId);

    public List<Thesis> getThesesByProfessor(Long professorId);

    public Thesis getThesisByProfessor(Long thesisId, Long professorId);

    public void addThesis(Thesis thesis, Long crenelId, Long roomId, List<Long> professorIds, List<Long> StudentIds) throws NotFoundException;

    public void deleteThesis(Long thesisId) throws Exception;

    /*public void updateThesis(Long thesisId,
                                String title,
                                String type,
                                String time,
                                Float finalNote,
                                String summary,
                                Long crenelId,
                                Long studentThesisId
    );*/

    public void updateThesis(Long thesisId, String title, String type, String time, Float finalNote, String summary, Long crenelId, Long roomId, List<Long> professorIds, List<Long> studentIds) throws NotFoundException;

    public void update(Long thesisId, Map<String, Object> request, Long crenelId, Long roomId, List<Long> professorIds, List<Long> studentIds) throws NotFoundException;

    }
