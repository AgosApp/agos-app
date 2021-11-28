package UFC.Agos.models;

import javax.persistence.*;

@Entity
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="criterion_seq")
    @SequenceGenerator(name="criterion_seq",sequenceName="criterion_seq", allocationSize=1)
    private Long id;
    private String title;

    public Criterion() {
    }

    public Criterion(String title) {
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
        return "Criterion{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
