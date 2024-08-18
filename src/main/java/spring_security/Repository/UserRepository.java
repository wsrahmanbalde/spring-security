package spring_security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_security.Models.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
