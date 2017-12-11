package accountManager.view;


import javax.swing.*;

import accountManager.controller.Controller;
import accountManager.model.AbstractModel;
import accountManager.model.Model;
import accountManager.model.ModelListener;


abstract public class JFrameView extends JFrame implements View, ModelListener {


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Model model;
    private Controller controller;

    public JFrameView (Model model, Controller controller) {
        setModel(model);
        setController(controller);
    }

    public void registerWithModel( ) {
        ((AbstractModel)model).addModelListener(this);
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
        registerWithModel();
    }
}
