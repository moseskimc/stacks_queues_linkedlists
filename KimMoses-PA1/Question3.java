/*
Name: Moses Kim
Course: COSI 11a, Fall 2015
Date: Sept. 28, 2015
Description: The program simulates the game duck-duck-goose by taking in two files, one
             has the number of people in a circle, and the other contains the person who
             is it and a list of the nth subsequent person who his tagged goose. The last
             integer in the second file will signal the end of the game. The final output
             is the list of people sitting in the circle and also the person who remained
             it.
*/

/* Choice of data structure for this problem is a circular doubly linked list. In this
particular problem we need to be able to traverse clockwise and counter clockwise
around a circle. Essentially, we set a pointer named 'current' to the designated 'goose'
and move this to the right if the integer is positive (clockwise motion) and 
move it to the left if the integer is negative (counterclockwise motion).
*/


import java.util.*;
import java.io.*;


public class Question3{


    public static void main(String[] args) throws IOException {

        //We first one to prompt the user to enter the circle file
        //We create an InputStreamReader object, pass it to a BufferReader and then
        //read the input from the user.
        System.out.print("Input circle file: ");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String file1 = br.readLine();
    
        //We do this again for a second file containing the simulation of the game.
        System.out.print("Input game file: ");
        String file2 = br.readLine();

    
        //First, we read the content of the circle file. This file only has strings.
        //We read the number of words which will tell us the number of people in the
        //circle. We first create a ReadFile object (see folder) and call the
        //readCharSize method on it.
        
        ReadFile r1 = new ReadFile(file1);
        int n = r1.readCharSize();
        
    
        //What we do now is create a linked list of maximum size n.
        
        LinkedList circle = new LinkedList(n);
        
        
        //Now we insert the elements into our linked list 'circle'!
        
        ReadFile r = new ReadFile(file1);
        Scanner x = r.openFile();
        while(x.hasNextLine()){
            String st = x.nextLine();
            circle.insertLast(st);              
        }
        x.close();
                
        
        //We set the pointer 'current' to head. This is important since this is the
        //reference point of the first person who is 'it'.
        circle.setCurrToHead();
                
        //We read the first string on the first line of game.txt and set the variable it 
        //to that name.
        ReadFile r2 = new ReadFile(file2);
        Scanner y = r2.openFile();      
        String it = y.next();             
        
        y.nextLine(); //We go to the next line and start reading integers.
          

        while(y.hasNext()){
            
            int location = (y.nextInt())%n; //we only care about the location of the new
                                            //'it'. So we mod out by the number of people
                                            //in the circle.        
        
            //if we have a positive integer we move the current pointer that many units
            //to the right by using the .next
            if (location > 0) {
                int count = location;
                while (count > 0){
                    circle.current = circle.current.next;
                    count--;
                }
                //This is simply assigning the new 'it'. This is the most important
                //method that we call from our linkedlist class (please see folder).
                //This method does two things: it removes the node at the current node.
                //inserts a new node with the value 'it', and returns the name of the
                //new 'it'.
                it = circle.removeInsertAtCurr(it);     
            }
            
            //Similarly, we move the 'current' to the left if the integer is negative.  
           
            else if (location < 0){
                int count2 = -1*location;    //We turn this negative integer to positive
                                             //in order to count the number of shifts
                                             //that 'current' has to make to the left.
                while (count2 > 0){
                    circle.current = circle.current.prev;
                    count2--;
                }
                it = circle.removeInsertAtCurr(it);
            }
            
            //If the integer is a multiple of n, then we do not move the current pointer
            //and designate the new 'it' to be there.
            else {
                it = circle.removeInsertAtCurr(it);
            }
        
        }
        
        
        //We finally print the person who is tagged last!
        System.out.printf("%s is still it!\n", it);
        
        
        //Now we simply print out the players in the circle starting at the head.
        //Calling the method removeFirst does this quickly.
                
        System.out.print("[");
        while (circle.size-1>0) {
            System.out.print(circle.removeFirst()+",");
        }
        System.out.print(circle.removeFirst()+"]\n");
        
    }    
}