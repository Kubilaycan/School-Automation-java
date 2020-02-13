package myPackage;
import java.util.Scanner;
import myPackage.*;

interface AppOps{
    void signUp();
    void logIn();
    void logOut();
    void mainMenu();
    void loggedMainMenu();
}

public abstract class Operation implements AppOps{
    public static void exitProgram(){
        System.out.println("Goodbye");
        java.lang.System.exit(0);
    }
    public void logOut(){

    }
    public abstract void logIn();
    public abstract void signUp();
    public abstract void mainMenu();
    public abstract void loggedMainMenu();
}