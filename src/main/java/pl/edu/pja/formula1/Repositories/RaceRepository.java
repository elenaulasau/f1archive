package pl.edu.pja.formula1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.formula1.Entities.Race;
import pl.edu.pja.formula1.Entities.Season;

import java.util.Date;
import java.util.List;


@Repository
public interface RaceRepository extends CrudRepository<Race, Long> {
    List<Race> findAllBySeasonOrderByDate(Season season);
    Race findByNameAndSeason(String name, Season season);
    List<Race> findAllBySeason(Season season);

    Race findByName(String raceName);
}
