public class Queue {
    // Circular Queue implementation in Java
    int front, rear;
    Passenger [] psgq;

    public Queue() {
        front = -1;
        rear = -1;
        Passenger [] psgq = new Passenger[5];
        this.psgq = psgq;
        for (int i = 0; i < 5; i++) {
            psgq[i] = new Passenger();
        }
    }



    // Adding an element
    void enQueue(String firstName, String lastName, int expenses){
        if (front==-1 && rear == -1){
            front = rear =0;
            psgq[rear] = new Passenger(firstName, lastName, expenses);
            System.out.println("Passenger inserted to queue.");
        }
        else if (((rear+1)%5) == front){
            System.out.println("Queue is full");
        }
        else{
            rear = (rear+1)%5;
            psgq[rear] = new Passenger(firstName, lastName, expenses);
            System.out.println("Passenger inserted to queue.");
        }
    }

    // Removing an element
    void deQueue(){
        if (front==-1 && rear == -1){
            System.out.println("Queue is Empty");
        }
        // Q has only one element, so we reset the queue after deleting it.
        else if (front == rear) {
            front = rear = -1;
        }
        else {
            front = (front + 1) % 5;
        }

    }

}
