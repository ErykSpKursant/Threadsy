package towary;

import java.io.*;
import java.util.Random;

public class GeneratorProductsFile {
    public static void main(String[] args) {
        String filePath = "C:/Users/Asus/IdeaProjects/Threadsy/src/towary/products";
        int numberOfPRoducts = 10001;
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 1; i < numberOfPRoducts; i++) {
                int weight = random.nextInt(100) + 1;
                writer.write(i + " " + weight);
                writer.newLine();
            }
            System.out.println(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
