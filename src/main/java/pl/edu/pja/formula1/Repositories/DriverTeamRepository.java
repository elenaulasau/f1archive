package pl.edu.pja.formula1.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.formula1.Entities.TeamDriverId;
import pl.edu.pja.formula1.Entities.Team_Driver;


@Repository
public interface DriverTeamRepository extends CrudRepository<Team_Driver, TeamDriverId> {
}
