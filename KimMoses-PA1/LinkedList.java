//We implement a circular doubly linked list using a node class.

import java.util.*;
import java.io.*;



//We need to define two subfields: next and prev.
class Node{
    public String name;
    public Node next;
    public Node prev;
    
    //We write a constructor for the class
    public Node(String name){
        this.name = name;
    }
}



public class LinkedList{

    public Node head;
    public Node current;
    public int size;
    public int maxlength;
    
    
    
    //We can specify any size we wish our list to have. Here we want a finite length
    //since the 'duck, duck, goose' game is played with finite number of people.
    
    LinkedList(int x){
        head = null;
        current = null;
        size = 0;
        int maxlength = x;  
    }
    
    
    public boolean isEmpty(){
        return (head == null);
    }
    
    public boolean isFull(){
        return (size == maxlength);
    }  
    
      
    public void insertFirst(String name){
        Node newnode = new Node(name);        
        
        //If the list is empty, then assign new node to head.
        if (isEmpty()){
            head = newnode;
            head.next = head;
            head.prev = head;

        }
        //However, if it is not, then draw an arrow from the previous link to the new
        //link, another arrow from the new link to the previous, and finally move set 
        //head equal to the new node.
        else {
            Node temp = head;
            
            //We write this while loop to position our pointer at the last node.
            while (temp.next!=head) {
                temp = temp.next;
            }
            newnode.next = head;
            head.prev = newnode;
            head = newnode;
            temp.next = head;
            head.prev = temp;      
        }          
        size++;
    }
        
   
    //This is exactly the same as the insert method except that the tail changes value
    
    public void insertLast(String x){
        Node newnode = new Node(x);
        
        if (isEmpty()){
            head = newnode;
            head.next = head;
            head.prev = head;

        }
        else {
            Node last = head;
            while (last.next!=head) {
                last = last.next;
            }
            Node temp = head;
            last.next = newnode;
            newnode.prev = last;
            last = newnode;
            last.next = head;
            head.prev = last;         
        }
        size++;
    }

    //Now we write a method that inserts elements after a node y.
    
    public void insertAfter(String x, Node y){
        Node temp = y;

        if (temp.next == head) {
            insertLast(x);
        }
        //This is the most important case. We need one additional pointer in order to a
        //new node after the node y. We draw two arrows: one going from y to the new
        //new node and the other coming from the new node and going back to y. The
        //pointer temp2 is at y.next. So we draw two arrows between temp2 and our new
        //node.
        else {
            Node newnode = new Node(x);
            Node temp2 = temp.next;
            temp.next = newnode;
            newnode.prev = temp;
            newnode.next = temp;
            temp2.prev = newnode; 
            size++;  
        }
                                          
    
    }
    
    
    //This method removes the first element of the linked list and returns it.
    
    public String removeFirst(){
        Node temp = head;    
        if (!isEmpty()){
            Node last = head;
            while(last.next != head){
                last = last.next;
            }
            head = head.next;
            head.prev = last;
            last.next = head;
            size--;            
        }
        else{
            System.out.println("List is empty!");
        }

        return temp.name;
    }
    
    //This method removes the last element of the list and returns it.
    public String removeLast(){   
        Node last = head;
        while (last.next!=head){
            last = last.next;
        }
        if (isEmpty()){
            System.out.println("List is empty!");
        }
        else {
        Node temp = last.prev;
        temp.next = head;
        head.prev = temp;
        size--;
        }
        return last.name;
    }
    
    
    //This method removes a specific node and returns the value of that node. We use this
    //method in order to simulate 'it' sitting in the position of the person he tags.
    
    public String remove(Node x){
        if (isEmpty()){
            System.out.println("List is empty!");
        }
        else{
            Node key = x;
            if (key == head){
                removeFirst();
            }
            else if (key == head.prev){
                removeLast();
            }
            else{
                Node key2 = key.prev;
                key2.next = key.next;
                (key.next).prev = key2;
                size--;   
            }             
        }
        return x.name;
    }
    
    
    //We use this at the start of the game. The first person who is 'it' moves from the 
    //head position of the circle. 
    public void setCurrToHead(){
        current = head;
    }
    
    //This is the most important method that we implement in order to play the game
    //'duck duck goose'. This method removes the person who was tagged goose, inserts
    //'it' in his or her position, and returns the name of the new 'it'.
    public String removeInsertAtCurr(String x){
        Node temp = current;
        //This is a very important case. The pointer current must stay in the head
        //position. If we simply remove the node at the head by forming the four
        //arrows needed (as you can see in the else case) then the pointer head becomes
        //assigned to the wrong person.
        
        if (current == head) {
            removeFirst();
            insertFirst(x);
            current = head;
        }
        else {
            Node newnode = new Node(x);
            newnode.prev = temp.prev;
            newnode.next = temp.next;
            (temp.prev).next = newnode;
            (temp.next).prev = newnode;
            temp.next = null;
            temp.prev = null;
            current = newnode;
        }
        return temp.name;
    
    }
    
    
}