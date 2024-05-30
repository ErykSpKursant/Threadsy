package towary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;

public class ProductReader implements Runnable {
    private final String filePath;
    private final Queue<Product> queue;
    private final Object lock;
    private final Product end;

    public ProductReader(String filePath, Queue<Product> queue, Object look, Product end_marker) {
        this.filePath = filePath;
        this.queue = queue;
        this.lock = look;
        end = end_marker;
    }

    public String getFilePath() {
        return filePath;
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
        try (
                BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[0]);
                int weight = Integer.parseInt(parts[1]);
                Product product = new Product(id, weight);

                synchronized (lock) {
                    queue.add(product);
                    count++;
                    if (count % 200 == 0) {
                        System.out.println("created: " + count + " objections");
                    }
                    lock.notify();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            synchronized (lock) {
                queue.add(end);
                lock.notify();
            }
        }
    }
}
