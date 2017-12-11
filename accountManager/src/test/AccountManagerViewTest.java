package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;
import accountManager.controller.*;
import accountManager.model.*;
import accountManager.view.*;

public class AccountManagerViewTest {

    @Test
    public void testAccountManagerView() throws IOException, InvalidInputFileException {
        AccountManager model = new AccountManager("accounts.txt");
        AccountManagerController controller = new AccountManagerController("accounts.txt");

        AccountManagerView view = new AccountManagerView(model, controller);
        view.setVisible(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
    }

//	@Test
//	public void testModelChanged() {
//		fail("Not yet implemented");
//	}
}
