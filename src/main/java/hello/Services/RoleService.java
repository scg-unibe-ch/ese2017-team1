package hello.Services;

import hello.LoginRole.Role;
import hello.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Iterable<Role> listAll() { return roleRepository.findAll(); }

    public Role findRole(Long roleId) { return roleRepository.findOne(roleId); }
}
