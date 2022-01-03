package UFC.Agos.controlers;

import UFC.Agos.models.Department;
import UFC.Agos.services.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"API for Departments CRUD operations."})
//@PreAuthorize("hasAuthority('ADMIN_ROLE')")
@RequestMapping(path = "/api/departments")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @ApiOperation(value = "Get Departments")
    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @ApiOperation(value = "Get Department")
    @GetMapping(path="/{departmentId}")
    public Department getDepartment(@PathVariable Long departmentId){
        return departmentService.getDepartment(departmentId);
    }

    @ApiOperation(value = "Add Department")
    @PostMapping
    public void saveDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
    }

    @ApiOperation(value = "Delete Department")
    @DeleteMapping(path = "/{departmentId}")
    public  void deleteDepartment(@PathVariable("departmentId") Long departmentId) throws Exception {
        departmentService.deleteDepartment(departmentId);
    }

    @ApiOperation(value = "Update Department")
    @PutMapping(path = "/{departmentId}")
    public void updateDepartment(@PathVariable("departmentId") Long departmentId,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String description){
        departmentService.updateDepartment(departmentId, name, description);
    }

}
