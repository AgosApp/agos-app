package UFC.Agos.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration thesisDuration;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration deliberationDuration;
    private LocalDate alertDelay;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="notationGroup_id")
    private NotationGroup notationGroup;

    public Session() {
    }

    public Session(String title, Duration duration, LocalDate alertDelay, Formation formation, NotationGroup notationGroup) {
        this.title = title;
        this.duration = duration;
        this.alertDelay = alertDelay;
        this.formation = formation;
        this.notationGroup = notationGroup;
    }

    public Session(String title, Duration duration, Duration thesisDuration, Duration deliberationDuration, LocalDate alertDelay, Formation formation, NotationGroup notationGroup) {
        this.title = title;
        this.duration = duration;
        this.thesisDuration = thesisDuration;
        this.deliberationDuration = deliberationDuration;
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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration getThesisDuration() {
        return thesisDuration;
    }

    public void setThesisDuration(Duration thesisDuration) {
        this.thesisDuration = thesisDuration;
    }

    public Duration getDeliberationDuration() {
        return deliberationDuration;
    }

    public void setDeliberationDuration(Duration deliberationDuration) {
        this.deliberationDuration = deliberationDuration;
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
                ", thesisDuration=" + thesisDuration +
                ", deliberationDuration=" + deliberationDuration +
                ", alertDelay=" + alertDelay +
                ", formation=" + formation +
                ", notationGroup=" + notationGroup +
                '}';
    }
}
