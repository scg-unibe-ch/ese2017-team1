package hello.Services;

import hello.LoginRole.Role;
import hello.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling requests concerning roles
 */
@Service("roleService")
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    /**
     * @return Iterable of all Roles in roleRepository
     */
    public Iterable<Role> listAll() { return roleRepository.findAll(); }

    /**
     * returns role with Id roleId
     */
    public Role findRole(Long roleId) { return roleRepository.findOne(roleId); }
}
