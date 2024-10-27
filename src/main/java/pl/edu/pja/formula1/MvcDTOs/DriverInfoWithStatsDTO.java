package pl.edu.pja.formula1.MvcDTOs;

import jakarta.annotation.Nullable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DriverInfoWithStatsDTO {
    private String name;

    private String surname;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startOfCareer;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Nullable
    private Date endOfCareer;

    private int totalPoint = 0;
    private String photoUrl;

    public String getName() {
        return name;
    }

    public void addPoints(int points){
        totalPoint += points;
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

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
