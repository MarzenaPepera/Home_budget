package pl.rafalmiskiewicz.BUDGET.hours;

import java.util.List;


public interface HourService {
    List<Hour> findAll();

    List<Hour> findAll(Hour hour);

    List<Hour> findAllFilter(Hour hour);
    Hour findHourById(int id);
    List<Hour> findAllByUserId(int id);
    void saveHour(Hour hour);
    void saveHourNew(Hour hour);
    void insertHourString(Hour hour);
    void updateHour(Hour hour);
}
