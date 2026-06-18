
/**
 * This is the class where the actual game actions occur
 * 
 * !! For the ecs100 library to work (and not cause compile error), BlueJ must be version 5.5.0
 * 
 * To do:
 * Erase order customer image
 *
 * @author Kanya Farley
 * @version 18/6
 */
import java.util.Random;
import ecs100.*;
import java.awt.*;
import java.awt.event.*;
public class mainECSver
{
    boolean active = true;

    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};

    double pressedX = 0;
    double pressedY = 0;
    double releasedX = 0;
    double releasedY = 0;

    OrderQueue oQueue = new OrderQueue();
    WaitingQueue wQueue = new WaitingQueue();
    String currentSprite;
    String currentRecipe;
    Customer cust1;
    Customer cust2;
    Customer cust3;
    Customer cust4;
    /* GUI */
    final int oQueueX = 252;
    final int oQueueY = 375;

    final int wQueueX = 628;
    final int wQueueY = 375;

    final int custWidth = 220;
    final int custHeight = 300;

    final int custXGap = 180;
    /**
     * Constructor for objects of class mainECSver
     */
    public void mainECSver()
    {
        /* background GUI */
        UI.setWindowSize(1098, 672);
        UI.drawImage("kitchen_DHP.jpeg", 0, 0);

        while (active) {

            /* testing */
            addOrder(oQueue);
            
            if (!oQueue.orderQueueEmpty() && wQueue.waitingQueueEmpty()) {
                wQueue.waitingEnqueue(orderTaken(oQueue));
                if (!wQueue.waitingQueueEmpty()) {
                    drawWaitingCustomer(wQueue.getFront().getSprite(), wQueue.getFront().getRecipe(), wQueue);
                }
            } else {
                UI.println("Sorry, you can't take any orders right now.");
            }
        }

    }

    public void setupGUI() {
        UI.setMouseListener(this::doMouse);
    }

    public void doMouse(String action, double x, double y) {
        switch (action) {
            case "pressed" -> {
                    this.pressedX = x;
                    this.pressedY = y;
                }
            case "released" -> {
                    this.releasedX = x;
                    this.releasedY = y;
                    
                }
        }
    }

    /* random generators */
    public Customer newRandomCustomer() {
        String recipe = randomRecipe();
        String sprite = randomCustomer();
        Customer newCustomer = new Customer(sprite, recipe); // make new customer
        UI.println(sprite + " orders a " + recipe); // debug
        drawOrderingCustomer(sprite, recipe, oQueue);
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

    public void drawOrderingCustomer(String sprite, String recipe, OrderQueue oQueue) {
        // may have to reframe to work with moving queue up??
        if (oQueue.getFront() == null) {
            UI.drawImage(sprite + "_DHP.png", oQueueX, oQueueY, custWidth, custHeight); // sprite
            UI.drawImage("speaking_DHP.png", oQueueX+140, oQueueY-50, 100, 100);
            UI.drawImage(recipe + "_DHP.png", oQueueX+165, oQueueY-35, 65, 70);
            cust1 = new Customer(sprite, recipe);
        } else if (oQueue.getSize() == 0) {
            UI.drawImage(sprite + "_DHP.png", oQueueX-custXGap, oQueueY, custWidth, custHeight);
            UI.drawImage("speaking_DHP.png", oQueueX-custXGap+140, oQueueY-50, 100, 100);
            UI.drawImage(recipe + "_DHP.png", oQueueX-custXGap+165, oQueueY-35, 65, 70);
            cust2 = new Customer(sprite, recipe);
        } else if (oQueue.getSize() == 1) {
            UI.drawImage(sprite + "_DHP.png", oQueueX-(custXGap*2), oQueueY, custWidth, custHeight);
            UI.drawImage("speaking_DHP.png", oQueueX-(custXGap*2)+140, oQueueY-50, 100, 100);
            UI.drawImage(recipe + "_DHP.png", oQueueX-(custXGap*2)+165, oQueueY-35, 65, 70);
            cust3 = new Customer(sprite, recipe);
        } else if (oQueue.getSize() == 2) {
            UI.drawImage(sprite + "_DHP.png", oQueueX-(custXGap*3), oQueueY, custWidth, custHeight);
            UI.drawImage("speaking_DHP.png", oQueueX-(custXGap*3)+140, oQueueY-50, 100, 100);
            UI.drawImage(recipe + "_DHP.png", oQueueX-(custXGap*3)+165, oQueueY-35, 65, 70);
            cust4 = new Customer(sprite, recipe);
        }
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
    public void addOrder(OrderQueue oQueue) {
        while (oQueue.getSize() < 3) {
            oQueue.orderEnqueue(newRandomCustomer());
            //UI.sleep(30000); // adds customer every 30 seconds
            UI.sleep(5000);
        }
    }

    public Customer orderTaken(OrderQueue oQueue) {
        WaitingQueue wQueue = new WaitingQueue();
        String [] next = (oQueue.orderDequeue()).split("-");
        currentSprite = next[0];
        currentRecipe = next[1];
        Customer waitingCustomer = new Customer(next[0], next[1]);
        UI.println(next[0] + " is waiting for a " + next[1]); // debugging

        /* moving queue up visually */
        UI.eraseImage(next[0] + "_DHP.png", oQueueX, oQueueY); // erases a block of the kitchen????
        UI.drawImage("kitchen_DHP.jpeg", 0, 0); // redraws the kitchen
        
        // not sure how to move customers up, may need external assistance...
        cust1 = cust2;
        drawOrderingCustomer(cust1.getSprite(), cust1.getRecipe(), oQueue);
        cust2 = cust3;
        drawOrderingCustomer(cust2.getSprite(), cust2.getRecipe(), oQueue);
        cust3 = cust4;
        drawOrderingCustomer(cust3.getSprite(), cust3.getRecipe(), oQueue);
        cust4 = null;

        return waitingCustomer;
    }

    public void drawWaitingCustomer(String sprite, String recipe, WaitingQueue wQueue) { // not working
        UI.drawImage(sprite + "_DHP.png", wQueueX, wQueueY, custWidth, custHeight); // sprite
        UI.drawImage("thinking_DHP.png", wQueueX-10, wQueueY-50, 100, 100);
        UI.drawImage(recipe + "_DHP.png", wQueueX, wQueueY-35, 65, 70);
    }

}
