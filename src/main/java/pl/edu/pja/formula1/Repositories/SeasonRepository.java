package pl.edu.pja.formula1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.formula1.Entities.Season;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SeasonRepository extends CrudRepository<Season, Long> {
    Optional<Season> findByYear(LocalDate year);
}
