package rs.ac.singidunum.isa.projekat.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.projekat.dto.DoctorRequest;
import rs.ac.singidunum.isa.projekat.dto.DoctorResponse;
import rs.ac.singidunum.isa.projekat.entities.Doctor;
import rs.ac.singidunum.isa.projekat.services.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {

    private final DoctorService doctorService;

    public HomeController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctors")
    public ResponseEntity<?> findDoctors(@RequestBody DoctorRequest doctorRequest, HttpServletRequest request) {
        return doctorService.findDoctors(doctorRequest, request);
    }
}
