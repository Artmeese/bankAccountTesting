package org.example;

public class CheckingAccount extends Account implements Accounts {

    public CheckingAccount(int balance) {
        super(balance);
    }

    @Override
    public void pay(int amount) {       //Платим с расчетного счета, если он не уйдет в минус
        if (balance >= amount) {
            balance -= amount;
            System.out.println("You have successfully paid " + amount + "$ from Checking account. " + this.toString());
        } else {
            this.unavailableOperation();
        }
    }

    @Override
    public void transfer(Account account, int amount) {     //Переводим со счета
        if (amount <= balance) {                            //Если баланс позволяет
            if (account.addMoney(amount)) {                 //И позволяет целевой счет
            balance -= amount;
                System.out.println("This " + amount + "$ have successfully transferred from Checking Account. " + this.toString());
            }
        } else {
            this.unavailableOperation();
        }
    }
    @Override
    public boolean addMoney(int amount) {   //пополняем расчетный на любую сумму
        balance += amount;
        System.out.println("You have successfully deposited " + amount + " $ to Checking account. " + this.toString());
        return true;
    }

    @Override
    public void unavailableOperation() {
        System.out.println("Unavailable operation: Your balance is not enough to transfer or pay. \n");
    }

    @Override
    public String toString() {
        return "Checking account balance is " + balance + ". \n";
    }
}
