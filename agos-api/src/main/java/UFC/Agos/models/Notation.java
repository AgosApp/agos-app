package UFC.Agos.models;


import javax.persistence.*;

@Entity
public class Notation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int bareme;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "criteria_id")
    private Criteria criteria;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "notationGroup_id")
    private NotationGroup notationGroup;

    public Notation() {

    }

    public Notation(Criteria criteria, NotationGroup notationGroup, int bareme) {
        this.criteria = criteria;
        this.notationGroup = notationGroup;
        this.bareme = bareme;
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

    public int getBareme() {
        return bareme;
    }

    public void setBareme(int bareme) {
        this.bareme = bareme;
    }

    @Override
    public String toString() {
        return "Notation{" +
                "id=" + id +
                ", criterion=" + criteria +
                ", notationGroup=" + notationGroup +
                ", bareme=" + bareme +
                '}';
    }
}
