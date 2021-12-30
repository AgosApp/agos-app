package UFC.Agos.services;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;

import java.util.List;

public interface INotationService {

    public List<Notation> getNotations();

    public Notation getNotation(Long notationId);

    public void addNotation(Notation notation
           // , Long criteriaId, Long notationGroupId
    );

    public void deleteNotation(Long notationId) throws Exception;

    public void updateNotation(Long notationId, int bareme, Long criteriaId, Long notationGroupId);

    public List<Notation> getNotationsByCriteria(Long criteriaId);

    public List<Notation> getNotationsByNotationGroup(Long notationGroupId);
}
