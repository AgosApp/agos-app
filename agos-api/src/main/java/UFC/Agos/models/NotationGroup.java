package UFC.Agos.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class NotationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="notationGroup_seq")
    @SequenceGenerator(name="notationGroup_seq",sequenceName="notationGroup_seq", allocationSize=1)
    private Long id;

    private String notationGroupTitle;

    @OneToMany(mappedBy = "notationGroup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Notation> notations = new ArrayList<>();

    public NotationGroup() {
    }


    /*public NotationGroup(String notation_group_title
            //, List<Notation> notations
                         ) {
        this.notationGroupTitle = notation_group_title;
        //this.notations = notations;
    }*/

    public Long getId() {
        return id;
    }
    public NotationGroup(String notationGroupTitle) {
        this.notationGroupTitle = notationGroupTitle;

    }


    public String getNotationGroupTitle() {
        return notationGroupTitle;
    }

    public void setNotationGroupTitle(String notationGroupTitle) {
        this.notationGroupTitle = notationGroupTitle;
    }

   public List<Notation> getNotations() {
        return notations;
    }

    public void setNotations(List<Notation> notations) {
        this.notations = notations;
    }

    public void addNotation(Notation notation){
        this.notations.add(notation);
    }
    @Override
    public String toString() {
        return "NotationGroup{" +
                "id=" + id +
                ", notationGroupTitle='" + notationGroupTitle + '\'' +
                '}';
    }
}
