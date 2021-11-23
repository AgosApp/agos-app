package UFC.Agos.services;

import UFC.Agos.models.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    public List<Department> getDepartments();

    public Optional<Department> getDepartment(Long departmentId);

    public void addDepartment(Department department);

    public void deleteDepartment(Long departmentId);

    public void updateDepartment(Long departmentId,
                                String name,
                                String description
    );
}
