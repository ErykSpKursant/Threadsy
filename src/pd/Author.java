package pd;

import java.time.Duration;
import java.util.LinkedList;
import java.util.Queue;

public class Author {
    private static final Queue<String> queue = new LinkedList<>();
    private static int counter;

    public static void main(String[] args) {
        //napisz program author - writer. Author co sekunde generuje napisy ktore otrzymuje jako parametr konstruktora (np talblica stringow)
        //i dodaje je do kolejki
        //writer ma je przyjac, wyjac z kolejki i wypisac na konsoli
        // analogicznie jak robilismy producer consumer

        String[] messages = {"Hello ", "Just fun ", "My world"};


        Thread author = new Thread(() -> {
            for (String m : messages) {
                try {
                    Thread.sleep(Duration.ofSeconds(1).toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (queue) {
                    queue.add("Author nr: " + m);
                    queue.notify();
                }
            }
        });

        Thread writer = new Thread(() -> {
            int messageLeft = messages.length;

            while (messageLeft > 0) {
                counter++;
                String message;
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    message = queue.poll();
                }
                messageLeft--;
                System.out.println("Writer received message: " + message);
                System.out.println("Messages processed: " + counter);
            }
        });
        author.start();
        writer.start();

    }
}
