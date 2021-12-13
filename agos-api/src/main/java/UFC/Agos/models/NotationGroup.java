package UFC.Agos.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class NotationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="notationGroup_seq")
    @SequenceGenerator(name="notationGroup_seq",sequenceName="notationGroup_seq", allocationSize=1)
    private Long id;

    private String notationGroupTitle;

    @OneToMany(mappedBy = "notationGroup", cascade = CascadeType.ALL)
    private Set<Notation> notations = new HashSet<>();

    public NotationGroup() {
    }

    public NotationGroup(String notationGroupTitle) {
        this.notationGroupTitle = notationGroupTitle;
    }


    public Long getId() {
        return id;
    }

    public String getNotationGroupTitle() {
        return notationGroupTitle;
    }

    public void setNotationGroupTitle(String notationGroupTitle) {
        this.notationGroupTitle = notationGroupTitle;
    }

    public Set<Notation> getNotations() {
        return notations;
    }

    public void setNotations(Set<Notation> notations) {
        this.notations = notations;
    }

    public void addNotation(Notation notation){
        this.notations.add(notation);
    }
    @Override
    public String toString() {
        return "NotationGroup{" +
                "id=" + id +
                ", notation_group_title='" + notationGroupTitle + '\'' +
                '}';
    }
}
