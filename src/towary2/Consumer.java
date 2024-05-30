package towary2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Item> queue;

    public Consumer(BlockingQueue<Item> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;
        int totalWeight = 0;
        try {
            while (true) {
                Item item = queue.take();
                if (item.getWeight() == 0 && item.getId() == -1) {
                    break; // End signal
                }
                totalWeight += item.getWeight();
                counter++;
                if (counter % 100 == 0) {
                    System.out.println("Calculated weight of " + counter + " items");
                }
            }
            System.out.println("Total weight of all items: " + totalWeight);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}