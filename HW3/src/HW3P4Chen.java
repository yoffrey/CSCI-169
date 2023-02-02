import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class HW3P4Chen {
    public static String fname;
    public static Date ripedate;
    public static void main(String[] args) {
        ArrayList<Food> objArray = new ArrayList<Food>();
        ArrayList<Object> input = new ArrayList<Object>(
                Arrays.asList("Food", "Orange", "Fruit", "Papaya", 3932728, "Apple", "now", "Food", "sandwich"));
        System.out.println(input);

        int i = 0;
        int j;

        for (i=0; i<input.size();) {
            for (j = i + 1; j < input.size(); j++) {
                if (Arrays.asList("Food", "Orange", "Fruit", "Apple").contains(input.get(j))) {
                    break;
                }
                else{
                    // If the string isn't an Object that we want and, it's not "now", then it is the name of the food
                    if ((input.get(j).getClass() == String.class) & (input.get(j) != "now")) {
                        fname = (String) input.get(j);
                    }
                    // If its "now" or an int. It is the ripe date.
                    else if(input.get(j) == "now"){
                        ripedate = new java.util.Date();
                    } else if (input.get(j).getClass() == Integer.class) {
                        int tmp = (int) input.get(j);
                        ripedate = new java.util.Date(tmp);
                    }
                }
            }

            if (input.get(i) == "Apple"){
                Apple ap = new Apple(ripedate);
                objArray.add(ap);
            }
            else if (input.get(i) == "Orange"){
                Orange og = new Orange(ripedate);
                objArray.add(og);
            }
            else if (input.get(i) == "Fruit"){
                Fruit ft = new Fruit(ripedate, fname);
                objArray.add(ft);
            }
            else if(input.get(i) == "Food"){
                Food fd = new Food(fname);
                objArray.add(fd);
            }
            ripedate = null;
            fname = null;
            i=j;
        }

        System.out.println(objArray);
        for (i=0; i<objArray.size(); i++){
            System.out.print("("+ objArray.get(i).getClass() + ")");
        }
    }
}
