import myPackage.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Main.mainMenu();

    }

    public static void mainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1-) Instructor operations\n2-) Student operations\n3-) Officer operations\n4-) Exit");
        int opr = scan.nextInt();
        while(opr > 0 || opr <= 4){
            switch(opr){
                case 1:
                    InsOps iop = new InsOps();
                    iop.mainMenu();
                    break;
                case 2:
                    StuOps sop = new StuOps();
                    sop.mainMenu();
                    break;
                case 3:
                    OfcOps oop = new OfcOps();
                    oop.mainMenu();
                    break;
                case 4:
                    Operation.exitProgram();
                default:
                    System.out.println("Invalid operation");
            }
            opr = scan.nextInt();
        }
    }
}