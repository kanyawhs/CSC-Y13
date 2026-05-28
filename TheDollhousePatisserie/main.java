
/**
 * This is the class where the actual game actions occur
 *
 * @author Kanya Farley
 * @version 28/5
 */
import java.util.Random;
public class main
{
    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};
    
    OrderQueue oQueue = new OrderQueue();
    WaitingQueue wQueue = new WaitingQueue();
    /**
     * Constructor for objects of class main
     */
    public void main()
    {
        orderQueueStatus(oQueue);
        
        /* testing randomizers */
        String hi = randomRecipe();
        String hello = randomCustomer();
        
        Customer newCustomer = new Customer(hello, hi); // make new customer
        System.out.println(hello + " orders a " + hi);
        oQueue.orderEnqueue(newCustomer); // adds to queue
        
        /* testing queue transferral AKA taking orders */
        orderQueueStatus(oQueue);
        waitingQueueStatus(wQueue);
        wQueue.waitingEnqueue(orderTaken(oQueue));
        System.out.println("Order has been taken");
        orderQueueStatus(oQueue);
        waitingQueueStatus(wQueue);
        
    }
    
    /* random generators */
    /**
     * Generates a random recipe selected from array options
     */
    public String randomRecipe() {
        Random ranRecipe = new Random();
        int randomRecipe = ranRecipe.nextInt(recipe.length);
        return recipe[randomRecipe];
    }
    
    /**
     * Generates a random customer sprite selected from array options
     */
    public String randomCustomer() {
        Random ranSprite = new Random();
        int randomSprite = ranSprite.nextInt(sprite.length);
        return sprite[randomSprite];
    }
    
    /* check status of queues */
    public void orderQueueStatus(OrderQueue queue) {
        if (queue.orderQueueEmpty() == true) {
            System.out.println("No customers.");
        } else if (queue.orderQueueEmpty() == false) {
            System.out.println("Someone wants to order!");
        }
    }
    
    public void waitingQueueStatus(WaitingQueue queue) {
        if (queue.waitingQueueEmpty() == true) {
            System.out.println("No current orders.");
        } else if (queue.waitingQueueEmpty() == false) {
            System.out.println("Someone is waiting for an order!");
        }
    }
    
    public Customer orderTaken(OrderQueue oQueue) {
        WaitingQueue wQueue = new WaitingQueue();
        String [] next = (oQueue.orderDequeue()).split("-");
        String nextSprite = next[0];
        String nextRecipe = next[1];
        Customer waitingCustomer = new Customer(next[0], next[1]);
        System.out.println(next[0] + " is waiting for a " + next[1]); // debugging
        return waitingCustomer;
    }
}
