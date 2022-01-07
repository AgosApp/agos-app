package UFC.Agos.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String username;
    private String password;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Collection<Role> roles = new ArrayList<>();


    public Student() {
    }

    public Student(String firstName, String lastName, String username, String password, Formation formation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.formation = formation;
    }

    public Long getId() {
        return id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", formation=" + formation +
                ", roles=" + roles +
                '}';
    }
}
