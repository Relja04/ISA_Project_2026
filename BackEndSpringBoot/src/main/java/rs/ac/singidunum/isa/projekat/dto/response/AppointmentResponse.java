package rs.ac.singidunum.isa.projekat.dto.response;

import java.time.Instant;
import java.time.OffsetDateTime;

public class AppointmentResponse {
    private int id;
    private int dasId;
    private int doctorId;
    private OffsetDateTime slotStart;
    private OffsetDateTime slotEnd;
    private String doctorName;
    private String medicalPractice;
    private Instant bookedAt;

    public AppointmentResponse(int id, int dasId, int doctorId, OffsetDateTime slotStart, OffsetDateTime slotEnd, String doctorName, String medicalPractice, Instant bookedAt) {
        this.id = id;
        this.dasId = dasId;
        this.doctorId = doctorId;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.doctorName = doctorName;
        this.medicalPractice = medicalPractice;
        this.bookedAt = bookedAt;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDasId() {
        return dasId;
    }

    public void setDasId(int dasId) {
        this.dasId = dasId;
    }

    public OffsetDateTime getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(OffsetDateTime slotStart) {
        this.slotStart = slotStart;
    }

    public OffsetDateTime getSlotEnd() {
        return slotEnd;
    }

    public void setSlotEnd(OffsetDateTime slotEnd) {
        this.slotEnd = slotEnd;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMedicalPractice() {
        return medicalPractice;
    }

    public void setMedicalPractice(String medicalPractice) {
        this.medicalPractice = medicalPractice;
    }

    public Instant getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Instant bookedAt) {
        this.bookedAt = bookedAt;
    }
}
