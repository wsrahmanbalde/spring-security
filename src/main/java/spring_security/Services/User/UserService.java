package spring_security.Services.User;

import spring_security.Models.User.AppRole;
import spring_security.Models.User.AppUser;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppUser findByUsername(String username);

    AppRole addRole(AppRole role);

    AppUser addRoleToUser(String username , String rolename);

}
