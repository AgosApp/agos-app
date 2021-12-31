package UFC.Agos.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Thesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;
    private Float finalNote;
    private String summary;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "crenel_id")
    private Crenel crenel;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "room_id")
    private Room room;


    public Thesis() {
    }

    public Thesis(String title, String type, LocalDateTime time, Float finalNote, String summary, Crenel crenel, Room room) {
        this.title = title;
        this.type = type;
        this.time = time;
        this.finalNote = finalNote;
        this.summary = summary;
        this.crenel = crenel;
        this.room = room;
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

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Float getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(Float finalNote) {
        this.finalNote = finalNote;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Crenel getCrenel() {
        return crenel;
    }

    public void setCrenel(Crenel crenel) {
        this.crenel = crenel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Thesis{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", finalNote=" + finalNote +
                ", summary='" + summary + '\'' +
                ", crenel=" + crenel +
                ", room=" + room +
                '}';
    }
}
