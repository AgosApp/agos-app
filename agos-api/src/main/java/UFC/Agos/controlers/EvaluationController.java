package UFC.Agos.controlers;

import UFC.Agos.models.Evaluation;
import UFC.Agos.services.imp.EvaluationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"API for Evaluation CRUD operations."})
//@PreAuthorize("hasAuthority('ADMIN_ROLE')")
@RestController
@RequestMapping(path = "api/")
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;

    @GetMapping(path="professors/{professorId}/evaluations")
    public Evaluation getEvaluationByThesisAndProfessor(@RequestParam Long thesisId, @PathVariable Long professorId){
        return evaluationService.getEvaluationByProfessorAndThesis(professorId, thesisId);
    }

    @GetMapping(path="theses/{thesisId}/evaluations")
    public List<Evaluation> getEvaluationsByThesis(@PathVariable Long thesisId){
        return evaluationService.getEvaluationsByThesis(thesisId);
    }

}
