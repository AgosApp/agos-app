package UFC.Agos.repositories;

import UFC.Agos.models.Department;
import UFC.Agos.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

     List<Professor> getProfessorsByDepartment(Department department);
     Professor getProfessorByIdAndDepartment(Long professorId, Department department);
     Professor findByUsername(String username);

}
