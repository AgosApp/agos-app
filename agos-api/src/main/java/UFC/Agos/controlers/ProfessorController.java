package UFC.Agos.controlers;

import UFC.Agos.models.Professor;
import UFC.Agos.services.IDepartmentService;
import UFC.Agos.services.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/departments/{departmentId}/professors")
public class ProfessorController {

    @Autowired
    IProfessorService professorService;

    @Autowired
    IDepartmentService departmentService;

    @GetMapping
    public List<Professor> getAll(@PathVariable Long departmentId){
        return professorService.getProfessorsByDepartment(departmentId);
    }

    @GetMapping(path = "/{professorId}")
    public Professor getOne(@PathVariable Long professorId,@PathVariable Long departmentId){
        return professorService.getProfessorByDepartment(professorId, departmentId);
    }

    @PostMapping
    public void save(@PathVariable(required = false) Long departmentId,
                     @RequestBody Professor professor){
        professorService.addProfessor(professor, departmentId);
    }

    @DeleteMapping(path = "/{professorId}")
    public void delete(@PathVariable Long professorId, @PathVariable Long departmentId) throws Exception {
        professorService.deleteProfessor(professorId);
    }

    @PutMapping(path = "/{professorId}")
    public void update(@PathVariable Long departmentId,
                       @PathVariable Long professorId,
                       @RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String login,
                       @RequestParam(required = false) boolean isAdmin
                       ){
        professorService.updateProfessor(professorId, firstName, lastName, login, isAdmin, departmentId);
    }
}
