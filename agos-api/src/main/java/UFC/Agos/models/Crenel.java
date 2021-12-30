package UFC.Agos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Crenel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    Session session;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Collection<Room> rooms= new ArrayList<>();

    public Crenel() {
    }

    public Crenel(LocalDate day, LocalTime startTime, LocalTime endTime, Session session, Collection<Room> rooms) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.session = session;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public void setRoom(Room room) {
        this.rooms.add(room);
    }

    public void setRooms(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Crenel{" +
                "id=" + id +
                ", day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", session=" + session +
                ", rooms=" + rooms +
                '}';
    }
}
