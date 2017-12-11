package accountManager.controller;


import java.util.ArrayList;

import accountManager.model.*;
import accountManager.view.*;


public class AgentManagerController extends AbstractController {

    AccountModel account;
    String withOrDep;

    /**
     * Registers a model and view with controller.
     * @param
     */
    public AgentManagerController(AccountModel model, String withOrDep) {
        account = model;
        this.withOrDep = withOrDep;
        setModel(new AgentManagerModel());

        setView(new AgentManagerView((AgentManagerModel)getModel(), this));
        ((JFrameView)getView()).setVisible(true);
    }

    /**
     * Defines the operations that can be run.
     * @param option Button/Action requested.
     */
    public void operation(String option) {


    }
}
