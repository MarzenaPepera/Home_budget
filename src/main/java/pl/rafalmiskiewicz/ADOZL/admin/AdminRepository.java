package pl.rafalmiskiewicz.ADOZL.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.rafalmiskiewicz.ADOZL.user.User;


@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);

    @Modifying
    @Query("UPDATE User u SET u.active = :intActive, u.is_fired = :is_fired WHERE u.id= :id")
    void updateActivationUser(@Param("intActive") int active, @Param("is_fired") boolean is_fired, @Param("id") int id);

    @Modifying
    @Query(value = "UPDATE user_role r SET r.id_role = :roleId WHERE r.id_user= :id", nativeQuery = true)
    void updateRoleUser(@Param("roleId") int nrRoli, @Param("id") int id);

}
