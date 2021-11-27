package UFC.Agos.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="student_seq")
    @SequenceGenerator(name="student_seq",sequenceName="student_seq", allocationSize=1)
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "formation_id")
    private Formation formation;

    public Student() {
    }

    public Student(String firstName, String lastName, String login, Formation formation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.formation = formation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", formation=" + formation.getName() +
                '}';
    }
}
