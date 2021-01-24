package com.company;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FileExplorer fo= new FileExplorer(System.getProperty("user.home")+"\\Documents\\File Explorer");
        Scanner mainInputs=new Scanner(System.in);
        String status;
        System.out.println("######################################"
                + "\n#########   File Explorer  #########"
                + "\n######################################");
        System.out.println("**************************************"
                + "\nlistcmd  : Show Commands"
                + "\ncontent  : Show structure of file"
                + "\nls       : Show content of folder"
                + "\nselect   : Select Directory and Files"
                + "\n--back   : previous Directory"
                + "\n--reset  : back to parent Directory"
                + "\nmakedir  : Create Directory"
                + "\nmakefile : Create File"
                + "\ndelete   : Delete File or Folder"
                + "\nexit     : Exit"
                + "\n**************************************");
        do {
            System.out.print(fo.imgCurrentDirectoryLocation);
            status=mainInputs.next();
            if ("listcmd".equals(status)) {
                System.out.println("**************************************"
                        + "\nlistcmd  : Show Commands"
                        + "\ncontent  : Show structure of file"
                        + "\nls       : Show content of folder"
                        + "\nselect   : Select Directory and Files"
                        + "\n--back   : previous Directory"
                        + "\n--reset  : back to parent Directory"
                        + "\nmakedir  : Create Directory"
                        + "\ndelete   : Delete File or Folder"
                        + "\nmakefile : Create File"
                        + "\nexit     : Exit"
                        + "\n**************************************");
            } else if ("content".equals(status)) {
                fo.fileStructure(fo.parentDirectoryLocation);
            } else if ("select".equals(status)) {
                fo.selectFileDirectory();
            } else if ("back".equals(status)) {
                fo.bselectFileDirectory();
            } else if ("reset".equals(status)) {
                fo.rselectFileDirectory();
            } else if ("makedir".equals(status)) {
                fo.createNewDirectory();
            } else if ("makefile".equals(status)) {
                try {
                    fo.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if ("delete".equals(status)) {
                System.out.println(fo.deleteFileFolder(fo.currentDirectoryLocation));
            } else if ("ls".equals(status)) {
                fo.listFilesFolder();
            } else {
                System.out.println("Command not defined");
            }
        }while(!status.equals("exit"));
        mainInputs.close();
    }
}
