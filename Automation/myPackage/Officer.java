package myPackage;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import myPackage.*;

interface OfficerOps{
    void setSyllabus();
}

public class Officer extends Person{
    
    
    public Officer(){
        super();
        InsertToFile();
    }

    public Officer(int id, String name, boolean dummy){
        super(id, name);
    }

    public void InsertToFile(){
        try{
            FileOutputStream file = new FileOutputStream("myFiles\\officers.txt" , true);
            String vals = Integer.toString(this.getId()) + "_" + this.getName() + "\n";
            byte[] byteVals = vals.getBytes();
            file.write(byteVals);
        }catch(FileNotFoundException fnfex){
            System.out.println(fnfex);
        }catch(IOException ioex){
            System.out.println(ioex);
        }
    }

    public void setSyllabus(){
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
            System.out.println("Choose day and time that you want to take (ex. Mon 5)");
            Scanner scan = new Scanner(System.in);
            String choosen = scan.nextLine();
            System.out.println("Enter the name of the lesson");
            String lesson = scan.nextLine();
            choosen = choosen.trim();
            String[] splitted = choosen.split(" ");
            int index = Integer.parseInt(splitted[1]);
            if(splitted[0].trim().toLowerCase().equals("mon")){
                if(rawdata[index-1][0].trim().equals("empty")){
                    rawdata[index-1][0] = lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("tue")){
                if(rawdata[index-1][1].trim().equals("empty")){
                    rawdata[index-1][1] = lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("wed")){
                if(rawdata[index-1][2].trim().equals("empty")){
                    rawdata[index-1][2] = lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("thu")){
                if(rawdata[index-1][3].trim().equals("empty")){
                    rawdata[index-1][3] = lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("fri")){
                if(rawdata[index-1][4].trim().equals("empty")){
                    rawdata[index-1][4] = lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            FileOutputStream writeFile = new FileOutputStream("myFiles\\syllabus.txt");
            for(int i = 0; i < list.size(); i++){
                String write = rawdata[i][0] + "_" + rawdata[i][1] + "_" + rawdata[i][2] + "_" + rawdata[i][3] + "_" + rawdata[i][4] + "\n";
                byte[] byteVals = write.getBytes();
                writeFile.write(byteVals);
            }
            writeFile.close();

        }catch(FileNotFoundException exception){
            System.out.println(exception);
        }
        catch(IOException ioexception){
            System.out.println(ioexception);
        }
    }

    public static String letterGrade(float grade){
        if(grade == 100){
            return "AA";
        }else if(grade < 100 && grade > 87){
            return "BA";
        }else if(grade < 87 && grade > 75){
            return "BB";
        }else if(grade < 75 && grade >  62){
            return "CB";
        }else if(grade < 62 && grade > 50){
            return "CC";
        }else if(grade < 50 && grade > 37){
            return "DC";
        }else if(grade < 37 && grade > 25){
            return "DD";
        }else {
            return "FF";
        }
    }
}