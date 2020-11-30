package pl.rafalmiskiewicz.BUDGET.transactions;

import java.util.List;


public interface TransactionService {
    List<Transaction> findAll();

    List<Transaction> findAll(Transaction transaction);

    List<Transaction> findAllFilter(Transaction transaction);
    Transaction findTransactionById(int id);
    List<Transaction> findAllByUserId(int id);
    void saveTransaction(Transaction transaction);
    //void saveTransactionNew(Transaction transaction);
    //void insertTransactionString(Transaction transaction);
    void updateTransaction(Transaction transaction);
}
