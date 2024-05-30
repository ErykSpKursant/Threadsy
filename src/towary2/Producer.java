package towary2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Item> queue;

    public Producer(BlockingQueue<Item> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            String line = reader.readLine(); // Ignore the first line with the description
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int id = Integer.parseInt(parts[0]);
                int weight = Integer.parseInt(parts[1]);
                Item item = new Item(id, weight);
                queue.put(item);
                counter++;
                if (counter % 200 == 0) {
                    System.out.println("Created " + counter + " items");
                }
            }
            queue.put(new Item(-1, 0)); // End signal
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}