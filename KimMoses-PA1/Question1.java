//Name: Moses Kim
//Course: COSI 21A, Section 1
//Date: September 29th, 2015
//Description: This program takes in a postfix expression one entry at a time
//              and displays the result once the calculation is complete.


//Choice of data structure is stacks. This is the most efficient way to compute
//a postfix expression in linear time.


import java.util.*;
import java.io.*;




public class Question1{

    public static void main(String[] arg) throws IOException {
    
    double firstnumber, secondnumber; //These are the global variables we use
                                      //and pass to the method binaryOp below
    
    int i=0;                          //We declare an integer i and use it as a counter
                                      //to determine the length of the expression.
                                      //We do this in order to create an array of 
                                      //appropriate size.
    
    String character;                 //This is also an argument of method binaryOp
    
    
    Stack expression = new Stack();   //We instantiate a stack. We push integers provided
                                      //by the user into this stack.
                                      
                                      //Note: the class stack is implemented in a separate
                                      //java file in folder.
    
    Stack display = new Stack();      //We create another stack in order to display
                                      //the whole expression at the end.

    //We print out some instructions about how the PostFix calculator works
    //telling the user that the calculator takes one input at a time!
     
    System.out.println("Welcome to our PostFix calculator! This calculator takes");
    System.out.println("one input at a time. So please type each character and");
    System.out.println("press ENTER! If you wish to start over again, type 'clear'");
    System.out.println("and press ENTER!");


    //We have a while loop that breaks whenever our stack 'expression' becomes empty
    
    while (true){
        System.out.print("Enter your character:"); //We prompt the reader to enter a char
        Scanner x = new Scanner(System.in);        //Scanner object x is created
        character = x.next();                      //We read the entry the user types

        //We need to write another if statement for the user to start over when he or she
        //types 'clear'.
                
        if (!character.equals("clear")){
            display.pushString(character);         //We push the entries of the user
                                                   //in order to print out the whole
                                                   //expression at the end.
        }
     
        
        //In this 'if' statement we check whether our string is an integer using 
        //the method isInteger, which is defined below on lines 75-83.
        
        if (isInteger(character)){
            int integer = Integer.parseInt(character);
            expression.push(integer);
        }
        
        //If an entry is not an integer, we enter the else block and pop an integer
        //which we assign to our variable secondnumber and pop once more and assign
        //that to our firstnumber. This order is very important because of the postfix
        //evaluation.
        
        //We reset empty out both expression and display stacks.
        
        else if (character.equals("clear")){
            expression.clear();
            display.clear();
        
        }           
        else{
            secondnumber = expression.pop();
            firstnumber = expression.pop();
            
            //If the stack is empty, we break the loop.
            
            if (expression.isEmpty()){
                break;
            }
            
            //If the stack is not empty we convert the string 'character' into a char
            //type and set it equal to the variable operator. 
            //Now we take the two integers we popped and do the operation 'operator' to
            //them. Then we push the result into our stack 'expression'.
            
            else{
            char operator = character.charAt(0);
            double endresult = binaryOp(firstnumber,secondnumber,operator);
            expression.push(endresult);
            }
        }
             
    }
    //The last two integers in the stack are popped, the while loop breaks.
    
    char operator = character.charAt(0); //This is our last non integer string
                                         //which we convert into a character using
                                         //the method charAt().
                                         
    //Once again, we pass all of these data to the 'binaryOp' method and print out
    //the final result                                    
    double endresult = binaryOp(firstnumber,secondnumber,operator);
    
    //We push the elements of display into our now empty stack 'expression'.
    //We pop the elements from 'expression' to get the expression in the order entered.
    
    while(!display.isEmpty()) {
    expression.pushString(display.popString());
    }
    
    System.out.print("The expression " +"");
    while(!expression.isEmpty()) {
    String st = expression.popString();
    System.out.print(st);
    }
    
    System.out.println(" is equal to "+ endresult+ "!");
    }

    //We write a method that checks if a string is an integer or not.
    //The static method isInteger has @arg String st
    //In this try and catch block we pass the string st to the method Integer.parseInt
    //Method returns true if string is an integer and false otherwise

    public static boolean isInteger(String st){
        try{
            Integer.parseInt(st);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }    

    //We define a method that has three arguments.
    //@arg double op1 is the first operand which is a double
    //@arg double op2 is our second double type operand.
    //@arg char op is the argument for the binary operator +,-,*, and /
    //Given two doubles op1 and op1 and an operator op, it returns the result of the
    //operation op.
    
    public static double binaryOp(double op1, double op2, char op){
        double result = 0;
        if (op == '+'){
        result = op1 + op2;
        }
        else if (op == '-'){
        result = op1 - op2;
        }
        else if (op == '*'){
        result = op1 * op2;
        }
        else if (op == '/'){
        result = op1 / op2;
        }
        return result;
    }
 
}
