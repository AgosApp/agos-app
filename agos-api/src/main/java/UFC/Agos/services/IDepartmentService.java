package UFC.Agos.services;

import UFC.Agos.models.Department;

import java.util.List;


public interface IDepartmentService {

    public List<Department> getDepartments();

    public Department getDepartment(Long departmentId);

    public void addDepartment(Department department);

    public void deleteDepartment(Long departmentId);

    public void updateDepartment(Long departmentId,
                                String name,
                                String description
    );
}
