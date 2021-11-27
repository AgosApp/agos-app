package UFC.Agos.services;

import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import UFC.Agos.models.Student;

import java.util.List;
import java.util.Optional;

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
