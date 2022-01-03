package UFC.Agos.services.imp;


import UFC.Agos.models.Professor;
import UFC.Agos.models.Role;
import UFC.Agos.repositories.ProfessorRepository;
import UFC.Agos.repositories.RoleRepository;
import UFC.Agos.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }


}
