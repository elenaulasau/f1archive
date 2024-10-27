package pl.edu.pja.formula1.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "Team")
public class Team {
    @Id
    @Column(name = "TeamId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Size(min = 5, max = 50)
    @Column(name = "Name")
    private String name;

    @Size(min = 5, max = 25)
    @Column(name = "Country_of_origin")
    private String countryOfOrigin;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "Foundation_date")
    private Date foundationDate;

    @Size(min = 5, max = 50)
    @Column(name = "Team_principle")
    //first and last name
    private String teamPrinciple;
    @Column(name = "Logo")
    private String logoUrl;
    @OneToMany(mappedBy = "seasonId")
    private Set<Season> winningSeasons;

    public Team() {
    }


    public Set<Season> getWinningSeasons() {
        return winningSeasons;
    }

    public void setWinningSeasons(Set<Season> winningSeasons) {
        this.winningSeasons = winningSeasons;
    }

    public void setId(Long id) {
        this.teamId = id;
    }

    public Long getId() {
        return teamId;
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
