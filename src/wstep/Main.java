package wstep;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // A - 1h, B - 1H, C-30min
        // A - 30min, B-30min, c-30, a-30min, b-30min
        /*
        A-1h
        B-1H
        c-30min
         */

//        Thread t1 = new MyThread();
//        t1.start();
//
//        Thread t2 = new Thread(new MyRunnable());
//        t2.start();
//
//        Thread t3 = new Thread(() -> {
//            System.out.println("jestem w watku stworzonym za pomoca lambdy");
//        });
//
//        t3.start();
//
//        Runnable runnable = () -> {
//            System.out.println("jestem w runnablu");
//        };
//
//        Thread t4 = new Thread(runnable);
//        t4.start();
//
//        new Thread(runnable).start();

//        Thread thread= new Thread(() -> {
//            System.out.println("thread start");
//
//            for(int i = 0; i < 5; i++){
//                System.out.println("thread: " + i);
//            }
//
//            System.out.println("thread stop");
//        });
//
//        thread.start();
//
//        System.out.println("Main start");
//        for(int i = 0; i< 5; i++){
//            System.out.println("Main: " + i);
//        }
//
//        System.out.println("Main stop");

/*
Main start
thread start
Main: 0
Main: 1
thread: 0
Main: 2
thread: 1
Main: 3
thread: 2
Main: 4
thread: 3
Main stop
thread: 4
thread stop
 */

        // Stwórz klase Licznik ktora ma pole typu int o wartosci 0, getter oraz metode
        // zwiekszajaca pole o 1

        Counter counter = new Counter();

        Runnable runnable = () -> {
            for(int i = 0; i < 100_000; i++){
                counter.increment();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
//metoda join zapewnia ze aktualny watek czeka na zakonczenie sie watku an ktorym join zostalo wywalone
        //main czeka na zakonczenie sie watku t1, jak ten sie skoncyz to czeka na zakonczenie t2 a potem t3
        //watki moga sie skonczyc w kolejnosci dowolnej
        System.out.println(counter.getI());
        System.out.println(counter.getI());
        System.out.println(counter.getI());
        /*
		 * To co uda³o Ci siê zaobserwowaæ wy¿ej to tak zwany wyœcig (ang. race condition). Taka sytuacja zachodzi jeœli kilka w¹tków jednoczeœnie
		 *  modyfikuje zmienn¹, która do takiej równoleg³ej zmiany nie jest przystosowana. Tylko dlaczego wartoœæ atrybutu value
		 *  mia³a tak ró¿ne wartoœci? Dzia³o siê tak dlatego, ¿e operacja value += 1 nie jest operacj¹ atomow¹.
		 *  Operacje atomowe to operacje niepodzielne, a i+=1 pobiera wartosc, dodaje 1, przypisuje
		 *
		 *  Aby unikn¹æ takich sytuacji, unikn¹æ wyœcigów, niezbêdna jest synchronizacja pracy w¹tków.
		 *
		 *  Ka¿dy obiekt w jêzyku Java powi¹zany jest z tak zwanym monitorem. Ka¿dy monitor mo¿e byæ w jednym z dwóch stanów: odblokowany albo zablokowany.
		 *   Monitor mo¿e byæ zablokowany wy³¹cznie przez jeden w¹tek w danym momencie. Dziêki tej w³aœciwoœci to obiekty u¿ywane s¹ do tego,
		 *   ¿eby synchronizowaæ w¹tki ze sob¹. S³u¿y do tego s³owo kluczowe synchronized.
		 *
		 *   synchronized (obiekt) {
    			// synchronizowany kod
			}
			Masz pewnoœæ, ¿e wszystko co znajduje siê wewn¹trz bloku w ka¿dym momencie uruchomione jest przez maksymalnie jeden w¹tek.
		 */
        /*
         * Ka¿dy obiekt w jêzyku Java powi¹zany jest z tak zwanym monitorem. Ka¿dy monitor mo¿e byæ w jednym z dwóch stanów:
         * odblokowany albo zablokowany. Monitor mo¿e byæ zablokowany wy³¹cznie przez jeden w¹tek w danym momencie.
         * Dziêki tej w³aœciwoœci to obiekty u¿ywane s¹ do tego, ¿eby synchronizowaæ w¹tki ze sob¹. S³u¿y do tego s³owo kluczowe synchronized.
         */




    }
}