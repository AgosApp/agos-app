package UFC.Agos.controlers;

import UFC.Agos.models.Professor;
import UFC.Agos.Dto.RoleToProfessorForm;
import UFC.Agos.services.IDepartmentService;
import UFC.Agos.services.IProfessorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"API for Professors CRUD operations."})
//@PreAuthorize("hasAuthority('ADMIN_ROLE')")
@RequestMapping(path="/api/")
public class ProfessorController {

    @Autowired
    IProfessorService professorService;

    @Autowired
    IDepartmentService departmentService;

    @ApiOperation(value = "Get All Professors")
    @GetMapping(path ="professors")
    public List<Professor> getAll(){
        return professorService.getAllProfessors();
    }

    @ApiOperation(value = "Get Professors by Department")
    @GetMapping(path ="departments/{departmentId}/professors")
    public List<Professor> getAll(@PathVariable Long departmentId){
        return professorService.getProfessorsByDepartment(departmentId);
    }

    @ApiOperation(value = "Get Professor by Department")
    @GetMapping(path = "departments/{departmentId}/professors/{professorId}")
    public Professor getOne(@PathVariable Long professorId,@PathVariable Long departmentId){
        return professorService.getProfessorByDepartment(professorId, departmentId);
    }

    @ApiOperation(value = "Add Professors in a Department")
    @PostMapping(path = "departments/{departmentId}/professors")
    public void save(@PathVariable(required = false) Long departmentId,
                     @RequestBody Professor professor) throws Exception {
        professorService.addProfessor(professor, departmentId);
    }

    @ApiOperation(value = "Delete Professor")
    @DeleteMapping(path = "departments/{departmentId}/professors/{professorId}")
    public void delete(@PathVariable Long professorId, @PathVariable Long departmentId) throws Exception {
        professorService.deleteProfessor(professorId);
    }

    @ApiOperation(value = "Update Professor")
    @PutMapping(path = "departments/{departmentId}/professors/{professorId}")
    public void update(@PathVariable Long departmentId,
                       @PathVariable Long professorId,
                       @RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String username,
                       @RequestParam(required = false) String password,
                       @RequestParam(required = false) boolean isAdmin
                       ) throws Exception {
        professorService.updateProfessor(professorId, firstName, lastName, username, password, isAdmin, departmentId);
    }
}

