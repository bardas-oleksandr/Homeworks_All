package IO.Console;

import Interfaces.IProblem;

import java.io.Console;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Console console = System.console();
        if(console != null){
            System.out.println("Your password:");
            char[] password = console.readPassword();
        }else{
            System.out.println("Console is unavailable");
        }

    }
}
