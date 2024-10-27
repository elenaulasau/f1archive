package pl.edu.pja.formula1.ApiDTOs;

public class RaceResultDTO {
    private Long raceId;
    private Long driverId;
    private int positionStarted;
    private int positionFinished;

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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
