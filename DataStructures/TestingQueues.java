
/**
 * Write a description of class TestingQueues here.
 *
 * @author Kanya Farley
 * @version 1/5
 */
public class TestingQueues
{
    public static void main(String[] args) {
        Queue myQueue = new Queue();
        if (myQueue.queueEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Queue is not empty.");
        }
        
        myQueue.createAnotherNode();
        myQueue.dequeue(); // should become empty after first node?
        
        if (myQueue.queueEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Queue is not empty.");
        }
        
    }
}
