package accountManager.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import accountManager.controller.*;
import accountManager.model.*;

public class AgentView extends JFrameView{

    public AgentView(Model model, Controller controller) {
        super(model, controller);
    }

    @Override
    public void modelChanged(ModelEvent event) {

    }

    class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
}
