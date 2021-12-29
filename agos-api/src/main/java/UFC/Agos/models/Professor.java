package UFC.Agos.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="professor_seq")
    @SequenceGenerator(name="professor_seq",sequenceName="professor_seq", allocationSize=1)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isAdmin;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Collection<Role> roles = new ArrayList<>();

    public Professor() {
    }

    public Professor(String firstName, String lastName, String username, String password, boolean isAdmin, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.department = department;
    }

    public Professor(String firstName, String lastName, String username, String password, boolean isAdmin, Department department,Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.department = department;
        this.roles = roles;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", isAdmin=" + isAdmin +
                ", department=" + department +
                '}';
    }
}
