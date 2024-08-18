package spring_security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_security.Models.AppRole;

@Repository
public interface RoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRolename(String rolename);
}
