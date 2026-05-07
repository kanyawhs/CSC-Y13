
/**
 * working :D
 *
 * @author Kanya Farley
 * @version 7/5
 */
import java.lang.Math;
public class testingPriorityQueues
{
    public static void main (String args[])
    {
        System.out.println("hi");
        PriorityQueue vehicles = new PriorityQueue();
        for (int i = 0; i < 50; i++) {
            if (Math.random() < 0.05) {
                Node bus = new Node();
                bus.setData("bus");
                vehicles.enqueue(bus, true);
            } else {
                Node car = new Node();
                car.setData("car");
                vehicles.enqueue(car, false);
            }
        }
        System.out.println("Lights are green!");

        while (!vehicles.queueEmpty()) {
            System.out.println(vehicles.dequeue());
        }
    }
}
