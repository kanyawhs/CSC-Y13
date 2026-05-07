
/**
 * Write a description of class testingGenericNodes here.
 *
 * @author Kanya Farley
 * @version 7/5
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
            myQueue.enqueue(tempIntNode); // doesn't work..
        }
    }
}
