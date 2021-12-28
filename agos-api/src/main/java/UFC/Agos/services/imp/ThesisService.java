package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.IThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    StudentRepository studentRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    StudentThesisRepository studentThesisRepository;

    @Autowired
    CriteriaRepository criteriaRepository;

    @Autowired
    CriteriaEvaluationRepository criteriaEvaluationRepository;


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
    public List<Thesis> getThesesByProfessor(Long professorId) {
        return thesisRepository.findThesesByProfessor(professorId);
    }

    @Override
    public Thesis getThesisByProfessor(Long thesisId, Long professorId) {
        return thesisRepository.findThesisByIdAndProfessor(thesisId, professorId);
    }

    @Override
    public void addThesis(Thesis thesis, Long sessionId, Long roomId, List<Long> professorIds, List<Long> studentIds){

        for (Long id: professorIds
             ) {
            Professor professor = professorRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("The professor with id " + id + " does not exist")
            );
            Evaluation evaluation = new Evaluation(null, null, thesis, professor);
            evaluationRepository.save(evaluation);
        }

        Session session = sessionRepository.findById(sessionId).orElseThrow(
                () -> new IllegalStateException("The session with id " + sessionId + " does not exist")
        );
        thesis.setSession(session);

        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new IllegalStateException("The room with id " + roomId + " does not exist")
        );
        thesis.setRoom(room);

        for (Long id : studentIds
             ) {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("The student with id " + id + " does not exist")
            );
            studentThesisRepository.save(new StudentThesis(student, thesis));
        }

        //fill in CriteriaEvaluation Table
        List<Criteria> criteriaList = criteriaRepository.getCriteriasBySession(sessionId);

        for (Criteria criteria : criteriaList ) {

            for (Long id: professorIds ) {
                Professor professor = professorRepository.findById(id).orElseThrow(
                        () -> new IllegalStateException("The professor with id " + id + " does not exist")
                );
                criteriaEvaluationRepository.save(new CriteriaEvaluation(professor, thesis, criteria,null));
            }
        }
        thesisRepository.save(thesis);
    }

    @Override
    public void deleteThesis(Long thesisId) throws Exception {
        boolean exists = thesisRepository.existsById(thesisId);
        if(!exists){
            throw new IllegalStateException("The thesis with id "+ thesisId + " does not exist");
        }
        Thesis thesis = thesisRepository.getById(thesisId);
        List<StudentThesis> studentTheses =  studentThesisRepository.getStudentThesesByThesis(thesis);
        List<Evaluation> evaluations =  evaluationRepository.getEvaluationsByThesis(thesis);
        List<CriteriaEvaluation> criteriaEvaluations =  criteriaEvaluationRepository.getCriteriaEvaluationsByThesis(thesis);

        if(!studentTheses.isEmpty() || !evaluations.isEmpty() || !criteriaEvaluations.isEmpty()){
            throw new Exception("th thesis with id "+ thesisId +" can't be removed because it has children");
        }
        thesisRepository.deleteById(thesisId);
    }

    @Override
    @Transactional
    public void updateThesis(Long thesisId, String title, String type, String time, Float finalNote, String summary, Long sessionId, Long roomId, List<Long> professorIds, List<Long> studentIds) {
        Thesis thesis = thesisRepository.findById(thesisId).orElseThrow(
                () -> new IllegalStateException("The thesis with id " + thesisId + " does not exist")
        );

        if(title != null && title.length()>0 && !Objects.equals(title, thesis.getTitle())){
            thesis.setTitle(title);
        }

        if(type != null && type.length()>0 && !Objects.equals(type, thesis.getType())){
            thesis.setType(type);
        }

        if(finalNote != null && !Objects.equals(finalNote, thesis.getFinalNote())){
            thesis.setFinalNote(finalNote);
        }

        if(summary != null && summary.length()>0  && !Objects.equals(summary, thesis.getSummary())){
            thesis.setSummary(summary);
        }

        if(time != null && !Objects.equals(time, thesis.getTime())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime thesisTime = LocalDateTime.parse(time, formatter);
            if(!thesisTime.isBefore(LocalDateTime.now()))     thesis.setTime(thesisTime);
        }

        if(sessionId != null && !Objects.equals(sessionId, thesis.getSession().getId())){
            Session session = sessionRepository.getById(sessionId);
            thesis.setSession(session);
        }

        if(roomId != null && !Objects.equals(roomId, thesis.getRoom().getId())){
            Room room = roomRepository.getById(roomId);
            thesis.setRoom(room);
        }

        List<Criteria> criteriaList = criteriaRepository.getCriteriasBySession(sessionId);
        for(Criteria criteria : criteriaList) {
            //Delete old jury
            criteriaEvaluationRepository.deleteByThesis(thesisId);
            evaluationRepository.deleteByThesis(thesisId);
            for (Long id : professorIds) {
                Professor professor = professorRepository.findById(id).orElseThrow(
                        () -> new IllegalStateException("The professor with id " + id + " does not exist")
                );
                evaluationRepository.save(new Evaluation(null, null, thesis, professor));
                criteriaEvaluationRepository.save(new CriteriaEvaluation(professor, thesis, criteria, null));

            }
        }

        //delete old students of the thesis
        studentThesisRepository.deleteByThesis(thesisId);
        for (Long id: studentIds ) {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("The student with id " + id + " does not exist")
            );
            studentThesisRepository.save(new StudentThesis(student, thesis));
        }
    }

    @Override
    @Transactional
    public void update(Long thesisId, Map<String, Object> request, Long sessionId, Long roomId, List<Long> professorIds, List<Long> studentIds){
        Thesis thesis = thesisRepository.findById(thesisId).orElseThrow(
                () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "thesis not found");
                }
        );

        request.forEach((key, value) -> {
            // convert the time coming from the request to date
            if(key =="time"){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse((CharSequence) value, formatter);
                value = dateTime;
            }

            Field field = ReflectionUtils.findField(Thesis.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, thesis, value);
        });

        if(sessionId != null && !Objects.equals(sessionId, thesis.getSession().getId())){
            Session session = sessionRepository.getById(sessionId);
            thesis.setSession(session);
        }

        if(roomId != null && !Objects.equals(roomId, thesis.getRoom().getId())){
            Room room = roomRepository.getById(roomId);
            thesis.setRoom(room);
        }

        List<Criteria> criteriaList = criteriaRepository.getCriteriasBySession(sessionId);
        for(Criteria criteria : criteriaList) {
            //Delete old jury
            criteriaEvaluationRepository.deleteByThesis(thesisId);
            evaluationRepository.deleteByThesis(thesisId);
            for (Long id : professorIds) {
                Professor professor = professorRepository.findById(id).orElseThrow(
                        () -> new IllegalStateException("The professor with id " + id + " does not exist")
                );
                criteriaEvaluationRepository.save(new CriteriaEvaluation(professor, thesis, criteria, null));
                evaluationRepository.save(new Evaluation(null, null, thesis, professor));
            }
        }

        //delete old students of the thesis
        studentThesisRepository.deleteByThesis(thesisId);
        for (Long id: studentIds ) {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("The student with id " + id + " does not exist")
            );
            studentThesisRepository.save(new StudentThesis(student, thesis));
        }

        thesisRepository.save(thesis);
    }

    }
