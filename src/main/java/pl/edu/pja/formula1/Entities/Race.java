package pl.edu.pja.formula1.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Race")
public class Race {
    @Id
    @Column(name = "RaceId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long raceId;
    @Size(max = 25)
    @Column(name = "Place")
    private String place;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "Date")
    private Date date;

    //TODO place + " " + year
    @Column(name = "Name")
    private String name;

    @Column(name = "Laps")
    private Integer laps;
    @Size(max = 50)
    @Column(name = "Weather")
    private String weather;
    @ManyToOne
    @JoinColumn(name = "Season")
    private Season season;

    public Race() {
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

}
