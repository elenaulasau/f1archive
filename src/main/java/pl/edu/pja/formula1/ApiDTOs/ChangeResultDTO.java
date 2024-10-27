package pl.edu.pja.formula1.ApiDTOs;

public class ChangeResultDTO {
    private String raceName;
    private String seasonYear;

    private String driverName;
    private String driverSurname;
    private int newFinished;

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSurname() {
        return driverSurname;
    }

    public void setDriverSurname(String driverSurname) {
        this.driverSurname = driverSurname;
    }

    public int getNewFinished() {
        return newFinished;
    }

    public void setNewFinished(int newFinished) {
        this.newFinished = newFinished;
    }
}
