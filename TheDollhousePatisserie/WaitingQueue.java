
/**
 * Ordering customers join this queue when order taken, and leave when served.
 *
 * @author Kanya Farley
 * @version 25/5
 */
import java.util.Scanner;
public class WaitingQueue
{
    private Customer front;
    private Customer back;
    
    Customer next;
    Scanner kb = new Scanner(System.in);
    /**
     * Constructor for objects of class WaitingQueue
     */
    public WaitingQueue()
    {
        //
    }
    
    public void firstInWaitingQueue(Customer customer) {
        this.front = customer;
        this.back = customer;
    }
    
    public boolean waitingQueueEmpty() {
        if (this.front == null) {
            return(true);
        } else {
            return(false);
        }
    }
    
    public void orderEnqueue(Customer newCust) { // need to get from last dequeued from order queue
        if (waitingQueueEmpty() == true) {
            firstInWaitingQueue(newCust);
        } else {
            this.back.setNextCustomer(newCust);
            this.back = newCust;
        }
    }
    
    public String orderDequeue() { 
        if (waitingQueueEmpty() == false) {
            String sprite = front.getSprite();
            String recipe = front.getRecipe();
            this.front = front.getNextCustomer();
            
            return(sprite + recipe);
        } else if (waitingQueueEmpty() == true) {
            System.out.println("No customers waiting");
        }
        return("");
    }
}