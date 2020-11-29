package pl.rafalmiskiewicz.ADOZL.hours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.ADOZL.schedule.Schedule;
import pl.rafalmiskiewicz.ADOZL.user.Role;
import pl.rafalmiskiewicz.ADOZL.user.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service("hourService")
@Transactional
public class HourServiceImpl implements HourService {
    @Autowired
    EntityManager em;

    @Qualifier("hourRepository")
    @Autowired
    private HourRepository hourRepository;

    @Override
    public List<Hour> findAll() {
        List<Hour> userList = hourRepository.findAll();
        return userList;
    }

    @Override
    public List<Hour> findAll(Hour hour) {
        return hourRepository.findAll(Example.of(hour));

    }

    @Override
    public List<Hour> findAllFilter(Hour hour) {
/*        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hour> cq = cb.createQuery(Hour.class);
        Metamodel m = em.getMetamodel();
        EntityType<Hour> Pet_ = m.entity(Hour.class);

        Root<Hour> pet = cq.from(Hour.class);
        Join<Hour, User> owner = pet.join(Pet_.get);*/
        Integer id = null;
        Date hourTo = hour.getHour_to();
        Set<Role> roles = hour.getUser().getRoles();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hour> cq = cb.createQuery(Hour.class);

        Root<Hour> book = cq.from(Hour.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Role> rolesC = cq.from(Role.class);
        //---------
/*
        Metamodel m = em.getMetamodel();
        EntityType<Hour> hourMetaModel = m.entity(Hour.class);

        Join<Hour, User> owner = book.join(hourMetaModel.getSet("owners", User.class));

*/

//-----
        //Join<Hour, User> owner = book.join();

        if (id != null) {
            predicates.add(cb.equal(book.get("id_user"), id));
        }
        if (hourTo != null) {
            predicates.add(cb.like(book.get("hour_to"), ">" + hourTo ));
        }
        if (roles != null) {
            predicates.add(cb.like(rolesC.get("id"), "=" + roles ));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        //rpSubQ.select(cq);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Hour findHourById(int id) {
        return (Hour) hourRepository.findById(id);
    }

    @Override
    public List<Hour> findAllByUserId(int id) {
        return hourRepository.findAllByUserId(id);
    }

    @Override
    public void saveHour(Hour hour) {

        hourRepository.save(hour);
    }

    @Override
    public void saveHourNew(Hour hour) {

        hourRepository.insertHour(hour.getId_user(), hour.getHour_from(), hour.getHour_to());
    }

    @Override
    public void insertHourString(Hour hour) {
        hourRepository.insertHourString(hour.getId_user(), hour.getHour_from_string(), hour.getHour_to_string());
    }

    @Override
    public void updateHour(Hour hour) {
        hourRepository.updateHour(hour.getId_hours(), hour.getHour_from(), hour.getHour_to());
    }
}
