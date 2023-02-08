import java.util.Comparator;
import java.util.Date;

public class Fruit extends Food implements Comparable<Fruit> {
    public Date ripe;
    public Fruit(){
        name = "";
    }
    public Fruit(Date r, String n){
        name = n;
        ripe = r;
    }
    public void ripeTime(){
        System.out.println("You can eat it on " + ripe);
    }

    @Override
    public int compareTo(Fruit ft) {
        if (ripe.compareTo(ft.ripe) > 0){
            return 1;
        }
        else if (ripe.compareTo(ft.ripe) < 0){
            return -1;
        }
        // ripe.compareTo(ft.ripe) == 0
        else return 0;
    }
}
