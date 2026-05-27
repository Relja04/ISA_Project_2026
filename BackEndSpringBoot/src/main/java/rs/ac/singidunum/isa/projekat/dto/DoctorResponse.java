package rs.ac.singidunum.isa.projekat.dto;

import java.time.OffsetDateTime;

public class DoctorResponse {
    private int id;
    private String name;
    private String lastName;
    private String practice;
    private OffsetDateTime start;
    private OffsetDateTime end;
    private String status;

    public DoctorResponse(int id, String name, String lastName, String practice, OffsetDateTime start, OffsetDateTime end, String status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.practice = practice;
        this.start = start;
        this.end = end;
        this.status = status;
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
}