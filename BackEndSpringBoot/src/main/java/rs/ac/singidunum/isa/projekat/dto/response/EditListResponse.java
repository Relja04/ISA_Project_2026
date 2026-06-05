package rs.ac.singidunum.isa.projekat.dto.response;

import java.time.OffsetDateTime;

public class EditListResponse {
    private OffsetDateTime slotStart;
    private OffsetDateTime slotEnd;
    private int dasId;

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

    public int getDasId() {
        return dasId;
    }

    public void setDasId(int dasId) {
        this.dasId = dasId;
    }

    public EditListResponse(OffsetDateTime slotStart, OffsetDateTime slotEnd, int dasId) {
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.dasId = dasId;
    }
}
