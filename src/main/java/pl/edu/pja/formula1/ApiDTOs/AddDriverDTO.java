package pl.edu.pja.formula1.ApiDTOs;

import jakarta.annotation.Nullable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AddDriverDTO {
    @Size(min = 2, max = 25)
    private String name;

    @Size(min = 2, max = 25)
    private String surname;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startOfCareer;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Nullable
    private Date endOfCareer;
    private String photoUrl;

    public AddDriverDTO() {
    }

    public AddDriverDTO(DriverDTO dto) {
        this.name = dto.getName();
        surname = dto.getSurname();
        dateOfBirth = dto.getDateOfBirth();
        startOfCareer = dto.getStartOfCareer();
        endOfCareer = dto.getEndOfCareer();
        photoUrl = dto.getPhotoUrl();
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
