package accountManager.model;

import accountManager.controller.*;

public class Main {

    public static void main(String[] args) throws InvalidInputFileException {
        if (args.length == 0 || args == null) {
            System.out.println("FILE NOT FOUND");
        }
        else {
            new AccountManagerController(args[0]);
        }
    }
}
