package UFC.Agos.services;

import UFC.Agos.models.Formation;
import UFC.Agos.models.Notation;

import java.util.List;

public interface INotationService {
    public List<Notation> getNotationsByCriteria(Long criteriaId);
}
