package UFC.Agos.services;

import UFC.Agos.models.Formation;

import java.util.List;

public interface IFormationService {

    public List<Formation> getFormationsByDepartment(Long departmentId);

    public Formation getFormationByDepartment(Long FormationId, Long departmentId);

    public void addFormation(Formation formation, Long departmentId);

    public void deleteFormation(Long formationId) throws Exception;

    public void updateFormation(Long formationId,
                                String name,
                                String description,
                                Long departmentId
                              );
}
