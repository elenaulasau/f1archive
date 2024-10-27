package pl.edu.pja.formula1.Entities;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class Penalty {
    @EmbeddedId
    private DriverRaceId id;

    @Size(max = 250)
    private String reason;

    @Size(max = 60)
    private String punishment;

    public DriverRaceId getId() {
        return id;
    }

    public Penalty() {
    }

    public void setId(DriverRaceId id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }
}
