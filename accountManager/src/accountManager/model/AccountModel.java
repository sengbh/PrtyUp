package accountManager.model;



public class AccountModel extends AbstractModel{

    public String editCurrencyType = "USD";


    //Balance should be stored as integer in the smallest denomination
    private int balance;
    private int ID;
    private String name;

    /**
     * This constructor initializes the balance to the value passed.
     * Value passed should be in USD cents.
     * @param initialBalance This should be in USD.
     * @throws IllegalArgumentException if initialBalance < 0
     */
    public AccountModel(String name, int id, int initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot "
                    + "be less than 0.");
        }
        else {
            balance = initialBalance;
            this.ID = id;
            this.name = name;
            ModelEvent me = new ModelEvent(this, 1, "", balance, editCurrencyType);
            notifyChanged(me);
        }
    }

    /**
     * Deposits money to account increasing balance
     * @param val Amount to deposit.
     * @param currencyCode The currency the deposit is done in.
     * @throws IllegalArgumentException if value < 0
     *
     */
    public void deposit(int val) {
        if (val < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }
        else {
            balance += val;
            ModelEvent me = new ModelEvent(this, 1, "", balance, editCurrencyType);
            notifyChanged(me);
        }
    }

    /**
     * @return the editCurrencyType
     */
    public String getEditCurrencyType() {
        return editCurrencyType;
    }

    /**
     * @param editCurrencyType the editCurrencyType to set
     */
    public void setEditCurrencyType(String editCurrencyType) {
        this.editCurrencyType = editCurrencyType;
    }

    /**
     * Withdraws money from account decreasing balance.
     * Throws exception if withdraw would overdraw account.
     * @param val Amount to withdraw.
     * @throws OverdrawException If about to overdraw, throw this.
     * @param currencyCode The currency the deposit is done in.
     * @throws IllegalArgumentException if value < 0
     */
    public void withdraw(int val) throws OverdrawException {
        if(balance < val) throw new OverdrawException(
                "Insufficient funds: amount to withdraw " + val +
                        " is greater than available funds " + balance);
        else if (val < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }
        else {
            balance -= val;
            ModelEvent me = new ModelEvent(this, 1, "", balance, "USD");
            notifyChanged(me);
        }
    }

    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @return the iD
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
