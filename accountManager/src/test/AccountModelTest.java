package test;

import accountManager.model.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class AccountModelTest {

    @Test
    public void testAccountModel() {
        AccountModel model = new AccountModel("Name1", 001, 500);
        assertEquals(500, model.getBalance());
        assertEquals(001, model.getID());
        assertEquals("Name1", model.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalInitialBalance() {
        AccountModel model = new AccountModel("Name1", 001, -500);
    }

    @Test
    public void testDeposit() {
        AccountModel model = new AccountModel("Name1", 001, 500);
        model.deposit(500);
        assertEquals(1000, model.getBalance());
        model.deposit(200);
        assertEquals(1200, model.getBalance());
        model.deposit(0);
        assertEquals(1200, model.getBalance());
        model.deposit(700);
        assertEquals(1900, model.getBalance());
        model.deposit(2000);
        assertEquals(3900, model.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentDeposit() {
        AccountModel model = new AccountModel("Name1", 001, 500);
        model.deposit(-500);
    }

    @Test
    public void testWithdraw() throws OverdrawException {
        AccountModel model = new AccountModel("Name1", 001, 10000);
        model.withdraw(1000);
        assertEquals(9000, model.getBalance());
        model.withdraw(200);
        assertEquals(8800, model.getBalance());
        model.withdraw(0);
        assertEquals(8800, model.getBalance());
        model.withdraw(123);
        assertEquals(8677, model.getBalance());
        model.withdraw(8677);
        assertEquals(0, model.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentWithdraw() throws OverdrawException {
        AccountModel model = new AccountModel("Name1", 001, 500);
        model.withdraw(-200);
    }
}