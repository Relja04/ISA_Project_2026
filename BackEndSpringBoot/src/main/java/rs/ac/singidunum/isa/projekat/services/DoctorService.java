package rs.ac.singidunum.isa.projekat.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.singidunum.isa.projekat.dto.request.DoctorRequest;
import rs.ac.singidunum.isa.projekat.dto.response.DoctorResponse;
import rs.ac.singidunum.isa.projekat.dto.response.PracticeResponse;
import rs.ac.singidunum.isa.projekat.repositories.DoctorRepositoryInterface;
import rs.ac.singidunum.isa.projekat.repositories.MedicalPracticeInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepositoryInterface doctorRepository;
    private final UserService userService;
    private final MedicalPracticeInterface medicalPracticeInterface;
    public DoctorService(DoctorRepositoryInterface doctorRepository, UserService userService,  MedicalPracticeInterface medicalPracticeInterface) {
        this.doctorRepository = doctorRepository;
        this.userService = userService;
        this.medicalPracticeInterface=medicalPracticeInterface;
    }
    public ResponseEntity<?> findDoctors(@RequestBody DoctorRequest doctorRequest, HttpServletRequest httpRequest) {
        if(userService.isTokenValid(httpRequest.getHeader("Authorization"))) {
            List<DoctorResponse> response=new ArrayList<>(doctorRepository.findDoctor(doctorRequest.getPractice()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    public ResponseEntity<?> findDoctorByDate(@RequestBody DoctorRequest doctorRequest, HttpServletRequest httpRequest) {
        if(userService.isTokenValid(httpRequest.getHeader("Authorization"))) {
            List<DoctorResponse> response=new ArrayList<>(doctorRepository.findDoctorsByDate(doctorRequest.getPractice(), doctorRequest.getDate()));
            System.out.println(doctorRequest.getDate().toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    public ResponseEntity<?> getPractice(){
        List<PracticeResponse> response=new ArrayList<>(medicalPracticeInterface.getPractices());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
