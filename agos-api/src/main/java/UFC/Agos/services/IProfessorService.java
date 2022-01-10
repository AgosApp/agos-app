package UFC.Agos.services;

import UFC.Agos.models.Professor;
import UFC.Agos.models.Student;

import java.util.List;

public interface IProfessorService {

    public List<Professor> getProfessorsByDepartment(Long departmentId);

    public Professor getProfessorByDepartment(Long professorId, Long departmentId);

    public void addProfessor(Professor professor, Long departmentId) throws Exception;

    public void deleteProfessor(Long professorId) throws Exception;

    public void updateProfessor(Long professorId,
                                String firstName,
                                String lastName,
                                String username,
                                String password,
                                boolean isAdmin,
                                Long departmentId
    ) throws Exception;

    void addRoleToProfessor(String username, String roleName);

    Professor findProfessorByUsername(String username);

}
