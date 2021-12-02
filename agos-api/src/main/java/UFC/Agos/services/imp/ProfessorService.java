package UFC.Agos.services.imp;

import UFC.Agos.models.*;
import UFC.Agos.repositories.CriteriaEvaluationRepository;
import UFC.Agos.repositories.DepartmentRepository;
import UFC.Agos.repositories.EvaluationRepository;
import UFC.Agos.repositories.ProfessorRepository;
import UFC.Agos.services.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ProfessorService implements IProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    CriteriaEvaluationRepository criteriaEvaluationRepository;

    @Override
    public List<Professor> getProfessorsByDepartment(Long departmentId) {
        Department department = departmentRepository.getById(departmentId);
        return professorRepository.getProfessorsByDepartment(department);
    }

    @Override
    public Professor getProfessorByDepartment(Long professorId, Long departmentId) {
        Department department = departmentRepository.getById(departmentId);
        return professorRepository.getProfessorByIdAndDepartment(professorId, department);
    }

    @Override
    public void addProfessor(Professor professor, Long departmentId) {
        Department department = departmentRepository.getById(departmentId);
        professor.setDepartment(department);
        professorRepository.save(professor);
    }

    @Override
    public void deleteProfessor(Long professorId) throws Exception {
        boolean exists = professorRepository.existsById(professorId);
        if(!exists){
            throw new IllegalStateException("The professor with id "+ professorId + " does not exist");
        }
        Professor professor = professorRepository.getById(professorId);
        List<CriteriaEvaluation> criteriaEvaluations =  criteriaEvaluationRepository.getCriteriaEvaluationsByProfessor(professor);
        Evaluation evaluation = evaluationRepository.getEvaluationByProfessor(professor);
        if(!criteriaEvaluations.isEmpty() || evaluation != null){
            throw new Exception("th professor with id "+ professorId +" can't be removed because he has evaluation or criteriaEvaluations ");
        }
        professorRepository.deleteById(professorId);

    }

    @Override
    @Transactional
    public void updateProfessor(Long professorId, String firstName, String lastName, String login, String abbreviation, boolean isAdmin, Long departmentId) {

        Professor professor = professorRepository.findById(professorId).orElseThrow(
                () -> new IllegalStateException("The professor with id " + professorId + " does not exist")
        );

        if(lastName != null && lastName.length()>0 && !Objects.equals(lastName, professor.getLastName())){
            professor.setLastName(lastName);
        }

        if(firstName != null && firstName.length()>0 && !Objects.equals(firstName, professor.getFirstName())){
            professor.setFirstName(firstName);
        }

        if(login != null && login.length()>0 && !Objects.equals(login, professor.getLogin())){
            professor.setLogin(login);
        }

        if(abbreviation != null && abbreviation.length()>0 && !Objects.equals(abbreviation, professor.getAbbreviation())){
            professor.setAbbreviation(abbreviation);
        }

        if(!Objects.equals(isAdmin, professor.isAdmin())){
            professor.setAdmin(isAdmin);
        }

        if(departmentId != null && departmentId != professor.getDepartment().getId() ){
            Department department = departmentRepository.getById(departmentId);
            professor.setDepartment(department);
        }
    }
}
