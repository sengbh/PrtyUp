package accountManager.controller;

import accountManager.model.Model;
import accountManager.view.View;

public interface Controller {

    void setModel(Model model);
    Model getModel();
    View getView();
    void setView(View view);
}
