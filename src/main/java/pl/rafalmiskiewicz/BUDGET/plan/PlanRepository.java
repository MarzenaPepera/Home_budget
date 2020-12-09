package pl.rafalmiskiewicz.BUDGET.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("planRepository")
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    Plan findById(int id);

    @Query(value = "SELECT * FROM plan WHERE plan.id_user=:idUser ", nativeQuery = true)
    List<Plan> findAllByUserId(@Param("idUser") int idUser);

    @Query(value = "SELECT * FROM plan WHERE plan.=:idUser ", nativeQuery = true)
    List<Plan> findAllBySchedule(@Param("idUser") int idUser);

    @Modifying
    @Query(value = "UPDATE `plan` SET `amount` = :amount, `description` = :description, `date` = :date WHERE `plan`.`id_plan` = :id_plan", nativeQuery = true)
    void updateTransaction(@Param("id_plan") int id_plan, @Param("amount") Double amount, @Param("description") String description, @Param("date") Date date);
}
