//Name: Moses Kim
//Course: COSI 21A, Section 1

//We create a class and name it Stack which is used in the program Question1.

import java.util.*;
import java.io.*;

//create the node class which will be used to create nodes in our list.

class Node{
    public double value;      //This field will be use for methods that take double as arg
    public String st;         //Similarly, this field is used to pass strings to methods
                              
    public Node next;
    
    //we must build a constructor that takes doubles
    public Node(double value){
    this.value = value;
    }
    
    //we write another constructor that takes strings
    public Node(String st){
    this.st=st;
    }
    
}

//this is the main class. We instantiate nodes using the class above.
public class Stack{
    
    public Node top; //we want to declare our top or head pointer
    
    Stack(){
    top = null;  //when the list is created we want the top to point no where!
    }
    
    //now we write a method that checks whether the stack is empty.
    public boolean isEmpty(){
    
    return (top==null); //this means that there is only one arrow
    
    }
    
    //We define a push method for inserting doubles into the stack
    //First we create a new node that takes in doubles as argument
    //We point the newnode arrow to top and set the top node equal to newnode.
    //In this way, every time a new node is added each node will have an arrow
    //pointing to its neighbor except for the first node that was inserted, which
    //points to null.
    public void push(double value){
    Node newnode = new Node(value);
    newnode.next = top;
    top = newnode;
    }
    
    //We create another method to push strings. Same as above. Only difference is that 
    //we use a different field. This time a string.
    public void pushString(String st){
    Node newnode = new Node(st);
    newnode.next = top;
    top = newnode;
    }
    
    
    //This is our pop method. It removes and returns the last element that was pushed 
    //into the stack. The type of data that it returns is a double.
    //So we have a pointer set to the node top. If the stack is not empty, we simply
    //set top equal to the node immediately after it and return the value of the removed
    //node temp.
    //On the other hand, if it is empty, we simply print the error: stack underflow.
    public double pop(){
        Node temp = top;
        if (!isEmpty()) {
        top = top.next;
        }
        else{
        System.out.println("Error: stack underflow!");
        }
        return temp.value;
    }
    
    //We create a separate function to pop strings from our stack. The only difference is
    //in the field. Now it returns a string instead of a double.
    public String popString(){
        Node temp = top;
        if (!isEmpty()) {
        top = top.next;
        }
        else{
        System.out.println("Error: stack underflow!");
        }
        return temp.st;
    }
    
    //One last method that we need is to empty out the stack. We do this by setting the
    //variable top equal to null.
    
    public void clear(){
        top = null;
    }
    
    

}





