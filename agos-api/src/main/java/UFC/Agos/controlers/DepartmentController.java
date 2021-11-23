package UFC.Agos.controlers;

import UFC.Agos.models.Department;
import UFC.Agos.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping(path="/{departmentId}")
    public Optional<Department> getDepartment(@PathVariable Long departmentId){
        return departmentService.getDepartment(departmentId);
    }

    @PostMapping
    public void saveDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
    }

    @DeleteMapping(path = "{departmentId}")
    public  void deleteDepartment(@PathVariable("departmentId") Long departmentId){
        departmentService.deleteDepartment(departmentId);
    }

    @PutMapping(path = "{departmentId}")
    public void updateDepartment(@PathVariable("departmentId") Long departmentId,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String description){
        departmentService.updateDepartment(departmentId, name, description);
    }

}
