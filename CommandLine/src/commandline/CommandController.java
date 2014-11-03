/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandline;

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
        String[] params = command.split(" ");
        System.arraycopy(params, 1, params, 0, params.length-1);
        switch(command){
            case "ls":
                ls(params);
                break;
            case "cp":
                cp(params);
                break;
            case "clear":
                clear(params);
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
    }
    public static void clear(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }        
    }
    public static void cd(String[] params){
        if(params.length > 1){
            wrongNumberOfParams(params.length,1);
            return;
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
    }
    public static void more(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }        
    }
    public static void less(String[] params){
        if(params.length != 1){
            wrongNumberOfParams(params.length,1);
            return;
        }        
    }
    public static void pwd(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }        
    }
    public static void help(String[] params){
        if(params.length != 0){
            wrongNumberOfParams(params.length,0);
            return;
        }        
    }
}
