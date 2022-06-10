import org.example.CheckingAccount;
import org.example.CreditAccount;
import org.example.SavingsAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingsAccountTesting {
    @Test
    public void savingsAccountCreate() {       //Проверяем правильный ли баланс после создания экземпляра класса
        //given
        final int balance = 20;
        //when
        SavingsAccount savingsAccount = new SavingsAccount(balance);
        //then
        Assertions.assertEquals(savingsAccount.getBalance(), balance);
    }

    @Test
    public void payTesting() {          //Проверяем невозможность оплаты со сберегательного
        //given
        final int balance = 100;
        final int amount = 50;
        SavingsAccount savingsAccount = new SavingsAccount(balance);
        final int expectedBalance = 100;
        //when
        savingsAccount.pay(amount);
        //then
        Assertions.assertEquals(expectedBalance, savingsAccount.getBalance());
    }
    @Test
    public void transferTesting() {
        //given           //Проверяем доступность перевода на расчетный, который позволяет и недоступность перевода на кредитный в плюс
        final int balance = 100;
        final int balanceReceivingCreditAccount = 0;
        final int balanceReceivingCheckingAccount = 0;
        final int amount = 30;
        final SavingsAccount savingsAccount = new SavingsAccount(balance);
        final CreditAccount creditReceivingAccount = new CreditAccount(balanceReceivingCreditAccount);
        final CheckingAccount checkingAccount = new CheckingAccount(balanceReceivingCheckingAccount);
        final int expectedBalance = 70; // 0 -> 0 после неудачной попытки -> 70 после удачной
        final int expectedCreditReceivingAccount = 0; // не перевели в плюс
        final int expectedCheckingReceivingAccount = 30;// Успешно перевели 30 cj c,thtufntkmyjuj
        //when
        savingsAccount.transfer(creditReceivingAccount, amount);
        savingsAccount.transfer(checkingAccount, amount);
        //then
        Assertions.assertEquals(expectedBalance, savingsAccount.getBalance());
        Assertions.assertEquals(expectedCreditReceivingAccount, creditReceivingAccount.getBalance());
        Assertions.assertEquals(expectedCheckingReceivingAccount, checkingAccount.getBalance());
    }
    @Test
    public void transferTestingFalseByBalance() {
        //given
        final int balance = 100;
        final int amount = 150;
        final SavingsAccount savingsAccount = new SavingsAccount(balance);
        final CheckingAccount checkingAccount = new CheckingAccount(balance);
        final int expectedBalance = 100; // не изменился
        final int expectedCheckingBalance = 100;// не изменился
        //when
        savingsAccount.transfer(checkingAccount, amount);
        //then
        Assertions.assertEquals(expectedBalance, savingsAccount.getBalance());
        Assertions.assertEquals(expectedCheckingBalance, checkingAccount.getBalance());

    }

    @Test
    public void addAddMoney() {
        //given
        final int balance = 100;
        final int amount = 150;
        final SavingsAccount savingsAccount = new SavingsAccount(balance);
        final int expectedBalance = 250; // 100 + 150
        //when
        savingsAccount.addMoney(amount);
        //then
        Assertions.assertEquals(expectedBalance, savingsAccount.getBalance());
    }
}
