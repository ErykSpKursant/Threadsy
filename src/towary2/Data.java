package towary2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Data {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("items.txt"));
        writer.write("item_id weight");
        writer.newLine();
        Random rand = new Random();
        for (int i = 1; i <= 10000; i++) {
            int weight = rand.nextInt(100) + 1; // Weight in range 1-100
            writer.write(i + " " + weight);
            writer.newLine();
        }
        writer.close();
        System.out.println("Data generated.");
    }
}