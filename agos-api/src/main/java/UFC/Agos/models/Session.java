package UFC.Agos.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="session_seq")
    @SequenceGenerator(name="session_seq",sequenceName="session_seq", allocationSize=1)
    private Long id;
    private String title;
    private Integer duration;
    private LocalDate alertDelay;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="notationGroup_id")
    private NotationGroup notationGroup;

    public Session() {}

    public Session(String title, Integer duration, LocalDate alertDelay, Formation formation, NotationGroup notationGroup) {
        this.title = title;
        this.duration = duration;
        this.alertDelay = alertDelay;
        this.formation = formation;
        this.notationGroup = notationGroup;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getAlertDelay() {
        return alertDelay;
    }

    public void setAlertDelay(LocalDate alertDelay) {
        this.alertDelay = alertDelay;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public NotationGroup getNotationGroup() {
        return notationGroup;
    }

    public void setNotationGroup(NotationGroup notationGroup) {
        this.notationGroup = notationGroup;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", alertDelay=" + alertDelay +
                ", formation=" + formation +
                ", notationGroup=" + notationGroup +
                '}';
    }
}
