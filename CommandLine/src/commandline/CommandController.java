/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandline;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author fahmy
 */
public class CommandController {

    public static String[] COMMANDS = {
    "clear", "cd", "ls", "cp", "mv", "rm","mkdir", "rmdir", "cat", "more",
        "less", "pwd"
    };
    
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
            default:
                System.out.println("Command not found");
                System.out.println("Type `help` to get a list of commands");
                
        }
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
        File dir = new File(System.getProperty("user.dir"));
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
        if(params[0].equals("..")){
            dir = new File(System.getProperty("user.dir"));
            System.setProperty("user.dir", dir.getParentFile().getAbsolutePath());
        }else{
            dir = new File(params[0]);
            if(dir.isDirectory()==true) {
                System.setProperty("user.dir", dir.getAbsolutePath());
            } else {
                System.out.println(params[0] + "is not a directory.");
            }
        }
    }
    public static void cp(String[] params){
        if(params.length != 2){
            wrongNumberOfParams(params.length,2);
            return;
        }        
    }
    public static void mv(String[] params){
        if(params.length != 2){
            wrongNumberOfParams(params.length,2);
            return;
        }        
    }    
    public static void rm(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }        
    }    
    public static void mkdir(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }   
        File newDir = new File(params[0]);
        newDir.mkdir();
    }    
    public static void rmdir(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }
    }    
    public static void cat(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }        
        String file = new IO().getInput(params[0]);
        System.out.print(file);
        System.out.println();
    }
    public static void more(String[] params) {
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }
        String file = new IO().getInput(params[0]);
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
        String text = new IO().getInput(params[0]);
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
    public static void help(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }        
    }
}
