package pl.edu.pja.formula1.ApiDTOs;

public class GetRaceResultDTO {
    private String raceName;
    private String driverName;
    private String driverSurname;
    private int positionStarted;
    private int positionFinished;

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
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

    public int getPositionStarted() {
        return positionStarted;
    }

    public void setPositionStarted(int positionStarted) {
        this.positionStarted = positionStarted;
    }

    public int getPositionFinished() {
        return positionFinished;
    }

    public void setPositionFinished(int positionFinished) {
        this.positionFinished = positionFinished;
    }

}
