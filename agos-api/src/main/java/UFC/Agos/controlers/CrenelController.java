package UFC.Agos.controlers;


import UFC.Agos.models.Crenel;
import UFC.Agos.services.ICrenelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"API for Crenaux CRUD operations."})
@RequestMapping(path="api/sessions/{sessionId}/crenaux")
public class CrenelController {

    @Autowired
    ICrenelService crenelService;

    @ApiOperation(value = "Get Crenaux by Session")
    @GetMapping
    public List<Crenel> getAll(@PathVariable Long sessionId){
        return crenelService.getCreneauxBySession(sessionId);
    }

    @ApiOperation(value = "Get Crenel by Session")
    @GetMapping(path = "/{crenelId}")
    public Crenel getOne(@PathVariable Long crenelId,@PathVariable Long sessionId){
        return crenelService.getCrenelBySession(crenelId, sessionId);
    }

    @ApiOperation(value = "Add Crenel in Session")
    @PostMapping
    public Crenel save(@PathVariable Long sessionId,
                       @RequestBody Crenel crenel,
                       @RequestParam(required = false) List<Long> rooms){
        crenelService.addCrenel(crenel, sessionId ,rooms);
        return crenel;
    }

    @ApiOperation(value = "Delete Crenel")
    @DeleteMapping(path = "/{crenelId}")
    public void delete(@PathVariable Long crenelId, @PathVariable Long sessionId) throws Exception {
        crenelService.deleteCrenel(crenelId);
        //return "Crenel with id " + crenelId + " is deleted.";
    }

    @ApiOperation(value = "Update Crenel")
    @PatchMapping(path = "/{crenelId}")
    public Crenel update(@PathVariable Long crenelId,
                       @PathVariable Long sessionId,
                         @RequestBody Map<String, Object> request,
                       @RequestParam(required = false) List<Long> rooms) throws Exception {
        crenelService.updateCrenel(crenelId, request, sessionId, rooms);
        return crenelService.getCrenelBySession(crenelId, sessionId);
    }
}
