package deadlock;

import java.util.Random;

public class Transfer implements Runnable {

    private Account sender;
    private Account receiver;

    public Transfer(Account sender, Account receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }


    @Override
    public void run() {
        Random random = new Random();

        while (true) {
            int i = random.nextInt(1000);
            System.out.println("transferujemy: " + i);
            transfer(sender, receiver, i);
        }
    }

    //Problemem przy korzystaniu z wielu blokad jest kolejność ich zakładania - ta musi być określona i jednakowa - do określienia
    //kolejności można użyć kodu hash code, albo innego identyfikatora obiektu który jest unikalny. Czyli najpierw wykonujemy porówanie
    //hash code obiektów i ten który ma mniejszy hascode ma zakładaną blokadę szybciej, w przypadku kolizji można użyć dodatkowej blokady.
    private void transfer(Account sender, Account receiver, int amount) {
        int compareTo = sender.getNumber().compareTo(receiver.getNumber());

        if (compareTo > 0) {
            synchronized (sender) {
                synchronized (receiver) {
                    receiver.increaseBalance(amount);
                    sender.increaseBalance(-amount);
                }
            }
        } else {
            synchronized (receiver) {
                synchronized (sender) {
                    receiver.increaseBalance(amount);
                    sender.increaseBalance(-amount);
                }

            }
        }
    }
}
