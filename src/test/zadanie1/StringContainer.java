package test.zadanie1;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringContainer {
    private Pattern pattern;
    private boolean duplicatesNotAllowed;
    private Element head;

    public StringContainer(String regex) {
        this(regex, false);
    }

    public StringContainer(String regex, boolean duplicatesNotAllowed) {
        try {
            this.pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            throw new InvalidStringContainerPatternException("Invalid pattern: " + regex);
        }
        this.duplicatesNotAllowed = duplicatesNotAllowed;
        this.head = null;
    }

    public void add(String value) {
        if (!pattern.matcher(value).matches()) {
            throw new InvalidStringContainerValueException("Invalid value: " + value);
        }
        if (!duplicatesNotAllowed && contains(value)) {
            throw new DuplicatedElementOnListException("Duplicate value: " + value);
        }
        Element newElement = new Element(value, LocalDateTime.now());
        if (head == null) {
            head = newElement;
        } else {
            Element current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newElement;
        }
    }

    public void remove(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (head == null) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (index == 0) {
            head = head.next;
            return;
        }
        Element current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current.next == null) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            current = current.next;
        }
        if (current.next == null) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        current.next = current.next.next;
    }

    public void remove(String value) {
        if (head == null) {
            throw new InvalidStringContainerValueException("Value not found: " + value);
        }
        if (head.value.equals(value)) {
            head = head.next;
            return;
        }
        Element current = head;
        while (current.next != null && !current.next.value.equals(value)) {
            current = current.next;
        }
        if (current.next == null) {
            throw new InvalidStringContainerValueException("Value not found: " + value);
        }
        current.next = current.next.next;
    }

    public int size() {
        int size = 0;
        Element current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public String get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        Element current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            current = current.next;
        }
        if (current == null) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return current.value;
    }

    private boolean contains(String value) {
        Element current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public StringContainer getDataBetween(LocalDateTime dateFrom, LocalDateTime dateTo) {
        StringContainer result = new StringContainer(pattern.pattern(), duplicatesNotAllowed);
        Element current = head;
        while (current != null) {
            if ((dateFrom == null || !current.timestap.isBefore(dateFrom)) &&
                    (dateTo == null || !current.timestap.isAfter(dateTo))) {
                result.add(current.value);
            }
            current = current.next;
        }
        return result;
    }

    public void storeToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(pattern.pattern() + "|" + duplicatesNotAllowed);
            writer.newLine();
            Element current = head;
            while (current != null) {
                writer.write(current.value + "|" + current.timestap.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                writer.newLine();
                current = current.next;
            }
        }
    }

    public static StringContainer fromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String regex = reader.readLine().split("\\|")[0];
            StringContainer container = new StringContainer(regex);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid file format");
                }
                Element newElement = new Element(parts[0], LocalDateTime.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                if (container.head == null) {
                    container.head = newElement;
                } else {
                    Element current = container.head;
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = newElement;
                }
            }
            return container;
        }
//        public static StringContainer fromFile(String filename) throws IOException {
//            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//                String[] config = reader.readLine().split("\\|");
//                if (config.length != 2) {
//                    throw new IllegalArgumentException("Invalid file format");
//                }
//                String regex = config[0];
//                boolean duplicatesNotAllowed = Boolean.parseBoolean(config[1]);
//                StringContainer container = new StringContainer(regex, duplicatesNotAllowed);
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    String[] parts = line.split("\\|");
//                    if (parts.length != 2) {
//                        throw new IllegalArgumentException("Invalid file format");
//                    }
//                    Element newElement = new Element(parts[0], LocalDateTime.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//                    if (container.head == null) {
//                        container.head = newElement;
//                    } else {
//                        Element current = container.head;
//                        while (current.next != null) {
//                            current = current.next;
//                        }
//                        current.next = newElement;
//                    }
//                }
//                return container;
    }

    public static boolean compareStringContainers(StringContainer sc1, StringContainer sc2) {
        if (sc1.size() != sc2.size()) {
            return false;
        }
        for (int i = 0; i < sc1.size(); i++) {
            if (!sc1.get(i).equals(sc2.get(i))) {
                return false;
            }
        }
        return true;
    }
}