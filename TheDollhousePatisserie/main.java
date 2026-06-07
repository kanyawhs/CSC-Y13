
/**
 * This is the class where the actual game actions occur
 *
 * @author Kanya Farley
 * @version 8/6
 */
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class main extends JFrame implements ActionListener
{
    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};
    
    OrderQueue oQueue = new OrderQueue();
    WaitingQueue wQueue = new WaitingQueue();
    String currentSprite;
    String currentRecipe;
    Customer currentCustomer;
    
    JMenuBar menuBar;
    JMenu mainGame;
    JMenuItem menuItemTakeOrder;
    JMenuItem menuItemDeliverOrder;
    JMenuItem menuItemQuit;
    /**
     * Constructor for objects of class main
     */
    public void main()
    {
        /* window GUI */
        setTitle("The Dollhouse Patisserie");
        this.getContentPane().setPreferredSize(new Dimension(828, 672));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        mainGame = new JMenu("Operation");
        menuBar.add(mainGame);
        
        menuItemTakeOrder = new JMenuItem("Take next order");
        menuItemTakeOrder.addActionListener(this);
        menuItemDeliverOrder = new JMenuItem("Deliver order");
        menuItemDeliverOrder.addActionListener(this);
        menuItemQuit = new JMenuItem("Quit");
        menuItemQuit.addActionListener(this);
        mainGame.add(menuItemTakeOrder);
        mainGame.add(menuItemDeliverOrder);
        mainGame.add(menuItemQuit);
        
        // content
        
        /*testing*/
        orderQueueStatus(oQueue);
        oQueue.orderEnqueue(newRandomCustomer()); // adds to queue
        
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Take next order":
                    wQueue.waitingEnqueue(orderTaken(oQueue));
                    System.out.println("Order has been taken");
                break;
            case "Deliver order":
                    if (wQueue.waitingQueueEmpty() == true) {
                        System.out.println("No customers waiting. Can't deliver.");
                    } else if (wQueue.waitingQueueEmpty() == false) {
                        wQueue.waitingDequeue();
                    }
                break;
            case "Quit":
                    System.exit(0);
                break;
        }
    }
    
    public Customer newRandomCustomer() {
        String recipe = randomRecipe();
        String sprite = randomCustomer();
        
        Customer newCustomer = new Customer(sprite, recipe); // make new customer
        System.out.println(sprite + " orders a " + recipe); // debug
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
        currentSprite = next[0];
        currentRecipe = next[1];
        Customer waitingCustomer = new Customer(next[0], next[1]);
        System.out.println(next[0] + " is waiting for a " + next[1]); // debugging
        return waitingCustomer;
    }
}
