package rs.ac.singidunum.isa.projekat.repositories;

import rs.ac.singidunum.isa.projekat.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositoryInterface extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
