
/**
 * Node that can contain data of any element type!
 *
 * @author Kanya Farley
 * @version 7/5
 */
public class GenericNode<E>
{
    // instance variables - replace the example below with your own
    private E data;
    private GenericNode nextNode;

    public GenericNode(E data)
    {
        this.data = data;
        this.nextNode = null;
    }
    
    /* getters */
    public E getData() {
        return this.data;
    }
    
    public GenericNode getNextNode() {
        return this.nextNode;
    }
    
    /* setters */
    public void setData(E data) {
        this.data = data;
    }
    
    public void setNextGenericNode(GenericNode n) {
        this.nextNode = n;
    }
}
