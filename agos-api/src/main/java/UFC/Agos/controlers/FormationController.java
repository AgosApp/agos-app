package UFC.Agos.controlers;


import UFC.Agos.models.Formation;
import UFC.Agos.services.IDepartmentService;
import UFC.Agos.services.IFormationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"API for Formations CRUD operations."})
@PreAuthorize("hasAuthority('ADMIN_ROLE')")
@RequestMapping(path="/api/departments/{departmentId}/formations")
public class FormationController {

    @Autowired
    IFormationService formationService;

    @Autowired
    IDepartmentService departmentService;

    @ApiOperation(value = "Get Formations by Department")
    @GetMapping
    public List<Formation> getAll(@PathVariable Long departmentId){
        return formationService.getFormationsByDepartment(departmentId);
    }

    @ApiOperation(value = "Get Formation by Department")
    @GetMapping(path = "/{formationId}")
    public Formation getOne(@PathVariable Long formationId,@PathVariable Long departmentId){
        return formationService.getFormationByDepartment(formationId, departmentId);
    }

    @ApiOperation(value = "Add Formation in a Department")
    @PostMapping
    public void save(@PathVariable(required = false) Long departmentId,
                              @RequestBody Formation formation){
        formationService.addFormation(formation, departmentId);
    }

    @ApiOperation(value = "Delete Formation")
    @DeleteMapping(path = "/{formationId}")
    public void delete(@PathVariable Long formationId, @PathVariable Long departmentId) throws Exception {
        formationService.deleteFormation(formationId);
    }

    @ApiOperation(value = "Update Formation")
    @PutMapping(path = "/{formationId}")
    public void update(@PathVariable Long formationId,
                       @PathVariable(required = false) Long departmentId,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String description
                                ){
        formationService.updateFormation(formationId, name, description, departmentId);
    }
}
