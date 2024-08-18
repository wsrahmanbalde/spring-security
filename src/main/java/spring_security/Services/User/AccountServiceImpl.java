package spring_security.Services.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring_security.Models.AppRole;
import spring_security.Models.AppUser;
import spring_security.Repository.RoleRepository;
import spring_security.Repository.UserRepository;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AppUser addNewUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser);
    }

    @Override
    public AppUser updateUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public AppUser findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public AppRole addRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public AppRole findByRolename(String rolename) {
        return roleRepository.findByRolename(rolename);
    }

    @Override
    public AppUser addRoleToUser(String username, String rolename) {
        AppUser user = userRepository.findByUsername(username);
        AppRole role = roleRepository.findByRolename(rolename);
        user.getAppRoles().add(role);
        return user;
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
}
