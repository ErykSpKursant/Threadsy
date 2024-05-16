package producer.consumer;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
    private static final Random generator = new Random();
    private static final Queue<String> queue = new LinkedList<>();
    private static int counter;

    public static void main(String[] args) {

        int productrCount = 5;

        Thread producer = new Thread(() -> {
            for(int i = 0; i < productrCount; i++){
                try {
                    Thread.sleep(Duration.ofSeconds(generator.nextInt(6)).toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (queue){
                    queue.add("Product nr: " + (i + 1));
                    queue.notify();
                }
            }
        });

        // wait()
        // notify()
        // notifyAll()
        Thread consumer = new Thread(() -> {
            int productLeft = productrCount;

            while (productLeft > 0){
                counter++;
                String product;
                synchronized (queue){
                   while(queue.isEmpty()){
                       try {
                           queue.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                    product = queue.poll();
                }
                productLeft--;
                System.out.println("Consumer got prodfuct: " + product);
                System.out.println(counter);
            }
        });

        producer.start();
        consumer.start();
    }
}
