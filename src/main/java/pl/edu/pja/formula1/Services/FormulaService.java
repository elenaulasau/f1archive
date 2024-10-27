package pl.edu.pja.formula1.Services;


import org.springframework.stereotype.Service;
import pl.edu.pja.formula1.ApiDTOs.*;
import pl.edu.pja.formula1.Entities.*;
import pl.edu.pja.formula1.MvcDTOs.*;
import pl.edu.pja.formula1.Repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FormulaService {
    private final DriverRepository driverRepository;
    private final TeamsRepository teamsRepository;
    private final SeasonRepository seasonRepository;
    private final RaceRepository raceRepository;
    private final Driver_RaceRepository driverRaceRepository;
    private final DriverTeamRepository driverTeamRepository;
    private final Mapper mapper;

    public FormulaService(DriverRepository driverRepository, TeamsRepository teamsRepository, SeasonRepository seasonRepository, RaceRepository raceRepository, Driver_RaceRepository driverRaceRepository, DriverTeamRepository driverTeamRepository, Mapper mapper) {
        this.driverRepository = driverRepository;
        this.teamsRepository = teamsRepository;
        this.seasonRepository = seasonRepository;
        this.raceRepository = raceRepository;
        this.driverRaceRepository = driverRaceRepository;
        this.driverTeamRepository = driverTeamRepository;
        this.mapper = mapper;
    }

    public List<RaceForCalendarDTO> getCalendar() {
        int year = LocalDate.now().getYear();
        Season season = seasonRepository.findByYear(LocalDate.of(year, 1, 1)).orElse(null);
        if (season == null)
            return null;
        List<Race> entities = raceRepository
                .findAllBySeason(season);

        List<RaceForCalendarDTO> res = new ArrayList<>();
        for (var e : entities)
            res.add(mapper.map(e));
        return res;
    }

    public String getLastRaceName() {
        int year = LocalDate.now().getYear();
        Season season = seasonRepository.findByYear(LocalDate.of(year, 1, 1)).orElse(null);
        Race race = raceRepository.findAllBySeasonOrderByDate(season).getFirst();
        return race.getName();
    }

    public List<GetRaceResultDTO> getLastRaceResults() {
        int year = LocalDate.now().getYear();
        Season season = seasonRepository.findByYear(LocalDate.of(year, 1, 1)).orElse(null);
        Race race = raceRepository.findAllBySeasonOrderByDate(season).getFirst();
        List<Driver_Race> driverRaces = driverRaceRepository.findByRaceId(race.getRaceId());
        return mapper.map(driverRaces);
    }

    public List<DriverInfoDTO> getAllDriversInfo() {
        List<DriverInfoDTO> res = new ArrayList<>();
        for (var dt : driverRepository.findAll()) {
            DriverInfoDTO toAdd = mapper.map(dt);
            if(toAdd.getEndOfCareer() == null)
                res.add(toAdd);
            if(!res.stream()
                    .anyMatch(x -> x.getSurname().equals(toAdd.getSurname())))
                res.add(toAdd);
        }
        return res;
    }

    public DriverInfoWithStatsDTO getDriverById(long id) {
       Driver1 entity =  driverRepository.findById(id).orElse(null);
       if(entity == null)
           return null;
       return mapper.mapFrom(entity, driverRaceRepository.findAllById_Driver1(entity));
    }

    public List<TeamWithIdDTO> getAllTeamsInfo() {
        List<TeamWithIdDTO> dtos = new ArrayList<>();
        for (var team : teamsRepository.findAll()){
            dtos.add(mapper.map(team));
        }
        return dtos;
    }

    public TeamWithIdDTO getTeamById(long id) {
        Team team = teamsRepository.findById(id).orElse(null);
        if(team == null)
            return null;
       return mapper.map(team);
    }

    public List<DriverWithPointsDTO> getCurrentDriverStanding() {
       Season season = seasonRepository.findByYear(LocalDate.of(LocalDate.now().getYear(), 1,1)).orElse(null);
       if (season == null)
           return null;
        List<Driver_Race> allByIdRaceSeason = driverRaceRepository.findAllById_Race_Season(season);
        if (allByIdRaceSeason == null)
            return null;
        List<GetRaceResultDTO> raceResultDTOS = new ArrayList<>();
        for(var raceRes : allByIdRaceSeason){
            GetRaceResultDTO toAdd = mapper.map(raceRes);
            raceResultDTOS.add(toAdd);
        }
        List<DriverWithPointsDTO> driverStandings = new ArrayList<>();
        driverStandings.addAll(mapper.mapFrom(raceResultDTOS));

        return driverStandings;
    }

    public List<TeamWithIdDTO> getCurrentTeamStanding() {
        return null;
    }

    public List<DriverWithPointsDTO> getNewDriversStandings(AddRaceResultDTO newResultDto) {
        Race race =  raceRepository.findByName(newResultDto.getRaceName());
        int year = race.getSeason().getYear().getYear();
        Season season = seasonRepository.findByYear(LocalDate.of(year,1,1)).orElse(null);
        List<Driver_Race> allByIdRaceSeason = driverRaceRepository.findAllById_Race_Season(season);
        if (allByIdRaceSeason == null)
            return null;
        List<GetRaceResultDTO> raceResultDTOS = new ArrayList<>();
        raceResultDTOS.add(mapper.mapTo(newResultDto));
        for(var raceRes : allByIdRaceSeason){
            GetRaceResultDTO toAdd = mapper.mapFrom(raceRes);
            if(raceResultDTOS.stream()
                    .noneMatch(x -> x.getDriverSurname().equals(toAdd.getDriverSurname())
                            && x.getRaceName().equals(toAdd.getRaceName())))
                raceResultDTOS.add(toAdd);
        }
        List<DriverWithPointsDTO> driverStandings = new ArrayList<>();
        driverStandings.addAll(mapper.mapFrom(raceResultDTOS));

        return driverStandings;
    }

    public List<ChampionDTO> getAllChampions() {
        List<ChampionDTO> champs = new ArrayList<>();
        for (Season s : seasonRepository.findAll()){
            if(s.getWinnerDriver() != null){
                champs.add(mapper.mapFrom(s));
            }
        }
        return champs;
    }
    public ChampionDTO getChampionByYear(int year) {
        Season season = seasonRepository.findByYear(LocalDate.of(year, 1,1 )).orElse(null);
        if(season == null)
            return null;
        return mapper.mapFrom(season);
    }
}
