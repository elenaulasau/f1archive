package pl.edu.pja.formula1.Services;


import org.springframework.stereotype.Service;
import pl.edu.pja.formula1.ApiDTOs.*;
import pl.edu.pja.formula1.Entities.*;

import java.util.*;



@Service
public class ApiMapper {
    public static int[] points = {25,18,15,12,10,8,6,4,2,1,0,0,0,0,0,0,0,0,0,0};

    public Driver1 map(AddDriverDTO dto) {
        Driver1 entity = new Driver1();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setStartOfCareer(dto.getStartOfCareer());
        entity.setEndOfCareer(dto.getEndOfCareer());
        entity.setPhotoUrl(dto.getPhotoUrl());
        return entity;
    }


    public DriverDTO map(Driver1 entity) {
        DriverDTO dto = new DriverDTO();
        dto.setDriverId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setStartOfCareer(entity.getStartOfCareer());
        dto.setEndOfCareer(entity.getEndOfCareer());
        dto.setPhotoUrl(entity.getPhotoUrl());
        return dto;
    }

    public Race map(AddRaceDTO dto, Season season) {
        Race res = new Race();
        res.setDate(dto.getDate());
        res.setLaps(dto.getLaps());
        res.setName(dto.getName());
        res.setSeason(season);
        res.setPlace(dto.getPlace());
        res.setWeather(dto.getWeather());
        return res;
    }

    public RaceDTO map(Race entity) {
        RaceDTO dto = new RaceDTO();
        //dto.setSeason(entity.getSeason());
        dto.setDate(entity.getDate());
        dto.setLaps(entity.getLaps());
        dto.setName(entity.getName());
        dto.setPlace(entity.getPlace());
        dto.setRaceId(entity.getRaceId());
        dto.setWeather(entity.getWeather());
        return dto;
    }

    public RaceResultDTO map(Driver_Race res) {
        RaceResultDTO dto = new RaceResultDTO();
        dto.setDriverId(res.getId().getDriver1().getId());
        dto.setRaceId(res.getId().getRace().getRaceId());
        dto.setPositionStarted(res.getStarted());
        dto.setPositionFinished(res.getFinished());
        return dto;
    }

    public GetRaceResultDTO mapFrom(Driver_Race res) {
        GetRaceResultDTO dto = new GetRaceResultDTO();
        dto.setDriverName(res.getId().getDriver1().getName());
        dto.setDriverSurname(res.getId().getDriver1().getSurname());
        dto.setRaceName(res.getId().getRace().getName());
        dto.setPositionStarted(res.getStarted());
        dto.setPositionFinished(res.getFinished());
        return dto;
    }

    public GetRaceResultDTO mapDr(Driver_Race driverRace) {
        GetRaceResultDTO dto = new GetRaceResultDTO();
        dto.setDriverName(driverRace.getId().getDriver1().getName());
        dto.setDriverSurname(driverRace.getId().getDriver1().getSurname());
        dto.setPositionFinished(driverRace.getFinished());
        dto.setPositionStarted(driverRace.getStarted());
        dto.setRaceName(driverRace.getId().getRace().getName());
        return dto;
    }

    public TeamDTO map(Team team) {
        TeamDTO dto = new TeamDTO();
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



    public List<DriverWithPointsDTO> map(List<GetRaceResultDTO> raceResultDTOS) {
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

    public Driver1 map(UpdateDriverDTo dto) {
        Driver1 res = new Driver1();
        res.setId(dto.getId());
        res.setDateOfBirth(dto.getDateOfBirth());
        res.setEndOfCareer(dto.getEndOfCareer());
        res.setName(dto.getName());
        res.setPhotoUrl(dto.getPhotoUrl());
        res.setStartOfCareer(dto.getStartOfCareer());
        res.setSurname(dto.getSurname());
        return res;
    }

    public Team map(UpdateTeamDTO dto) {
        Team res = new Team();
        res.setCountryOfOrigin(dto.getCountryOfOrigin());
        res.setFoundationDate(dto.getFoundationDate());
        res.setId(dto.getId());
        res.setLogoUrl(dto.getLogoUrl());
        res.setName(dto.getName());
        res.setTeamPrinciple(dto.getTeamPrinciple());
        return res;
    }
}
