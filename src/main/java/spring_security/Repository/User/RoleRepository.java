package spring_security.Repository.User;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_security.Models.Users.AppRole;

public interface RoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRole(String role);
}
