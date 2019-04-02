import java.util.*;
import java.io.*;

//We implement a queue of size 10 using an array.

class Queue{
    int front, rear;
    int counter;      //we use this variable to keep track of the number of elements
                      //in our queue.
    
    int n;            //This will be the size of the array we will be using to implement
                      //our queue.
                      
    int[] B;          //We declare the name of the array to be used.
    
    //We write a constructor for this class. The constructor takes one argument which
    //is the size of the array that will be used to implement our queue. 
    public Queue(int size){
        front = 0;
        rear = 0;
        counter = 0;
        n = size;
        
        //Now we create an array of integers of given size 'size'
        B = new int[size];
    }
    
    
    //This method doesn't take any arguments. We compute the number of elements in the
    //array.
    
    public int size() {
        if (front == rear && counter == n){
            return n;
        }
        else{
            return (10-front+rear)%10;
        }
    }
    
    //This checks whether the Queue is empty.
    public boolean isEmpty() {
        return (counter == 0);
    }
    
    public boolean full() {
        return (counter == n);
    }
    
    public int front(){
        return front;
    }
    
    public int rear(){
        return rear;
    }
    
    public void enqueue(int x) {
        if (counter == 10){
            System.out.println("Queue is full!");
        }
        else {
            B[rear] = x;
            rear = (rear + 1)%10;
            counter++;
        }
    }
    
    public int dequeue() throws Exception{        
        if (counter==0) 
            throw new Exception("Queue is empty!");
        int result = B[front];
        front = (front + 1)%10;
        counter--;
        return result;
    }  
    
    

}