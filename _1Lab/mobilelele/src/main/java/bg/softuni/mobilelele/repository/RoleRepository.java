package bg.softuni.mobilelele.repository;

import bg.softuni.mobilelele.model.entities.Role;
import bg.softuni.mobilelele.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(UserRole userRole);
}
