package myPackage;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import myPackage.*;

interface User{
    String getName();
    void setName(String name);
    int getId();
    void setId(int id);
    void InsertToFile();
}



public abstract class Person implements User{
    private String name;
    private int id;
    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Person(){
        int tId;
        String tName;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id ");
        tId = scan.nextInt();
        System.out.println("Enter name ");
        tName = scan.next();
        setId(tId);
        setName(tName);
    }

    public void listPeople(int var){
        ArrayList<Person> list = new ArrayList<Person>();
        try{
            if(var == 1){
                FileInputStream file = new FileInputStream("myFiles\\students.txt");
                BufferedReader readBuffer = new BufferedReader(new InputStreamReader(file));
                String data;
                while((data = readBuffer.readLine()) != null){
                    String trimmed = data.trim();
                    String[] splitted = trimmed.split("_");
                    Student temp = new Student(Integer.parseInt(splitted[0]), splitted[1], "", true); 
                    list.add(temp);
                }
                readBuffer.close();
            }
            if(var == 2){
                FileInputStream file = new FileInputStream("myFiles\\instructors.txt");
                BufferedReader readBuffer = new BufferedReader(new InputStreamReader(file));
                String data;
                while((data = readBuffer.readLine()) != null){
                    String trimmed = data.trim();
                    String[] splitted = trimmed.split("_");
                    Instructor temp = new Instructor(Integer.parseInt(splitted[0]), splitted[1], "", true); 
                    list.add(temp);
                }
            }
        }catch(FileNotFoundException exception){
            System.out.println(exception);
        }
        catch(IOException ioexception){
            System.out.println(ioexception);
        }
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getId() + " " + list.get(i).getName());
        }
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public abstract void InsertToFile();
}




