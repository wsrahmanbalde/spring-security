package spring_security.Repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_security.Models.Users.AppUser;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
