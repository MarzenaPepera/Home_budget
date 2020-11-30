package pl.rafalmiskiewicz.BUDGET.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository("transactionRepository")
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public Transaction findById(int id);

    @Query(value = "SELECT * FROM transaction WHERE transaction.id_user=:idUser ", nativeQuery = true)
    List<Transaction> findAllByUserId(@Param("idUser") int idUser);

    @Query(value = "SELECT * FROM transaction WHERE transaction.=:idUser ", nativeQuery = true)
    List<Transaction> findAllBySchedule(@Param("idUser") int idUser);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `transaction` (`id_transactions`, `id_user`, `transaction_from`, `transaction_to`) VALUES (NULL, :id_user, :transaction_from, :transaction_to);", nativeQuery = true)
    public void insertTransaction(@Param("id_user") int id_user, @Param("transaction_from") Date transaction_from, @Param("transaction_to") Date transaction_to);

/*
    @Query(value = "SELECT * FROM transactions WHERE (:idUser is null or transactions.idUser = :idUser)", nativeQuery = true)
    List<Transaction> findAllFilter(@Param("idUser") Integer idUser);
*/

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `transaction` (`id_transactions`, `id_user`, `transaction_from`, `transaction_to`) VALUES (NULL, :id_user, :transaction_from, :transaction_to);", nativeQuery = true)
    public void insertTransactionString(@Param("id_user") int id_user, @Param("transaction_from") String transaction_from, @Param("transaction_to") String transaction_to);

    @Modifying
    @Query(value = "UPDATE `transaction` SET `amount` = :amount, `description` = :description, `date` = :date WHERE `transaction`.`id_transaction` = :id_transaction", nativeQuery = true)
    void updateTransaction(@Param("id_transaction") int id_transaction, @Param("amount") Double amount, @Param("description") String description, @Param("date") Date date);
}
