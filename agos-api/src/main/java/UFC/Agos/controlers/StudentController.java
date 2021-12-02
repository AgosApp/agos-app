package UFC.Agos.controlers;

import UFC.Agos.models.Student;
import UFC.Agos.services.imp.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/formations/{formationId}/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> getAll(@PathVariable Long formationId){
        return studentService.getStudentsByFormation(formationId);
    }

    @GetMapping(path = "/{studentId}")
    public Student getOne(@PathVariable Long studentId,@PathVariable Long formationId){
        return studentService.getStudentByFormation(studentId, formationId);
    }

    @PostMapping
    public void save(@PathVariable(required = false) Long formationId,
                     @RequestBody Student student){
        studentService.addStudent(student, formationId);
    }

    @DeleteMapping(path = "/{studentId}")
    public void delete(@PathVariable Long studentId, @PathVariable Long formationId) throws Exception {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/{studentId}")
    public void update(@PathVariable Long studentId,
                       @PathVariable(required = false) Long formationId,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String login

                       ){
        studentService.updateStudent(studentId, firstName, lastName, login, formationId);
    }
}
