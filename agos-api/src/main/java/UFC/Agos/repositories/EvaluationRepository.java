package UFC.Agos.repositories;

import UFC.Agos.models.Evaluation;
import UFC.Agos.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    public Evaluation getEvaluationByProfessor(Professor professor);

}
