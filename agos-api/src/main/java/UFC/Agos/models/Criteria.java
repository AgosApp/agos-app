package UFC.Agos.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;


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


    @Override
    public String toString() {
        return "Criteria{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
