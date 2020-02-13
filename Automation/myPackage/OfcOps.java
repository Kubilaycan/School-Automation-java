package myPackage;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import myPackage.*;

public class OfcOps extends Operation{
    private boolean loogedIn;
    private int id;
    private String name;

    public void logIn(){
        System.out.println("Enter your id");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        try{
            FileInputStream file = new FileInputStream("myFiles\\officers.txt");
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(file));
            String data;
            ArrayList<String> list = new ArrayList<String>();
            while((data = readBuffer.readLine()) != null){
                //System.out.println(data);
                list.add(data);
            }
            readBuffer.close();
            String[][] rawdata = new String[list.size()][2];
            for(int i = 0; i < list.size(); i++){
                String trimmed = list.get(i).trim();
                String[] splitted = trimmed.split("_");
                rawdata[i][0] = splitted[0];
                rawdata[i][1] = splitted[1];
            }
            boolean found = false;
            for(int i = 0; i < list.size(); i++){
                if(id == Integer.valueOf(rawdata[i][0])){
                    found = true;
                    System.out.println("Welcome " + rawdata[i][1]);
                    this.id = Integer.valueOf(rawdata[i][0]);
                    this.name = rawdata[i][1];
                    this.loogedIn = true;
                }
            }
            if(!found){
                System.out.println("User not found");
            }
        }catch(FileNotFoundException fnfex){
            System.out.println(fnfex);
        }catch(IOException ioex){
            System.out.println(ioex);
        }
        if(this.loogedIn){
            loggedMainMenu();
        }
    }

    public void loggedMainMenu(){
        Officer temp = new Officer(this.id, this.name, true);
        Scanner scan = new Scanner(System.in);
        int op;
        while(this.loogedIn){
            System.out.println("1-) Set syllabus\n2-) List students\n3-) List instructors\n4-) Log out");
            op = scan.nextInt();
            switch(op){
                case 1:
                    temp.setSyllabus();
                    break;
                case 2:
                    temp.listPeople(1);
                    break;
                case 3:
                    temp.listPeople(2);
                    break;
                case 4:
                    logOut();
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }
    }

    public void signUp(){
        Officer user = new Officer();
        mainMenu();
    }

    @Override
    public void logOut(){
        this.loogedIn = false;
    }

    public void mainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1-) Log In\n2-) Sign Up\n3-) Exit");
        int opr = scan.nextInt();
        switch(opr){
            case 1:
                logIn();
                break;
            case 2:
                signUp();
                break;
            case 3:
                Operation.exitProgram();
                break;
            default:
                System.out.println("Invalid operation");
        }
    }
}