package xoxo.client.ui.command;

import java.io.PrintWriter;
import java.util.Scanner;

import xoxo.client.ui.ICommand;

public abstract class UserCommand extends Command {
    
    protected String username = "";
    protected String password = "";

    public UserCommand(String label) {
        super(label);
    }

    @Override
    public ICommand takeArgs(Scanner in, PrintWriter out) {
        while (username.equals("")) {
            out.print("enter username: ");
            out.flush();
            username = in.nextLine();
        }
        while (password.equals("")) {
            out.print("enter password: ");
            out.flush();
            password = in.nextLine();
        }
        return this;
    }
}