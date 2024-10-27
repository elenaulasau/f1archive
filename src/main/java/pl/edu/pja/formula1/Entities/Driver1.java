package pl.edu.pja.formula1.Entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Driver1")
public class Driver1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    @Column(name = "DriverId")
    private Long driverId;

    @Size(min = 2, max = 25)
    @Column(name = "Name")
    private String name;

    @Size(min = 2, max = 25)
    @Column(name = "Surname")
    private String surname;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "Date_of_birth")
    private Date dateOfBirth;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "Start_of_career")
    private Date startOfCareer;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Nullable
    @Column(name = "End_of_career")
    private Date endOfCareer;
    @Column(name = "Photo")
    private String photoUrl;
    @OneToMany(mappedBy = "seasonId")
    private Set<Season> winningSeasons;

    public Driver1() {
    }

    public Set<Season> getWinningSeasons() {
        return winningSeasons;
    }

    public void setWinningSeasons(Set<Season> winningSeasons) {
        this.winningSeasons = winningSeasons;
    }

    public void setId(Long id) {
        this.driverId = id;
    }

    public Long getId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getStartOfCareer() {
        return startOfCareer;
    }

    public void setStartOfCareer(Date startOfCareer) {
        this.startOfCareer = startOfCareer;
    }

    @Nullable
    public Date getEndOfCareer() {
        return endOfCareer;
    }

    public void setEndOfCareer(@Nullable Date endOfCareer) {
        this.endOfCareer = endOfCareer;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
