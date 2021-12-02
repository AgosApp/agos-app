package UFC.Agos.repositories;

import UFC.Agos.models.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaRepository   extends JpaRepository<Criteria, Long> {
    Criteria findById(long criteriaId);
}
