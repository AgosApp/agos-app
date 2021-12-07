package UFC.Agos.repositories;

import UFC.Agos.models.Session;
import UFC.Agos.models.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {

    public List<Thesis> getThesesBySession(Session session);
}
