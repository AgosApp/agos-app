package UFC.Agos.models;


import javax.persistence.*;

@Entity
public class Notation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="notation_seq")
    @SequenceGenerator(name="notation_seq",sequenceName="notation_seq", allocationSize=1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "criterion_id")
    private Criterion criterion;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "notationGroup_id")
    private NotationGroup notationGroup;

    public Notation() {
    }

    public Notation(Criterion criterion, NotationGroup notationGroup) {
        this.criterion = criterion;
        this.notationGroup = notationGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    public NotationGroup getNotationGroup() {
        return notationGroup;
    }

    public void setNotationGroup(NotationGroup notationGroup) {
        this.notationGroup = notationGroup;
    }

    @Override
    public String toString() {
        return "Notation{" +
                "id=" + id +
                ", criterion=" + criterion +
                ", notationGroup=" + notationGroup +
                '}';
    }
}
