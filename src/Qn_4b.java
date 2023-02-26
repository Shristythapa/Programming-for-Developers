/*Given a linked list containing an integer value, return the number of steps required to sort an array in
ascending order by eliminating elements at each step
Note: at each step remove element a[i] where a[i-1]> a[i]*/
class Node{
    /*node of singly linked list*/
    int data;
    Node next;
    Node head=null;
    Node(int data){
        this.data = data;
        this.next = null;
    }
    public void addNode(int data){
        /*add node to link list*/
        Node newNode = new Node(data);
        if (head==null){
            head=newNode;
        } else{
            Node current=head;
            while(current.next!=null){
                current=current.next;
            }
            current.next=newNode;
        }
    }
    public void sortList(){
        Node first=head;
        Node second=head.next;
        int step=0;
        while (second!=null){
            if(first.data>second.data){
                //switch required
                first.next=second.next;
                second.next=null;
                second=first.next;
                step++;
            } else{
                first=first.next;
                second=second.next;
            }
        }
        System.out.println("The number of steps is "+step);
    }
}
 class SortLinkedList {
    public static void main(String[] args) {
        Node newnode = new Node(30);
        newnode.addNode(20);
        newnode.addNode(5);
        newnode.addNode(50);
        newnode.addNode(4);
        newnode.sortList();
    }
}