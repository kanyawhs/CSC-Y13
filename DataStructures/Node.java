
/**
 * Linked list with nodes
 *
 * @author Kanya Farley
 * @version 24/04
 */
public class Node
{
    private String data;
    private Node next;
    
    private Node head;
    private Node tail;
    
    /** Constructor for nodes with no data*/
    public Node() {
        this.data = "";
    }
    
    /** Constructor for nodes with data*/
    public Node(String data) {
        this.data = data;
    }
    
    /* setters */
    public void setData(String data) {
        this.data = data;
    }
    
    public void setNextNode(Node n) {
        this.next = n;
    }
    
    /* getters */
    public String getData() {
        return(this.data);
    }
    
    public Node getNextNode() {
        return(this.next);
    }
    
    /* for queues */
    public void createHead(String data) {
        this.head = new Node();
    }
    
    public boolean queueEmpty() {
        if (this.head == null) {
            return(true);
        } else {
            return(false);
        }
    }
}
