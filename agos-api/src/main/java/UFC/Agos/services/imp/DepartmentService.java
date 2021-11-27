package UFC.Agos.services.imp;

import UFC.Agos.models.Department;
import UFC.Agos.repositories.DepartmentRepository;
import UFC.Agos.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(
                ()-> new IllegalStateException("The department with id "+ departmentId + " does not exist")
        );
    }

    @Override
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);

        if(!exists){
            throw new IllegalStateException("The student with id "+ departmentId + " does not exist");
        }
        departmentRepository.deleteById(departmentId);
    }

    @Override
    @Transactional
    public void updateDepartment(
            Long departmentId,
            String name,
            String description) {

        Department department = departmentRepository.findById(departmentId).orElseThrow(
                ()-> new IllegalStateException("The department with id "+ departmentId + " does not exist")
        );

        if(name != null && name.length()>0 && !Objects.equals(name, department.getName())){
            department.setName(name);
        }

        if(description != null && description.length()>0 && !Objects.equals(description, department.getDescription())){
            department.setDescription(description);
        }
    }
}
