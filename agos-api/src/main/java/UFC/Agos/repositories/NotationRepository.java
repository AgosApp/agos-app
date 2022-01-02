package UFC.Agos.repositories;

import UFC.Agos.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotationRepository extends JpaRepository<Notation, Long> {
    public List<Notation> getNotationsByCriteria(Criteria criteria);
    public List<Notation> getNotationsByNotationGroup(NotationGroup notationGroup);
}
