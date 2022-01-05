package UFC.Agos.configurations;

import UFC.Agos.models.Criteria;
import UFC.Agos.models.Notation;
import UFC.Agos.models.NotationGroup;
import UFC.Agos.repositories.CriteriaRepository;
import UFC.Agos.repositories.DepartmentRepository;
import UFC.Agos.repositories.NotationGroupRepository;
import UFC.Agos.repositories.NotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NotationConfiguration {

    @Autowired
    private CriteriaRepository criteriaRepository;
    private NotationGroupRepository notationGroupRepository;
    private NotationRepository notationRepository;

    @Bean
    CommandLineRunner notationCommandLineRunner(NotationRepository notationRepository) {

        return args -> {
            Criteria criteria = new Criteria("criteria1");
            Criteria criteria2 = new Criteria("criteria2");

            criteriaRepository.save(criteria);
            criteriaRepository.save(criteria2);
            /*Notation notation = new Notation(
                    //criteria, notationGroupId,
                    criteria,notationGroup, 5);
            NotationGroup notationGroup = new NotationGroup("group1", notation);

            //notationGroup.addNotation(notation);
            notationGroupRepository.save(notationGroup);*/


        };
    }
}
