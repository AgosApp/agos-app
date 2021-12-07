package UFC.Agos.controlers;

import UFC.Agos.models.CriteriaEvaluation;
import UFC.Agos.models.Formation;
import UFC.Agos.services.ICriteriaEvaluationService;
import UFC.Agos.services.imp.CriteriaEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/criteria_evaluations")
public class CriteriaEvaluationController {

    @Autowired
    ICriteriaEvaluationService criteriaEvaluationService;

}
