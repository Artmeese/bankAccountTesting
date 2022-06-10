import org.example.CheckingAccount;
import org.example.CreditAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTesting {

    @Test
    public void creditAccountCreate() {       //Проверяем правильный ли баланс после создания экземпляра класса c 0 на счету
        //given                                //Проверяем что не создался плюсовой кредитный аккаунт
        final int balance = 0;
        final int tryBalancePlus = 20;
        final int expectedBalanceAfterTrying = 0;
        //when
        CreditAccount creditAccount = new CreditAccount(balance);
        CreditAccount creditAccountPlus = new CreditAccount(tryBalancePlus);
        //then
        Assertions.assertEquals(creditAccount.getBalance(), balance);
        Assertions.assertEquals(creditAccountPlus.getBalance(), expectedBalanceAfterTrying);
    }

    @Test
    public void payTesting() {  //Проверяем что оплата происходит при любом балансе
        //given
        final int balance = 0;
        final CreditAccount creditAccount = new CreditAccount(balance);
        final int amount = 50;
        final int expectedBalance = -50;
        //when
        creditAccount.pay(amount);
        //then
        Assertions.assertEquals(expectedBalance, creditAccount.getBalance());
    }

    @Test
    public void transferTesting() {     //Проверяем доступность перевода на расчетный, который позволяет и недоступность перевода на кредитный в плюс
        //given
        final int balance = 0;
        final int balanceReceivingCreditAccount = 0;
        final int balanceReceivingCheckingAccount = 0;
        final int amount = 50;
        final CreditAccount creditAccount = new CreditAccount(balance);
        final CreditAccount creditReceivingAccount = new CreditAccount(balanceReceivingCreditAccount);
        final CheckingAccount checkingAccount = new CheckingAccount(balanceReceivingCheckingAccount);
        final int expectedBalance = -50; // 0 -> 0 после неудачной попытки -> -50 после удачной
        final int expectedCreditReceivingAccount = 0; // не перевели в плюс
        final int expectedCheckingReceivingAccount = 50;// Успешно перевели 50 с кредитного
        //when
        creditAccount.transfer(creditReceivingAccount, amount);
        creditAccount.transfer(checkingAccount, amount);
        //then
        Assertions.assertEquals(expectedBalance, creditAccount.getBalance());
        Assertions.assertEquals(expectedCreditReceivingAccount, creditReceivingAccount.getBalance());
        Assertions.assertEquals(expectedCheckingReceivingAccount, checkingAccount.getBalance());

    }
    @Test
    public void addMoneyTesting() {         //Проходит ли пополнение и не проходит ли пополнение в плюс
        //given
        final int balance = -50;
        final int amount = 30;
        final int tryAmountPlus = 100;
        final CreditAccount creditAccount = new CreditAccount(balance);
        final CreditAccount creditAccountTryPlus = new CreditAccount(balance);
        final int expectedBalance = -20;  // -50 + 30
        final int expectedBalanceAfterTryingPlus = -50; // не изменился
        //when
        creditAccount.addMoney(amount);
        creditAccountTryPlus.addMoney(tryAmountPlus);
        //then
        Assertions.assertEquals(expectedBalance, creditAccount.getBalance());
        Assertions.assertEquals(expectedBalanceAfterTryingPlus, creditAccountTryPlus.getBalance());
    }





}
