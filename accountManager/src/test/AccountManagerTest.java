package test;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import accountManager.model.*;
import org.junit.runners.MethodSorters;

public class AccountManagerTest {

    @Test
    public void testReadAccount1() throws InvalidInputFileException {
        AccountManager accManager = new AccountManager("accounts.txt");
        AccountModel model = accManager.selectAccount(1);
        assertEquals(model.getBalance(), 50000);
        assertEquals(model.getID(), 001);
        assertEquals(model.getName(), "Name1");
    }

    @Test
    public void testReadAccount2() throws InvalidInputFileException {
        AccountManager accManager = new AccountManager("accounts.txt");
        AccountModel model = accManager.selectAccount(2);
        assertEquals(model.getBalance(), 100000);
        assertEquals(model.getID(), 002);
        assertEquals(model.getName(), "Name2");
    }

    @Test
    public void testWriteAccount1() throws InvalidInputFileException {
        AccountManager accManager = new AccountManager("accounts.txt");
        AccountModel model = accManager.selectAccount(2);
        assertEquals(model.getBalance(), 100000);
        assertEquals(model.getID(), 002);
        assertEquals(model.getName(), "Name2");

        model.deposit(100000);
        accManager.updateBalance(model);
        accManager.saveAccounts();

        AccountManager accMan2 = new AccountManager("accounts.txt");
        model = accManager.selectAccount(2);
        assertEquals(model.getBalance(), 200000);
        assertEquals(model.getID(), 002);
        assertEquals(model.getName(), "Name4");

        try {
            model.withdraw(100000);
        } catch (OverdrawException e) {
            e.printStackTrace();
        }
        accMan2.updateBalance(model);
        accMan2.saveAccounts();
    }

}
