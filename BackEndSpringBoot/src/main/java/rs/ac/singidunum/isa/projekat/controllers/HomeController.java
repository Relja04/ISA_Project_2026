package rs.ac.singidunum.isa.projekat.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.isa.projekat.dto.request.AppointmentEditRequest;
import rs.ac.singidunum.isa.projekat.dto.request.BookRequest;
import rs.ac.singidunum.isa.projekat.dto.request.DoctorRequest;
import rs.ac.singidunum.isa.projekat.services.AppointmentService;
import rs.ac.singidunum.isa.projekat.services.DoctorService;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public HomeController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/doctors")
    public ResponseEntity<?> findDoctors(@RequestBody DoctorRequest doctorRequest, HttpServletRequest request) {
        return doctorService.findDoctors(doctorRequest, request);
    }

    @PostMapping("/doctorsDate")
    public ResponseEntity<?> findDoctorsByDate(@RequestBody DoctorRequest doctorRequest, HttpServletRequest request) {
        return doctorService.findDoctorByDate(doctorRequest, request);
    }

    @PostMapping("/doctors/book")
    public ResponseEntity<?> book(@RequestBody BookRequest bookRequest, HttpServletRequest request) {

        return doctorService.book(bookRequest, request);
    }

    @GetMapping("/doctors/practice")
    public ResponseEntity<?> getPractice() {
        return doctorService.getPractice();
    }

    @GetMapping("/appointment/list")
    public ResponseEntity<?> getAppointments(HttpServletRequest request) {
        return appointmentService.getAppointments(request);
    }

    @PutMapping("/appointment/cancel/{id}/{dasId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable int id, @PathVariable int dasId,HttpServletRequest request) {
        return appointmentService.cancel(id,dasId,request);
    }

    @GetMapping("/appointment/editList/{doctorId}")
    public ResponseEntity<?> editAppointment(@PathVariable int doctorId,HttpServletRequest request) {
        return appointmentService.editList(doctorId,request);
    }
    @PostMapping("/appointment/edit")
    public ResponseEntity<?> edit(@RequestBody AppointmentEditRequest editRequest, HttpServletRequest request) {
        return appointmentService.edit(editRequest,request);
    }
}
