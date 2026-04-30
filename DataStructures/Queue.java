
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
        System.out.println("Enter first node data or leave blank: ");
        String firstData = kb.nextLine();
        Node firstNode = new Node();
        if (!firstData.equals("")) {
            createHead(firstData);
            createAnotherNode();
        } else {
            queueEmpty();
        }
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
        boolean running = true;
        while (running) {
            System.out.println("Enter next node data or leave blank: ");
            String nextData = kb.nextLine();
            Node nextNode = new Node();
            if (!nextData.equals("")) {
                //createHead(nextData);
                createAnotherNode();
            } else {
                queueEmpty();
                running = false;
            }
        }
    }

    public void enqueue(Node newNode) {
        Node node = new Node();
        if (queueEmpty() == true) { // works correctly if false???????????
            this.head = newNode;
            this.tail = newNode;
        } else { // no idea if this does what I want it to
            String nextData = newNode.getData();
            Node nextNode = new Node();
            this.tail = nextNode;
        }
    }

    /*public String dequeue() {
        if (queueEmpty() == false) {

            this.head = nextNode;
        }
    }*/
}
