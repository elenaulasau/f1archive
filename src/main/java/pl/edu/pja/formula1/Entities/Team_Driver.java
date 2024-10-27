package pl.edu.pja.formula1.Entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Team_Driver")
public class Team_Driver {
    @EmbeddedId
    private TeamDriverId id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "Date_joined")
    private Date joined;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Nullable
    @Column(name = "Date_left")
    private Date left;

    public Team_Driver() {
    }

    public TeamDriverId getId() {
        return id;
    }

    public void setId(TeamDriverId id) {
        this.id = id;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    @Nullable
    public Date getLeft() {
        return left;
    }

    public void setLeft(@Nullable Date left) {
        this.left = left;
    }
}

