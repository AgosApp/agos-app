package UFC.Agos.controlers;

import UFC.Agos.models.Thesis;
import UFC.Agos.services.IThesisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
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

    @ApiOperation(value = "Get Theses by Crenel")
    @GetMapping(path="creneaux/{crenelId}/theses")
    public List<Thesis> getThesesByCrenel(@PathVariable Long crenelId){
        return thesisService.getThesesByCrenel(crenelId);
    }

    @ApiOperation(value = "Get Thesis by Crenel")
    @GetMapping(path="creneaux/{crenelId}/theses/{thesisId}")
    public Thesis getThesisByCrenel(@PathVariable Long thesisId,@PathVariable Long crenelId){
        return thesisService.getThesisByCrenel(thesisId, crenelId);
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
    @PostMapping(path ="creneaux/{crenelId}/theses")
    public Thesis save(@PathVariable Long crenelId,
                     @RequestParam Long roomId,
                     @RequestParam List<Long> professors,
                     @RequestParam List<Long> students,
                     @RequestBody Thesis thesis) throws Exception {
        thesisService.addThesis(thesis, crenelId, roomId, professors, students);
        return thesis;
    }

    @ApiOperation(value = "Delete Thesis from a Crenel")
    @DeleteMapping(path = "creneaux/{crenelId}/theses/{thesisId}")
    public void delete(@PathVariable Long crenelId, @PathVariable Long thesisId) throws Exception {
        thesisService.deleteThesis(thesisId);
    }

    @ApiOperation(value = "Update Thesis")
    @PutMapping(path = "creneaux/{crenelId}/theses/{thesisId}")
    public Thesis update(@PathVariable Long thesisId,
                       @PathVariable Long crenelId,
                       @RequestParam(required = false) String title,
                       @RequestParam(required = false) String type,
                       @RequestParam(required = false) String summary,
                       @RequestParam(required = false) String time,
                       @RequestParam(required = false) Float finalNote,
                       @RequestParam(required = false) Long roomId,
                       @RequestParam(required = false) List<Long> students,
                       @RequestParam(required = false) List<Long> professors
                       ) throws Exception {
        thesisService.updateThesis(thesisId, title, type, time, finalNote, summary, crenelId, roomId, professors, students);
        return thesisService.getThesisByCrenel(thesisId, crenelId);
    }

    @ApiOperation(value = "Update Thesis")
    @PatchMapping(path = "creneaux/{crenelId}/theses/{thesisId}")
    public Thesis update(@PathVariable Long thesisId,
                         @PathVariable Long crenelId,
                         @RequestParam(required = false) Long roomId,
                         @RequestParam(required = false) List<Long> professors,
                         @RequestParam(required = false) List<Long> students,
                         @RequestBody Map<String, Object> request) throws Exception {
        thesisService.update(thesisId,  request, crenelId, roomId, professors, students);
        return thesisService.getThesisByCrenel(thesisId, crenelId);
    }

}
