package test;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import accountManager.model.*;
import org.junit.Test;

public class AgentManagerModelTest {

    @Test
    public void testCreation() {

        String exception;
        AccountModel accModel = new AccountModel("Name", 001, 25000);
        AgentManagerModel agMan = new AgentManagerModel();
        try {
            agMan.createAgent(001, accModel);
        } catch (DuplicateIDException e) {
            exception = "Duplicate ID";
            e.printStackTrace();
        }
        try {
            agMan.agents.get(0).startAutoWithdraw(1000, 1.499);
        } catch (IllegalArgumentException | OverdrawException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        agMan.agents.get(1).stopAgent();
        assertEquals(21000, accModel.getBalance());

    }
}
