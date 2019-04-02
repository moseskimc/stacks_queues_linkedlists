// Name: Moses Kim
// Course: COSI 21a, Section 1
// Date: 9/25/2015
// Description: This program calculates the average waiting time per student given
//              a text file of arrival times of any size n

/* Data Structure: Queue is the most efficient data structure we can use since arriving
                   and leaving are two natural methods that can be performed using a 
                   queue. */

import java.util.*;
import java.io.*;

public class Question2{   
    static int n;          //This will be the max. length of the line.

        
    public static void main(String[] args) throws IOException {

        //We prompt the user to enter the text file MailroomTest.txt to be read.
        //After creating an InputStreamReader object, then we pass this object to
        //our BufferedRead br and finally get the input from the user.
        
        System.out.print("Please enter the name of your textfile: ");
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String name = br.readLine();


        //Since the maximum number of students that can be in the line at our mailroom
        //is 10, we create a queue(see folder) with of length 10 (I don't know which word
        //to use to be honest. size or length?)
        
        int n = 10;
        Queue B = new Queue(n);


        
        //Here we instantiate a new object of ReadFile, which is a separte class found
        //in the folder. One important method that we can call on this object counts
        //the number of integers in a txt file, which is crucial in our problem.
        
        ReadFile r = new ReadFile(name);
        
        //Here we pass the file name to the method readSize to find the number of int.
        //which is the number of people
        int numberofpeople = r.readSize(); 
        
        //Then, we create an array of that size
        int[] arr = new int[numberofpeople];
        
        //We create a scanner object and per
        Scanner x = r.openFile();
        
        //The next step would be to read the integers into our array arr. We do this by
        //creating a while loop.
    

        int k = 0;
        while(x.hasNext()){
            arr[k] = x.nextInt();
            k++;               
        }
        x.close();
        
        //We get the starting time and the time of the last student.
        
        int starttime = arr[0] ;
        int lastarrtime = arr[numberofpeople - 1];
        


        int total = 0;            //We declare this variable to be the total wait time
                                  //of all students fromt t=starttime to t=lastarrtime
              
        int missed = 0;           //This is to keep track of people who missed the line.
                                  //A calculation check.

        int i = 0;                //This counter is very important. We will use this to
                                  //skip people in array arr who miss the line because
                                  //they find the queue full.
        
        int peoplewhowaited = 0;  //This is, again, a calculation check.

        
        //We start our for loop at s = starttime and increase in five units since it 
        //takes five minutes to serve each student.
        
        for (int s = starttime; s <= lastarrtime ; s+=5){
            //We add a student if the line is not full and increment the counter
            //i as we do so.
            while (arr[i] == s && B.size() < n) {
                B.enqueue(arr[i]);
                i++;
                peoplewhowaited++;
            }
            int timeofrear = arr[i - 1];   //arrival time of the last person in line
            
            //However, if a student arrives and find the queue full, this student will
            //leave. Soo all student who arrive at the same time as the last person in 
            //line must be skipped. We do this by ch
            while (i < numberofpeople && B.full() && timeofrear == arr[i]) {
                i++;            //We need to skip all those who arrive to the mailroom
                                //and find the queue full.
                                
                missed++;       //we need to keep count of those people who miss the line
             }
             
            total = total + 5*(B.size()-1);  //total waiting time up to t=s minutes.    
             
            //If the queue is not empty, after five minutes pass one person will leave
            //the mail room. In this if statement we do just that. We take out the person
            //from the front of the line.
            
            if (!B.isEmpty()) {
                try{
                    B.dequeue();
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            else {
            }
        
        }
    
    //When the for loop ends there are still people in line. We need to compute the time
    //that they wait in line. If there are 3 people waiting in line then the waiting time
    //would be 5(2+1). So whatever the B.size() is, we add up 1+2+3+...+B(size)-1 and then
    //multiply by five. In simple notation, this is nothing but five times B.size() - 1
    //choose 2, or (5)C(B.size()-1,2). 
    total = total + 5 * (B.size()*B.size() - B.size())/2;
    
    //Now we are ready to calculate the average waiting time. Divide total by people who
    //waited. We have to exclude those people who could not make it.
    double averagewaitingtime = (double) total / (peoplewhowaited); 
    
    //We finally print our result!   
    System.out.println("The average waiting time per student is: " + averagewaitingtime);   
	}
    
  
}