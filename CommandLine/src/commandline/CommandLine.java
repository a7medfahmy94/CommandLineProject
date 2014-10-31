/*
 * This project simulates the Unix command line (terminal)
 * this is an assignment at FCI-CU 3rd-level OS1-course
 */
package commandline;

import java.util.Scanner;

/**
 *
 * @author ahmed fahmy - a7med.fahmy94@gmail.com
 * @author hala mohamed
 * 
 */
public class CommandLine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String command = new String();
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        
        while(command != "exit"){//exit - exits the program
            // process the command
        }
    
    }
    
}
