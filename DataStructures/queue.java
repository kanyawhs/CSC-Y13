
/**
 * Creates a queue type linked list!
 * unfinished
 *
 * @author Kanya Farley
 * @version 30/04
 */
import java.util.Scanner;
public class queue
{
    Node firstNode;
    Node nextNode;
    Scanner kb = new Scanner(System.in);
    /**
     * Constructor for objects of class Queue
     */
    public queue()
    {
        System.out.println("Enter first node data or leave blank: ");
        String firstData = kb.nextLine();
        Node firstNode = new Node();
        if (!firstData.equals("")) {
            firstNode.createHead(firstData);
            createAnotherNode();
        } else {
            System.out.println("Queue is empty.");
        }
        if (firstNode.queueEmpty() == true) {
            System.out.println("Queue is empty.");
        } else if (firstNode.queueEmpty() == false) {
            System.out.println("Queue is not empty.");
        }
    }

    public void createAnotherNode() {
        boolean running = true;
        while (running) {
            System.out.println("Enter next node data or leave blank: ");
            String nextData = kb.nextLine();
            Node nextNode = new Node();
            if (!nextData.equals("")) {
                nextNode.createHead(nextData);
                createAnotherNode();
            } else {
                System.out.println("Queue is empty.");
                running = false;
            }
        }
        if (firstNode.queueEmpty() == true) {
            System.out.println("Queue is empty.");
        } else if (firstNode.queueEmpty() == false) {
            System.out.println("Queue is not empty.");
        }
    }
}
