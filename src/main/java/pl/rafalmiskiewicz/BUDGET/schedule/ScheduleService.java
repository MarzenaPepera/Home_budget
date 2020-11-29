package pl.rafalmiskiewicz.BUDGET.schedule;

import java.util.List;


public interface ScheduleService {

    List<Schedule> findAll();
    List<Schedule> findAll(Schedule schedule);

    Schedule findScheduleById(int id);

    List<Schedule> findAllByUserId(int id);

    //void save(Schedule schedule);
    void saveSchedule(Schedule schedule);


    void saveScheduleNew(Schedule schedule);
    void insertScheduleString(Schedule schedule);

    void updateSchedule(Schedule schedule);


}
