package accountManager.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import accountManager.controller.*;
import accountManager.model.*;

public class StartAgentView extends JFrameView {

    /**
     * Creates Account Manager view and registers model & controller.
     * @param model Model to register with view.
     * @param controller Controller to register with view.
     */
    public StartAgentView(AgentModel model, AgentController controller) {
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
