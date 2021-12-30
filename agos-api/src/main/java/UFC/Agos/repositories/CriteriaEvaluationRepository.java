package UFC.Agos.repositories;


import UFC.Agos.models.Criteria;
import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaEvaluationRepository extends JpaRepository<CriteriaEvaluation, Long> {

    public List<CriteriaEvaluation> getCriteriaEvaluationsByCriteria(Criteria criteria);
    public List<CriteriaEvaluation> getCriteriaEvaluationsByProfessor(Professor professor);

}
