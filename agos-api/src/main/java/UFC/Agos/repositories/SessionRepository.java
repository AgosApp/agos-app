package UFC.Agos.repositories;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    public List<Session> getSessionsByFormation(Formation formation);
    public Session getSessionByIdAndFormation(Long sessionId, Formation formation);

}
