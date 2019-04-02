

class test {

    public static void main(String[] args){
        LinkedList list = new LinkedList();
        list.insert("John");
        list.insertRight("Mark");
        System.out.println("The size now is: "+ list.size());
        System.out.printf("The element to the right is: %s\n", list.removeLast());
        System.out.println("The size now is: "+ list.size());
        System.out.printf("To the left is %s\n", list.removeFirst());
        System.out.println("The size now is: "+ list.size());
       // System.out.println("Now the list is empty: "+ list.isEmpty());
       
        list.insert("Matthew");
        System.out.println("I want to somehow print the index of a node!");
        System.out.println("Let's see..." + list.returnHead());
    
    
    }



}