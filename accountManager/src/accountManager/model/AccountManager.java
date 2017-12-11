package accountManager.model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AccountManager extends AbstractModel {

    public final int setLength = 3;
    ArrayList<String> accountData = new ArrayList<String>();
    String filename;

    /**
     * Creates array with file information
     * @param filename
     * @throws InvalidInputFileException
     */
    public AccountManager(String fn) throws InvalidInputFileException{
        filename = fn;
        File file = new File(filename);

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                accountData.add(sc.next());
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ModelEvent me = new ModelEvent(this, 1, "", accountData);
        notifyChanged(me);
    }

    /**
     * Create AccountModel and return
     * @param index Which account to load
     * @return AccountModel with parameters
     */
    public AccountModel selectAccount(int index){
        //Navigate to first element in selected account
        int i = (index * setLength);
        AccountModel model = new AccountModel(accountData.get(i),
                Integer.parseInt(accountData.get(++i)),
                Integer.parseInt(accountData.get(++i)));
        return model;
    }

    public void updateBalance(AccountModel model) {
        Boolean found = false;
        int index = 1;

        while (!found && accountData.size() > index) {
            if((Integer.parseInt(accountData.get(index)) == model.getID())) {
                found = true;
                accountData.set((index + 1), Integer.toString(model.getBalance()));
            }
            else index += 3;
        }
    }

    /**
     * Saves account state to file, pass in the model that is to be updated
     * @param model The model to update.
     */
    public void saveAccounts() {
        File fnew = new File(filename);
        try {
            FileWriter fw = new FileWriter(fnew, false);
            for (int i = 0; i < accountData.size(); i++) {
                fw.write(accountData.get(i) + " ");
                if ((i + 1) % setLength == 0) {
                    fw.write(System.getProperty("line.separator"));
                }
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forceNotify() {
        ModelEvent me = new ModelEvent(this, 1, "", accountData);
        notifyChanged(me);
    }

    public int numOfAccounts() {
        return ((accountData.size() + 1) / 3);
    }

}
