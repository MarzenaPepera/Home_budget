package pl.rafalmiskiewicz.BUDGET.places;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("placesRepository")
public interface PlacesRepository extends JpaRepository<Places, Integer> {
    public Places findById(int id);

}
