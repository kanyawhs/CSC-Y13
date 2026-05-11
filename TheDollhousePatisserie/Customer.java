
/**
 * Node class for ordering and waiting queues
 *
 * @author Kanya Farley
 * @version 11/5
 */
public class Customer
{
    private String sprite;
    private String recipe;
    private Customer next;
    /**
     * Constructor for objects of class Customer
     */
    public Customer(String sprite, String recipe)
    {
        this.sprite = sprite;
        this.recipe = recipe;
    }
    
}
