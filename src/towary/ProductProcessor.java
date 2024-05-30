package towary;

import java.util.Queue;

public class ProductProcessor implements Runnable {
    private final Queue<Product> queue;
    private final Object lock;
    private final Product end;

    public ProductProcessor(Queue<Product> queue, Object lock, Product end_marker) {
        this.queue = queue;
        this.lock = lock;
        end = end_marker;
    }

    public Queue<Product> getQueue() {
        return queue;
    }

    public Object getLock() {
        return lock;
    }

    @Override
    public void run() {
        int count = 0;
        int totalWeight = 0;
        while (true) {
            Product product = null;
            synchronized (lock) {
                while (queue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                product = queue.poll();
            }
            if (product == end) {
                break;
            }
            if (product != null) {
                totalWeight += product.getWeight();
                count++;
                if (count % 100 == 0) {
                    System.out.println("weight counted: " + count + " products");
                }
            }
            if (product == null && queue.isEmpty()) {
                break;
            }
        }
        System.out.println("Weight of all goods: " + totalWeight);
    }

}
