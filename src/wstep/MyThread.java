package wstep;

public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("jestem w watku dziedziczacym po Thread");
    }
}
