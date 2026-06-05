package rs.ac.singidunum.isa.projekat.dto.request;

public class BookRequest {
    private int dasId;
    private int userId;

    public BookRequest(int dasId, int userId) {
        this.dasId = dasId;
        this.userId = userId;
    }

    public int getDasId() {
        return dasId;
    }

    public void setDasId(int dasId) {
        this.dasId = dasId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "dasId=" + dasId +
                ", userId=" + userId +
                '}';
    }
}
