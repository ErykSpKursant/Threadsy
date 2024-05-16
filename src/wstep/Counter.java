package wstep;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger i = new AtomicInteger(0);

    public void increment(){
        i.addAndGet(1);
    }

    public AtomicInteger getI() {
        return i;
    }

    //    private int i = 0;

//    private Lock lock = new ReentrantLock();
//    public void increment (){
//        i++;
//    }

//    public synchronized void increment (){
//        i++;
//    }

//    public void increment (){
//        synchronized (this){
//            i++;
//        }
//    }

//    public void increment() {
//        try{
//            lock.lock();
//            i++;
//        } finally {
//            lock.unlock();
//        }
//    }

//    public int getI() {
//        return i;
//    }

}
