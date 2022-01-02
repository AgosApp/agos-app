package UFC.Agos.repositories;


import UFC.Agos.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import UFC.Agos.models.CriteriaEvaluation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaEvaluationRepository extends JpaRepository<CriteriaEvaluation, Long> {

     List<CriteriaEvaluation> getCriteriaEvaluationsByCriteria(Criteria criteria);
     List<CriteriaEvaluation> getCriteriaEvaluationsByProfessor(Professor professor);
     List<CriteriaEvaluation> getCriteriaEvaluationsByThesis(Thesis thesis);

     @Modifying
     @Query(value = "DELETE from criteria_evaluation WHERE thesis_id = ?", nativeQuery = true)
     void deleteByThesis(Long thesisId);


}
