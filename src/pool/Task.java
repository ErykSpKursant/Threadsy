package pool;

import java.util.concurrent.Callable;

public class Task implements Callable<Void> {

    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Void call() throws Exception {
        for (int i = 0; i < 2; i++) {
            System.out.println(name + " jeszcze nie zakonczony");
            Thread.sleep(2000);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
