package UFC.Agos.controlers;

import UFC.Agos.models.Crenel;
import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Formation;
import UFC.Agos.services.ICriteriaEvaluationService;
import UFC.Agos.services.imp.CriteriaEvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"API for CriteriaEvaluation CRUD operations."})
@RestController
@RequestMapping(path="/api/")
public class CriteriaEvaluationController {

    @Autowired
    ICriteriaEvaluationService criteriaEvaluationService;

    @ApiOperation(value = "Get Evaluations by Thesis")
    @GetMapping(path="theses/{thesisId}/criteriaEvaluations")
    public List<CriteriaEvaluation> getAll(@PathVariable Long thesisId){
        return criteriaEvaluationService.getCriteriaEvaluationsByThesis(thesisId);
    }

}
