package spring_security.Services.User;

import spring_security.Models.AppRole;
import spring_security.Models.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppUser updateUser(AppUser appUser);
    AppUser findByUsername(String username);
    AppUser findById(Long id);
    void deleteUser(Long id);
    AppRole addRole(AppRole role);
    AppRole findByRolename(String rolename);
    AppUser addRoleToUser(String username,String rolename);
    List<AppUser> findAll();
}
