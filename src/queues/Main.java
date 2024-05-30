package queues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {


        Queue<String> queue = new LinkedList<>();

        //add - Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available.
        // offer - Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions, returning true upon success and false if no space is currently available.
        // put - Inserts the specified element into this queue, waiting if necessary for space to become available.
        //peek - Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
        // element - rozni sie od peek tylko tym, ze jesli pusta zwroci NoSuchElementException
        //poll - Retrieves and removes the head of this queue, or returns null if this queue is empty.
        // move - rozni sie od poll, ze jesli pusta NoSuchElementException

        //add - Wstawia określony element do tej kolejki, jeśli można to zrobić natychmiast bez naruszania ograniczeń pojemności, zwracając wartość true po powodzeniu i rzucając wyjątek IllegalStateException, jeśli aktualnie nie ma dostępnego miejsca.
        // offer - Wstawia określony element do tej kolejki, jeśli można to zrobić natychmiast bez naruszania ograniczeń pojemności, zwracając wartość true po powodzeniu i false, jeśli aktualnie nie ma dostępnego miejsca.
        // put - Wstawia określony element do tej kolejki, czekając w razie potrzeby na wolne miejsce.
        //peek - Pobiera, ale nie usuwa, nagłówek tej kolejki lub zwraca wartość null, jeśli kolejka jest pusta.
        // element - rozni sie od peek tylko tym, ze jesli pusta zwroci NoSuchElementException
        //poll - Pobiera i usuwa nagłówek tej kolejki lub zwraca wartość null, jeśli kolejka jest pusta.
        // move - rozni sie od poll, ze jesli pusta NoSuchElementException




        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);


        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                blockingQueue.add("cos");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        System.out.println(blockingQueue.poll());

        try {
            System.out.println(blockingQueue.poll(1100, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Arrays.asList(4,1,5,2,8,3, -5));
        System.out.println(priorityQueue);

        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.poll());
        }
    }
}
