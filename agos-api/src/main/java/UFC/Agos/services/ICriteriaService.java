package UFC.Agos.services;

import UFC.Agos.models.Criteria;

import java.util.List;

public interface ICriteriaService {

    public List<Criteria> getCriterias();

    public Criteria getCriteria(Long criteriaId);

    public void addCriteria(Criteria criteria);

    public void deleteCriteria(Long criteriaId) throws Exception;

    public void updateCriteria(Long criteriaId, String title);

}
