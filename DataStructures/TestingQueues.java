
/**
 * Write a description of class TestingQueues here.
 *
 * @author Kanya Farley
 * @version 1/5
 */
import java.util.Scanner;
public class TestingQueues
{
    public static Scanner kb = new Scanner(System.in);
    public static void main(String[] args) {
        Queue myQueue = new Queue();
        if (myQueue.queueEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Queue is not empty.");
        }
        
        System.out.println("Enter next node data or leave blank: ");
        String nextData = kb.nextLine(); // if inequal to "hello" only 1 hello is counted, otherwise 3??
        System.out.println(nextData);
        Node nextNode = new Node(nextData);
        myQueue.enqueue(nextNode);
        
        Node nexttNode = new Node("hello");
        myQueue.enqueue(nexttNode);
        
        Node nextttNode = new Node("hello");
        myQueue.enqueue(nextttNode);
        
        System.out.println(myQueue.Count("hello"));
        
    }
}
