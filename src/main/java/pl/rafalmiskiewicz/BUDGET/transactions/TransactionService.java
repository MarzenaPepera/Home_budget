package pl.rafalmiskiewicz.BUDGET.transactions;

import java.util.List;


public interface TransactionService {
    List<Transaction> findAll();
    Transaction findTransactionById(int id);
    List<Transaction> findAllByUserId(int id);
    void saveTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
}
