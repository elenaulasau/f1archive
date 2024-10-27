package pl.edu.pja.formula1.Services;


import org.springframework.stereotype.Service;
import pl.edu.pja.formula1.ApiDTOs.*;
import pl.edu.pja.formula1.Entities.*;
import pl.edu.pja.formula1.MvcDTOs.*;

import java.util.*;

@Service
public class Mapper {
    public static int[] points = {25,18,15,12,10,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0};

    public RaceForCalendarDTO map(Race e) {
        RaceForCalendarDTO dto = new RaceForCalendarDTO();
        dto.setDate(e.getDate());
        dto.setLaps(e.getLaps());
        dto.setName(e.getName());
        dto.setPlace(e.getPlace());
        return dto;
    }

    public List<GetRaceResultDTO> map(List<Driver_Race> driverRaces) {
        List<GetRaceResultDTO> res = new ArrayList<>();
        for (var dr : driverRaces){
            GetRaceResultDTO toAdd = new GetRaceResultDTO();
            toAdd.setDriverName(dr.getId().getDriver1().getName());
            toAdd.setDriverSurname(dr.getId().getDriver1().getSurname());
            toAdd.setPositionFinished(dr.getFinished());
            toAdd.setPositionStarted(dr.getStarted());
            res.add(toAdd);
        }

        return res;
    }


    public DriverInfoDTO map(Driver1 dt) {
        DriverInfoDTO res = new DriverInfoDTO();
        res.setId(dt.getId());
        res.setDateOfBirth(dt.getDateOfBirth());
        res.setEndOfCareer(dt.getEndOfCareer());
        res.setName(dt.getName());
        res.setStartOfCareer(dt.getStartOfCareer());
        res.setSurname(dt.getSurname());
        return res;
    }

    public DriverInfoWithStatsDTO mapFrom(Driver1 entity, List<Driver_Race> races) {
        DriverInfoWithStatsDTO dto = new DriverInfoWithStatsDTO();
        for(var race : races){
            int finished = race.getFinished();
            if(finished != -1)
                dto.addPoints(points[finished-1]);
        }
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setEndOfCareer(entity.getEndOfCareer());
        dto.setName(entity.getName());
        dto.setPhotoUrl(entity.getPhotoUrl());
        dto.setStartOfCareer(entity.getStartOfCareer());
        dto.setSurname(entity.getSurname());
        return dto;
    }

    public TeamWithIdDTO map(Team team) {
        TeamWithIdDTO dto = new TeamWithIdDTO();
        dto.setPhotourl(team.getLogoUrl());
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setTeamPrinciple(team.getTeamPrinciple());
        dto.setCountryOfOrigin(team.getCountryOfOrigin());
        dto.setFoundationDate(team.getFoundationDate());
        Set<Season> winningSeasons = team.getWinningSeasons();
        List<SeasonDTO> b = new ArrayList<>();
        for(Season s : winningSeasons){
            SeasonDTO sdto = new SeasonDTO();
            sdto.setEndDate(s.getEndDate());
            sdto.setStartDate(s.getStartDate());
            sdto.setYear(s.getYear());
            if(s.getWinnerDriver() != null)
                sdto.setWinnerDriver1(s.getWinnerDriver().getName());
            if(s.getWinnerTeam() != null)
                sdto.setWinnerTeam(s.getWinnerTeam().getName());
            b.add(sdto);
        }
        dto.setWinningSeasons(b);
        return dto;
    }

    public GetRaceResultDTO map(Driver_Race res) {
        GetRaceResultDTO dto = new GetRaceResultDTO();
        dto.setDriverName(res.getId().getDriver1().getName());
        dto.setDriverSurname(res.getId().getDriver1().getSurname());
        dto.setRaceName(res.getId().getRace().getName());
        dto.setPositionStarted(res.getStarted());
        dto.setPositionFinished(res.getFinished());
        return dto;
    }

    public List<DriverWithPointsDTO> mapFrom(List<GetRaceResultDTO> raceResultDTOS) {
        Map<String, String> driverSurnames = new HashMap<>();
        for(var res : raceResultDTOS){
            driverSurnames.putIfAbsent(res.getDriverSurname(), res.getDriverName());
        }
        List<DriverWithPointsDTO> results = new ArrayList<>();
        for(var mapEntry : driverSurnames.entrySet()){
            DriverWithPointsDTO dto = new DriverWithPointsDTO();
            dto.setSurname(mapEntry.getKey());
            dto.setName(mapEntry.getValue());
            List<GetRaceResultDTO> listOfRacesForDriver = raceResultDTOS.stream()
                    .filter(x -> x.getDriverSurname().equals(mapEntry.getKey())  &&  x.getDriverName().equals(mapEntry.getValue()))
                    .toList();

            for(var raceRes : listOfRacesForDriver){
                //-1 for not finished
                if(raceRes.getPositionFinished() != -1)
                    dto.addPoints(points[raceRes.getPositionFinished()-1]);
            }
            results.add(dto);
        }

        return results.stream()
                .sorted(Comparator.comparingInt(DriverWithPointsDTO::getTotalPoints))
                .toList()
                .reversed();
    }

    public GetRaceResultDTO mapTo(AddRaceResultDTO newRes) {
        GetRaceResultDTO res = new GetRaceResultDTO();
        res.setDriverName(newRes.getDriverName());
        res.setDriverSurname(newRes.getDriverSurname());
        res.setPositionFinished(newRes.getPositionFinished());
        res.setPositionStarted(newRes.getPositionStarted());
        res.setRaceName(newRes.getRaceName());
        return res;
    }

    public GetRaceResultDTO mapFrom(Driver_Race raceRes) {
        GetRaceResultDTO dto = new GetRaceResultDTO();
        dto.setDriverName(raceRes.getId().getDriver1().getName());
        dto.setDriverSurname(raceRes.getId().getDriver1().getSurname());
        dto.setRaceName(raceRes.getId().getRace().getName());
        dto.setPositionStarted(raceRes.getStarted());
        dto.setPositionFinished(raceRes.getFinished());
        return dto;
    }

    public ChampionDTO mapFrom(Season season) {
        ChampionDTO res = new ChampionDTO();
        Driver1 winnerDriver = season.getWinnerDriver();
        Team winnerTeam = season.getWinnerTeam();
        if(winnerDriver != null && winnerTeam != null){
            res.setName(winnerDriver.getName());
            res.setSurname(winnerDriver.getSurname());
            res.setTeam(winnerTeam.getName());
            res.setYear(season.getYear().getYear());
            return res;
        }
            return null;
    }
}
