package pl.rafalmiskiewicz.BUDGET.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository("scheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    public Schedule findById(int id);

    @Query(value = "SELECT * FROM schedule WHERE schedule.id_user=:idUser ", nativeQuery = true)
    List<Schedule> findAllByUserId(@Param("idUser") int idUser);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `schedule` (`id_schedules`, `id_user`, `schedule_from`, `schedule_to`) VALUES (NULL, :id_user, :schedule_from, :schedule_to);", nativeQuery = true)
    public void insertSchedule(@Param("id_user") int id_user, @Param("schedule_from") Date schedule_from, @Param("schedule_to") Date schedule_to);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `schedule` (`id_schedules`, `id_user`, `schedule_from`, `schedule_to`) VALUES (NULL, :id_user, :schadle_from, :schadle_to);", nativeQuery = true)
    public void insertScheduleString(@Param("id_user") int id_user, @Param("schadle_from") String schadle_from, @Param("schadle_to") String schadle_to);

    @Modifying
    @Query(value = "UPDATE `schedule` SET `schadle_from` = :schadle_from, `schadle_to` = :schadle_to WHERE `schedule`.`id_schadles` = :id_schadles", nativeQuery = true)
    void updateSchedule(@Param("id_schadles") int id_schadles, @Param("schadle_from") Date schadle_from, @Param("schadle_to") Date schadle_to);
}
