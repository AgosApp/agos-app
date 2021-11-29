package UFC.Agos.models;

import javax.persistence.*;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="professor_seq")
    @SequenceGenerator(name="professor_seq",sequenceName="professor_seq", allocationSize=1)
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String abbreviation;
    private boolean isAdmin;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "department_id")
    private Department department;

    public Professor() {
    }

    public Professor(String firstName, String lastName, String login, String abbreviation, boolean isAdmin, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.abbreviation = abbreviation;
        this.isAdmin = isAdmin;
        this.department = department;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

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

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", isAdmin=" + isAdmin +
                ", department=" + department +
                '}';
    }
}
