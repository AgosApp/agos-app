package UFC.Agos.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="criteria_seq")
    @SequenceGenerator(name="criteria_seq",sequenceName="criteria_seq", allocationSize=1)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL)
    private Set<Notation> notations = new HashSet<>();

    public Criteria() {
    }

    public Criteria(String title) {
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Notation> getNotations() {
        return notations;
    }

    public void setNotations(Set<Notation> notations) {
        this.notations = notations;
    }

    @Override
    public String toString() {
        return "Criterion{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
