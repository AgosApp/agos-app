package UFC.Agos.controlers;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Department;
import UFC.Agos.services.ICriteriaService;
import UFC.Agos.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/criterias")
public class CriteriaController {

    @Autowired
    ICriteriaService criteriaService;

    @GetMapping
    public List<Criteria> getCriterias() {
        return criteriaService.getCriterias();
    }

    @GetMapping(path="/{criteriaId}")
    public Criteria getCriteria(@PathVariable Long criteriaId){
        return criteriaService.getCriteria(criteriaId);
    }

    @PostMapping
    public void saveCriteria(@RequestBody Criteria criteria){
        criteriaService.addCriteria(criteria);
    }

    @DeleteMapping(path = "/{criteriaId}")
    public  void deleteCriteria(@PathVariable("criteriaId") Long criteriaId) throws Exception {
        criteriaService.deleteCriteria(criteriaId);
    }

    @PutMapping(path = "/{criteriaId}")
    public void updateCriteria(@PathVariable("criteriaId") Long criteriaId,
                                 @RequestParam(required = false) String title){
        criteriaService.updateCriteria(criteriaId, title);
    }
}
