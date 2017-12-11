package accountManager.model;

import java.util.ArrayList;

public class AgentManagerModel extends AbstractModel {

    //List to hold running agents
    public ArrayList<AgentModel> agents = new ArrayList<AgentModel>();
    private ArrayList<Integer> agentIds = new ArrayList<Integer>();
    int agentsRunning = 0;

    /**
     * Creates an agent and keeps track of id
     * @param id ID of agent to be made
     * @param accModel Model for agent to manage
     * @throws DuplicateIDException
     */
    public void createAgent(int id, AccountModel accModel) throws DuplicateIDException{
        for (int i = 0; i < agentsRunning; i++) {
            if (id == agents.get(i).getId()){
                throw new DuplicateIDException();
            }
        }
        AgentModel agModel = new AgentModel(id, accModel);
        agents.add(agModel);
        agentIds.add(id);
        agentsRunning++;
    }

    /**
     * Removes an agent from the list.
     * @param id ID of agent to remove.
     * @throws AgentNotStoppedException
     * @throws NoAgentsRunningException
     */
    public void removeAgent(int id)
            throws AgentNotStoppedException, NoAgentsRunningException {
        boolean found = false;
        if (agentsRunning > 0) {
            for (int i = 0; (i < agentsRunning) || (!found); i++) {
                if (id == agents.get(i).getId()) {
                    found = true;
                    if (agents.get(i).getState() != "stopped") {
                        agents.remove(i);
                        agentsRunning--;
                    }
                    else
                        throw new AgentNotStoppedException();
                }
            }
        }
        else
            throw new NoAgentsRunningException();
    }
}
