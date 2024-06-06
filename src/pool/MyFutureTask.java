package pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyFutureTask extends FutureTask<Void> {
    private final Callable<Void> callable;

    public MyFutureTask(Callable<Void> callable) {
        super(callable);
        this.callable = callable;
    }

    @Override
    protected void done() {
        System.out.println(callable + " zakonczony");
        try {
            System.out.println(callable + " rezultat " + get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
