package test.zadanie1;

import java.time.LocalDateTime;

public class Element {
    public String value;
    public LocalDateTime timestap;
    public Element next;

    public Element(String value, LocalDateTime timestap) {
        this.value = value;
        this.timestap = timestap;
        this.next = null;
    }

}

