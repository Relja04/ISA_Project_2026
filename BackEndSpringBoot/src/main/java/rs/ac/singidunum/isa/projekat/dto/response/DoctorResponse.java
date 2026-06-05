package rs.ac.singidunum.isa.projekat.dto.response;

import java.time.OffsetDateTime;

public class DoctorResponse {
    private int appointmentId;
    private int id;
    private String name;
    private String lastName;
    private String practice;
    private OffsetDateTime start;
    private OffsetDateTime end;
    private String status;

    public DoctorResponse(int appointmentId, int id, String name,String lastName, String practice, OffsetDateTime start, OffsetDateTime end, String status) {
        this.appointmentId = appointmentId;
        this.id = id;
        this.name = name;
        this.practice = practice;
        this.start = start;
        this.end = end;
        this.status = status;
        this.lastName = lastName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public OffsetDateTime getStart() {
        return start;
    }

    public void setStart(OffsetDateTime start) {
        this.start = start;
    }

    public OffsetDateTime getEnd() {
        return end;
    }

    public void setEnd(OffsetDateTime end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DoctorResponse{" +
                "appointmentId=" + appointmentId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", practice='" + practice + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", status='" + status + '\'' +
                '}';
    }
}