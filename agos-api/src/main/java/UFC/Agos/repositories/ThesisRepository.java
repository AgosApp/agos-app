package UFC.Agos.repositories;

import UFC.Agos.models.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {

}
