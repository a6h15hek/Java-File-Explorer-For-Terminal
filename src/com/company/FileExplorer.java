package com.company;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileExplorer {
    public String parentDirectoryLocation;
    public String currentDirectoryLocation ;
    public String imgCurrentDirectoryLocation=">>File Explorer>";
    protected String newFileName;
    private String lastEnteredLoc[]=new String[10];
    private int top=-1;
    private Scanner fileInputs=new Scanner(System.in);
    FileExplorer(String parentDirectoryLocation){
        this.parentDirectoryLocation=parentDirectoryLocation;
        this.currentDirectoryLocation=parentDirectoryLocation;
        pushLocation("noValueTillNow");
        File newDirectory=new File(currentDirectoryLocation+"\\"+"test");
        newDirectory.mkdirs();
        if(newDirectory.exists())
            System.out.println("Ready...");
        else
            System.out.println("some error occured");
    }
    private void pushLocation(String data) {
        if(top>=9) {
            return;
        }else {
            top++;
            lastEnteredLoc[top]=data;
        }
    }
    private String popLocation() {
        if(top<0) {
            return "noValueTillNow" ;
        }else {
            String x=lastEnteredLoc[top];
            top--;
            return x;
        }
    }
    private String peepLocation() {
        if(top<0) {
            return "noValueTillNow" ;
        }else {
            String x=lastEnteredLoc[top];
            return x;
        }
    }
    public void fileStructure(String tempParentDirectory) {
        File directory=new File(tempParentDirectory);
        //get all file from directory
        File[] fileList=directory.listFiles();
        for(File fileElement : fileList) {
            if(fileElement.isFile())
                System.out.println("    >>"+fileElement.getAbsolutePath().substring(20+System.getProperty("user.name").length(),fileElement.getAbsolutePath().length()));
            else if(fileElement.isDirectory()) {
                System.out.println("dir>>"+fileElement.getAbsolutePath().substring(19+System.getProperty("user.name").length(),fileElement.getAbsolutePath().length()));
                fileStructure(fileElement.getAbsolutePath());
            }
        }
    }
    public String selectFileDirectory() {
        System.out.print(">");
        String enteredLoc=fileInputs.nextLine();
        File directory=new File(currentDirectoryLocation);
        File[] fileList=directory.listFiles();
        for(File fileElement : fileList) {
            if(enteredLoc.equals(fileElement.getName())) {
                pushLocation(enteredLoc); //to store last value of enteredLoc
                currentDirectoryLocation+="\\"+enteredLoc;
                imgCurrentDirectoryLocation+=enteredLoc+">";
                return imgCurrentDirectoryLocation;
            }
        }
        System.out.println("No such File or Folder");
        return "Invalid File Location";
    }
    //method to go back location
    public String bselectFileDirectory() {
        if(peepLocation().equals("noValueTillNow")) {
            System.out.println("Cannot be take back - Invalid Input");
            return "Cannot be take back - Invalid Input";
        }
        currentDirectoryLocation=removeLastChars(currentDirectoryLocation,peepLocation().length()+1);
        //System.out.println(currentDirectoryLocation);
        imgCurrentDirectoryLocation=removeLastChars(imgCurrentDirectoryLocation,peepLocation().length()+1);
        popLocation();
        return imgCurrentDirectoryLocation;
    }
    //method to reset location to parent directory
    public String rselectFileDirectory() {
        currentDirectoryLocation=parentDirectoryLocation;
        imgCurrentDirectoryLocation=">>Contact App>";
        top=0;
        return imgCurrentDirectoryLocation;
    }
    //method to remove last characters
    private String removeLastChars(String str,int noChar) {
        return str.substring(0, str.length() - noChar);
    }
    public void createNewDirectory(){
        System.out.print("Create a new Directory \nEnter name of Directory >>");
        String enteredDirectoryName =fileInputs.nextLine();
        File newDirectory=new File(currentDirectoryLocation+"\\"+enteredDirectoryName);
        boolean created=newDirectory.mkdirs();
        if(created)
            System.out.println("Directory successfully created at path :\n"+newDirectory.getPath());
        else if(newDirectory.exists())
            System.out.println("Directory already exist at path :\n"+newDirectory.getPath());
        else System.out.println("Unable to Create Directory");
    }
    public void createNewFile() throws IOException{
        System.out.print("Create a new File \nEnter name of File >>");
        String enteredFileName =fileInputs.next();
        newFileName=currentDirectoryLocation+"\\"+enteredFileName;
        File newFile=new File(newFileName);
        boolean created=newFile.createNewFile();
        if(created)
            System.out.println("File successfully created at path :\n"+newFile.getPath());
        else if(newFile.exists())
            System.out.println("File already exist at path :\n"+newFile.getPath());
        else System.out.println("Unable to Create File");
    }
    public String deleteFileFolder(String tempCurrentDirectory) {
        if(tempCurrentDirectory==parentDirectoryLocation||peepLocation().equals("noValueTillNow")) {
            return "Parent Folder cannot be deleted";
        }
        File directory=new File(tempCurrentDirectory);
        //get all file from directory
        if(directory.isFile()) {
            directory.delete();
            System.out.println(currentDirectoryLocation);
            currentDirectoryLocation=removeLastChars(currentDirectoryLocation,peepLocation().length()+1);
            System.out.println(currentDirectoryLocation);
            imgCurrentDirectoryLocation=removeLastChars(imgCurrentDirectoryLocation,peepLocation().length()+1);
            popLocation();
        }else {
            deleteFolder(tempCurrentDirectory);
            directory.delete();
            System.out.println(currentDirectoryLocation);
            currentDirectoryLocation=removeLastChars(currentDirectoryLocation,peepLocation().length()+1);
            System.out.println(currentDirectoryLocation);
            imgCurrentDirectoryLocation=removeLastChars(imgCurrentDirectoryLocation,peepLocation().length()+1);
            popLocation();
        }
        return "file or folder deleted successfully";
    }
    private void deleteFolder(String tempCurrentDirectory) {
        File directory=new File(tempCurrentDirectory);
        File[] fileList=directory.listFiles();
        for(File fileElement : fileList) {
            if(fileElement.isFile())
                fileElement.delete();
            else if(fileElement.isDirectory()) {
                deleteFolder(fileElement.getAbsolutePath());
                fileElement.delete();
            }
        }
    }

    public void listFilesFolder() {
        File directory=new File(currentDirectoryLocation);
        //get all file from directory
        File[] fileList=directory.listFiles();
        for(File fileElement : fileList) {
            if(fileElement.isFile())
                System.out.println(fileElement.getName());
            else if(fileElement.isDirectory()) {
                System.out.println(fileElement.getName()+"(folder)");
            }
        }
    }
}
