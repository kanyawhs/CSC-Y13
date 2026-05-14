
/**
 * Write a description of class OrderQueue here.
 *
 * @author Kanya Farley
 * @version 15/5
 */
import java.util.Scanner;
public class OrderQueue
{
    private Customer front;
    private Customer back;
    
    Customer next;
    Scanner kb = new Scanner(System.in);
    /**
     * Constructor for objects of class OrderQueue
     */
    public OrderQueue()
    {
        //
    }

    public void firstInQueue(Customer customer) {
        this.front = customer;
        this.back = customer;
    }
    
    public boolean queueEmpty() {
        if (this.front == null) {
            return(true);
        } else {
            return(false);
        }
    }
    
    public void orderEnqueue(Customer newCust) {
        if (queueEmpty() == true) {
            firstInQueue(newCust);
        } else {
            this.back.setNextCustomer(newCust);
            this.back = newCust;
        }
    }
    
    public String orderDequeue() {
        if (queueEmpty() == false) {
            String sprite = front.getSprite();
            String recipe = front.getRecipe();
            this.front = front.getNextCustomer();
            return(sprite + recipe);
        } else if (queueEmpty() == true) {
            System.out.println("No customers");
        }
        return("");
    }
}
