package pl.edu.pja.formula1.ApiDTOs;

public class DriverWithPointsDTO {
    private String name;
    private String surname;
    private int totalPoints = 0;

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

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void addPoints(int point) {
        totalPoints += point;
    }
}
