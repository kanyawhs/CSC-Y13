
/**
 * Node class for ordering and waiting queues
 *
 * @author Kanya Farley
 * @version 15/5
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
    
    /* setters */
    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
    
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
    
    public void setNextCustomer(Customer next) {
        this.next = next;
    }
    
    /* getters */
    public String getSprite() {
        return(this.sprite);
    }
    
    public String getRecipe() {
        return(this.recipe);
    }
    
    public Customer getNextCustomer() {
        return(this.next);
    }
}
