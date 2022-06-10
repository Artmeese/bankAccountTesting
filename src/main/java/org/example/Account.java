package org.example;

public abstract class Account {

    int balance;

    Account(int balance) {
        this.balance = balance;
    }

    public  int getBalance() {
        return balance;
    }

    public abstract void pay(int amount);

    public abstract void transfer(Account account, int amount);

    public abstract boolean addMoney(int amount);

}
