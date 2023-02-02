import java.util.Date;

public class Fruit extends Food{
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
}
