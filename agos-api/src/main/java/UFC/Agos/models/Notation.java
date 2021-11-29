package UFC.Agos.models;


import javax.persistence.*;

@Entity
public class Notation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="notation_seq")
    @SequenceGenerator(name="notation_seq",sequenceName="notation_seq", allocationSize=1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "notationGroup_id")
    private NotationGroup notationGroup;

    public Notation() {
    }

    public Notation(Criteria criteria, NotationGroup notationGroup) {
        this.criteria = criteria;
        this.notationGroup = notationGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
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
                ", criterion=" + criteria +
                ", notationGroup=" + notationGroup +
                '}';
    }
}
