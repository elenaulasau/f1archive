package pl.edu.pja.formula1.Repositories;

import jakarta.validation.constraints.Size;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.formula1.Entities.Driver1;


@Repository
public interface DriverRepository extends CrudRepository<Driver1, Long> {
    Driver1 findDriver1ByNameAndSurname(@Size(min = 2, max = 25) String name, @Size(min = 2, max = 25) String surname);
}
