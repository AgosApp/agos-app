package UFC.Agos.models;

import javax.persistence.*;

@Entity
public class NotationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="notationGroup_seq")
    @SequenceGenerator(name="notationGroup_seq",sequenceName="notationGroup_seq", allocationSize=1)
    private Long id;
    private String title;

    public NotationGroup() {
    }

    public NotationGroup(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NotationGroup{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
