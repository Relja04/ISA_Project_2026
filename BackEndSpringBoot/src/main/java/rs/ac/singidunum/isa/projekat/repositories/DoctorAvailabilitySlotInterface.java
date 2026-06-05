package rs.ac.singidunum.isa.projekat.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.singidunum.isa.projekat.dto.response.DoctorResponse;
import rs.ac.singidunum.isa.projekat.entities.DoctorAvailabilitySlot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoctorAvailabilitySlotInterface extends JpaRepository<DoctorAvailabilitySlot, Integer> {
    @Query("select das.doctor.id " +
            "from DoctorAvailabilitySlot das " +
            "where das.id=:id")
    int getDoctorId(@Param("id") int id);

    @Query("select das.id as appointmentId,das.doctor.id as id, das.doctor.name,das.doctor.lastName,das.doctor.practice.name as practice, das.slotStart,das.slotEnd,das.status " +
            "from DoctorAvailabilitySlot das " +
            "where das.doctor.practice.name=:practice and das.status!='BOOKED' " +
            "order by das.slotStart")
    List<DoctorResponse> findDoctors(@Param("practice") String practice);

    @Query("select das.id as appointmentId,das.doctor.id as id, das.doctor.name,das.doctor.lastName,das.doctor.practice.name as practice, das.slotStart,das.slotEnd,das.status " +
            "from DoctorAvailabilitySlot das " +
            "where das.doctor.practice.name=:practice and cast(das.slotStart as localdate)=:date and das.status!='BOOKED'")
    List<DoctorResponse> findDoctorsByDate(@Param("practice") String practice, @Param("date") LocalDate date);

    Optional<DoctorAvailabilitySlot> findById(Integer id);

    List<DoctorAvailabilitySlot> findByStatusAndDoctorIdOrderBySlotStartAsc(String status, int doctorId);
}
