package UFC.Agos.services.imp;

import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import UFC.Agos.models.Student;
import UFC.Agos.repositories.DepartmentRepository;
import UFC.Agos.repositories.FormationRepository;
import UFC.Agos.repositories.StudentRepository;
import UFC.Agos.services.IFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FormationService implements IFormationService {

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<Formation> getFormationsByDepartment(Long departmentId) {
        Department department = departmentService.getDepartment(departmentId);
        return formationRepository.getFormationsByDepartment(department);
    }

    @Override
    public Formation getFormationByDepartment(Long formationId, Long departmentId) {
        Department department = departmentService.getDepartment(departmentId);
        return formationRepository.getFormationByIdAndDepartment(formationId, department);
    }

    @Override
    public void addFormation(Formation formation, Long departmentId) {
        Department department = departmentService.getDepartment(departmentId);
        formation.setDepartment(department);
        formationRepository.save(formation);
    }

    @Override
    public void deleteFormation(Long formationId) throws Exception {
        boolean exists = formationRepository.existsById(formationId);
        if(!exists){
            throw new IllegalStateException("The formation with id "+ formationId + " does not exist");
        }
        Formation formation = formationRepository.getById(formationId);
        List<Student> students =  studentRepository.getStudentByFormation(formation);
            if(!students.isEmpty()){
                throw new Exception("th formation with id "+ formationId +" can't be removed because it contains students");
            }
        formationRepository.deleteById(formationId);
    }

    @Override
    @Transactional
    public void updateFormation(Long formationId, String name, String description, Long departmentId) {

        Formation formation = formationRepository.findById(formationId).orElseThrow(
                () -> new IllegalStateException("The formation with id " + formationId + " does not exist")
        );

        if(name != null && name.length()>0 && !Objects.equals(name, formation.getName())){
            formation.setName(name);
        }

        if(description != null && description.length()>0 && !Objects.equals(description, formation.getDescription())){
            formation.setDescription(description);
        }

        if(departmentId != null && departmentId != formation.getDepartment().getId() ){
            Department department = departmentRepository.getById(departmentId);
            formation.setDepartment(department);
        }
    }
}
