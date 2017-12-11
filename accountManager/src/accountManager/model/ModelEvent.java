package accountManager.model;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ModelEvent extends ActionEvent{

    /**
     * Added serial version UID per IDE recommendation
     */
    private static final long serialVersionUID = 5939937586052315280L;

    private int amount;
    private String currency;
    private ArrayList<String> accountData;

    /**
     * Constructor for deposit/withdraw/updateBalance events.
     * @param obj Usually this.
     * @param id Give an ID. Not used in AccountManager.
     * @param message Name of command.
     * @param amount Value to pass.
     * @param currency Standard 3 letter denom. abbreviation.
     */
    public ModelEvent (Object obj, int id, String message, int amount, String currency) {
        super( obj, id, message);
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Constructor for accountManager which reads account data.
     * @param obj Usually this.
     * @param id Give an ID. Not used in AccountManager.
     * @param message Name of command.
     * @param accountData ArrayList of all the accountData to display.
     */
    public ModelEvent (Object obj, int id, String message, ArrayList<String> accountData) {
        super( obj, id, message);
        this.accountData = accountData;
    }

    /**
     * @return the accountData
     */
    public ArrayList<String> getAccountData() {
        return accountData;
    }

    /**
     * Getter for amount.
     * @return Returns amount.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Getter for currency.
     * @return Returns currency.
     */
    public String getCurrency() {
        return currency;
    }
}
