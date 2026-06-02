package rs.ac.singidunum.isa.projekat.dto.response;

public class ApiError {
    private String message;
    private int status;

    public ApiError(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
