package UFC.Agos.controlers;

import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;
import UFC.Agos.services.INotationGroupService;
import UFC.Agos.services.INotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/notations")
public class NotationController {

    @Autowired
    INotationGroupService notationGroupService;

    @Autowired
    INotationService notationService;

    @GetMapping
    public List<Notation> getNotations() {
        return notationService.getNotations();
    }

    @GetMapping(path="/{notationId}")
    public Notation getNotation(@PathVariable Long notationId){
        return notationService.getNotation(notationId);
    }

    @PostMapping
    public void saveNotation(@RequestBody Notation notation
            //, @PathVariable(required = false) Long criteriaId, @PathVariable(required = false) Long notationGroupId
                             ){
        notationService.addNotation(notation
        //        , criteriaId, notationGroupId
        );
    }

    @DeleteMapping(path = "/{notationId}")
    public  void deleteNotation(@PathVariable("notationId") Long notationId) throws Exception {
        notationService.deleteNotation(notationId);
    }
}
