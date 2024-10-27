package pl.edu.pja.formula1.Services;


import org.springframework.stereotype.Service;
import pl.edu.pja.formula1.ApiDTOs.*;
import pl.edu.pja.formula1.Entities.*;
import pl.edu.pja.formula1.Repositories.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FormulaApiService {
    private DriverRepository driverRepository;
    private TeamsRepository teamsRepository;
    private SeasonRepository seasonRepository;
    private RaceRepository raceRepository;
    private Driver_RaceRepository driverRaceRepository;
    private ApiMapper apiMapper;

    public FormulaApiService(DriverRepository driverRepository, TeamsRepository teamsRepository, SeasonRepository seasonRepository, RaceRepository raceRepository, Driver_RaceRepository driverRaceRepository, ApiMapper apiMapper) {
        this.driverRepository = driverRepository;
        this.teamsRepository = teamsRepository;
        this.seasonRepository = seasonRepository;
        this.raceRepository = raceRepository;
        this.driverRaceRepository = driverRaceRepository;
        this.apiMapper = apiMapper;
    }

    public DriverDTO addNewDriver(AddDriverDTO addDriverDTO) {
        Driver1 entityToAdd = apiMapper.map(addDriverDTO);
        Driver1 res = driverRepository.save(entityToAdd);
        return apiMapper.map(res);
    }

    public RaceDTO addNewRace(AddRaceDTO addRaceDTO) {
        int year = Integer.parseInt(addRaceDTO.getSeason());
        Season season = seasonRepository.findByYear(LocalDate.of(year, 1,1 )).orElse(null);
        if (season == null)
                return null;
        Race entity = apiMapper.map(addRaceDTO, season);
        Race res = raceRepository.save(entity);
        return apiMapper.map(res);
    }

    public RaceResultDTO addRaceResult(AddRaceResultDTO addRaceResultDTO) {
        Driver1 driver = driverRepository
                .findDriver1ByNameAndSurname(addRaceResultDTO.getDriverName(),
                        addRaceResultDTO.getDriverSurname());
        int year = Integer.parseInt(addRaceResultDTO.getSeasonYear());
        Season season = seasonRepository.findByYear(LocalDate.of(year, 1,1 )).orElse(null);
        if (season == null)
            return null;
        Race race = raceRepository.findByNameAndSeason(addRaceResultDTO.getRaceName(), season);
        Driver_Race entity = new Driver_Race(new DriverRaceId(driver, race), addRaceResultDTO.getPositionStarted(), addRaceResultDTO.getPositionFinished());
        Driver_Race res = driverRaceRepository.save(entity);
        return apiMapper.map(res);
    }

    public List<GetRaceResultDTO> getRaceResultById(long id) {
        List<Driver_Race> driverRace = driverRaceRepository.findByRaceId(id);
        List<GetRaceResultDTO> res = new ArrayList<>();
        for (var dr : driverRace)
            res.add(apiMapper.mapDr(dr));
        return res;
    }

    public List<RaceDTO> getCalendar() {
        int year = LocalDate.now().getYear();
        Season season = seasonRepository.findByYear(LocalDate.of(year,1,1)).orElse(null);
        if(season == null)
            return null;
        List<Race> entities = raceRepository
                .findAllBySeason(season);

        List<RaceDTO> res = new ArrayList<>();
        for(var e : entities)
            res.add(apiMapper.map(e));
        return res;
    }

    public List<TeamDTO> getTeams() {
        List<TeamDTO> dtos = new ArrayList<>();
        for (var team : teamsRepository.findAll()){
            dtos.add(apiMapper.map(team));
        }
        return dtos;
    }

    public DriverDTO getDriverById(long id) {
        Driver1 dr = driverRepository.findById(id).orElse(null);
        if (dr == null)
            return null;

        return apiMapper.map(dr);
    }

    public DriverDTO getChampionByYear(String year) {
        int yyyy = Integer.parseInt(year);
        Season season = seasonRepository.findByYear(LocalDate.of(yyyy, 1,1 )).orElse(null);
       if(season == null)
           return null;
       Driver1 champ = season.getWinnerDriver();
       return apiMapper.map(champ);
    }

    public List<DriverWithPointsDTO> calculateNewResults(List<GetRaceResultDTO> newResults) {
         Race race =  raceRepository.findByName(newResults.getFirst().getRaceName());
        int year = race.getSeason().getYear().getYear();
        Season season = seasonRepository.findByYear(LocalDate.of(year,1,1)).orElse(null);
        List<Driver_Race> allByIdRaceSeason = driverRaceRepository.findAllById_Race_Season(season);
        if (allByIdRaceSeason == null)
            return null;
        List<GetRaceResultDTO> raceResultDTOS = new ArrayList<>();
        raceResultDTOS.addAll(newResults);
        for(var raceRes : allByIdRaceSeason){
            GetRaceResultDTO toAdd = apiMapper.mapFrom(raceRes);
            if(newResults.stream()
                    .noneMatch(x -> x.getDriverSurname().equals(toAdd.getDriverSurname())
                            && x.getRaceName().equals(toAdd.getRaceName())))
                raceResultDTOS.add(toAdd);
        }
        List<DriverWithPointsDTO> driverStandings = new ArrayList<>();
            driverStandings.addAll(apiMapper.map(raceResultDTOS));

        return driverStandings;

    }

    public void updateDriver(UpdateDriverDTo patchedDTO) {
        driverRepository.save(apiMapper.map(patchedDTO));
    }

    public Team getTeamById(long id) {
       return teamsRepository.findById(id).orElse(null);
    }

    public void updateTeam(UpdateTeamDTO patchedDTO) {
        teamsRepository.save(apiMapper.map(patchedDTO));
    }
}
