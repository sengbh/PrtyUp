package accountManager.controller;

import accountManager.model.*;
import accountManager.util.CurrencyConverter;
import accountManager.util.InputParsing;
import accountManager.view.*;

public class AccountController extends AbstractController {

    public String editCurrencyType = "USD";

    /**
     * Registers a model and view with controller.
     */
    public AccountController(AccountModel model, String currency) {
        editCurrencyType = currency;
        setModel(model);
        setView(new AccountView((AccountModel)getModel(), this));
        ((JFrameView)getView()).setVisible(true);
    }

    /**
     * Defines the operations that can be run.
     * @param option Button/Action requested.
     */
    public void operation(String option) {
        if (option.equals(AccountView.DEPOSIT)) {
            String inputText = ((AccountView)getView()).amountField.getText();
            ((AccountView)getView()).amountField.setText("0.00");
            int value = InputParsing.inputToInt(inputText);

            if (editCurrencyType == "USD") {
                ((AccountModel)getModel()).deposit(value);
            }
            else if (editCurrencyType == "JPY") {
                value = CurrencyConverter.jpyToUsd(value);
                ((AccountModel)getModel()).deposit(value);
            }
            else if (editCurrencyType == "EUR") {
                value = CurrencyConverter.eurToUsd(value);
                ((AccountModel)getModel()).deposit(value);

            }
        }
        else if (option.equals(AccountView.WITHDRAW)) {
            String inputText = ((AccountView)getView()).amountField.getText();
            ((AccountView)getView()).amountField.setText("0.00");
            int value = InputParsing.inputToInt(inputText);
            try {
                if (editCurrencyType == "USD") {
                    ((AccountModel)getModel()).withdraw(value);
                }
                else if (editCurrencyType == "JPY") {
                    value = CurrencyConverter.jpyToUsd(value);
                    ((AccountModel)getModel()).withdraw(value);
                }
                else if (editCurrencyType == "EUR") {
                    value = CurrencyConverter.eurToUsd(value);
                    ((AccountModel)getModel()).withdraw(value);
                }
            }
            catch (OverdrawException e) {
                new ExceptionWindow("Insufficient funds: The amount to withdraw "
                        + inputText + " is greater than available funds " +
                        ((AccountModel)getModel()).getBalance(),
                        "OVERDRAW ERROR", false);
            }
        }
        else if (option.equals(AccountView.DISMISS)) {
            ((JFrameView)getView()).setVisible(false);
        }
        else if (option.equals(AccountView.CREATE_W_AGENT)) {
            new AgentManagerController((AccountModel)getModel(), "W");
        }
        else if (option.equals(AccountView.CREATE_D_AGENT)) {
            new AgentManagerController((AccountModel)getModel(), "D");
        }

    }
}
