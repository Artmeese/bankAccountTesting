package org.example;

public class CreditAccount extends Account implements Accounts {


    public CreditAccount(int balance) {
        super(balance);
        if (balance > 0) {          //Не даем создать кредитный счет с балансом больше 0
            this.balance = 0;
            System.out.println("No positive balance possible. Credit Account balance set to 0.");
        } else {
            this.balance = balance;
        }
    }

    @Override
    public void pay(int amount) {

        balance -= amount;
        System.out.println("You have successfully paid " + amount + "$ from Credit account. " + this.toString());
    }

    @Override
    public void transfer(Account account, int amount) {
        if (account.addMoney(amount)) {      //Переводим при любом баласе кредитного счета, если целевой позволяет принять платёж
            balance -= amount;
            System.out.println("This " + amount + "$ have successfully transferred from Credit account. " + this.toString());
        } else {
            this.unavailableOperation();
        }
    }

    @Override
    public boolean addMoney(int amount) {
        if (balance + amount <= 0) {        //Позволяем пополнить кредитный в случае, когда его баланс не превысит 0
            balance += amount;
            System.out.println("You have successfully deposited " + amount + " $ to Credit account. " + this.toString());
            return true;
        } else {
            unavailableOperation();
            return false;
        }
    }

    @Override
    public void unavailableOperation() {
        System.out.println("Unavailable operation. Deposit a smaller amount. \n");
    }

    @Override
    public String toString() {
        return "Credit account balance is " + balance + ".\n";
    }

}
