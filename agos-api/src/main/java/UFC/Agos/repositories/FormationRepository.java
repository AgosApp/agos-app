package UFC.Agos.repositories;

import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, Long> {

    public List<Formation> getFormationsByDepartment(Department department);
    public Formation getFormationByIdAndDepartment(Long formationId, Department department);
}
