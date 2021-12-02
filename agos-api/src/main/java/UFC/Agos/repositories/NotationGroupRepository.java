package UFC.Agos.repositories;

import UFC.Agos.models.NotationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotationGroupRepository extends JpaRepository<NotationGroup, Long> {
}
