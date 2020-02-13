package myPackage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import myPackage.*;


interface StudentOps{
    void seeNotes();
    void setDepartment(String department);
    String getDepartment();
    String getNotes();
    void setNotes();
    void showSyllabus();
    void showDiscontinuity();
}


public class Student extends Person implements StudentOps{
    private String department;
    private String notes = "X";
    public Student(){
        super();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter department ");
        String tDep = scan.nextLine();
        setDepartment(tDep);
        InsertToFile();
    }

    public Student(int id, String name){
        super(id, name);
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter department ");
        String tDep = scan.nextLine();
        setDepartment(tDep);
        InsertToFile();
    }

    public Student(int id, String name, String department){
        super(id, name);
        setDepartment(department);
        InsertToFile();
    }

    public Student(int id, String name, String department, boolean dummy){
        super(id, name);
        this.setDepartment(department);
    }
    public Student(int id, String name, String department, String notes){
        super(id, name);
        this.setDepartment(department);
        this.notes = notes;
    }

    public void InsertToFile(){
        //değerleri dosyaya yaz
        try{
            FileOutputStream file = new FileOutputStream("myFiles\\students.txt" , true);
            String vals = Integer.toString(this.getId()) + "_" + this.getName() + "_" + this.department + "_" + this.notes + "\n";
            byte[] byteVals = vals.getBytes();
            file.write(byteVals);
            file.close();
        }catch(FileNotFoundException fnfex){
            System.out.println(fnfex);
        }catch(IOException ioex){
            System.out.println(ioex);
        }
        
    }

    public void showDiscontinuity(){
        int discontinuity = 0;
        try{
            FileInputStream file = new FileInputStream("myFiles\\discontinuity.txt");
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(file));
            String data;
            while((data = readBuffer.readLine()) != null){
                if(Integer.valueOf(data) == this.getId()){
                    discontinuity++;
                }
            }
        }catch(FileNotFoundException fnfe){
            System.out.println(fnfe);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
        System.out.println("Your discontinuity is " +  discontinuity + " day(s)");
    }
    
    
    public static void ListStudents(){
        try{
            FileInputStream file = new FileInputStream("myFiles\\students.txt");
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(file));
            String data;
            ArrayList<String> list = new ArrayList<String>();
            while((data = readBuffer.readLine()) != null){
                //System.out.println(data);
                list.add(data);
            }
            String[][] rawdata = new String[list.size()][2];
            for(int i = 0; i < list.size(); i++){
                String trimmed = list.get(i).trim();
                String[] splitted = trimmed.split("_");
                System.out.println(splitted[0] + " " + splitted[1] + " " + splitted[2]);
            }
            readBuffer.close();
        }catch(FileNotFoundException exception){
            System.out.println(exception);
        }
        catch(IOException ioexception){
            System.out.println(ioexception);
        }
    }

    public String getDepartment(){
        return this.department;
    }

    public void setDepartment(String department){
        this.department = department;
    }

    public void showSyllabus(){
        try{
            FileInputStream file = new FileInputStream("myFiles\\syllabus.txt");
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(file));
            String data;
            ArrayList<String> list = new ArrayList<String>();
            while((data = readBuffer.readLine()) != null){
                //System.out.println(data);
                list.add(data);
            }
            String[][] rawdata = new String[list.size()][5];
            System.out.printf("    %15s %15s %15s %15s %15s\n", "Mon", "Tue", "Wed", "Thu", "Fri");
            for(int i = 0; i < list.size(); i++){
                String trimmed = list.get(i).trim();
                String[] splitted = trimmed.split("_");
                System.out.printf("%d-) %15s %15s %15s %15s %15s\n", (i+1), splitted[0], splitted[1], splitted[2], splitted[3], splitted[4]);
                for(int j = 0; j < 5; j++){
                    rawdata[i][j] = splitted[j];
                }
            }
            readBuffer.close();
        }catch(FileNotFoundException exception){
            System.out.println(exception);
        }
        catch(IOException ioexception){
            System.out.println(ioexception);
        }
    }

    public String getNotes(){
        return this.notes;
    }

    public void seeNotes(){
        float sum = 0;
        String noteArray[] = this.notes.trim().split("X");
        System.out.println(noteArray.length);
        if(noteArray.length > 0){
            for(int i = 0; i < noteArray.length; i++){
                if(checkNum(noteArray[i])){
                    System.out.print(noteArray[i] + " ");
                    sum += Integer.parseInt(noteArray[i]);
                }
            }
            sum /= (noteArray.length - 1);
        }else if(noteArray.length == 0){
            System.out.print("No notes found");
            return;
        }
        System.out.println("Average :" + sum);
        System.out.println("Letter grade :" + Officer.letterGrade(sum));
    }

    private static boolean checkNum(String num){
        if(num == null){
            return false;
        }
        try{
            int data = Integer.parseInt(num);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }

    public void setNotes(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter note");
        this.notes += scan.nextLine();
        this.notes += "X";
    }

}