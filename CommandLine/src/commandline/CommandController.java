/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;




/**
 *
 * @author fahmy
 */
public class CommandController {

    public static String PATH = System.getProperty("user.dir") + "/";
    
    public CommandController(){}
    
    public static void process(String command){
        //search for the base command
        //if not found => it'll print an error "command not found"
        String[] p = command.split(" ");
        String[] params = new String[p.length-1];
        if(p.length > 1){
            for(int i = 1 ; i < p.length; i++){
                params[i-1] = p[i];
            }
        }else
            params = new String[0];
        command = p[0];
        switch(command){
            case "ls":
                ls(params);
                break;
            case "cp":
                cp(params);
                break;
            case "clear":
                try{
                    clear(params);
                }catch(Exception e){
                    
                }
                break;
            case "cd":
                cd(params);
                break;
            case "mv":
                mv(params);
                break;
            case "rm":
                rm(params);
                break;
            case "mkdir":
                mkdir(params);
                break;
            case "rmdir":
                rmdir(params);
                break;
            case "cat":
                cat(params);
                break;
            case "more":
                more(params);
                break;
            case "less":
                less(params);
                break;
            case "pwd":
                pwd(params);
                break;
            case "help":
                help(params);
                break;
            case "date":
                date(params);
                break; 
            case "args":
                args(params);
                break; 
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Command not found");
                System.out.println("Type `help` to get a list of commands");
                
        }
        PATH = System.getProperty("user.dir") + "/";

    }
    
    private static void wrongNumberOfParams(int given,int required){
        System.out.println(String.format("wrong number of params (%d for %d)",
                given , required));
    }
    
    public static void ls(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }
        File dir = new File(PATH);
        String childs[] = dir.list();
        for(String child: childs){
            System.out.println(child);
        }
    }
    public static void clear(String[] params) throws IOException{
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }        
        char c = '\n';
        int length = 50;
        char[] chars = new char[length];
        Arrays.fill(chars, c);
        System.out.print(String.valueOf(chars));
    }
    public static void cd(String[] params){
        if(params.length > 1){
            wrongNumberOfParams(params.length,1);
            return;
        }
        File dir;
        if(params.length == 0){
            System.setProperty("user.dir", "/home/fahmy");
        }else if(params[0].equals("..")){
            dir = new File(PATH);
            System.setProperty("user.dir", dir.getParentFile().getAbsolutePath());
        }else{
            if(params[0].charAt(0) != '/')
                dir = new File(PATH + params[0]);
            else
                dir = new File(params[0]);
            if(dir.isDirectory()==true) {
                System.setProperty("user.dir", dir.getAbsolutePath());
            } else {
                System.out.println(params[0] + " is not a directory.");
            }
        }
    }
    public static void cp(String[] params){
        if(params.length != 2){
            wrongNumberOfParams(params.length,2);
            return;
        }      
        File s = new File(params[0]);
        File d = new File(params[1]);
        try{
            copyFile(s, d);
        }catch(Exception e){
            System.out.println("Somthing is wrong");
        }
    }
    public static void mv(String[] params){
        if(params.length != 2){
            wrongNumberOfParams(params.length,2);
            return;
        }  
        File s = new File(params[0]);
        File d = new File(params[1]);
        try{
            copyFile(s, d);
            s.delete();
        }catch(Exception e){
            System.out.println("Something is wrong");
        }
    }    
    public static void rm(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }        
        File file = new File(PATH + params[0]);
        if(file.delete()){}
        else{System.err.println("file not found");}
    }    
    public static void mkdir(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }   
        File newDir = new File(PATH+params[0]);
        newDir.mkdir();
    }    
    public static void rmdir(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }
        File file = new File(PATH + params[0]);
        while(!file.delete()){
            for(String s:file.list()){
                rmdir(new String[]{file.getName() + "/" + s});
            }
        }
    }    
    public static void cat(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }        
        String file = new IO().getInput(PATH + params[0]);
        System.out.print(file);
        System.out.println();
    }
    public static void more(String[] params) {
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }
        String file = new IO().getInput(PATH + params[0]);
        if(file.length() != 0){
            int numLines = 0;
            for(int i = 0; i < file.length(); i++){
                System.out.print(file.charAt(i));
                if(file.charAt(i) == '\n'){
                    numLines++;
                }
                if(numLines == 9){
                    System.out.print("MORE");
                    numLines = 0;
                    Scanner in = new Scanner(System.in);
                    in.nextLine();
                }
            }
        }
    }
    public static void less(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }        
        String text = new IO().getInput(PATH + params[0]);
        if(text.length() != 0){
            String[] lines = text.split("\n");
            Scanner in = new Scanner(System.in);
            try{
                clear(new String[0]);
            }catch(Exception e){}
            for(int i = 0 ; i < lines.length; i++){
                System.out.print(lines[i]);
                in.nextLine();
            }
        }
    }
    public static void pwd(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        } 
        System.out.println(System.getProperty("user.dir"));
    }
    public static void date(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }
    public static void args(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,0);
            return;
        }
        switch(params[0]){
            case "cd":
                System.out.println("cd: change directory \n" + "cd [path] \n" +
                    "path is the absolute path of a directory or a relative path to the current working directory");
                break;
            case "clear":
                System.out.println("Clear : "+"clear takes no parameters"
                    +"clear the screen");
                break;
            case "cp":
                System.out.println("copy :  cp  SOURCE... DIRECTORY \n"+"copy file from source to directory");
                break;
            case "ls":
                System.out.println("list : list takes no parameters \n"+"list the files in your current directory");
                break;
            case "mkdir":
                System.out.println(" mkdir :Create Directories \n"+"mkdir (directory name) \n"+"allow you to create directories");
                break;
            case "rm":
                System.out.println("rm :ReMove\n"+"rm filename/directories "+"remove or delete a file in your directory.");
                break;
            case "mv":
                System.out.println("Move : mv  (rename) files");
                break;
            case "rmdir":
                System.out.println("Delete directory: rmdir (directory name)");
                break;
            case "cat":
                System.out.println("cat:Print file content \n"+" cat (filename)");
                break;
            case "more":
                System.out.println("More : more filename \n" +"more (filename) \n"+"show text in pages");
                break;
            case "pwd":
                System.out.println("pwd :Print working directory \n "+"pwd takes no parameters");
                break;
            case "args":
                System.out.println("args : List all command arguments \n  "+"args takes no parameters");
                break;
            case "date":
                System.out.println("date : Current date/time \n  "+"date takes no parameters");
                break;
            default:
                System.out.println("invalid command");
        }
    }
    public static void help(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }   
        System.out.println("Copy : cp  SOURCE... DIRECTORY");
        System.out.println("Clear : clear");
        System.out.println("Change Directories :  cd (/directory/location)");
        System.out.println("List : ls");
        System.out.println("Create Directories : mkdir (directory name)");
        System.out.println("ReMove : rm filename/directories ");
        System.out.println("MoVe : mv  (rename) files");
        System.out.println("Delete directory: rmdir (directory name)");
        System.out.println("Print file content : cat filename");
        System.out.println("More : more filename");
        System.out.println("Less : less filename");
        System.out.println("Print working directory : pwd");
        System.out.println(" List all command arguments : args ");
        System.out.println("Current date/time : date ");
        System.out.println("Stop all : exit ");
    }
    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
         destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
             source.close();
            }
            if(destination != null) {
             destination.close();
            }
        }
    }
}
