package UFC.Agos.repositories;

import UFC.Agos.models.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriteriaRepository   extends JpaRepository<Criteria, Long> {

    @Query(value = "SELECT * FROM `criteria` WHERE id IN ( SELECT criteria_id FROM notation WHERE notation_group_id IN ( SELECT notation_group_id FROM session WHERE id = ?))", nativeQuery = true)
    List<Criteria> getCriteriasBySession(Long sessionId);
}
