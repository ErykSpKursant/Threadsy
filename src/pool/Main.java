package pool;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        /*
         * pule wątków
         * Obsługę cyklicznych, występujących jednocześnie zdarzeń warto
         * często zrównoleglić. Na przykład obsługa żądań HTTP, zapytań do bazy danych,
         * odpytywania zewnętrznych serwisów, czy wykonywania wymagających obliczeń
         * matematycznych. Możemy to uzyskać poprzez korzystanie z wątków.
         *
         * Ale warto pamiętać, że tworzenie nowych wątków per każde takie działanie jest
         * bardzo kosztowne. Wymaga stworzenia prawdziwego wątku w systemie operacyjnym,
         * zaalokowania pamięci, stworzenia deskryptora wątku. Wszystko to kosztuje.
         *
         * W sytuacji, gdy dane zadanie będzie żyć stosunkowo krótko i dany wątek zaraz
         * będziemy ubijać jest to marnowanie zasobów.
         *
         * Zamiast tego dużo lepiej jest skorzystać z puli wątków, za pomocą której
         * będziemy tylko wskazywać jakie zadanie ma być wykonane, a pula wątków
         * samodzielnie zadba o to by przypisać wolny wątek do wykonania tego zadania.
         *
         * W Javie pule wątków są opisywane przez dwa interfejsy.
         *
         * Executor oraz  ExecutorService
         *
         * Executor Dzięki niemu możemy przekazać nasze zadanie (poprzez implementację interfejsu Runnable) do wykonania na puli wątków.
         *
         * ExecutorService służy do wykonywania zadań i zarządzania całą pulą wątków. Posiada zdecydowanie bogatszy zakres metod.
         *
         * Praca domowa https://sztukakodu.pl/co-warto-wiedziec-o-pulach-watkow-w-javie-najlepszy-przewodnik-jakiego-potrzebujesz/
         */


        /*
         * Wątki często wykorzystujemy do zwracania wyników jakiś operacji, które
         * zostaną wykorzystane przez inny wątek. Oczywiście moglibyśmy taką komunikację
         * oprogramować metodami wait(), notify(), ale jest w Javie jest dedykowany
         * interfejs, którego używamy tak samo jak Runnable'a z tą różnicą, że Callable
         * jest interfejsem generycznym, zdolnym do zwracania dowolnego typu wyników.
         */
        Callable<Integer> callable = () -> {
            System.out.println("start callable");
            return 5;
        };
//        callable.call();
//Tak samo jak Runnable'a, Callable'a można przekazać do puli wątków.
        //Pojawia się pytanie jak odebrać wartość zwróconą przez obiekt Callable.
        //Robimy to metodą get() obiektu Future zwracanego przez metodę submit() puli wątków.
        //wywolanie get blokuje watek glowny do czasu zwrocenia wartosci przez callable
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<Integer> result = pool.submit(callable);

        System.out.println(result.get());
//Ciekawszym użyciem metody get() jest podanie jej maksymalnego czasu w którym wątkek zwróci wynik. Jeśli wątek nie wyrobi się
        //we wskazanym timeout'cie, wtedy get() podniesie wyjątek TimeoutException.

        Callable<String> callable1 = () -> {
            Thread.sleep(1000);
            return "cos tam";
        };

        ExecutorService pool2 = Executors.newFixedThreadPool(5);
        Future<String>result2 = pool2.submit(callable1);
        System.out.println(result2.get(1500, TimeUnit.MILLISECONDS));

        // dokonczymy na nbastepnych zajeciach






        pool.shutdown();

    }
}
