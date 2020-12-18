package pl.rafalmiskiewicz.BUDGET.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rafalmiskiewicz.BUDGET.user.User;

import java.util.Date;
import java.util.List;


@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Qualifier("transactionRepository")
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
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
    public List<Transaction> findAllByMonth(User user, Date date) {
        return transactionRepository.findAllByMonth(user.getId(),date.getMonth()+1);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.updateTransaction(transaction.getId_transaction(), transaction.getAmount(), transaction.getDescription(), transaction.getDate());
    }
}
