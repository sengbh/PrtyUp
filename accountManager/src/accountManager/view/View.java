package accountManager.view;

import accountManager.controller.Controller;
import accountManager.model.Model;

public interface View {

    Controller getController();
    void setController(Controller controller);
    Model getModel();
    void setModel(Model model);
}
