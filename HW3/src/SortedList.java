//What role should binary search play in a truly object-oriented program?
import java.util.Scanner;
class SortedList
{
    public static int arr[];
    public static void main(String args[])
    {
        int c, first, last, middle, n, search, array[];
        int ins;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of elements");
        n = in.nextInt();
        array = new int[n];

        System.out.println("Enter " + n + " integers in any order");

        for (c = 0; c < n; c++)
            array[c] = in.nextInt();

        System.out.println("Enter value to find");
        search = in.nextInt();

        SortedList sl = new SortedList(array);
        sl.binarySearch(search);

        System.out.println("Enter value to insert");
        ins = in.nextInt();

        sl.insert(ins);
        print();

        System.out.println("Enter new to find");
        search = in.nextInt();
        sl.binarySearch(search);
        in.close();

    }
    public SortedList() {
        int c, first, last, middle, n, search;
        c = first = last = middle = n = search = 0;
    }
    public SortedList(int a[]) {
        //fills arr with the items in a, ensuring they're sorted
        int c, first, last, middle, search;
        c = first = last = middle = search = 0;
        int n = a.length;
        arr = a;
        quicksort(arr, 0, arr.length-1);
        print();
    }
    public int binarySearch(int search) {
        int first  = 0;
        int last   = arr.length - 1;
        int middle = (first + last)/2;
        while( first <= last )
        {
            if ( arr[middle] < search )
                first = middle + 1;
                else if ( arr[middle] == search )
                {
                    System.out.println(search + " found at location " + middle + ".");
                    return middle;
                }
            else
                last = middle - 1;

            middle = (first + last)/2;
       }
       if ( first > last )
            System.out.println(search + " is not present in the list.\n");

       return middle;
    }

    public void insert(int val){
        int n = arr.length;
        int tmp = 0;
        int newarr[] = new int[n+1];
        if (val>=arr[n-1]){
            for(int i=0; i<n; i++){
                newarr[i] = arr[i];
            }
            newarr[newarr.length-1] = val;
        }
        else {
            for (int i = 0; i < n; i++) {
                if (arr[i] >= val) {
                    newarr[i] = val;
                    tmp = i;
                    break;
                } else {
                    newarr[i] = arr[i];
                }
            }
            for (int i = tmp; i < arr.length; i++) {
                newarr[i+1] = arr[i];
            }
        }
        arr = newarr;
    }

    private static void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }

    public static int partition(int[] input, int p, int r){
        int pivot = input[r];

        while (p < r){
            while (input[p] < pivot){
                p++;
            }
            while (input[r] > pivot){
                r--;
            }
            if (input[p] == input[r]){
                p++;
            } else if (p < r) {
                int tmp = input[p];
                input[p] = input[r];
                input[r] = tmp;
            }
        }
        return r;
    }
    private  static void quicksort(int[] input, int p, int r){
        if (p < r){
            int j = partition(input, p ,r);
            quicksort(input, p, j-1);
            quicksort(input, j+1, r);
        }
        arr = input;
    }
}
