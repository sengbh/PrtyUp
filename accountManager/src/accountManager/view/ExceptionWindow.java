package accountManager.view;

import accountManager.model.*;
import accountManager.controller.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accountManager.controller.AccountController;
import accountManager.model.ModelEvent;
import accountManager.view.AccountView.Handler;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ExceptionWindow extends JFrame {

    private JPanel contentPane;
    Boolean criticalError;

    /**
     * Create the frame.
     */
    public ExceptionWindow(String exceptionMessage, String title, Boolean critical) {

        criticalError = critical;
        Handler handler = new Handler();

        setResizable(false);
        setTitle(title);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 600, 161);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JLabel lblErrorMessage = new JLabel(exceptionMessage);
        GridBagConstraints gbc_lblErrorMessage = new GridBagConstraints();
        gbc_lblErrorMessage.insets = new Insets(0, 0, 5, 0);
        gbc_lblErrorMessage.gridx = 0;
        gbc_lblErrorMessage.gridy = 0;
        contentPane.add(lblErrorMessage, gbc_lblErrorMessage);

        JButton btnDismiss = new JButton("Dismiss");
        btnDismiss.addActionListener(handler);
        GridBagConstraints gbc_btnDismiss = new GridBagConstraints();
        gbc_btnDismiss.gridx = 0;
        gbc_btnDismiss.gridy = 3;
        contentPane.add(btnDismiss, gbc_btnDismiss);

        setVisible(true);
    }

    class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (criticalError)
            { System.exit(0); }
            else
                setVisible(false);
        }
    }
}
