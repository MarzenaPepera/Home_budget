package pl.rafalmiskiewicz.BUDGET.places;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("placesService")
@Transactional
public class PlacesServiceImpl implements PlacesService {

    @Qualifier("placesRepository")
    @Autowired
    private PlacesRepository placesRepository;


    @Override
    public Places findPlacesById(int id) {
        return (Places) placesRepository.findById(id);
    }


}
