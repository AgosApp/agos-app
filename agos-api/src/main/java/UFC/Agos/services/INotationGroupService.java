package UFC.Agos.services;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface INotationGroupService {

    public List<NotationGroup> getNotationGroups();

    public NotationGroup getNotationGroup(Long notationGroupId);

    public void addNotationGroup(NotationGroup notationGroup //, List<Notation> notations
                                  );

    public void deleteNotationGroup(Long notationGroupId) throws Exception;

    public void updateNotationGroup(NotationGroup notationGroup, Long id);
}
