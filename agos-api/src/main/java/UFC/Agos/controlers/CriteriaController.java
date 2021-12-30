package UFC.Agos.controlers;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Department;
import UFC.Agos.services.ICriteriaService;
import UFC.Agos.services.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = {"API for Criterias CRUD operations."})
@RequestMapping(path="api/criterias")
public class CriteriaController {

    @Autowired
    ICriteriaService criteriaService;

    @ApiOperation(value = "Get Criterias")
    @GetMapping
    public List<Criteria> getCriterias() {
        return criteriaService.getCriterias();
    }

    @ApiOperation(value = "Get Criteria")
    @GetMapping(path="/{criteriaId}")
    public Criteria getCriteria(@PathVariable Long criteriaId){
        return criteriaService.getCriteria(criteriaId);
    }

    @ApiOperation(value = "Add Criteria")
    @PostMapping
    public void saveCriteria(@RequestBody Criteria criteria){
        criteriaService.addCriteria(criteria);
    }

    @ApiOperation(value = "Delete Criteria")
    @DeleteMapping(path = "/{criteriaId}")
    public  void deleteCriteria(@PathVariable("criteriaId") Long criteriaId) throws Exception {
        criteriaService.deleteCriteria(criteriaId);
    }

    @ApiOperation(value = "Update Criterias")
    @PutMapping(path = "/{criteriaId}")
    public void updateCriteria(@PathVariable("criteriaId") Long criteriaId,
                                 @RequestParam(required = false) String title){
        criteriaService.updateCriteria(criteriaId, title);
    }
}
