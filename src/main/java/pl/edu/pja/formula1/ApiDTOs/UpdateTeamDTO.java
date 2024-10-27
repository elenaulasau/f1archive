package pl.edu.pja.formula1.ApiDTOs;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import pl.edu.pja.formula1.Entities.Team;

import java.util.Date;

public class UpdateTeamDTO {
    private Long id;
    private String name;

    private String countryOfOrigin;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date foundationDate;

    @Size(min = 5, max = 50)
    private String teamPrinciple;

    private String logoUrl;

    public UpdateTeamDTO() {
    }

    public UpdateTeamDTO(Team team) {
        id = team.getId();
        name = team.getName();
        countryOfOrigin = team.getCountryOfOrigin();
        foundationDate = team.getFoundationDate();
        teamPrinciple = team.getTeamPrinciple();
        logoUrl = team.getLogoUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getTeamPrinciple() {
        return teamPrinciple;
    }

    public void setTeamPrinciple(String teamPrinciple) {
        this.teamPrinciple = teamPrinciple;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
