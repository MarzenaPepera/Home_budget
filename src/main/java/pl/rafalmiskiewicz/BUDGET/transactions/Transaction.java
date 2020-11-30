package pl.rafalmiskiewicz.BUDGET.transactions;

import pl.rafalmiskiewicz.BUDGET.user.User;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_transaction")
    private Integer id_transaction;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private User user;

    @Transient
    private String date_string;

    public Integer getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(Integer id_transaction) {
        this.id_transaction = id_transaction;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate_string() {
        return date_string;
    }

    public void setDate_string(String date_string) {
        this.date_string = date_string;
    }

    public void stringToDate() throws ParseException {
        setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date_string+ ":00"));
    }

    public void dateToString() throws ParseException {
        setDate_string(getDate().toString());
    }
}
