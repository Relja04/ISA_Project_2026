package rs.ac.singidunum.isa.projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.singidunum.isa.projekat.dto.DoctorResponse;
import rs.ac.singidunum.isa.projekat.entities.Doctor;

import java.util.List;

public interface DoctorRepositoryInterface extends JpaRepository<Doctor, Integer> {

    @Query("select d.id, d.name, d.lastName, mp.name, das.slotStart, das.slotEnd, das.status " +
            "from Doctor d " +
            "join d.practice mp " +
            "join d.availabilitySlots das " +
            "where mp.name=?1")
    List<DoctorResponse> findDoctor(@Param("practice") String practice);
}