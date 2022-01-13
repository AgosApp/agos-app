package UFC.Agos.controlers;

import UFC.Agos.models.Student;
import UFC.Agos.services.imp.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"API for Students CRUD operations."})
//@PreAuthorize("hasAuthority('ADMIN_ROLE')")
@RequestMapping(path = "api/")
public class StudentController {

    @Autowired
    StudentService studentService;

    @ApiOperation(value = "Get All Students")
    @GetMapping(path = "students")
    public List<Student> getAll(){
        return studentService.getAllStudents();
    }

    @ApiOperation(value = "Get Students by Formation")
    @GetMapping(path = "formations/{formationId}/students")
    public List<Student> getByDepartment(@PathVariable Long formationId){
        return studentService.getStudentsByFormation(formationId);
    }

    @ApiOperation(value = "Get Student by Formation")
    @GetMapping(path = "formations/{formationId}/students/{studentId}")
    public Student getOne(@PathVariable Long studentId,@PathVariable Long formationId){
        return studentService.getStudentByFormation(studentId, formationId);
    }

    @ApiOperation(value = "Add Student in a Formation")
    @PostMapping(path = "formations/{formationId}/students")
    public void save(@PathVariable(required = false) Long formationId,
                     @RequestBody Student student) throws Exception {
        studentService.addStudent(student, formationId);
    }

    @ApiOperation(value = "Delete Student from a Formation")
    @DeleteMapping(path = "formations/{formationId}/students/{studentId}")
    public void delete(@PathVariable Long studentId, @PathVariable Long formationId) throws Exception {
        studentService.deleteStudent(studentId);
    }

    @ApiOperation(value = "Update Student")
    @PutMapping(path = "formations/{formationId}/students/{studentId}")
    public void update(@PathVariable Long studentId,
                       @PathVariable(required = false) Long formationId,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String username,
                       @RequestParam(required = false) String password
                       ) throws Exception {
        studentService.updateStudent(studentId, firstName, lastName, username, password, formationId);
    }
}
