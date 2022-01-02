package UFC.Agos.models;

import javax.persistence.*;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String remark;
    private Float thesisNote;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Evaluation() {
    }

    public Evaluation(String remark, Float thesisNote, Thesis thesis, Professor professor) {
        this.remark = remark;
        this.thesisNote = thesisNote;
        this.thesis = thesis;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getThesisNote() {
        return thesisNote;
    }

    public void setThesisNote(Float thesisNote) {
        this.thesisNote = thesisNote;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", remark='" + remark + '\'' +
                ", thesisNote=" + thesisNote +
                ", thesis=" + thesis +
                ", professor=" + professor +
                '}';
    }
}
