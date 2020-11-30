package pl.rafalmiskiewicz.BUDGET.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.BUDGET.user.Role;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    EntityManager em;

    @Qualifier("transactionRepository")
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        List<Transaction> userList = transactionRepository.findAll();
        return userList;
    }

    @Override
    public List<Transaction> findAll(Transaction transaction) {
        return transactionRepository.findAll(Example.of(transaction));

    }

    @Override
    public List<Transaction> findAllFilter(Transaction transaction) {
/*        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Metamodel m = em.getMetamodel();
        EntityType<Transaction> Pet_ = m.entity(Transaction.class);

        Root<Transaction> pet = cq.from(Transaction.class);
        Join<Transaction, User> owner = pet.join(Pet_.get);*/
        Integer id = null;
        Date transactionTo = transaction.getDate();
        Set<Role> roles = transaction.getUser().getRoles();


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);

        Root<Transaction> book = cq.from(Transaction.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Role> rolesC = cq.from(Role.class);
        //---------
/*
        Metamodel m = em.getMetamodel();
        EntityType<Transaction> transactionMetaModel = m.entity(Transaction.class);

        Join<Transaction, User> owner = book.join(transactionMetaModel.getSet("owners", User.class));

*/

//-----
        //Join<Transaction, User> owner = book.join();

        if (id != null) {
            predicates.add(cb.equal(book.get("id_user"), id));
        }
        if (transactionTo != null) {
            predicates.add(cb.like(book.get("transaction_to"), ">" + transactionTo));
        }
        if (roles != null) {
            predicates.add(cb.like(rolesC.get("id"), "=" + roles));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        //rpSubQ.select(cq);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Transaction findTransactionById(int id) {
        return (Transaction) transactionRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllByUserId(int id) {
        return transactionRepository.findAllByUserId(id);
    }

    @Override
    public void saveTransaction(Transaction transaction) {

        transactionRepository.save(transaction);
    }

//    @Override
//    public void saveTransactionNew(Transaction transaction) {
//        transactionRepository.insertTransaction(transaction.getId_user(), transaction.getTransaction_from(), transaction.getTransaction_to());
//    }
//
//    @Override
//    public void insertTransactionString(Transaction transaction) {
//        transactionRepository.insertTransactionString(transaction.getId_user(), transaction.getTransaction_from_string(), transaction.getTransaction_to_string());
//    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.updateTransaction(transaction.getId_transaction(), transaction.getAmount(), transaction.getDescription(), transaction.getDate());
    }
}
