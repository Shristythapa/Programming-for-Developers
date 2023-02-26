package Qn_9to12;

public class Queue {
    private int size;
    private int[] arr;
    private int rear=-1;
    private int front =-1;

    public Queue(int size){
        this.size=size;
        this.arr = new int[size];
    }
    public void enqueue(int data){
        if(isFull()){
            System.out.println("Queue Overflow");
        }else{
            if (front==-1)
                front=0;
            arr[++rear]=data;
        }
    }
    public int deque(){
        if(isEmpty()){
            System.out.println("StackUnderFlow");
            return -5678;
        }else{
            if(front==rear){
                int val=front;
                front=rear=-1;
                return arr[val];
            }
            return arr[front++];
        }
    }
    public boolean isEmpty(){
        return front==-1 && rear==-1;
    }
    public boolean isFull(){
        return rear==arr.length-1;
    }


}
