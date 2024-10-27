package pl.edu.pja.formula1.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class TeamDriverId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "driverId", referencedColumnName = "DriverId")
    private Driver1 driver1;

    @ManyToOne
    @JoinColumn(name = "teamId", referencedColumnName = "TeamId")
    private Team team;

    public Driver1 getDriver() {
        return driver1;
    }

    public void setDriver(Driver1 driver1) {
        this.driver1 = driver1;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
