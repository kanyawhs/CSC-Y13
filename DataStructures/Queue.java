
/**
 * Creates a queue type linked list!
 *
 * @author Kanya Farley
 * @version 4/5
 * 
 * dequeue() not quite working??
 * 
 */
import java.util.Scanner;
public class Queue
{
    private Node head;
    private Node tail;

    Node nextNode;
    Scanner kb = new Scanner(System.in);
    /**
     * Constructor for objects of class Queue
     */
    public Queue()
    {
        //
    }

    public void createHead(Node node) {
        this.head = node;
        this.tail = node;
    }

    public boolean queueEmpty() {
        if (this.head == null) {
            return(true);
        } else {
            return(false);
        }
    }

    public void createAnotherNode() {
        System.out.println("Enter next node data or leave blank: ");
        String nextData = kb.nextLine();
        Node nextNode = new Node();
        if (!nextData.equals("")) {
            enqueue(nextNode);
            createAnotherNode();
        } else {
            queueEmpty();
        }
    }

    public void enqueue(Node newNode) {
        if (queueEmpty() == true) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
    }

    public String dequeue() {
        if (queueEmpty() == false) { // NOT WORKING YET!
            String data = head.getData();
            // COME BACK TO THIS W SHANE
            return(data);
        } else if (queueEmpty() == true) {
            System.out.println("Queue empty. Nothing to dequeue.");
        }
        return("");
    }

    public void addAtEnd(Node end) {
        this.tail = new Node(); // get tail
        enqueue(end); // add final node
    }

    public int Count (int payload) { // where do i get payload lol
        Node thisNode = new Node();
        thisNode = this.head;
        int count = 0;
        for (int i = 1; i < payload+1; i++) {
            if (thisNode.getData().equals(Integer.toString(payload))) {
                count++;
            }
            thisNode.getNextNode();
        }
        return(count);
    }

    public void InsertInOrder (Node anode) {
        
    }
    
    public void Uniquify() {
        
    }
    
    public void Sort() {
        
    }
}
