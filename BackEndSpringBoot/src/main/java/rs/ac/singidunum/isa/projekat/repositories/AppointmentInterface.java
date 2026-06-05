package rs.ac.singidunum.isa.projekat.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.isa.projekat.entities.Appointment;

public interface AppointmentInterface extends JpaRepository<Appointment, Integer> {
    @Transactional
    void deleteByDasId(Integer id);
}
