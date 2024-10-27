package pl.edu.pja.formula1.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "User1")
public class User1 {
    @Id
    @Size(min = 5, max = 25)
    @Column(name = "Username", unique = true)
    private String username;

    @Size(min = 2, max = 25)
    @Column(name = "Name")
    private String name;

    @Size(min = 2, max = 25)
    @Column(name = "Surname")
    private String surname;

    @Size(min = 7, max = 25)
    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "Favourite_Team")
    private Team favTeam;

    @Size(max = 1)
    private char isAdmin;


    public User1() {
    }

    public char getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(char isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Team getFavTeam() {
        return favTeam;
    }

    public void setFavTeam(Team favTeam) {
        this.favTeam = favTeam;
    }
}
