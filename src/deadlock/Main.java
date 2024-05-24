package deadlock;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {

        //Klasa Account ma number i balance
        //Klasa Tranfser przyjmuje dwa konta i ma metode transfer ktora przeklada hajs z jednego konta na drugie

        Account eryk = new Account("812973891", 500);
        Account szymon = new Account("8976318723", 500);

        Transfer transfer1 = new Transfer(eryk, szymon);
        Transfer transfer2 = new Transfer(szymon, eryk);

        Thread t1 = new Thread(transfer1);
        Thread t2 = new Thread(transfer2);

        t1.start();
        t2.start();

        // Deadlock jest sytuacja: wiele wątków są blokowane, jeden lub wszystkie z nich
        // czeka na zasób ma zostać wydany.
        // Ponieważ wątek jest zablokowany w nieskończoność, aby program nie może być
        // zakończony normalnie.


    }
}
