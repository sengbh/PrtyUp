package test;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import accountManager.model.*;
import org.junit.Test;

public class AgentModelTest {

    @Test
    public void testAgentModel() {
        AccountModel accModel = new AccountModel("String", 001, 500);
        AgentModel model = new AgentModel(001, accModel);
        assertEquals(001, model.getId());
        AgentModel model2 = new AgentModel(1553, accModel);
        assertEquals(1553, model2.getId());
    }

    @Test
    public void testAutoDepositAndStop1() {
        AccountModel accModel = new AccountModel("String2", 153, 10000);
        AgentModel agModel = new AgentModel(101, accModel);

        agModel.startAutoDeposit(20000, 0.499);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        agModel.stopAgent();
        assertEquals(230000, accModel.getBalance());
    }

    @Test
    public void testAutoDepositAndStop2() {
        AccountModel accModel = new AccountModel("Sring3", 999, 0);
        AgentModel agModel = new AgentModel(998546, accModel);

        Runnable agentThread = new Runnable() {
            public void run() {
                agModel.startAutoDeposit(70000, 1.499);
            }
        };

        new Thread(agentThread).start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        agModel.stopAgent();
        assertEquals(280000, accModel.getBalance());
    }

    @Test
    public void testAutoWithdraw() {
        AccountModel accModel = new AccountModel("String4", 001, 25000);
        AgentModel agModel = new AgentModel(998546, accModel);

        try {
            agModel.startAutoWithdraw(1000, 1.499);
        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (OverdrawException e1) {
            e1.printStackTrace();
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        agModel.stopAgent();
        assertEquals(21000, accModel.getBalance());
    }

    @Test
    public void testSynchWithAndDepos() {
        AccountModel accModel = new AccountModel("String5", 001, 10500);
        AgentModel depModel = new AgentModel(001, accModel);
        AgentModel withModel = new AgentModel(002, accModel);

        depModel.startAutoDeposit(5000, 1.005);
        try {
            withModel.startAutoWithdraw(1000, 1.499);
        } catch (IllegalArgumentException | OverdrawException e) {
            e.printStackTrace();
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        depModel.stopAgent();
        withModel.stopAgent();

        assertEquals(31500, accModel.getBalance());
    }

}
