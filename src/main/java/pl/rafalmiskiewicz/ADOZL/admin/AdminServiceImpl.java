package pl.rafalmiskiewicz.ADOZL.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.ADOZL.user.User;

import java.util.List;


@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Qualifier("adminRepository")
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<User> findAll() {
        List<User> userList = adminRepository.findAll();
        return userList;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> userList = adminRepository.findAll(pageable);
        return userList;
    }

    @Override
    public User findUserById(int id) {
        User user = adminRepository.findUserById(id);
        return user;
    }

    @Override
    public void updateUser(int id, int nrRoli, int activity, boolean is_fired) {
        adminRepository.updateActivationUser(activity,is_fired, id);
        adminRepository.updateRoleUser(nrRoli, id);
    }
}
