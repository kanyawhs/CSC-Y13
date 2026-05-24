
/**
 * Write a description of class main here.
 *
 * @author Kanya Farley
 * @version 25/5
 */
import java.util.Random;
public class main
{
    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};
    /**
     * Constructor for objects of class main
     */
    public void main()
    {
        OrderQueue queue = new OrderQueue(); // make new queue
        
        orderQueueStatus(queue);
        
        /* testing randomizers */
        String hi = randomRecipe();
        String hello = randomCustomer();
        
        Customer newCustomer = new Customer(hello, hi); // make new customer
        System.out.println(hello + " orders a " + hi);
        queue.orderEnqueue(newCustomer); // adds to queue
        orderQueueStatus(queue);
        
    }
    
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
    
    public void orderQueueStatus(OrderQueue queue) {
        if (queue.orderQueueEmpty() == true) {
            System.out.println("No customers.");
        } else if (queue.orderQueueEmpty() == false) {
            System.out.println("Someone is ordering!");
        }
    }
    
    /*public WaitingQueue orderTaken(Customer lastOrdered) {
        
    }*/
}
