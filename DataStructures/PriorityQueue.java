
/**
 * Queueing where different data types have different prioritisation
 *
 * @author Kanya Farley
 * @version 7/5
 */
public class PriorityQueue
{
    private Queue lowPriority;
    private Queue highPriority;

    /**
     * Constructor for objects of class PriorityQueue
     */
    public PriorityQueue()
    {
        lowPriority = new Queue();
        highPriority = new Queue();
    }
    
    public void enqueue(Node newNode, boolean high) {
        if (high) {
            highPriority.enqueue(newNode);
        } else {
            lowPriority.enqueue(newNode);
        }
    }
    
    public String dequeue() {
        if (highPriority.queueEmpty()) {
            return "" + lowPriority.dequeue();
        } else {
            return "" + highPriority.dequeue();
        }
    }
    
    public boolean queueEmpty() {
        if (highPriority.queueEmpty() && lowPriority.queueEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
