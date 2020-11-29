package pl.rafalmiskiewicz.ADOZL.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.ADOZL.places.Places;

import java.util.List;


@Service("scheduleService")
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    @Qualifier("scheduleRepository")
    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public List<Schedule> findAll() {
        List<Schedule> userList = scheduleRepository.findAll();
        return userList;
    }

    @Override
    public List<Schedule> findAll(Schedule schedule) {
        return scheduleRepository.findAll(Example.of(schedule));

    }

    @Override
    public Schedule findScheduleById(int id) {
        return (Schedule) scheduleRepository.findById(id);
    }

    @Override
    public List<Schedule> findAllByUserId(int id) {
        return scheduleRepository.findAllByUserId(id);
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void saveScheduleNew(Schedule schedule) {

        scheduleRepository.insertSchedule(schedule.getId_user(),schedule.getHour_from(),schedule.getHour_to());
    }

    @Override
    public void insertScheduleString(Schedule schedule) {
        scheduleRepository.insertScheduleString(schedule.getId_user(),schedule.getHour_from_string(),schedule.getHour_to_string());
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleRepository.updateSchedule(schedule.getId_schedule(),schedule.getHour_from(),schedule.getHour_to());
    }


}
