package org.example;

public class SavingsAccount extends Account implements Accounts {

    public SavingsAccount(int balance) {
        super(balance);
    }

    @Override
    public void pay(int amount) {       //Нельзя платить со сберегательного
        this.unavailableOperation();
    }

    @Override
    public void transfer(Account account, int amount) {       //Переводим на другой счет
        if (amount <= balance) {                              //Если баланс это позволяет
            if (account.addMoney(amount)) {                   //И целевой счет это позволяет
                balance -= amount;
                System.out.println("This " + amount + "$ have successfully transferred from Savings account. " + this.toString());
            }
        } else {
            this.unavailableOperation();
        }
    }

    @Override
    public boolean addMoney(int amount) {      //Даем пополнить на любую сумму
        balance += amount;
        System.out.println("You have successfully deposited " + amount + "$. " + this.toString());
        return true;
    }

    @Override
    public void unavailableOperation() {
        System.out.println("Unavailable operation: Your can't pay with this account or your balance is not enough to transfer\n");
    }
    @Override
    public String toString() {
        return "Savings Account balance is " + balance + ". \n";
    }
}
