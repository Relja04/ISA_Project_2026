package rs.ac.singidunum.isa.projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.singidunum.isa.projekat.entities.Doctor;
import java.util.Optional;

public interface DoctorRepositoryInterface extends JpaRepository<Doctor, Integer> {



    @Query("select d " +
            "from Doctor d " +
            "where d.id=:id")
    Optional<Doctor>findById(@Param("id") int id);
}