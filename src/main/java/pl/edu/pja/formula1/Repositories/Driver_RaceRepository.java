package pl.edu.pja.formula1.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.formula1.Entities.Driver1;
import pl.edu.pja.formula1.Entities.DriverRaceId;
import pl.edu.pja.formula1.Entities.Driver_Race;
import pl.edu.pja.formula1.Entities.Season;

import java.util.List;

@Repository
public interface Driver_RaceRepository extends CrudRepository<Driver_Race, DriverRaceId> {

    List<Driver_Race> findAllById_Driver1(Driver1 id_driver1);

    @Query("select dr as password from Driver_Race dr where dr.id.race.raceId = :raceId")
    List<Driver_Race> findByRaceId(long raceId);

    List<Driver_Race> findAllById_Race_Season(Season season);
}
