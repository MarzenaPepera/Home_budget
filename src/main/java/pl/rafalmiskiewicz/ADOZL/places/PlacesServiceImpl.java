package pl.rafalmiskiewicz.ADOZL.places;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.ADOZL.schedule.Schedule;
import pl.rafalmiskiewicz.ADOZL.schedule.ScheduleRepository;
import pl.rafalmiskiewicz.ADOZL.schedule.ScheduleService;

import java.util.List;


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
