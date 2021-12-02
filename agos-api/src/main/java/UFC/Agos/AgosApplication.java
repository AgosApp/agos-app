package UFC.Agos;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;
import UFC.Agos.repositories.CriteriaRepository;
import UFC.Agos.repositories.DepartmentRepository;
import UFC.Agos.repositories.NotationGroupRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgosApplication.class, args);
	}

}
