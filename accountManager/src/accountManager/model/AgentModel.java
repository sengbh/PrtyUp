package accountManager.model;

public class AgentModel extends AbstractModel {

    final String
            BLOCKED = "blocked",
            RUNNING = "running",
            STOPPED = "stopped";

    private int id;
    private double operationsPerSecond = 0.0;
    private String state = STOPPED;
    private int amountTransferred = 0;
    private int operationsCompleted = 0;
    private int amountToChange = 0;
    private AccountModel accountModel;

    /**
     * Sets ID for this agent
     * @param id to set
     */
    public AgentModel(int id, AccountModel model) {
        this.id = id;
        this.accountModel = model;
    }

    /**
     * Does an autoDeposit cycle based on variables entered.
     * Must run as its own thread!
     * @param value	Amount to deposit.
     * @param operationsPerSecond Delay between operations
     * @throws Value should not be < 0
     */
    synchronized public void startAutoDeposit(int value, double operationsPerSecond)
            throws IllegalArgumentException  {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }

        startAgent();

        Runnable depThread = new Runnable() {
            synchronized public void run() {
                while (state != STOPPED) {
                    accountModel.deposit(value);
                    try {
                        wait((long)(operationsPerSecond * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        new Thread(depThread).start();
    }



    /**
     * Does an autoWithdraw cycle based on variables entered.
     * Must run as its own thread!
     * @param value Amount to withdraw.
     * @param operationsPerSecond Delay between operations
     * @throws Value should not be < 0
     * @throws This method cannot overdraw accounts.
     */
    synchronized public void startAutoWithdraw(final int value, final double operationsPerSecond)
            throws OverdrawException, IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        while ((accountModel.getBalance() - value) < 0) {
            blockAgent();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startAgent();

        Runnable withThread = new Runnable() {
            synchronized public void run() {
                while (state != STOPPED) {
                    try {
                        accountModel.withdraw(value);
                    } catch (OverdrawException e1) {
                        try {
                            blockAgent();
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        wait((long) (operationsPerSecond * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(withThread).start();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the operationsPerSecond
     */
    public double getOperationsPerSecond() {
        return operationsPerSecond;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Start agent and set state
     */
    public void startAgent() {
        state = RUNNING;
        ModelEvent me = new ModelEvent(this, 1, "stateChange", id, "");
        notifyChanged(me);
    }

    /**
     * Set account state to STOPPED
     */
    public void stopAgent() {
        state = STOPPED;
        ModelEvent me = new ModelEvent(this, 1, "stateChange", id, "");
        notifyChanged(me);
    }

    /**
     * Set account state to BLOCKED
     */
    public void blockAgent() {
        state = BLOCKED;
        ModelEvent me = new ModelEvent(this, 1, "stateChange", id, "");
        notifyChanged(me);
    }

    /**
     * @return the amountTransferred
     */
    public int getAmountTransferred() {
        return amountTransferred;
    }

    /**
     * @return the operationsCompleted
     */
    public int getOperationsCompleted() {
        return operationsCompleted;
    }

    /**
     * @return the amountToChange
     */
    public int getAmountToChange() {
        return amountToChange;
    }
}
