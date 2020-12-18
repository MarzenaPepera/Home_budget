package pl.rafalmiskiewicz.BUDGET.transactions;

import pl.rafalmiskiewicz.BUDGET.user.User;

import java.util.Date;
import java.util.List;


public interface TransactionService {
    List<Transaction> findAll();
    Transaction findTransactionById(int id);
    List<Transaction> findAllByUserId(int id);
    List<Transaction> findAllByMonth(User user, Date date);
    void saveTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
}
