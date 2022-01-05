package UFC.Agos.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class StudentThesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    public StudentThesis() {
    }

    public StudentThesis(Student student, Thesis thesis) {
        this.student = student;
        this.thesis = thesis;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudents(Student student) {
        this.student = student;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }
}
