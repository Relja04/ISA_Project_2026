package rs.ac.singidunum.isa.projekat.dto.request;

public class AppointmentEditRequest {
    private int oldAppointmentId;
    private int newAppointmentId;
    private int userId;

    public int getOldAppointmentId() {
        return oldAppointmentId;
    }

    public void setOldAppointmentId(int oldAppointmentId) {
        this.oldAppointmentId = oldAppointmentId;
    }

    public int getNewAppointmentId() {
        return newAppointmentId;
    }

    public void setNewAppointmentId(int newAppointmentId) {
        this.newAppointmentId = newAppointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public AppointmentEditRequest(int oldAppointmentId, int newAppointmentId, int userId) {
        this.oldAppointmentId = oldAppointmentId;
        this.newAppointmentId = newAppointmentId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AppointmentEditRequest{" +
                "oldAppointmentId=" + oldAppointmentId +
                ", newAppointmentId=" + newAppointmentId +
                ", userId=" + userId +
                '}';
    }
}
