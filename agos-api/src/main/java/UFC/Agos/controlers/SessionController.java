package UFC.Agos.controlers;


import UFC.Agos.models.Session;
import UFC.Agos.services.imp.FormationService;
import UFC.Agos.services.imp.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"API for Sessions CRUD operations."})
@RequestMapping(path = "api/formations/{formationId}/sessions")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @Autowired
    FormationService formationService;

    @ApiOperation(value = "Get Sessions by Formation")
    @GetMapping
    public List<Session> getAll(@PathVariable Long formationId){
        return sessionService.getSessionsByFormation(formationId);
    }

    @ApiOperation(value = "Get Session by Formation")
    @GetMapping(path = "/{sessionId}")
    public Session getOne(@PathVariable Long sessionId,@PathVariable Long formationId){
        return sessionService.getSessionByFormation(sessionId, formationId);
    }

    @ApiOperation(value = "Add Session")
    @PostMapping
    public Session save(@PathVariable Long formationId,
                     @RequestParam(required = false) Long notationGroupId,
                     @RequestBody Session session){
        sessionService.addSession(session, formationId, notationGroupId);
        return session;
    }

    @ApiOperation(value = "Delete Session")
    @DeleteMapping(path = "/{sessionId}")
    public void delete(@PathVariable Long sessionId, @PathVariable Long formationId) throws Exception {
        sessionService.deleteSession(sessionId);
    }

    @ApiOperation(value = "Update Session")
    @PutMapping(path = "/{sessionId}")
    public Session update(@PathVariable Long sessionId,
                          @PathVariable Long formationId,
                          @RequestParam(required = false) String title,
                          @RequestParam(required = false) Integer duration,
                          @RequestParam(required = false) Integer thesisDuration,
                          @RequestParam(required = false) Integer deliberationDuration,
                          @RequestParam(required = false) String alertDelay,
                          @RequestParam(required = false) Long notationGroupId
                       ) {
        sessionService.updateSession(sessionId, title, duration, thesisDuration, deliberationDuration, alertDelay, notationGroupId ,formationId);
        return sessionService.getSessionByFormation(sessionId, formationId);
    }

    @ApiOperation(value = "Update Session")
    @PatchMapping(path = "/{sessionId}")
    public Session update(@PathVariable Long sessionId,
                         @PathVariable Long formationId,
                         @RequestBody Map<String, Object> request,
                         @RequestParam(required = false) Long notationGroupId)  {
        sessionService.updateSession(sessionId, request, formationId, notationGroupId);
        return sessionService.getSessionByFormation(sessionId, formationId);
    }

}
