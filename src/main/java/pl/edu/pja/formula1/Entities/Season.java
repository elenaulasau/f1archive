package pl.edu.pja.formula1.Entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Season")
public class Season {
    @Id
    @Column(name = "SeasonId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;
    @DateTimeFormat(pattern = "yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "DateYear")
    private LocalDate year;

    @ManyToOne
    @JoinColumn(name = "DriverId")
    @Nullable
    private Driver1 winnerDriver1;
    @ManyToOne
    @JoinColumn(name = "TeamId")
    @Nullable
    private Team winnerTeam;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "Start_date")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "End_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "raceId")
    //@OneToMany
    private Set<Race> races;

    public Season() {
    }

    public Season(LocalDate year, @Nullable Driver1 winnerDriver1, @Nullable Team winnerTeam, LocalDate startDate, LocalDate endDate) {
        this.year = year;
        this.winnerDriver1 = winnerDriver1;
        this.winnerTeam = winnerTeam;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return seasonId;
    }

    public void setId(Long id) {
        this.seasonId = id;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Driver1 getWinnerDriver() {
        return winnerDriver1;
    }

    public void setWinnerDriver(Driver1 winnerDriver1) {
        this.winnerDriver1 = winnerDriver1;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
