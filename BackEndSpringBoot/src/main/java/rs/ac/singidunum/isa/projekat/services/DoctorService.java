package rs.ac.singidunum.isa.projekat.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.singidunum.isa.projekat.dto.DoctorRequest;
import rs.ac.singidunum.isa.projekat.dto.DoctorResponse;
import rs.ac.singidunum.isa.projekat.repositories.DoctorRepositoryInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepositoryInterface doctorRepository;
    private final UserService userService;
    public DoctorService(DoctorRepositoryInterface doctorRepository, UserService userService) {
        this.doctorRepository = doctorRepository;
        this.userService = userService;
    }
    public ResponseEntity<?> findDoctors(@RequestBody DoctorRequest doctorRequest, HttpServletRequest httpRequest) {
        if(userService.isTokenValid(httpRequest.getHeader("Authorization"))) {
            List<DoctorResponse> response=new ArrayList<>(doctorRepository.findDoctor(doctorRequest.getPractice()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
