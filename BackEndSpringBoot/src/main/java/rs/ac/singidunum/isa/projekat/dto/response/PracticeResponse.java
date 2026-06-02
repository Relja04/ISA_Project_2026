package rs.ac.singidunum.isa.projekat.dto.response;

public class PracticeResponse {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PracticeResponse(String name) {
        this.name = name;
    }
}
