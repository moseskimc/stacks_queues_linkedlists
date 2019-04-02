import java.io.*;
import java.util.*;

public class ReadFile {
    
    String s;
    int t;
    int length = 0;
    String fileName;

    //We build a constructor for our class ReadFile
    
    public ReadFile(String fileName){
        this.fileName = fileName;
    }
    
    public Scanner openFile(){
        Scanner x = null;
        try{
            x = new Scanner(new File(fileName));
        }
        catch(Exception e){
            System.out.println("could not find file");
        }
        return x;
    }
    
    public int readCharSize(){
        int sum = 0, i=0;
        Scanner x = openFile();
        while(x.hasNext()){   
            s = x.next();       
            i++;
        }
        x.close();
        return i;  
    }
    
    public int readSize(){
        int sum = 0, i=0;
        Scanner x = openFile();
        while(x.hasNext()){   
            s = x.next();
            i++;
        }
        x.close();
        return i;  
    }
    
      
}