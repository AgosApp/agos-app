package UFC.Agos.controlers;

import UFC.Agos.models.Thesis;
import UFC.Agos.services.IThesisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"API for Thesis CRUD operations."})
@RequestMapping(path="/api/")
public class ThesisController {

    @Autowired
    IThesisService thesisService;

    @ApiOperation(value = "Get Theses by Session")
    @GetMapping(path="sessions/{sessionId}/theses")
    public List<Thesis> getThesesBySession(@PathVariable Long sessionId){
        return thesisService.getThesesBySession(sessionId);
    }

    @ApiOperation(value = "Get Thesis by Session")
    @GetMapping(path="sessions/{sessionId}/theses/{thesisId}")
    public Thesis getThesisBySession(@PathVariable Long thesisId,@PathVariable Long sessionId){
        return thesisService.getThesisBySession(thesisId, sessionId);
    }

    @ApiOperation(value = "Get Theses by Student")
    @GetMapping(path="students/{studentId}/theses")
    public List<Thesis> getThesesByStudent(@PathVariable Long studentId){
        return thesisService.getThesesByStudent(studentId);
    }

    @ApiOperation(value = "Get Thesis by Student")
    @GetMapping(path="students/{studentId}/theses/{thesisId}")
    public Thesis getThesisByStudent(@PathVariable Long thesisId,@PathVariable Long studentId){
        return thesisService.getThesisByStudent(thesisId, studentId);
    }

    @ApiOperation(value = "Get Theses by Professor")
    @GetMapping(path="professors/{professorId}/theses")
    public List<Thesis> getThesesByProfessor(@PathVariable Long professorId){
        return thesisService.getThesesByProfessor(professorId);
    }

    @ApiOperation(value = "Get Thesis by Professor")
    @GetMapping(path="professors/{professorId}/theses/{thesisId}")
    public Thesis getThesisByProfessor(@PathVariable Long thesisId,@PathVariable Long professorId){
        return thesisService.getThesisByProfessor(thesisId, professorId);
    }

    @ApiOperation(value = "Add Thesis")
    @PostMapping(path ="sessions/{sessionId}/theses")
    public Thesis save(@PathVariable Long sessionId,
                     @RequestParam Long roomId,
                     @RequestParam List<Long> professors,
                     @RequestParam List<Long> students,
                     @RequestBody Thesis thesis){
        thesisService.addThesis(thesis, sessionId, roomId, professors, students);
        return thesis;
    }

    @ApiOperation(value = "Delete Thesis from a Session")
    @DeleteMapping(path = "sessions/{sessionId}/theses/{thesisId}")
    public void delete(@PathVariable Long sessionId, @PathVariable Long thesisId) throws Exception {
        thesisService.deleteThesis(thesisId);
    }

    @ApiOperation(value = "Update Thesis")
    @PutMapping(path = "sessions/{sessionId}/theses/{thesisId}")
    public Thesis update(@PathVariable Long thesisId,
                       @PathVariable Long sessionId,
                       @RequestParam(required = false) String title,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) String summary,
                       @RequestParam(required = false) String time,
                       @RequestParam(required = false) Float finalNote,
                       @RequestParam(required = false) Long roomId,
                       @RequestParam(required = false) List<Long> students,
                       @RequestParam(required = false) List<Long> professors
                       ){
        thesisService.updateThesis(thesisId, title, type, time, finalNote, summary, sessionId, roomId, professors, students);
        return thesisService.getThesisBySession(thesisId, sessionId);
    }

    @ApiOperation(value = "Update Thesis")
    @PatchMapping(path = "sessions/{sessionId}/theses/{thesisId}")
    public Thesis update(@PathVariable Long thesisId,
                         @PathVariable Long sessionId,
                         @RequestParam(required = false) Long roomId,
                         @RequestParam(required = false) List<Long> professors,
                         @RequestParam(required = false) List<Long> students,
                         @RequestBody Map<String, Object> request){
        thesisService.update(thesisId,  request, sessionId, roomId, professors, students);
        return thesisService.getThesisBySession(thesisId, sessionId);
    }

}
