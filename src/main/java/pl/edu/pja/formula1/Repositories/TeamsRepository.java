package pl.edu.pja.formula1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.formula1.Entities.Team;


@Repository
public interface TeamsRepository extends CrudRepository<Team, Long> {
}
