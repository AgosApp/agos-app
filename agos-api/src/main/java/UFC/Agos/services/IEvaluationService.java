package UFC.Agos.services;

import UFC.Agos.models.Evaluation;

import java.util.List;

public interface IEvaluationService {

    Evaluation getEvaluationByProfessorAndThesis(Long professorId, Long thesisId);

    List<Evaluation> getEvaluationsByThesis(Long thesisId);

    List<Evaluation> getEvaluationsByProfessor(Long professorId);




}
