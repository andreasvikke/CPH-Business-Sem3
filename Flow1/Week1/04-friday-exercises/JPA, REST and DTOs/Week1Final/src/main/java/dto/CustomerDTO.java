package dto;

import entities.BankCustomer;

/**
 *
 * @author andreas
 */
public class CustomerDTO {
    private long customerId;
    private String fullName;
    private String accountNumber;
    private double balance;

    public CustomerDTO(BankCustomer bc) {
        this.customerId = bc.getId();
        this.fullName = bc.getFirstName() + " " + bc.getLastName();
        this.accountNumber = bc.getAccountNumber();
        this.balance = bc.getBalance();
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
