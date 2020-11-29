package pl.rafalmiskiewicz.ADOZL.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rafalmiskiewicz.ADOZL.schedule.Schedule;
import pl.rafalmiskiewicz.ADOZL.schedule.ScheduleService;
import pl.rafalmiskiewicz.ADOZL.utilities.UserUtilities;

import java.util.List;

@RestController
@RequestMapping("api/")
public class ScheduleApi {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("schedule/all")
    public List<Schedule> getAll(){
        return scheduleService.findAllByUserId(1);
    }
}
