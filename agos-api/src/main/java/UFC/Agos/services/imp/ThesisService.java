package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.IThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThesisService implements IThesisService {

    @Autowired
    ThesisRepository thesisRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    RoomRepository roomRepository;



    @Override
    public List<Thesis> getThesesBySession(Long sessionId) {
        Session session = sessionRepository.getById(sessionId);
        return thesisRepository.getThesesBySession(session);
    }

    @Override
    public Thesis getThesisBySession(Long thesisId, Long sessionId) {
        Session session = sessionRepository.getById(sessionId);
        return thesisRepository.getThesisByIdAndSession(thesisId, session);
    }

    @Override
    public List<Thesis> getThesesByStudent(Long studentId) {
        return thesisRepository.findThesesByStudent(studentId);
    }

    @Override
    public Thesis getThesisByStudent(Long thesisId, Long studentId) {
        return thesisRepository.findThesisByIdAndStudent(thesisId, studentId);
    }

    @Override
    public void addThesis(Thesis thesis, Long sessionId, Long roomId, Long professorId) {
        Professor professor = professorRepository.getById(professorId);
        Evaluation evaluation = new Evaluation(null, null, thesis, professor);
        evaluationRepository.save(evaluation);

        Session session = sessionRepository.getById(sessionId);
        thesis.setSession(session);

        Room room = roomRepository.getById(roomId);
        thesis.setRoom(room);

        thesisRepository.save(thesis);
    }

    @Override
    public void deleteThesis(Long thesisId) throws Exception {

    }

    @Override
    public void updateThesis(Long thesisId, String title, String time, Float finalNote, String summary, Long sessionId, Long studentThesisId) {

    }
}
