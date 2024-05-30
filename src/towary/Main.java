package towary;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        // https://sdkp.pjwstk.edu.pl/html/staskshtml/S_TOWARY/S_TOWARY.html
        String filePath = "C:/Users/Asus/IdeaProjects/Threadsy/src/towary/products";
        Queue<Product> queue = new LinkedList<>();
        Object lock = new Object();
        Product end = new Product(-1, -1);

        Thread readerThread = new Thread(new ProductReader(filePath, queue, lock, end));
        Thread processorThread = new Thread(new ProductProcessor(queue, lock, end));

        readerThread.start();
        processorThread.start();

        try {
            readerThread.join();
            processorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Program completed");

    }
}
