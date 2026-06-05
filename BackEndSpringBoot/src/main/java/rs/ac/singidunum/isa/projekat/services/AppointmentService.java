package rs.ac.singidunum.isa.projekat.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.projekat.dto.request.AppointmentEditRequest;
import rs.ac.singidunum.isa.projekat.dto.request.BookRequest;
import rs.ac.singidunum.isa.projekat.dto.response.AppointmentResponse;
import rs.ac.singidunum.isa.projekat.dto.response.EditListResponse;
import rs.ac.singidunum.isa.projekat.entities.Appointment;
import rs.ac.singidunum.isa.projekat.entities.DoctorAvailabilitySlot;
import rs.ac.singidunum.isa.projekat.repositories.AppointmentInterface;
import rs.ac.singidunum.isa.projekat.repositories.DoctorAvailabilitySlotInterface;


import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentInterface appointmentInterface;
    private final DoctorAvailabilitySlotInterface das;
    private final DoctorService doctorService;
    private final UserService userService;

    public AppointmentService(AppointmentInterface appointmentInterface,  DoctorAvailabilitySlotInterface das, DoctorService doctorService, UserService userService) {
        this.appointmentInterface = appointmentInterface;
        this.das = das;
        this.userService = userService;
        this.doctorService = doctorService;
    }

    public ResponseEntity<?> getAppointments(HttpServletRequest request) {
        if(userService.isTokenValid(request.getHeader("Authorization"))) {
            List<Appointment> appointments = appointmentInterface.findAll();
            List<AppointmentResponse> appointmentList = appointments.stream().map(a -> new AppointmentResponse(
                    a.getId(),
                    a.getDas().getId(),
                    a.getDas().getDoctor().getId(),
                    a.getDas().getSlotStart(),
                    a.getDas().getSlotEnd(),
                    a.getDas().getDoctor().getName() + " " + a.getDas().getDoctor().getLastName(),
                    a.getDas().getDoctor().getPractice().getName(),
                    a.getBookedAt()
            )).toList();
            return new ResponseEntity<>(appointmentList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    public ResponseEntity<?> cancel(int id, int dasId,HttpServletRequest request) {
        if (userService.isTokenValid(request.getHeader("Authorization"))) {
            try {
                if (!appointmentInterface.existsById(id)) {
                    return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
                }

                appointmentInterface.deleteById(id);
                DoctorAvailabilitySlot slot = das.findById(dasId).get();
                slot.setStatus("FREE");
                das.save(slot);
                return new ResponseEntity<>("Appointment cancelled successfully", HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>("An error occurred while canceling", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> editList(int doctorId,HttpServletRequest request) {
        if (userService.isTokenValid(request.getHeader("Authorization"))) {
            List<DoctorAvailabilitySlot>dasList=das.findByStatusAndDoctorIdOrderBySlotStartAsc("FREE",doctorId);
            List<EditListResponse> editListResponse=dasList.stream().map(e->new EditListResponse(
                    e.getSlotStart(),
                    e.getSlotEnd(),
                    e.getId()
            )).toList();

            return new ResponseEntity<>(editListResponse,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> edit(AppointmentEditRequest editRequest, HttpServletRequest request) {
        if (userService.isTokenValid(request.getHeader("Authorization"))) {
            try {
                BookRequest bookRequest = new BookRequest(editRequest.getNewAppointmentId(),editRequest.getUserId());
                doctorService.book(bookRequest,request);
                DoctorAvailabilitySlot doctorAvailabilitySlot=das.findById(editRequest.getOldAppointmentId()).get();
                doctorAvailabilitySlot.setStatus("FREE");
                das.save(doctorAvailabilitySlot);
                appointmentInterface.deleteByDasId(editRequest.getOldAppointmentId());
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("An error occurred while editing", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Appointment updated successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
