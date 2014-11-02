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
public class Command {
    private String   baseCommand;
    private String[] params;
    private String[] paramsHelp;
    private String   help;
    private int      numberOfRequiredParams;

    public Command(String base){
        baseCommand = base;
    }
    
    public String getBaseCommand(){return baseCommand;}
    public String[] getParams(){return params;}
    public String[] getParamsHelp(){return paramsHelp;}
    public String getHelp(){return help;}
    public int getNumberOfRequiredParams(){return numberOfRequiredParams;}
    
    public void setBaseCommand(String s){baseCommand=s;}
    public void setParams(String[] s){params=s;numberOfRequiredParams=s.length;}
    public void setParamsHelp(String[] s){paramsHelp=s;}
    public void setHelp(String s){help=s;}


    
    /**
     * this method checks the given command against a list of given commands
     * to see if it matches any command of them 
     * then it tests that the number of parameters in the command 
     * matches the number of parameters in the corresponding valid command
     * 
     * note: it checks only the number of parameters not the format
     * 
     * @param commands a list of commands to search into
     * @param s the command in question
     * @return boolean
     */
    public static boolean valid(Command[] commands , String s){
        s = s.trim();
        String[] c = s.split(" ");
        for(Command t: commands){
            if(t.getBaseCommand().equals(c[0])){
                return c.length-1 == t.getNumberOfRequiredParams();
            }
        }
        return false;
    }
}
