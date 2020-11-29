package pl.rafalmiskiewicz.BUDGET.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rafalmiskiewicz.BUDGET.schedule.Schedule;
import pl.rafalmiskiewicz.BUDGET.schedule.ScheduleService;

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
