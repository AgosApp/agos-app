package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.*;
import UFC.Agos.services.IThesisService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    CrenelRepository crenelRepository;

    @Override
    public List<Thesis> getThesesByCrenel(Long crenelId) {
        Crenel crenel = crenelRepository.getById(crenelId);
        return thesisRepository.getThesesByCrenel(crenel);
    }

    @Override
    public Thesis getThesisByCrenel(Long thesisId, Long crenelId) {
        Crenel crenel = crenelRepository.getById(crenelId);
        return thesisRepository.getThesisByIdAndCrenel(thesisId, crenel);
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
    public void addThesis(Thesis thesis, Long crenelId, Long roomId, List<Long> professorIds, List<Long> studentIds, List<String> typesProfessors) throws NotFoundException {

        for (Long id: professorIds
             ) {
            Professor professor = professorRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("The professor with id " + id + " does not exist")
            );
            Evaluation evaluation = new Evaluation(null, null, thesis, professor);
            //set type of professor
            evaluation.setTypeProfessor(typesProfessors.get(professorIds.indexOf(id)));
            evaluationRepository.save(evaluation);
        }

        Crenel crenel = crenelRepository.findById(crenelId).orElseThrow(
                () -> new IllegalStateException("The crenel with id " + crenelId + " does not exist")
        );
        thesis.setCrenel(crenel);

        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new IllegalStateException("The room with id " + roomId + " does not exist")
        );
        if(crenel.getRooms().contains(room)){
            thesis.setRoom(room);
        }else throw new NotFoundException("The room with id " + roomId + " does not exist in crenel with id " + crenelId);

        for (Long id : studentIds
             ) {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("The student with id " + id + " does not exist")
            );
            studentThesisRepository.save(new StudentThesis(student, thesis));
        }

        //fill in CriteriaEvaluation Table
        List<Criteria> criteriaList = criteriaRepository.getCriteriasBySession(crenel.getSession().getId());
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
    public void updateThesis(Long thesisId, String title, String type, String time, Float finalNote, String summary, Long crenelId, Long roomId, List<Long> professorIds, List<Long> studentIds, List<String> typesProfessors) throws NotFoundException {
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

        Crenel crenel = crenelRepository.getById(crenelId);
        if(crenelId != null && !Objects.equals(crenelId, thesis.getCrenel().getId())){
            thesis.setCrenel(crenel);
        }

        if(roomId != null && !Objects.equals(roomId, thesis.getRoom().getId())){
            Room room = roomRepository.getById(roomId);
            if(crenelRepository.getById(crenelId).getRooms().contains(room)){
                thesis.setRoom(room);
            }else throw new NotFoundException("The room with id " + roomId + " does not exist in crenel with id " + crenelId);
        }

        List<Criteria> criteriaList = criteriaRepository.getCriteriasBySession(crenel.getSession().getId());
        for(Criteria criteria : criteriaList) {
            if(professorIds != null) {
                //Delete old jury
                criteriaEvaluationRepository.deleteByThesis(thesisId);
                evaluationRepository.deleteByThesis(thesisId);
                for (Long id : professorIds) {
                    Professor professor = professorRepository.findById(id).orElseThrow(
                            () -> new IllegalStateException("The professor with id " + id + " does not exist")
                    );
                    Evaluation evaluation = new Evaluation(null, null, thesis, professor);
                    //set type of professor
                    evaluation.setTypeProfessor(typesProfessors.get(professorIds.indexOf(id)));
                    evaluationRepository.save(evaluation);
                    criteriaEvaluationRepository.save(new CriteriaEvaluation(professor, thesis, criteria, null));

                }
            }
        }

        //delete old students of the thesis
        if(studentIds != null) {
            studentThesisRepository.deleteByThesis(thesisId);
            for (Long id : studentIds) {
                Student student = studentRepository.findById(id).orElseThrow(
                        () -> new IllegalStateException("The student with id " + id + " does not exist")
                );
                studentThesisRepository.save(new StudentThesis(student, thesis));
            }
        }
    }

    @Override
    @Transactional
    public void update(Long thesisId, Map<String, Object> request, Long crenelId, Long roomId, List<Long> professorIds, List<Long> studentIds, List<String> typesProfessors) throws NotFoundException {
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

        Crenel crenel = crenelRepository.getById(crenelId);

        if(crenelId != null && !Objects.equals(crenelId, thesis.getCrenel().getId())){
            thesis.setCrenel(crenel);
        }

        if(roomId != null && !Objects.equals(roomId, thesis.getRoom().getId())){
            Room room = roomRepository.getById(roomId);
            if(crenelRepository.getById(crenelId).getRooms().contains(room)){
                thesis.setRoom(room);
            }else throw new NotFoundException("The room with id " + roomId + " does not exist in crenel with id " + crenelId);
        }

        List<Criteria> criteriaList = criteriaRepository.getCriteriasBySession(crenel.getSession().getId());
        for(Criteria criteria : criteriaList) {
            if(professorIds != null) {
                //Delete old jury
                criteriaEvaluationRepository.deleteByThesis(thesisId);
                evaluationRepository.deleteByThesis(thesisId);
                for (Long id : professorIds) {
                    Professor professor = professorRepository.findById(id).orElseThrow(
                            () -> new IllegalStateException("The professor with id " + id + " does not exist")
                    );
                    criteriaEvaluationRepository.save(new CriteriaEvaluation(professor, thesis, criteria, null));
                    Evaluation evaluation = new Evaluation(null, null, thesis, professor);
                    evaluation.setTypeProfessor(typesProfessors.get(professorIds.indexOf(id)));
                    evaluationRepository.save(evaluation);
                }
            }
        }

        if(studentIds != null){
            //delete old students of the thesis
            studentThesisRepository.deleteByThesis(thesisId);
        for (Long id: studentIds ) {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException("The student with id " + id + " does not exist")
            );
            studentThesisRepository.save(new StudentThesis(student, thesis));
        }
        }
        thesisRepository.save(thesis);
    }

    }
