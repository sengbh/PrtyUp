package accountManager.controller;


import java.util.ArrayList;

import accountManager.model.*;
import accountManager.view.*;


public class AccountManagerController extends AbstractController{


    public ArrayList<AccountModel> initAccounts = new ArrayList<AccountModel>();
    public ArrayList<AccountModel> accounts = new ArrayList<AccountModel>();
    public Boolean modified = false;
    public int numOfAccounts;
    int selectedAccount;

    /**
     * Registers a model and view with controller.
     */
    public AccountManagerController(String filename) {
        try {
            setModel(new AccountManager(filename));
        } catch (InvalidInputFileException e) {
            new ExceptionWindow("The input file is invalid!", "Invalid input file!", true);
        }
        numOfAccounts = ((AccountManager)getModel()).numOfAccounts();
        for (int i = 0; i < numOfAccounts; i++)
            initAccounts.add(((AccountManager)getModel()).selectAccount(i));
        accounts = initAccounts;
        setView(new AccountManagerView((AccountManager)getModel(), this));
        ((AccountManager) getModel()).forceNotify();
        ((JFrameView)getView()).setVisible(true);
    }

    /**
     * Defines the operations that can be run.
     * @param option Button/Action requested.
     */
    public void operation(String option) {
        selectedAccount = ((AccountManagerView)getView()).accountList.getSelectedIndex();

        if (option.equals(AccountManagerView.EDIT_USD)) {
            accounts.get(selectedAccount).setEditCurrencyType("USD");
            new AccountController(accounts.get(selectedAccount), "USD");

        }
        else if (option.equals(AccountManagerView.EDIT_JPY)) {
            accounts.get(selectedAccount).setEditCurrencyType("JPY");
            new AccountController(accounts.get(selectedAccount), "JPY");
        }
        else if (option.equals(AccountManagerView.EDIT_EUR)) {
            accounts.get(selectedAccount).setEditCurrencyType("EUR");
            new AccountController(accounts.get(selectedAccount), "EUR");
        }
        else if (option.equals(AccountManagerView.SAVE)) {
            for (int i = 0; i < numOfAccounts; i++)
                ((AccountManager)getModel()).updateBalance(accounts.get(i));
            ((AccountManager) getModel()).saveAccounts();
        }
        else if (option.equals(AccountManagerView.EXIT)) {
            for (int i = 0; i < numOfAccounts; i++)
                ((AccountManager)getModel()).updateBalance(accounts.get(i));
            if (initAccounts.equals(accounts))
                ((AccountManager) getModel()).saveAccounts();
            System.exit(0);
        }
    }
}
