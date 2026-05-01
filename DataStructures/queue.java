
/**
 * Creates a queue type linked list!
 *
 * @author Kanya Farley
 * @version 1/5
 * 
 */
import java.util.Scanner;
public class Queue
{
    private Node head;
    private Node tail;

    Node firstNode;
    Node nextNode;
    Scanner kb = new Scanner(System.in);
    /**
     * Constructor for objects of class Queue
     */
    public Queue()
    {
        //
    }

    public void createHead(String data) {
        this.head = new Node();
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
        Node node = new Node();
        if (queueEmpty() == true) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            String nextData = newNode.getData(); // points to next node
            Node nextNode = new Node();
            this.tail = nextNode; // newest node becomes tail
        }
    }

    public String dequeue() {
        if (queueEmpty() == false) {
            String last = this.head.getData(); // gets head
            this.head.getNextNode(); // points to follower
            return(last);
        } else if (queueEmpty() == true) {
            System.out.println("Queue empty. Nothing to dequeue.");
        }
        return("");
    }
}
