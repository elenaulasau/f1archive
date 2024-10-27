package pl.edu.pja.formula1.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.formula1.ApiDTOs.*;
import pl.edu.pja.formula1.Services.FormulaApiService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/formula/api",
produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class FormulaApiController {
    private FormulaApiService service;
    private final ObjectMapper objectMapper;

    public FormulaApiController(FormulaApiService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }


    @Tag(name = "GET")
    @GetMapping("/race_results/{id}")
    public ResponseEntity<List<GetRaceResultDTO>> getRaceResult(@PathVariable long id){
        List<GetRaceResultDTO> resultDTO = service.getRaceResultById(id);
        if(resultDTO == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(resultDTO);
    }

    @Tag(name = "GET")
    @GetMapping("/driver/{id}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable long id){
        DriverDTO resultDTO = service.getDriverById(id);
        if(resultDTO == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(resultDTO);
    }

    @Tag(name = "GET")
    @GetMapping("/champion/{year}")
    public ResponseEntity<DriverDTO> getChampionByYear(@PathVariable String year){
        DriverDTO resultDTO = service.getChampionByYear(year);
        if(resultDTO == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(resultDTO);
    }

    @Tag(name = "GET")
    @GetMapping("/calendar")
    public ResponseEntity<List<RaceDTO>> getCalendar(){
        List<RaceDTO> resultDTO = service.getCalendar();
        if(resultDTO == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(resultDTO);
    }

    @Tag(name = "GET")
    @GetMapping("/teams")
    public ResponseEntity<List<TeamDTO>> getTeams(){
        List<TeamDTO> resultDTO = service.getTeams();
        if(resultDTO == null)
            return ResponseEntity.status(404).build();
        return ResponseEntity.status(HttpStatus.FOUND).body(resultDTO);
    }


    @Tag(name = "PATCH", description = "Update information")
    @PatchMapping("/driver/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable long id, @RequestBody JsonMergePatch patch){
        try {
            UpdateDriverDTo updateDto = new UpdateDriverDTo(service.getDriverById(id));
            UpdateDriverDTo patchedDTO = applyPatch(updateDto, patch);
            service.updateDriver(patchedDTO);
        }catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }  catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    @Tag(name = "PATCH", description = "Update information")
    @PatchMapping("/team/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable long id, @RequestBody JsonMergePatch patch){
        try {
            UpdateTeamDTO updateDto = new UpdateTeamDTO(service.getTeamById(id));
            UpdateTeamDTO patchedDTO = applyPatch(updateDto, patch);
            service.updateTeam(patchedDTO);
        }catch (NoSuchElementException | NullPointerException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }  catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

    private UpdateTeamDTO applyPatch(UpdateTeamDTO linkDTO, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode linkNode = objectMapper.valueToTree(linkDTO);
        JsonNode patchNode = patch.apply(linkNode);
        return objectMapper.treeToValue(patchNode, UpdateTeamDTO.class);
    }


    private UpdateDriverDTo applyPatch(UpdateDriverDTo linkDTO, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode linkNode = objectMapper.valueToTree(linkDTO);
        JsonNode patchNode = patch.apply(linkNode);
        return objectMapper.treeToValue(patchNode, UpdateDriverDTo.class);
    }

    @Tag(name = "PUT")
    @PutMapping("/add_race_results")
    public ResponseEntity<RaceResultDTO> addRaceResult(@Valid @RequestBody AddRaceResultDTO addRaceResultDTO) {
        RaceResultDTO res = null;
        try{
            res = service.addRaceResult(addRaceResultDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        if (res == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(201).body(res);
    }

    @Tag(name = "POST")
    @PostMapping("/races")
    public ResponseEntity<RaceDTO> addRace(@Valid @RequestBody AddRaceDTO addRaceDTO) {
        RaceDTO res = null;
        try{
            res = service.addNewRace(addRaceDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        if (res == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(201).body(res);
    }

    @Tag(name = "POST")
    @PostMapping("/analyze_result")
    public ResponseEntity<List<DriverWithPointsDTO>> calculeteOutcome(@Valid @RequestBody List<GetRaceResultDTO> newResults) {
        List<DriverWithPointsDTO> res = null;
        try{
            res = service.calculateNewResults(newResults);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        if (res == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(201).body(res);
    }


//    @Tag(name = "POST", description = "Add new driver")
//    @PostMapping("/drivers")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<DriverDTO> addDriver(@Valid @RequestBody AddDriverDTO addDriverDTO) {
//        DriverDTO res = null;
//        try{
//             res = service.addNewDriver(addDriverDTO);
//        } catch (Exception e){
//            return ResponseEntity.badRequest().build();
//        }
//        if (res == null)
//                return ResponseEntity.badRequest().build();
//        return ResponseEntity.status(201).body(res);
//    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handle(MethodArgumentNotValidException ex){
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
