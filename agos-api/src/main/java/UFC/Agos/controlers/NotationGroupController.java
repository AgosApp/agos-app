package UFC.Agos.controlers;


import UFC.Agos.models.Criteria;
import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;
import UFC.Agos.services.ICriteriaService;
import UFC.Agos.services.INotationGroupService;
import UFC.Agos.services.INotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = {"API for NotationGroup CRUD operations."})
@RestController
@RequestMapping(path="api/notationGroups")
public class NotationGroupController {

    @Autowired
    INotationGroupService notationGroupService;

    @Autowired
    INotationService notationService;

    @GetMapping
    public List<NotationGroup> getNotationGroups() {
        return notationGroupService.getNotationGroups();
    }

    @GetMapping(path="/{notationGroupId}")
    public NotationGroup getNotationGroup(@PathVariable Long notationGroupId){
        return notationGroupService.getNotationGroup(notationGroupId);
    }


    @PostMapping
    public void saveNotationGroup(@RequestBody NotationGroup notationGroup){
        System.out.println(notationGroup);
         notationGroupService.addNotationGroup(notationGroup);

    }

    @PutMapping(path="/{notationGroupId}")
    public void updateNotationGroup(@RequestBody NotationGroup notationGroup, @PathVariable Long notationGroupId){
        System.out.println("id is : "+notationGroupId);
        System.out.println("object in controller is : "+notationGroup);
        notationGroupService.updateNotationGroup(notationGroup, notationGroupId);

    }

    @DeleteMapping(path = "/{notationGroupId}")
    public void delete(@PathVariable Long notationGroupId) throws Exception {
        notationGroupService.deleteNotationGroup(notationGroupId);
    }



}
