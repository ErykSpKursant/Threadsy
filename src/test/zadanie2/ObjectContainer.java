package test.zadanie2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ObjectContainer<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private transient Predicate<T> condition;
    private Element<T> head;

    public ObjectContainer(Predicate<T> condition) {
        this.condition = condition;
        this.head = null;
    }

    public boolean add(T obj) {
        if (condition.test(obj)) {
            if (head == null) {
                head = new Element<>(obj);
            } else {
                Element<T> current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = new Element<>(obj);
            }
            return true;
        } else {
            throw new IllegalArgumentException("Object does not meet the condition.");
        }
    }

    public List<T> getWithFilter(Predicate<T> filter) {
        List<T> listaObjects = new ArrayList<>();
        Element<T> current = head;
        while (current != null) {
            if (filter.test(current.data)) {
                listaObjects.add(current.data);
            }
            current = current.next;
        }
        return listaObjects;
    }

    public void removeIf(Predicate<T> filter) {
        while (head != null && filter.test(head.data)) {
            head = head.next;
        }
        Element<T> current = head;
        while (current != null && current.next != null) {
            if (filter.test(current.next.data)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    public void storeToFile(String filename, Predicate<T> filter, Function<T, String> formatter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Element<T> current = head;
            while (current != null) {
                if (filter.test(current.data)) {
                    writer.write(formatter.apply(current.data));
                    writer.newLine();
                }
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void storeToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> ObjectContainer<T> fromFile(String filename, Predicate<T> condition) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            ObjectContainer<T> container = (ObjectContainer<T>) ois.readObject();
            container.condition = condition;
            return container;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while deserializing from file: " + filename);
            e.printStackTrace();
            return null;
        }
    }
}

