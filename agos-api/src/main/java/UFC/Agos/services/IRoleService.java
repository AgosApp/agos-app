package UFC.Agos.services;

import UFC.Agos.models.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAllRoles();

    void addRole(Role role);

}
