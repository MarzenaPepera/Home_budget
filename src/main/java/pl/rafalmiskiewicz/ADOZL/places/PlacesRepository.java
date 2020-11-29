package pl.rafalmiskiewicz.ADOZL.places;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.rafalmiskiewicz.ADOZL.schedule.Schedule;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository("placesRepository")
public interface PlacesRepository extends JpaRepository<Places, Integer> {
    public Places findById(int id);

}
