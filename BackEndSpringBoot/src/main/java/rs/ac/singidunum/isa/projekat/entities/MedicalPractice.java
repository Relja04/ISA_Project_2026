package rs.ac.singidunum.isa.projekat.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "medical_practice")
public class MedicalPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "practice_id")
    private Set<Doctor> doctors = new LinkedHashSet<>();


}