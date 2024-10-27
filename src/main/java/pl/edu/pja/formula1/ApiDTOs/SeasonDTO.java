package pl.edu.pja.formula1.ApiDTOs;

import java.time.LocalDate;

public class SeasonDTO {
    private LocalDate year;

    private String winnerDriver1;
    private String winnerTeam;
    private LocalDate startDate;

    private LocalDate endDate;

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getWinnerDriver1() {
        return winnerDriver1;
    }

    public void setWinnerDriver1(String winnerDriver1) {
        this.winnerDriver1 = winnerDriver1;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
