package rs.ac.singidunum.isa.projekat.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import rs.ac.singidunum.isa.projekat.dto.request.BookRequest;
import rs.ac.singidunum.isa.projekat.dto.request.DoctorRequest;
import rs.ac.singidunum.isa.projekat.dto.response.DoctorResponse;
import rs.ac.singidunum.isa.projekat.dto.response.PracticeResponse;
import rs.ac.singidunum.isa.projekat.entities.Appointment;
import rs.ac.singidunum.isa.projekat.entities.DoctorAvailabilitySlot;
import rs.ac.singidunum.isa.projekat.entities.User;
import rs.ac.singidunum.isa.projekat.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepositoryInterface doctorRepository;
    private final UserService userService;
    private final MedicalPracticeInterface medicalPracticeInterface;
    private final DoctorAvailabilitySlotInterface doctorAvailabilitySlotInterface;
    private final AppointmentInterface appointmentInterface;
    private final UserRepositoryInterface userRepository;

    public DoctorService(DoctorRepositoryInterface doctorRepository, UserService userService, MedicalPracticeInterface medicalPracticeInterface, DoctorAvailabilitySlotInterface doctorAvailabilitySlotInterface, AppointmentInterface appointmentInterface, UserRepositoryInterface userRepository) {
        this.doctorRepository = doctorRepository;
        this.userService = userService;
        this.medicalPracticeInterface = medicalPracticeInterface;
        this.doctorAvailabilitySlotInterface = doctorAvailabilitySlotInterface;
        this.appointmentInterface = appointmentInterface;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> findDoctors(@RequestBody DoctorRequest doctorRequest, HttpServletRequest httpRequest) {
        List<DoctorResponse> response = new ArrayList<>(doctorAvailabilitySlotInterface.findDoctors(doctorRequest.getPractice()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> findDoctorByDate(@RequestBody DoctorRequest doctorRequest, HttpServletRequest httpRequest) {
            List<DoctorResponse> response = new ArrayList<>(doctorAvailabilitySlotInterface.findDoctorsByDate(doctorRequest.getPractice(), doctorRequest.getDate()));
            System.out.println(doctorRequest.getDate().toString());
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getPractice() {
        List<PracticeResponse> response = new ArrayList<>(medicalPracticeInterface.getPractices());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> book(BookRequest bookRequest, HttpServletRequest request) {
        if(userService.isTokenValid(request.getHeader("Authorization"))) {
            try{
                DoctorAvailabilitySlot das = doctorAvailabilitySlotInterface.findById(bookRequest.getDasId()).get();

                das.setStatus("BOOKED");
                doctorAvailabilitySlotInterface.save(das);

                Appointment appointment = new Appointment();
                User u = userRepository.findById(bookRequest.getUserId()).get();

                appointment.setUser(u);
                appointment.setDas(das);
                appointmentInterface.save(appointment);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
