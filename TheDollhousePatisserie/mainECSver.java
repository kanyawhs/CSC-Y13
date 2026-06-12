
/**
 * This is the class where the actual game actions occur
 * 
 * !! For the ecs100 library to work (and not cause compile error), BlueJ must be version 5.5.0
 * 
 * To do:
 * Erase order customer image
 *
 * @author Kanya Farley
 * @version 12/6
 */
import java.util.Random;
import ecs100.*;
import java.awt.*;
import java.awt.event.*;
public class mainECSver
{
    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};
    
    OrderQueue oQueue = new OrderQueue();
    WaitingQueue wQueue = new WaitingQueue();
    String currentSprite;
    String currentRecipe;
    Customer currentQueueCustomer = oQueue.getFront();
    Customer currentWaitingCustomer = wQueue.getFront();
    
    /* GUI */
    final int oQueueX = 252;
    final int oQueueY = 375;
    
    final int wQueueX = 452;
    final int wQueueY = 375;
    
    final int custWidth = 220;
    final int custHeight = 300;
    /**
     * Constructor for objects of class mainECSver
     */
    public void mainECSver()
    {
        /* background GUI */
        UI.setWindowSize(1098, 672);
        UI.drawImage("kitchen_DHP.jpeg", 0, 0);
        
        
        oQueue.orderEnqueue(newRandomCustomer()); // adds to order queue
        if (currentQueueCustomer != null) {
            //UI.drawImage(
        }
        wQueue.waitingEnqueue(orderTaken(oQueue));
        if (currentWaitingCustomer != null) {
            drawWaitingCustomer(currentWaitingCustomer.getSprite());
        }
        
        
    }
    
    public void setupGUI() {
        
    }
    
    /* random generators */
    public Customer newRandomCustomer() {
        String recipe = randomRecipe();
        String sprite = randomCustomer();
        Customer newCustomer = new Customer(sprite, recipe); // make new customer
        UI.println(sprite + " orders a " + recipe); // debug
        drawOrderingCustomer(sprite);
        return newCustomer;
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
    
    public void drawOrderingCustomer(String sprite) {
        switch (sprite) {
            case "girl1" : UI.drawImage("girl1_DHP.png", oQueueX, oQueueY, custWidth, custHeight);
                break;
            case "girl2" : UI.drawImage("girl2_DHP.png", oQueueX, oQueueY, custWidth, custHeight);
                break;
            case "boy1" : UI.drawImage("boy1_DHP.png", oQueueX, oQueueY, custWidth, custHeight);
                break;
            case "boy2" : UI.drawImage("boy2_DHP.png", oQueueX, oQueueY, custWidth, custHeight);
                break;
            case "mascot1" : UI.drawImage("mascot1_DHP.png", oQueueX, oQueueY, custWidth, custHeight);
                break;
            case "mascot2" : UI.drawImage("mascot2_DHP.png", oQueueX, oQueueY, custWidth, custHeight);
                break;
        }
    }
    
    public void drawOrder(String recipe) {
        //case "Parfait" : UI.drawImage(
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
    
    /* other (core) methods */
    public void takeOrder(OrderQueue oQueue) {
        if (oQueue.getSize() < 4) {
            oQueue.orderEnqueue(newRandomCustomer());
            UI.sleep(30000); // adds customer every 30 seconds
        }
    }
    
    public Customer orderTaken(OrderQueue oQueue) {
        WaitingQueue wQueue = new WaitingQueue();
        String [] next = (oQueue.orderDequeue()).split("-");
        currentSprite = next[0];
        currentRecipe = next[1];
        Customer waitingCustomer = new Customer(next[0], next[1]);
        //UI.println(next[0] + " is waiting for a " + next[1]); // debugging
        return waitingCustomer;
    }
    
    public void drawWaitingCustomer(String sprite) {
        switch (sprite) {
            case "girl1" : UI.drawImage("girl1_DHP.png", wQueueX, wQueueY, custWidth, custHeight);
                break;
            case "girl2" : UI.drawImage("girl2_DHP.png", wQueueX, wQueueY, custWidth, custHeight);
                break;
            case "boy1" : UI.drawImage("boy1_DHP.png", wQueueX, wQueueY, custWidth, custHeight);
                break;
            case "boy2" : UI.drawImage("boy2_DHP.png", wQueueX, wQueueY, custWidth, custHeight);
                break;
            case "mascot1" : UI.drawImage("mascot1_DHP.png", wQueueX, wQueueY, custWidth, custHeight);
                break;
            case "mascot2" : UI.drawImage("mascot2_DHP.png", wQueueX, wQueueY, custWidth, custHeight);
                break;
        }
    }

}
