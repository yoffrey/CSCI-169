import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class HW4P2bChen implements Iterable<Integer> {
    private List<Integer> list = new ArrayList<>();

    public static void main(String args[]) {
        int ins;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of elements");
        int n = in.nextInt();
        HW4P2bChen sl = new HW4P2bChen();
        System.out.println("Enter " + n + " integers in order");
        for (int i = 0; i < n; i++) {
            sl.list.add(in.nextInt());
        }
        sl.print();

        System.out.println("Enter value to find");
        int search = in.nextInt();
        sl.binarySearch(search);

        System.out.println("Enter value to insert");
        ins = in.nextInt();
        sl.insert(ins);
        sl.print();

        System.out.println("Enter new value to find");
        search = in.nextInt();
        sl.binarySearch(search);
        in.close();
    }

    @Override
    public java.util.Iterator<Integer> iterator() {
        return list.iterator();
    }

    public int binarySearch(int search) {
        int index = Collections.binarySearch(list, search);
        if (index >= 0) {
            System.out.println(search + " found at location " + index + ".");
        } else {
            System.out.println(search + " is not present in the list.\n");
        }
        return 0;
    }

    public void insert(int val) {
        int index = Collections.binarySearch(list, val);
        if (index < 0) {
            index = -(index + 1);
        }
        list.add(index, val);
    }

    public void print() {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
