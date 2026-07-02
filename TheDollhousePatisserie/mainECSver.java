
/**
 * This is the class where the actual game actions occur
 * 
 * !! For the ecs100 library to work (and not cause compile error), BlueJ must be version 5.5.0
 * 
 * Notes:
 * Cannot move to next step/method for recipes...
 *
 * @author Kanya Farley
 * @version 2/7
 */
import java.util.Random;
import ecs100.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class mainECSver
{
    boolean active = true;

    String[] recipe = {"Parfait", "Fruit Tart", "Cinnamon Roll", "Cake", "Ube Cupcake", "Coffee Jelly", "Melon Float"};
    String[] sprite = {"girl1", "boy1", "girl2", "boy2", "mascot1", "mascot2"};
    ArrayList<String> step = new ArrayList<String>();
    String actual;

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
    boolean orderComplete = true;

    /* GUI */
    final int oQueueX = 252;
    final int oQueueY = 375;

    final int wQueueX = 628;
    final int wQueueY = 375;

    final int custWidth = 220;
    final int custHeight = 300;

    final int custXGap = 180;
    /**
     * Constructor for objects of class CopyOfmainECSver
     */
    public void CopyOfmainECSver()
    {
        /* background GUI */
        UI.setWindowSize(1098, 672);
        UI.drawImage("kitchen_DHP.jpeg", 0, 0);
        UI.setMouseListener(this::doMouse);

        while (active) {
            addOrder(oQueue);
        }

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
                    if (this.active && releasedX >=oQueueX+140 && releasedX <= oQueueX+240 && releasedY >= oQueueY-50 && releasedY <= oQueueY+50 && !oQueue.orderQueueEmpty() && wQueue.waitingQueueEmpty()) {
                        wQueue.waitingEnqueue(orderTaken(oQueue));
                        if (!wQueue.waitingQueueEmpty()) {
                            drawWaitingCustomer(wQueue.getFront().getSprite(), wQueue.getFront().getRecipe(), wQueue);

                        }
                    } else if (releasedX >=oQueueX+140 && releasedX <= oQueueX+240 && releasedY >= oQueueY && releasedY <= oQueueY+100 && orderComplete == false) {
                        UI.println("Sorry, you can't take an order right now.");
                    }

                    if (this.active && releasedX >= wQueueX-10 && releasedX <= wQueueX+90 && releasedY >= wQueueY-50 && releasedY <= wQueueY+50 && orderComplete == true) {
                        serveWaitingCustomer();
                    }

                    /* refridgerate */
                    if (!wQueue.waitingQueueEmpty() && releasedX >= 70 && releasedX <= 220 && releasedY >= 20 && releasedY <= 240) {
                        actual = "refridgerate";
                        if (actual == step.get(0)) {
                            refridgerate(wQueue.getFront().getRecipe());
                        } else {
                            UI.println("Wrong step!");
                            UI.println("Required step: " + step);
                        }
                    }
                    /* chop */
                    if (!wQueue.waitingQueueEmpty() && releasedX >= 268 && releasedX <= 318 && releasedY >= 90 && releasedY <= 190) {
                        actual = "chop";
                        if (actual == step.get(0)) {
                            chop(wQueue.getFront().getRecipe());
                        } else {
                            UI.println("Wrong step!");
                            UI.println("Required step: " + step);
                        }
                    }
                    /* mix */
                    if (!wQueue.waitingQueueEmpty() && releasedX >= 392 && releasedX <= 482 && releasedY >= 68 && releasedY <= 143) {
                        actual = "mix";
                        if (actual == step.get(0)) {
                            mix(wQueue.getFront().getRecipe());
                        } else {
                            UI.println("Wrong step!");
                            UI.println("Required step: " + step);
                        }
                    }
                    /* oven */
                    if (!wQueue.waitingQueueEmpty() && releasedX >= 516 && releasedX <=684 && releasedY >= 85 && releasedY <= 250) {
                        actual = "oven";
                        if (actual == step.get(0)) {
                            oven(wQueue.getFront().getRecipe());
                        } else {
                            UI.println("Wrong step!");
                            UI.println("Required step: " + step);
                        }
                    }
                    /* decorate */
                    if (!wQueue.waitingQueueEmpty() && releasedX >= 700 && releasedX <= 770 && releasedY >= 94 && releasedY <= 159) {
                        actual = "decorate";
                        if (actual == step.get(0)) {
                            decorate(wQueue.getFront().getRecipe());
                        } else {
                            UI.println("Wrong step!");
                            UI.println("Required step: " + step);
                        }
                    }
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
            UI.drawImage("speaking_DHP.png", oQueueX+140-custXGap, oQueueY-50, 100, 100);
            UI.drawImage(recipe + "_DHP.png", oQueueX+165-custXGap, oQueueY-35, 65, 70);
            UI.drawImage("speakingoverlay_DHP.png", oQueueX+140-custXGap, oQueueY-50, 100, 100);
            cust2 = new Customer(sprite, recipe);
        } else if (oQueue.getSize() == 1) {
            UI.drawImage(sprite + "_DHP.png", oQueueX-(custXGap*2), oQueueY, custWidth, custHeight);
            UI.drawImage("speaking_DHP.png", oQueueX+140-(custXGap*2), oQueueY-50, 100, 100);
            UI.drawImage(recipe + "_DHP.png", oQueueX+165-(custXGap*2), oQueueY-35, 65, 70);
            UI.drawImage("speakingoverlay_DHP.png", oQueueX+140-(custXGap*2), oQueueY-50, 100, 100);
            cust3 = new Customer(sprite, recipe);
        } else if (oQueue.getSize() == 2) {
            UI.drawImage(sprite + "_DHP.png", oQueueX-(custXGap*3), oQueueY, custWidth, custHeight);
            UI.drawImage("speaking_DHP.png", oQueueX+140-(custXGap*3), oQueueY-50, 100, 100);
            UI.drawImage(recipe + "_DHP.png", oQueueX+165-(custXGap*3), oQueueY-35, 65, 70);
            UI.drawImage("speakingoverlay_DHP.png", oQueueX+140-(custXGap*3), oQueueY-50, 100, 100);
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
        UI.eraseImage(next[0] + "_DHP.png", oQueueX, oQueueY); // erase head of queue
        UI.drawImage("kitchen_DHP.jpeg", 0, 0); // redraws the kitchen (where blank space would be)
        cust1 = cust2;
        cust2 = cust3;
        cust3 = cust4;
        cust4 = null;
        if (cust1 != null) {
            UI.drawImage(cust1.getSprite() + "_DHP.png", oQueueX, oQueueY, custWidth, custHeight); // sprite
            UI.drawImage("speaking_DHP.png", oQueueX+140, oQueueY-50, 100, 100);
            UI.drawImage(cust1.getRecipe() + "_DHP.png", oQueueX+165, oQueueY-35, 65, 70);
        }
        if (cust2 != null) {
            UI.drawImage(cust2.getSprite() + "_DHP.png", oQueueX-custXGap, oQueueY, custWidth, custHeight);
            UI.drawImage("speaking_DHP.png", oQueueX+140-custXGap, oQueueY-50, 100, 100);
            UI.drawImage(cust2.getRecipe() + "_DHP.png", oQueueX+165-custXGap, oQueueY-35, 65, 70);
            UI.drawImage("speakingoverlay_DHP.png", oQueueX+140-custXGap, oQueueY-50, 100, 100);
        }
        if (cust3 != null) {
            UI.drawImage(cust3.getSprite() + "_DHP.png", oQueueX-(custXGap*2), oQueueY, custWidth, custHeight);
            UI.drawImage("speaking_DHP.png", oQueueX+140-(custXGap*2), oQueueY-50, 100, 100);
            UI.drawImage(cust2.getRecipe() + "_DHP.png", oQueueX+165-(custXGap*2), oQueueY-35, 65, 70);
            UI.drawImage("speakingoverlay_DHP.png", oQueueX+140-(custXGap*2), oQueueY-50, 100, 100);
        }

        orderComplete = false;
        recipeStart(currentRecipe, wQueue);
        return waitingCustomer;
    }

    public void drawWaitingCustomer(String sprite, String recipe, WaitingQueue wQueue) {
        UI.drawImage(sprite + "_DHP.png", wQueueX, wQueueY, custWidth, custHeight); // sprite
        UI.drawImage("thinking_DHP.png", wQueueX-10, wQueueY-50, 100, 100);
        UI.drawImage(recipe + "_DHP.png", wQueueX, wQueueY-35, 65, 70); // PLACED POORLY!
    }

    public void serveWaitingCustomer() {
        if (orderComplete) {
            wQueue.waitingDequeue();
            UI.eraseImage(sprite + "_DHP.png", wQueueX, wQueueY); // sprite
            UI.eraseImage("thinking_DHP.png", wQueueX-10, wQueueY-50);
            UI.drawImage("kitchen_DHP.jpeg", 0, 0);
            
            /* redraw order queue? */
            if (cust1 != null) {
                UI.drawImage(cust1.getSprite() + "_DHP.png", oQueueX, oQueueY, custWidth, custHeight); // sprite
                UI.drawImage("speaking_DHP.png", oQueueX+140, oQueueY-50, 100, 100);
                UI.drawImage(cust1.getRecipe() + "_DHP.png", oQueueX+165, oQueueY-35, 65, 70);
            }
            if (cust2 != null) {
                UI.drawImage(cust2.getSprite() + "_DHP.png", oQueueX-custXGap, oQueueY, custWidth, custHeight);
            }
            if (cust3 != null) {
                UI.drawImage(cust3.getSprite() + "_DHP.png", oQueueX-(custXGap*2), oQueueY, custWidth, custHeight);
            }
        }
    }

    public void recipeStart(String recipe, WaitingQueue wQueue) {
        // definitely not refined yet
        switch (recipe) {
            case "Parfait" :
                step.add("refridgerate");
                step.add("chop");
                step.add("decorate");
                break;
            case "Fruit Tart" :
                step.add("mix");
                step.add("oven");
                step.add("chop");
                step.add("decorate");
                step.add("refridgerate");
                break;
            case "Cinnamon Roll":
                step.add("mix");
                step.add("chop");
                step.add("oven");
                break;
            case "Cake":
                step.add("mix");
                step.add("oven");
                step.add("decorate");
                break;
            case "Ube Cupcake":
                step.add("mix");
                step.add("oven");
                step.add("decorate");
                break;
            case "Coffee Jelly":
                step.add("mix");
                step.add("refridgerate");
                step.add("decorate");
                break;
            case "Melon Float":
                step.add("mix");
                step.add("refridgerate");
                step.add("decorate");
                break;
        }
    }
    
    public void stepCheck() {
        if (step.size() != 0) {
            step.remove(0);
        } else if (step.size() == 0) {
            orderComplete = true;
            UI.println("Order complete!");
        }
    }

    public void chop (String recipe) {
        /*placeholder*/
        UI.println("Chopping...");
        UI.sleep(5000);
        UI.println("Done!");
        stepCheck();
    }

    public void mix (String recipe) {
        /*placeholder*/
        UI.println("Mixing...");
        UI.sleep(5000);
        UI.println("Done!");
        stepCheck();
    }

    public void oven (String recipe) {
        /*placeholder*/
        UI.println("Baking...");
        UI.sleep(5000);
        UI.println("Done!");
        stepCheck();
    }

    public void refridgerate (String recipe) {
        /*placeholder*/
        UI.println("Refridgerating...");
        UI.sleep(5000);
        UI.println("Done!");
        stepCheck();
    }

    public void decorate (String recipe) {
        /*placeholder*/
        UI.println("Decorating...");
        UI.sleep(5000);
        UI.println("Done!");
        stepCheck();
    }

}
