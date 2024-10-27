package pl.edu.pja.formula1.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Driver_Race")
public class Driver_Race {
    @EmbeddedId
    private DriverRaceId id;

    @Column(name = "Position_started")
    private int started;

    @Column(name = "Position_finished")
    private int finished;

    public Driver_Race(DriverRaceId id, int started, int finished) {
        this.id = id;
        this.started = started;
        this.finished = finished;
    }

    public DriverRaceId getId() {
        return id;
    }

    public Driver_Race() {
    }

    public void setId(DriverRaceId id) {
        this.id = id;
    }

    public int getStarted() {
        return started;
    }

    public void setStarted(int started) {
        this.started = started;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }
}
