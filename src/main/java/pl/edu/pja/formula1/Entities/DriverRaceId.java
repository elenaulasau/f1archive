package pl.edu.pja.formula1.Entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class DriverRaceId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "driverId", referencedColumnName = "DriverId")
    private Driver1 driver1;

    @ManyToOne
    @JoinColumn(name = "raceId", referencedColumnName = "RaceId")
    private Race race;

    public DriverRaceId() {
    }

    public DriverRaceId(Driver1 driver1, Race race) {
        this.driver1 = driver1;
        this.race = race;
    }

    public Driver1 getDriver1() {
        return driver1;
    }

    public void setDriver1(Driver1 driver1) {
        this.driver1 = driver1;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}