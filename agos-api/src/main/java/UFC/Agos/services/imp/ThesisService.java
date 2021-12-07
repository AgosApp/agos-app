package UFC.Agos.services.imp;

import UFC.Agos.models.Thesis;
import UFC.Agos.repositories.ThesisRepository;
import UFC.Agos.services.IThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisService implements IThesisService {

    @Autowired
    ThesisRepository thesisRepository;



    @Override
    public List<Thesis> getThesesBySession(Long sessionId) {
        return null;
    }

    @Override
    public Thesis getThesisBySession(Long thesisId, Long sessionId) {
        return null;
    }

    @Override
    public List<Thesis> getThesesByStudentThesis(Long studentThesisId) {
        return null;
    }

    @Override
    public Thesis getThesisByStudentThesis(Long thesisId, Long studentThesisId) {
        return null;
    }

    @Override
    public void addThesis(Thesis thesis, Long sessionId, Long studentThesisId) {

    }

    @Override
    public void deleteThesis(Long thesisId) throws Exception {

    }

    @Override
    public void updateThesis(Long thesisId, String title, String time, Float finalNote, String summary, Long sessionId, Long studentThesisId) {

    }
}
