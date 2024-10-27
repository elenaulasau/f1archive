package pl.edu.pja.formula1.Controllers;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.edu.pja.formula1.ApiDTOs.AddRaceResultDTO;
import pl.edu.pja.formula1.ApiDTOs.DriverWithPointsDTO;
import pl.edu.pja.formula1.ApiDTOs.GetRaceResultDTO;
import pl.edu.pja.formula1.ApiDTOs.TeamDTO;
import pl.edu.pja.formula1.MvcDTOs.*;
import pl.edu.pja.formula1.Services.FormulaService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FormulaController {
    private FormulaService service;

    public FormulaController(FormulaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        List<RaceForCalendarDTO> calendarDTOList = service.getCalendar();
        List<GetRaceResultDTO> results = service.getLastRaceResults();
        model.addAttribute("last_race_name", service.getLastRaceName());
        model.addAttribute("last_race_res", results);
        model.addAttribute("calendar", calendarDTOList);
        return "index";
    }
    @GetMapping("/calculator")
    public String raceCalculator(Model model){
        if(!model.containsAttribute("new_results"))
            model.addAttribute("new_results", new AddRaceResultDTO());
        if(!model.containsAttribute("driver_standings"))
            model.addAttribute("driver_standings", service.getCurrentDriverStanding());

        return "calculator";
    }
    @PostMapping("/calculate")
    public String calculate(@Valid @ModelAttribute("new_results")AddRaceResultDTO resultsDTO,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors())
            return "redirect:index";
        try{
            List<DriverWithPointsDTO> drivers = service.getNewDriversStandings(resultsDTO);
            redirectAttributes.addFlashAttribute("driver_standings", drivers);
        } catch (Exception e){
            return "redirect:index";
        }

        return "redirect:calculator";
    }
    @GetMapping("/champions")
    public String getListChampions(Model model){
        model.addAttribute("year", null);
        try{
            List<ChampionDTO> championDTOS = service.getAllChampions();
            model.addAttribute("champions", championDTOS);
        } catch (Exception e){
            model.addAttribute("error", "No champion for given year");
        }
       return "champions";
    }

    @PostMapping("/findChampion")
    public String findChampion( @RequestParam(name = "year", required = false) Integer year,
                                RedirectAttributes redirectAttributes){
        if(year == null)
            return "redirect:champions";
        try{
           ChampionDTO found =  service.getChampionByYear(year);
            redirectAttributes.addFlashAttribute("found_champion", found);
        } catch (Exception e){
            return "redirect:index";
        }

        return "redirect:champions";
    }

    @GetMapping("/drivers")
    public String driversList(Model model){
        List<DriverInfoDTO> infos = new ArrayList<>();
        infos.addAll(service.getAllDriversInfo());
        model.addAttribute("drivers", infos);
       return "drivers";
    }
    @GetMapping("/driver/{id}")
    public String driverById(Model model, @PathVariable long id){
        DriverInfoWithStatsDTO driverById = service.getDriverById(id);
        if(driverById == null) {
            model.addAttribute("error", "drivers with such id not found");
            return "driver";
        }
            model.addAttribute("driver", driverById);
        return "driver";
    }

    @GetMapping("/teams")
    public String teamsList(Model model){
        List<TeamWithIdDTO> allTeamsInfo = service.getAllTeamsInfo();
        List<TeamWithIdDTO> teams = new ArrayList<>(allTeamsInfo);
        model.addAttribute("teams", teams);

        return "teams";
    }

    @GetMapping("/team/{id}")
    public String teamById(Model model, @PathVariable long id){
        TeamWithIdDTO teamById = service.getTeamById(id);
        if(teamById == null) {
            model.addAttribute("error", "Team with such id not found");
            return "team";
        }
        model.addAttribute("team", teamById);
        return "team";
    }
}
