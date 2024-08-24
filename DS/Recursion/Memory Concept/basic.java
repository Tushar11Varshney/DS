import java.util.*;

public class basic {

    public static void arrayListonHeap(ArrayList<Integer> arr) {
        arr.set(0, 10);
    }

    public static void fun() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(29);
        System.out.println(arr.get(0)); //29
        arrayListonHeap(arr);
        System.out.println(arr.get(0)); //10
    }

    public static void checking(int[] arr) {
        arr[0] = 15;
    }

    public static void main(String[] args) {          
        // fun();
        // int []arr={65,3,23,4};
        // checking(arr);
        // System.out.println(arr[0]); //15
    }

}
