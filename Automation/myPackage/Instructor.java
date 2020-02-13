package myPackage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import myPackage.*;

interface InstructorOps{
    void enterNotes();
    void setSyllabus();
    void roll();
}

public class Instructor extends Person implements InstructorOps{
    private String lesson;
    public Instructor(){
        super();
        System.out.println("Enter name of your lesson");
        Scanner scan = new Scanner(System.in);
        String tLesson = scan.nextLine();
        setLesson(tLesson);
        InsertToFile();
    }

    public Instructor(int id, String name, String lesson, boolean dummy){
        super(id, name);
        setLesson(lesson);
    }

    public void InsertToFile(){
        //değerleri dosyaya yaz
        try{
            FileOutputStream file = new FileOutputStream("myFiles\\instructors.txt" , true);
            String vals = Integer.toString(this.getId()) + "_" + this.getName() + "_" + this.getLesson() + "\n";
            byte[] byteVals = vals.getBytes();
            file.write(byteVals);
        }catch(FileNotFoundException fnfex){
            System.out.println(fnfex);
        }catch(IOException ioex){
            System.out.println(ioex);
        }
        
    }

    public static void ListInstructors(){
        try{
            FileInputStream file = new FileInputStream("myFiles\\instructors.txt");
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

    public void roll(){
        try{
            Scanner scan = new Scanner(System.in);
            FileOutputStream file = new FileOutputStream("myFiles\\discontinuity.txt", true);
            System.out.println("Enter student's id");
            String tempid = scan.nextLine();
            tempid += "\n";
            byte[] vals = tempid.getBytes();
            file.write(vals);
            file.close();
        }catch(FileNotFoundException fnfe){
            System.out.println(fnfe);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }

    public void enterNotes(){
        System.out.println("Enter id of student");
        Scanner scan = new Scanner(System.in);
        int id = scan.nextInt();
        try{
            FileInputStream file = new FileInputStream("myFiles\\students.txt");
            BufferedReader readBuffer = new BufferedReader(new InputStreamReader(file));
            String data;
            ArrayList<String> list = new ArrayList<String>();
            while((data = readBuffer.readLine()) != null){
                //System.out.println(data);
                list.add(data);
            }
            readBuffer.close();
            String[][] rawdata = new String[list.size()][4];
            ArrayList<Student> studentList = new ArrayList<Student>();
            for(int i = 0; i < list.size(); i++){
                String trimmed = list.get(i).trim();
                String[] splitted = trimmed.split("_");
                Student temp = new Student(Integer.valueOf(splitted[0]), splitted[1], splitted[2], splitted[3]);
                studentList.add(temp);
                rawdata[i][0] = splitted[0];
                rawdata[i][1] = splitted[1];
                rawdata[i][2] = splitted[2];
                rawdata[i][3] = splitted[3];
            }
            boolean found = false;
            for(int i = 0; i < list.size(); i++){
                if(studentList.get(i).getId() == id){
                    found = true;
                    studentList.get(i).setNotes();
                }
            }
            if(!found){
                System.out.println("Student not found");
            }

            FileOutputStream updateFile = new FileOutputStream("myFiles\\students.txt");
            for(int i = 0; i < studentList.size(); i++){
                System.out.println("helo");
                String updatedVals = Integer.toString(studentList.get(i).getId()) + "_" + studentList.get(i).getName() + "_" + studentList.get(i).getDepartment() + "_" + studentList.get(i).getNotes() + "\n";
                byte[] byteVals = updatedVals.getBytes();
                updateFile.write(byteVals);
            }
            updateFile.close();

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
            choosen = choosen.trim();
            String[] splitted = choosen.split(" ");
            int index = Integer.parseInt(splitted[1]);
            if(splitted[0].trim().toLowerCase().equals("mon")){
                if(rawdata[index-1][0].trim().equals("empty")){
                    rawdata[index-1][0] = this.lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("tue")){
                if(rawdata[index-1][1].trim().equals("empty")){
                    rawdata[index-1][1] = this.lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("wed")){
                if(rawdata[index-1][2].trim().equals("empty")){
                    rawdata[index-1][2] = this.lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("thu")){
                if(rawdata[index-1][3].trim().equals("empty")){
                    rawdata[index-1][3] = this.lesson;
                }else{
                    System.out.println("Lesson is already taken.");
                }
            }
            if(splitted[0].trim().toLowerCase().equals("fri")){
                if(rawdata[index-1][4].trim().equals("empty")){
                    rawdata[index-1][4] = this.lesson;
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
    private void setLesson(String lesson){
        this.lesson = lesson;
    }
    private String getLesson(){
        return this.lesson;
    }
}