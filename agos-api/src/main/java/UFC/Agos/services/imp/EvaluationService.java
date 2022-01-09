package UFC.Agos.services.imp;

import UFC.Agos.models.Evaluation;
import UFC.Agos.models.Professor;
import UFC.Agos.models.Thesis;
import UFC.Agos.repositories.EvaluationRepository;
import UFC.Agos.repositories.ProfessorRepository;
import UFC.Agos.repositories.ThesisRepository;
import UFC.Agos.services.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService implements IEvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ThesisRepository thesisRepository;


    @Override
    public Evaluation getEvaluationByProfessorAndThesis(Long professorId, Long thesisId) {
        Professor professor = professorRepository.getById(professorId);
        Thesis thesis = thesisRepository.getById(thesisId);
        return evaluationRepository.getEvaluationByProfessorAndThesis(professor, thesis);
    }

    @Override
    public List<Evaluation> getEvaluationsByThesis(Long thesisId) {
        Thesis thesis = thesisRepository.getById(thesisId);
        return evaluationRepository.getEvaluationsByThesis(thesis);
    }

    @Override
    public List<Evaluation> getEvaluationsByProfessor(Long professorId) {
        Professor professor = professorRepository.getById(professorId);
        return evaluationRepository.getEvaluationsByProfessor(professor);
    }
}
