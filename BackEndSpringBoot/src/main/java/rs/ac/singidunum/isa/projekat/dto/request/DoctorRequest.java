package rs.ac.singidunum.isa.projekat.dto.request;

import java.time.LocalDate;

public class DoctorRequest {
    private String practice;
    private LocalDate date;


    public DoctorRequest() {
    }

    public DoctorRequest(String practice) {
        this.practice = practice;
    }

    public DoctorRequest(String practice, LocalDate date) {
        this.practice = practice;
        this.date = date;
    }

    public String getPractice() {
        return practice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }
}
