import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class HW4P2aChen {
        public static void main(String[] args)
        {
            Date d = new Date(123, 1, 6, 12,00);
            Date dafter = new Date(124, 1, 6, 12,00);
            Date dbefore = new Date(122, 1, 6, 12,00);

            Fruit pom  = new Fruit(d, "pomegranate");
            Fruit app = new Apple(dafter);
            Orange o = new Orange(dbefore);

            System.out.println("Orange is in 2022, Pomegranate Fruit is in 2023, Apple is in 2024");

            o.ripeTime();
            pom.ripeTime();
            app.ripeTime();

            ArrayList<Fruit> ft = new ArrayList<Fruit>();
            ft.add(app);
            ft.add(o);
            ft.add(pom);

            System.out.println("This list is out of order:");
            System.out.println(ft);
            Collections.sort(ft);

            System.out.println("This list is sorted [Orange, Fruit('pomegranate'), Apple:");
            System.out.println(ft);
        }
    }
