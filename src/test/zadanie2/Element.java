package test.zadanie2;

import java.io.Serial;
import java.io.Serializable;

public class Element<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    T data;
    Element<T> next;

    public Element(T data) {
        this.data = data;
        this.next = null;
    }
}
