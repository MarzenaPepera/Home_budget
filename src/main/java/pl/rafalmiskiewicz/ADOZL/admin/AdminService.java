package pl.rafalmiskiewicz.ADOZL.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.rafalmiskiewicz.ADOZL.user.User;

import java.util.List;

public interface AdminService {

    public List<User> findAll();

    Page<User> findAll(Pageable pageable);

    User findUserById(int id);

    void updateUser(int id, int nrRoli, int activity, boolean is_fired);
}
