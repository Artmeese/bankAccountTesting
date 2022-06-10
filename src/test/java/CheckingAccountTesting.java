import org.example.CheckingAccount;
import org.example.CreditAccount;
import org.example.SavingsAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class CheckingAccountTesting {
    @Test
    public void checkingAccountCreate() {       //Проверяем правильный ли баланс после создания экземпляра класса
        //given
        final int balance = 20;
        //when
        CheckingAccount checkingAccount = new CheckingAccount(balance);
        //then
        Assertions.assertEquals(checkingAccount.getBalance(), balance);
    }
    @Test
    public void payTestingMinus() {         //Пытаемся списать в минус, баланс не должен изменится
        //given
        final int balance = 100;
        final CheckingAccount checkingAccount = new CheckingAccount(balance);
        final int amount = 120;
        final int expectedBalance = 100;

        //when
        checkingAccount.pay(amount);

        //then
        Assertions.assertEquals(expectedBalance, checkingAccount.getBalance());
    }
    @Test
    public void payTestingPlus() {      //Списываем, проверяем остаток баланса
        //given
        final int balance = 100;
        final CheckingAccount checkingAccount = new CheckingAccount(balance);
        final int amount = 80;
        final int expectedBalance = 20;
        //when
        checkingAccount.pay(amount);
        //then
        Assertions.assertEquals(expectedBalance, checkingAccount.getBalance());
    }

    @Test
    public void transferTestingTrue() { //На списание суммы, меньшей баланса на SavingAccount который позволяет принять, проверяем остатки
        //given
        final int balance = 100;
        final int balanceReceivingSavingsAccount = 0;
        final int amount = 80;
        final CheckingAccount checkingAccount = new CheckingAccount(balance);
        final SavingsAccount savingsAccount = new SavingsAccount(balanceReceivingSavingsAccount);
        final int newBalance = 20;          //100 - 80
        final int newBalanceReceivingSavingsAccount = 80; //0 + 80
        //when
        checkingAccount.transfer(savingsAccount, amount);

        //then
        Assertions.assertEquals(newBalance, checkingAccount.getBalance());
        Assertions.assertEquals(newBalanceReceivingSavingsAccount, savingsAccount.getBalance());
    }

    @Test
    public void transferTestingFalseByReceiving() { //На списание суммы, меньшей баланса на CreditAccount который не позволяет принять в плюс, проверяем остатки
        //given
        final int balance = 100;
        final int balanceReceivingCreditAccount = 0;
        final int amount = 80;
        final CheckingAccount checkingAccount = new CheckingAccount(balance);
        final CreditAccount creditAccount = new CreditAccount(balanceReceivingCreditAccount);
        final int newBalance = 100;          //Не изменился
        final int newBalanceReceivingCreditAccount = 0; //не изменился
        //when
        checkingAccount.transfer(creditAccount, amount);

        //then
        Assertions.assertEquals(newBalance, checkingAccount.getBalance());
        Assertions.assertEquals(newBalanceReceivingCreditAccount, creditAccount.getBalance());
    }
    @Test
    public void transferTestingFalseByBalance() { //На невозможность списания суммы, большей баланса трансфер на SavingAccount который позволяет принять, проверяем остатки
        //given
        final int balance = 100;
        final int balanceReceivingSavingsAccount = 0;
        final int amount = 120;
        final CheckingAccount checkingAccount = new CheckingAccount(balance);
        final SavingsAccount savingsAccount = new SavingsAccount(balanceReceivingSavingsAccount);
        final int newBalance = 100;          //Не изменился
        final int newBalanceSavingsAccount = 0; //не изменился
        //when
        checkingAccount.transfer(savingsAccount, amount);
        //then
        Assertions.assertEquals(newBalance, checkingAccount.getBalance());
        Assertions.assertEquals(newBalanceSavingsAccount, savingsAccount.getBalance());
    }

    @Test
    public void addMoneyTesting() {
        //given
        final int balance = 100;
        final int amount = 20;
        final CheckingAccount checkingAccount = new CheckingAccount(balance);
        final int expectedBalance = 120;
        //when
        checkingAccount.addMoney(amount);
        //then
        Assertions.assertEquals(expectedBalance, checkingAccount.getBalance());
    }
    

}
