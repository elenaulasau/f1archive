package pl.edu.pja.formula1.ApiDTOs;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class TeamDTO {
    private String name;

    private String countryOfOrigin;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date foundationDate;

    @Size(min = 5, max = 50)
    private String teamPrinciple;
    private List<SeasonDTO> winningSeasons;

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

    public List<SeasonDTO> getWinningSeasons() {
        return winningSeasons;
    }

    public void setWinningSeasons(List<SeasonDTO> winningSeasons) {
        this.winningSeasons = winningSeasons;
    }
}
