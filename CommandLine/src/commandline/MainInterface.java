/*
 * This project simulates the Unix command line (terminal)
 * this is an assignment at FCI-CU 3rd-level OS1-course
 */
package commandline;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ahmed fahmy - a7med.fahmy94@gmail.com
 * @author hala mohamed
 * 
 */
public class MainInterface {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MainInterface cli = new MainInterface();
        String command = new String();
        String[] commandsQueue;
        Scanner in = new Scanner(System.in);
        
        while(true){
            System.out.print("$ ");
            command = in.nextLine(); //input a new line
            commandsQueue = cli.parseCommand(command);//a list of commands
            for(String c: commandsQueue){
                if(c.length() != 0)
                    CommandController.process(c);
            }
        }
        
    }
    
    
    
    /**
     * This method will transform a string into a list of commands included
     * in that string 
     * for example if the command was "cd /home ; ls"
     * it will return ["cd /home" , "ls"]
     * @param command
     * @return String[] containing a list of commands
     */
    private String[] parseCommand(String command){
        String[] ret = command.split(";");
        return ret;
    }
    

}
