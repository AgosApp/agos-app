package UFC.Agos.controlers;

import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;
import UFC.Agos.services.INotationGroupService;
import UFC.Agos.services.INotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/")
//@PreAuthorize("hasAuthority('ADMIN_ROLE')"+ "|| hasAuthority('PROF_ROLE')")
public class NotationController {

    @Autowired
    INotationGroupService notationGroupService;

    @Autowired
    INotationService notationService;

    @GetMapping(path="notations")
    public List<Notation> getNotations() {
        return notationService.getNotations();
    }

    @GetMapping(path="notationGroups/{notationGroupId}/notations")
    public List<Notation> getNotationsByNotationGroup(@PathVariable Long notationGroupId) {
        return notationService.getNotationsByNotationGroup(notationGroupId);
    }

    @GetMapping(path="notations/{notationId}")
    public Notation getNotation(@PathVariable Long notationId){
        return notationService.getNotation(notationId);
    }

    @PostMapping(path="notations")
    public void saveNotation(@RequestBody Notation notation
            //, @PathVariable(required = false) Long criteriaId, @PathVariable(required = false) Long notationGroupId
                             ){
        notationService.addNotation(notation
        //        , criteriaId, notationGroupId
        );
    }

    @DeleteMapping(path = "notations/{notationId}")
    public  void deleteNotation(@PathVariable("notationId") Long notationId) throws Exception {
        notationService.deleteNotation(notationId);
    }
}
