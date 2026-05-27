package rs.ac.singidunum.isa.projekat.dto;

public class DoctorRequest {
    private String practice;

    public DoctorRequest(String practice) {
        this.practice = practice;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }
}
