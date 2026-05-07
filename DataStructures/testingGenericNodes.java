
/**
 * Write a description of class testingGenericNodes here.
 *
 * @author Kanya Farley
 * @version 8/5
 */
public class testingGenericNodes
{
    Queue myQueue = new Queue();

    /**
     * Constructor for objects of class testingGenericNodes
     */
    public static void main(String args[])
    {
        for (int i = 0; i < 5; i++) {
            GenericNode tempIntNode = new GenericNode(i);
            myQueue.enqueue(tempIntNode);
        }
        
        GenericNode tempBoolNode = new GenericNode(false);
        myQueue.enqueue(tempBoolNode);
        
        while(!myQueue.queueEmpty()) {
            System.out.println(myQueue.dequeue().getData());
        }
    }
}
