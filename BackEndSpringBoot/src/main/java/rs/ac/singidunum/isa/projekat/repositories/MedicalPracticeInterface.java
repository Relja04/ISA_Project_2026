package rs.ac.singidunum.isa.projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.singidunum.isa.projekat.dto.response.PracticeResponse;
import rs.ac.singidunum.isa.projekat.entities.MedicalPractice;

import java.util.List;

public interface MedicalPracticeInterface extends JpaRepository<MedicalPractice, Integer> {
    @Query("select mp.name as name FROM MedicalPractice mp")
    List<PracticeResponse> getPractices();
}
