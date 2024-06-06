package test.zadanie1;

import java.time.LocalDateTime;

public class Main {

//    Zadanie 01:
//    napisz kod tak aby fragment ponizej dzialal.
//    Nie mozesz uzywac tablic, list, setow, zadnych kolekcji czy kolejek, ani konkatenowac to w Stringi czy appendowac
//    w string buildery
//
//    //tworzymy klaske String container ktora bedzie mogla przyjmowac tylko Stringi z okreslonym Patternem podanym jako argument.
////podczas tworzenia obiektu nalezy zdefinowac poprawnosc patternu i jesli pattern bedzie "zly- czyli taki ktory sie nie kompiluje"
////to ma zostac rzucony wyjatek InvalidStringContainerPatternException(badPattern)
////wszystkie wyjatki w programie maja dziedziczyc RuntimeException.
////tu w przykladzie dodajemy kody pocztowe
//    StringContainer st = new StringContainer("\\d{2}[-]\\d{3}");
//
//st.add("02-495");//git
//st.add("01-120");//git
//st.add("05-123");//git
//st.add("00-000");//git
////st.add("ala ma kota"); //powinno sie wywalic wyjatkiem InvalidStringContainerValueException(badValue)
//for(int i=0; i<st.size(); i++){
//        System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
//    }
//
//st.remove(0);  //usuwa "02-495"
//st.remove("00-000"); // usuwa "00-000"
//
//System.out.println("po usunieciu");
//for(int i=0; i<st.size(); i++){
//        System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
//    }
//
//    nasza liste mozna tez parametryzowac tak aby nie dalo sie wrzucac powtorzen np:
//    StringContainer st = new StringContainer("\\d{2}[-]\\d{3}", true); //jakis parametr np: duplicatedNotAllowed - domyslnie false
//    wtedy np:
//            st.add("02-495");//git
//st.add("02-495");//powinno rzucic wyjatkiem DuplicatedElementOnListException
//    //kazdy element w StringContainer powinien miec date+czas dodania elementu do niego,
////nalezy zaimplementowac metode:
//    StringContainer stBetween = st.getDataBetween(dateFrom, dateTo);
////ktora zwroci elementy dodane pomiedzy dateFrom a dateTo
////gdzie dateFrom i dateTo to obiekty LocalDateTime i moga byc nullami.
////dodatkowo pomysl o persystencji StringContainer tj:
//st.storeToFile("postalCodes.txt"); // powinno zapisac zawartosc
//    StringContainer fromFile = StringContainer.fromFile("postalCodes.txt"); // powinno wczytac zawartosc z pliku i "fromFile" musi miec te same dane co "st"


    public static void main(String[] args) {
        try {
            StringContainer st = new StringContainer("\\d{2}[-]\\d{3}");
            st.add("02-495");
            st.add("01-120");
            st.add("05-123");
            // st.add("05-123");
            st.add("00-000");
            //st.add("ala ma kota");

            for (int i = 0; i < st.size(); i++) {
                System.out.println(st.get(i));
            }

            st.remove(2);
            st.remove("00-000");

            System.out.println("After removal:");
            for (int i = 0; i < st.size(); i++) {
                System.out.println(st.get(i));
            }

            st.add("23-432");
            st.add("13-411");

            LocalDateTime dateFrom = LocalDateTime.now().minusDays(1);
            LocalDateTime dateTo = LocalDateTime.now().plusDays(1);

            StringContainer stBetween = st.getDataBetween(dateFrom, dateTo);
            System.out.println("Between elements: " + dateFrom + " or: " + dateTo);
            for (int i = 0; i < stBetween.size(); i++) {
                System.out.println(stBetween.get(i));
            }

            st.storeToFile("postalCodes.txt");
            StringContainer fromFile = StringContainer.fromFile("postalCodes.txt");
            System.out.println("From file:");
            for (int i = 0; i < fromFile.size(); i++) {
                System.out.println(fromFile.get(i));
            }
            boolean isEqual = StringContainer.compareStringContainers(st, fromFile);
            System.out.println("The content before and after is identical? " + isEqual);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

