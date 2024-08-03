package spring_security.Services.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_security.Models.User.AppRole;
import spring_security.Models.User.AppUser;
import spring_security.Repository.User.RoleRepository;
import spring_security.Repository.User.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppRole addRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public AppUser addRoleToUser(String username, String rolename) {
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByRoleName(rolename);

        user.getAppRoles().add(role);
        return user;
    }
}
