package UFC.Agos.models;

import javax.persistence.*;

@Entity
public class CriteriaEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="criteriaEvaluation_seq")
    @SequenceGenerator(name="criteriaEvaluation_seq",sequenceName="criteriaEvaluation_seq", allocationSize=1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

    @OneToOne
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    private Float note;

    public CriteriaEvaluation() {
    }

    public CriteriaEvaluation(Professor professor, Thesis thesis, Criteria criteria, Float note) {
        this.professor = professor;
        this.thesis = thesis;
        this.criteria = criteria;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Thesis getThesis() {
        return thesis;
    }

    public void setThesis(Thesis thesis) {
        this.thesis = thesis;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CriteriaEvaluation{" +
                "id=" + id +
                ", professor=" + professor +
                ", thesis=" + thesis +
                ", criteria=" + criteria +
                ", note=" + note +
                '}';
    }
}
