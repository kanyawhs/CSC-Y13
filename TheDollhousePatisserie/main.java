
/**
 * This is the class where the actual game actions occur
 *
 * @author Kanya Farley
 * @version 11/6
 */
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class main extends JFrame implements ActionListener, MouseListener
{
    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};
    
    OrderQueue oQueue = new OrderQueue();
    WaitingQueue wQueue = new WaitingQueue();
    String currentSprite;
    String currentRecipe;
    Customer currentCustomer;
    boolean orderBeenTaken = false;
    
    JMenuBar menuBar;
    JMenu mainGame;
    JMenuItem menuItemTakeOrder;
    JMenuItem menuItemDeliverOrder;
    JMenuItem menuItemQuit;
    
    JPanel mainBG;
    String backgroundImg = "kitchen_DHP.jpeg";
    JLabel backgroundImgLabel;
    
    /* sprites */
    String girl1Img = "girl1_DHP.png";
    JLabel girl1ImgLabel;
    String girl2Img = "girl2_DHP.png";
    JLabel girl2ImgLabel;
    String boy1Img = "boy1_DHP.png";
    JLabel boy1ImgLabel;
    String boy2Img = "boy2_DHP.png";
    JLabel boy2ImgLabel;
    String mascot1Img = "mascot1_DHP.png";
    JLabel mascot1ImgLabel;
    String mascot2Img = "mascot2_DHP.png";
    JLabel mascot2ImgLabel;
    
    /* recipes */
    
    
    /**
     * Constructor for objects of class main
     */
    public void main()
    {
        /* window GUI */
        setTitle("The Dollhouse Patisserie");
        this.getContentPane().setPreferredSize(new Dimension(828, 672));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        /* menu */
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
        
        addMouseListener(this);
        
        /* background */
        mainBG = new JPanel();
        mainBG.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainBG.setBackground(Color.decode("#ffe3f5"));
        this.add(mainBG, BorderLayout.CENTER);
        
        ImageIcon background = new ImageIcon(backgroundImg);
        backgroundImgLabel = new JLabel(background);
        mainBG.add(backgroundImgLabel);
        
        /*testing*/
        orderQueueStatus(oQueue);
         // adds to queue
        oQueue.orderEnqueue(newRandomCustomer());
        oQueue.orderEnqueue(newRandomCustomer());
        
        /* window state */
        this.pack();
        this.toFront();
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Take next order":
                    if (orderBeenTaken) {
                        System.out.println("You have an active order to prepare.");
                    } else if (oQueue.orderQueueEmpty() == true) {
                        System.out.println("No orders to be taken.");
                    } else if (oQueue.orderQueueEmpty() == false) {
                        wQueue.waitingEnqueue(orderTaken(oQueue));
                        System.out.println("Order has been taken");
                        orderBeenTaken = true;
                    }
                break;
            case "Deliver order":
                    if (wQueue.waitingQueueEmpty() == true) {
                        System.out.println("No customers waiting. Can't deliver.");
                        orderBeenTaken = false;
                    } else if (wQueue.waitingQueueEmpty() == false) {
                        wQueue.waitingDequeue();
                        currentRecipe = null;
                        currentSprite = null;
                        orderBeenTaken = false;
                        System.out.println("Order delivered!");
                    }
                break;
            case "Quit":
                    System.exit(0);
                break;
        }
    }
    
    /* mouse listeners */
    public void mouseEntered(MouseEvent e) {/* */}
    
    public void mouseExited(MouseEvent e) {/* */}
    
    public void mousePressed(MouseEvent e){/* */}
    
    public void mouseReleased(MouseEvent e) {/* */}
    
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        
        // call recipe methods
    }
    
    /* random generators */
    public Customer newRandomCustomer() {
        String recipe = randomRecipe();
        String sprite = randomCustomer();
        Customer newCustomer = new Customer(sprite, recipe); // make new customer
        System.out.println(sprite + " orders a " + recipe); // debug
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
    
    /* other (core) methods */
    public void takeOrder(OrderQueue oQueue) {
        if (oQueue.getSize() < 4) {
            oQueue.orderEnqueue(newRandomCustomer());
            try {
                Thread.sleep(30000); // adds customer every 30 seconds
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }
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
    
    /* recipe methods */
    public void callRecipeMethod(String[] recipe) {
        for (int i = 0; i < recipe.length; i++) {
            switch (recipe[i]) {
                case "Parfait" :
                    break;
                case "Fruit Tart" :
                    break;
                case "Cinnamon Roll" :
                    break;
                case "Cake" :
                    break;
                case "Ube Cupcake" :
                    break;
                case "Coffee Jelly" :
                    break;
                case "Melon Float" :
                    break;
            }
        }
    }
}
