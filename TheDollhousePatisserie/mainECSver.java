
/**
 * This is the class where the actual game actions occur
 *
 * @author Kanya Farley
 * @version 5/6
 */
import java.util.Random;
import ecs100.*;
public class mainECSver
{
    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};
    
    OrderQueue oQueue = new OrderQueue();
    WaitingQueue wQueue = new WaitingQueue();
    /**
     * Constructor for objects of class mainECSver
     */
    public void mainECSver()
    {
        orderQueueStatus(oQueue);
        
        oQueue.orderEnqueue(newRandomCustomer()); // adds to order queue
        
        /* testing queue transferral AKA taking orders */
        orderQueueStatus(oQueue);
        waitingQueueStatus(wQueue);
        wQueue.waitingEnqueue(orderTaken(oQueue));
        System.out.println("Order has been taken");
        orderQueueStatus(oQueue);
        waitingQueueStatus(wQueue);
        
    }
    
    public Customer newRandomCustomer() {
        /* testing randomizers */
        String recipe = randomRecipe();
        String sprite = randomCustomer();
        
        Customer newCustomer = new Customer(sprite, recipe); // make new customer
        UI.println(sprite + " orders a " + recipe); // debug
        return newCustomer;
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
            UI.println("No customers.");
        } else if (queue.orderQueueEmpty() == false) {
            UI.println("Someone wants to order!");
        }
    }
    
    public void waitingQueueStatus(WaitingQueue queue) {
        if (queue.waitingQueueEmpty() == true) {
            UI.println("No current orders.");
        } else if (queue.waitingQueueEmpty() == false) {
            UI.println("Someone is waiting for an order!");
        }
    }
    
    public Customer orderTaken(OrderQueue oQueue) {
        WaitingQueue wQueue = new WaitingQueue();
        String [] next = (oQueue.orderDequeue()).split("-");
        String nextSprite = next[0];
        String nextRecipe = next[1];
        Customer waitingCustomer = new Customer(next[0], next[1]);
        UI.println(next[0] + " is waiting for a " + next[1]); // debugging
        return waitingCustomer;
    }
}
