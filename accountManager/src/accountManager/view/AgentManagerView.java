package accountManager.view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import accountManager.controller.*;
import accountManager.model.*;


public class AgentManagerView extends JFrameView {

    private JPanel contentPane;
    private JTextField textAgentID;
    private JTextField textAmount;
    private JLabel lblState;
    private JTextField textState;
    private JLabel lblAmountTransferred;
    private JTextField textTransferred;
    private JLabel lblOpsCompleted;
    private JTextField textOpsCompleted;
    private JButton btnStopAgent;
    private JButton btnDismissAgent;



    public AgentManagerView(AgentManagerModel model, AgentManagerController controller) {
        super(model, controller);

        Handler handler = new Handler();

        setTitle("Account Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 365, 225);
        ((JComponent) this.getContentPane()).setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(this.getContentPane());
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.getContentPane().setLayout(gbl_contentPane);

        JLabel lblAgentID = new JLabel("AGENT ID");
        GridBagConstraints gbc_lblAgentID = new GridBagConstraints();
        gbc_lblAgentID.insets = new Insets(0, 0, 5, 5);
        gbc_lblAgentID.anchor = GridBagConstraints.EAST;
        gbc_lblAgentID.gridx = 0;
        gbc_lblAgentID.gridy = 0;
        this.getContentPane().add(lblAgentID, gbc_lblAgentID);

        textAgentID = new JTextField();
        GridBagConstraints gbc_textAgentID = new GridBagConstraints();
        gbc_textAgentID.insets = new Insets(0, 0, 5, 0);
        gbc_textAgentID.fill = GridBagConstraints.HORIZONTAL;
        gbc_textAgentID.gridx = 1;
        gbc_textAgentID.gridy = 0;
        this.getContentPane().add(textAgentID, gbc_textAgentID);
        textAgentID.setColumns(10);

        JLabel lblAmount = new JLabel("AMOUNT");
        GridBagConstraints gbc_lblAmount = new GridBagConstraints();
        gbc_lblAmount.anchor = GridBagConstraints.EAST;
        gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
        gbc_lblAmount.gridx = 0;
        gbc_lblAmount.gridy = 1;
        this.getContentPane().add(lblAmount, gbc_lblAmount);

        textAmount = new JTextField();
        GridBagConstraints gbc_textAmount = new GridBagConstraints();
        gbc_textAmount.insets = new Insets(0, 0, 5, 0);
        gbc_textAmount.fill = GridBagConstraints.HORIZONTAL;
        gbc_textAmount.gridx = 1;
        gbc_textAmount.gridy = 1;
        this.getContentPane().add(textAmount, gbc_textAmount);
        textAmount.setColumns(10);

        lblState = new JLabel("STATE");
        GridBagConstraints gbc_lblState = new GridBagConstraints();
        gbc_lblState.anchor = GridBagConstraints.EAST;
        gbc_lblState.insets = new Insets(0, 0, 5, 5);
        gbc_lblState.gridx = 0;
        gbc_lblState.gridy = 2;
        this.getContentPane().add(lblState, gbc_lblState);

        textState = new JTextField();
        GridBagConstraints gbc_textState = new GridBagConstraints();
        gbc_textState.insets = new Insets(0, 0, 5, 0);
        gbc_textState.fill = GridBagConstraints.HORIZONTAL;
        gbc_textState.gridx = 1;
        gbc_textState.gridy = 2;
        this.getContentPane().add(textState, gbc_textState);
        textState.setColumns(10);

        lblAmountTransferred = new JLabel("AMOUNT TRANSFERRED");
        GridBagConstraints gbc_lblAmountTransferred = new GridBagConstraints();
        gbc_lblAmountTransferred.anchor = GridBagConstraints.EAST;
        gbc_lblAmountTransferred.insets = new Insets(0, 0, 5, 5);
        gbc_lblAmountTransferred.gridx = 0;
        gbc_lblAmountTransferred.gridy = 3;
        this.getContentPane().add(lblAmountTransferred, gbc_lblAmountTransferred);

        textTransferred = new JTextField();
        textTransferred.setEditable(false);
        GridBagConstraints gbc_textTransferred = new GridBagConstraints();
        gbc_textTransferred.insets = new Insets(0, 0, 5, 0);
        gbc_textTransferred.fill = GridBagConstraints.HORIZONTAL;
        gbc_textTransferred.gridx = 1;
        gbc_textTransferred.gridy = 3;
        this.getContentPane().add(textTransferred, gbc_textTransferred);
        textTransferred.setColumns(10);

        lblOpsCompleted = new JLabel("OPERATIONS COMPLETED");
        GridBagConstraints gbc_lblOpsCompleted = new GridBagConstraints();
        gbc_lblOpsCompleted.anchor = GridBagConstraints.EAST;
        gbc_lblOpsCompleted.insets = new Insets(0, 0, 5, 5);
        gbc_lblOpsCompleted.gridx = 0;
        gbc_lblOpsCompleted.gridy = 4;
        this.getContentPane().add(lblOpsCompleted, gbc_lblOpsCompleted);

        textOpsCompleted = new JTextField();
        textOpsCompleted.setEditable(false);
        GridBagConstraints gbc_textOpsCompleted = new GridBagConstraints();
        gbc_textOpsCompleted.insets = new Insets(0, 0, 5, 0);
        gbc_textOpsCompleted.fill = GridBagConstraints.HORIZONTAL;
        gbc_textOpsCompleted.gridx = 1;
        gbc_textOpsCompleted.gridy = 4;
        this.getContentPane().add(textOpsCompleted, gbc_textOpsCompleted);
        textOpsCompleted.setColumns(10);

        btnStopAgent = new JButton("STOP");
        GridBagConstraints gbc_btnStopAgent = new GridBagConstraints();
        gbc_btnStopAgent.insets = new Insets(0, 0, 0, 5);
        gbc_btnStopAgent.gridx = 0;
        gbc_btnStopAgent.gridy = 6;
        this.getContentPane().add(btnStopAgent, gbc_btnStopAgent);

        btnDismissAgent = new JButton("DISMISS");
        GridBagConstraints gbc_btnDismissAgent = new GridBagConstraints();
        gbc_btnDismissAgent.gridx = 1;
        gbc_btnDismissAgent.gridy = 6;
        this.getContentPane().add(btnDismissAgent, gbc_btnDismissAgent);

    }

    @Override
    public void modelChanged(ModelEvent event) {
        // TODO Auto-generated method stub

    }

    class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ((AccountManagerController)getController()).operation(e.getActionCommand());
        }
    }
}
