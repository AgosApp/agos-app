package UFC.Agos.models;

import javax.persistence.*;


@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formation_seq")
    @SequenceGenerator(name = "formation_seq", sequenceName = "formation_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;


    public Formation() {
    }

    public Formation(String name, String description, Department department) {
        this.name = name;
        this.description = description;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department=" + department.getName() +
                '}';
    }

}
