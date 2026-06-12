
/**
 * New customers appear in this queue and are dequeued when the customers order is taken
 *
 * @author Kanya Farley
 * @version 12/6
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;

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

    public void firstInOrderQueue(Customer customer) {
        this.front = customer;
        this.back = customer;
        
    }
    
    public boolean orderQueueEmpty() {
        if (this.front == null) {
            return(true);
        } else {
            return(false);
        }
    }
    
    public void orderEnqueue(Customer newCust) {
        if (orderQueueEmpty() == true) {
            firstInOrderQueue(newCust);
        } else {
            this.back.setNextCustomer(newCust);
            this.back = newCust;
        }
    }
    
    public String orderDequeue() {
        if (orderQueueEmpty() == false) {
            String sprite = front.getSprite();
            String recipe = front.getRecipe();
            this.front = front.getNextCustomer();
            return(sprite + "-" + recipe);
        } else if (orderQueueEmpty() == true) {
            System.out.println("No customers");
        }
        return("");
    }
    
    public Customer getFront() {
        return this.front;
    }
    
    public int getSize() {
        int count = 0;
        Customer currentCustomer = this.front;
        while (currentCustomer.getNextCustomer() != null) {
            count++;
            currentCustomer = currentCustomer.getNextCustomer();
        }
        return count;
    }
}
