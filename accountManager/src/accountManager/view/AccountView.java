package accountManager.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import accountManager.controller.*;
import accountManager.model.*;
import accountManager.util.CurrencyConverter;

public class AccountView extends JFrameView {

    private JTextField balanceField;
    public JTextField amountField;
    private JButton btnDeposit;
    private JButton btnWithdraw;
    private JButton btnDismiss;
    private JButton btnCreateWithdrawAgent;
    private JButton btnCreateDepositAgent;

    public static final String AVAIL_FUNDS = "Available funds: ";
    public static final String DEPOSIT = "Deposit";
    public static final String WITHDRAW = "Withdraw";
    public static final String DISMISS = "Dismiss";
    public static final String CREATE_W_AGENT = "Withdraw agent";
    public static final String CREATE_D_AGENT = "Deposit agent";

    /**
     * Creates Account Manager view and registers model & controller.
     * @param model Model to register with view.
     * @param controller Controller to register with view.
     */
    public AccountView(AccountModel model, AccountController controller) {
        super(model, controller);

        final String TITLE = (model.getName() + " " + model.getID() + ": "
                + "Operations in " + model.getEditCurrencyType());
        final String AMOUNT = ("Enter amount in " + model.getEditCurrencyType() + ": ");

        Handler handler = new Handler();
        setResizable(false);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 505, 308);

        ((JComponent) this.getContentPane()).setBorder(new EmptyBorder(10, 20, 5, 20));
        setContentPane(this.getContentPane());
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.getContentPane().setLayout(gbl_contentPane);

        JLabel lblAvailableFunds = new JLabel(AVAIL_FUNDS);
        lblAvailableFunds.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbc_lblAvailableFunds = new GridBagConstraints();
        gbc_lblAvailableFunds.gridwidth = 2;
        gbc_lblAvailableFunds.insets = new Insets(0, 0, 5, 5);
        gbc_lblAvailableFunds.anchor = GridBagConstraints.EAST;
        gbc_lblAvailableFunds.gridx = 0;
        gbc_lblAvailableFunds.gridy = 0;
        this.getContentPane().add(lblAvailableFunds, gbc_lblAvailableFunds);

        balanceField = new JTextField();
        balanceField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        int balance = model.getBalance();
        if (model.getEditCurrencyType() == "EUR") {
            balance = CurrencyConverter.usdToEur(balance);
        }
        if (model.getEditCurrencyType() == "JPY") {
            balance = CurrencyConverter.usdToJpy(balance);
        }
        String text = Integer.toString(balance);
        while (text.length() < 3) {
            text = new StringBuilder(text).insert(0, "0").toString();
        }
        text = new StringBuilder(text).insert(text.length()-2,  ".").toString();
        balanceField.setText(text);
        balanceField.setEditable(false);
        GridBagConstraints gbc_balanceField = new GridBagConstraints();
        gbc_balanceField.insets = new Insets(0, 0, 5, 0);
        gbc_balanceField.fill = GridBagConstraints.HORIZONTAL;
        gbc_balanceField.gridx = 2;
        gbc_balanceField.gridy = 0;
        this.getContentPane().add(balanceField, gbc_balanceField);
        balanceField.setColumns(10);

        JLabel lblAmount = new JLabel(AMOUNT);
        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblAmount = new GridBagConstraints();
        gbc_lblAmount.gridwidth = 2;
        gbc_lblAmount.anchor = GridBagConstraints.EAST;
        gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
        gbc_lblAmount.gridx = 0;
        gbc_lblAmount.gridy = 2;
        this.getContentPane().add(lblAmount, gbc_lblAmount);

        amountField = new JTextField();
        amountField.setText("0000.00");
        GridBagConstraints gbc_amountField = new GridBagConstraints();
        gbc_amountField.insets = new Insets(0, 0, 5, 0);
        gbc_amountField.fill = GridBagConstraints.HORIZONTAL;
        gbc_amountField.gridx = 2;
        gbc_amountField.gridy = 2;
        this.getContentPane().add(amountField, gbc_amountField);
        amountField.setColumns(10);

        btnDeposit = new JButton(DEPOSIT);
        btnDeposit.addActionListener(handler);
        btnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_btnDeposit = new GridBagConstraints();
        gbc_btnDeposit.anchor = GridBagConstraints.EAST;
        gbc_btnDeposit.insets = new Insets(0, 0, 5, 5);
        gbc_btnDeposit.gridx = 0;
        gbc_btnDeposit.gridy = 4;
        this.getContentPane().add(btnDeposit, gbc_btnDeposit);

        btnWithdraw = new JButton(WITHDRAW);
        btnWithdraw.addActionListener(handler);
        btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_btnWithdraw = new GridBagConstraints();
        gbc_btnWithdraw.insets = new Insets(0, 0, 5, 5);
        gbc_btnWithdraw.gridx = 1;
        gbc_btnWithdraw.gridy = 4;
        this.getContentPane().add(btnWithdraw, gbc_btnWithdraw);

        btnDismiss = new JButton(DISMISS);
        btnDismiss.addActionListener(handler);
        btnDismiss.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_btnDismiss = new GridBagConstraints();
        gbc_btnDismiss.anchor = GridBagConstraints.NORTH;
        gbc_btnDismiss.insets = new Insets(0, 0, 5, 0);
        gbc_btnDismiss.gridx = 2;
        gbc_btnDismiss.gridy = 5;
        this.getContentPane().add(btnDismiss, gbc_btnDismiss);

        btnCreateWithdrawAgent = new JButton(CREATE_W_AGENT);
        btnCreateWithdrawAgent.addActionListener(handler);
        GridBagConstraints gbc_btnCreateWithdrawAgent = new GridBagConstraints();
        gbc_btnCreateWithdrawAgent.insets = new Insets(0, 0, 0, 5);
        gbc_btnCreateWithdrawAgent.gridx = 0;
        gbc_btnCreateWithdrawAgent.gridy = 7;
        this.getContentPane().add(btnCreateWithdrawAgent, gbc_btnCreateWithdrawAgent);

        btnCreateDepositAgent = new JButton(CREATE_D_AGENT);
        btnCreateDepositAgent.addActionListener(handler);
        GridBagConstraints gbc_btnCreateDepositAgent = new GridBagConstraints();
        gbc_btnCreateDepositAgent.gridwidth = 2;
        gbc_btnCreateDepositAgent.gridx = 1;
        gbc_btnCreateDepositAgent.gridy = 7;
        this.getContentPane().add(btnCreateDepositAgent, gbc_btnCreateDepositAgent);
    }

    @Override
    public void modelChanged(ModelEvent event) {
        int balance = event.getAmount();
        String output;
        if (event.getCurrency() == "EUR") {
            balance = CurrencyConverter.usdToEur(balance);
        }
        if (event.getCurrency() == "JPY") {
            balance = CurrencyConverter.usdToJpy(balance);
        }
        output = Integer.toString(balance);
        output = new StringBuilder(output).insert(output.length()-2,  ".").toString();
        balanceField.setText(output);
    }

    class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ((AccountController)getController()).operation(e.getActionCommand());
        }
    }
}
