package UFC.Agos.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Crenel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToMany
    Collection<Room> rooms= new ArrayList<>();

    public Crenel() {
    }

    public Crenel(LocalDate day, LocalTime startTime, LocalTime endTime, Collection<Room> rooms) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
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
                ", rooms=" + rooms +
                '}';
    }
}
