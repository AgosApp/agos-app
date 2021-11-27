package UFC.Agos.controlers;


import UFC.Agos.models.Department;
import UFC.Agos.models.Formation;
import UFC.Agos.services.IDepartmentService;
import UFC.Agos.services.IFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/departments/{departmentId}/formations")
public class FormationController {

    @Autowired
    IFormationService formationService;

    @Autowired
    IDepartmentService departmentService;

    @GetMapping
    public List<Formation> getAll(@PathVariable Long departmentId){
        return formationService.getFormationsByDepartment(departmentId);
    }

    @GetMapping(path = "/{formationId}")
    public Formation getOne(@PathVariable Long formationId,@PathVariable Long departmentId){
        return formationService.getFormationByDepartment(formationId, departmentId);
    }

    @PostMapping
    public void save(@PathVariable(required = false) Long departmentId,
                              @RequestBody Formation formation){
        formationService.addFormation(formation, departmentId);
    }

    @DeleteMapping(path = "/{formationId}")
    public void delete(@PathVariable Long formationId, @PathVariable Long departmentId) throws Exception {
        formationService.deleteFormation(formationId);
    }

    @PutMapping(path = "/{formationId}")
    public void update(@PathVariable Long formationId,
                       @PathVariable(required = false) Long departmentId,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String description
                                ){
        formationService.updateFormation(formationId, name, description, departmentId);
    }
}
