package org.example;

public class Main {
    public static void main(String[] args) {
        Account checkingAcc = new CheckingAccount(2000);
        System.out.println(checkingAcc);

        Account creditAcc = new CreditAccount(200); //пытаемся кредитному назначить баланс 200
        System.out.println(creditAcc);

        Account savingsAcc = new SavingsAccount(3000);
        System.out.println(savingsAcc);

        savingsAcc.addMoney(300);   //добавляем в сберегательный
        savingsAcc.pay(1000);       //не можем оплатить со сберегательного
        savingsAcc.transfer(checkingAcc, 1500); //переводим со сберегательного на расчетный
        savingsAcc.transfer(creditAcc, 200); //Не можем перевести кредитному "в плюс"

        checkingAcc.addMoney(1000); // добавляем в расчетный
        checkingAcc.pay(500);// платим с расчетного
        checkingAcc.transfer(savingsAcc,50000);//перевод с уходом в минус недоступен

        creditAcc.pay(4000);// Оплатой уходим кредитным в минус
        creditAcc.transfer(checkingAcc, 10000); //переводим еще на расчетный
        creditAcc.addMoney(15000); //Не можем закрыть кредитный "в плюс"
        creditAcc.addMoney(14000); //Закрываем его в ноль

    }
}
