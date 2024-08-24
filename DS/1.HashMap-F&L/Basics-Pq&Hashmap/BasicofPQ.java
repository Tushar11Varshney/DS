import java.util.PriorityQueue;
// import java.util.Collections;

public class BasicofPQ {

    public static void set1(int[] arr) {
        // int ranks[] = { 2, 1, 7, 90, 80 };
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //max pq
        // for (int val : ranks)
        //     pq.add(val); // nlogn

        // while (pq.size() > 0) // nlogn
        // {
        //     System.out.println(pq.peek());
        //     pq.remove();
        // }
        
        // PriorityQueue<Integer> pq = new PriorityQueue<>(); // minPQ

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {  //jisko compare krna chahte ho wo pehla argument jiske saath krna chahte ho wo dusra argument..dont go in depth nhi tou 8 scenario sochne pdenge + +,+ - so on...
            //integer ki jgh hashmap daalenge tou = and hashcode override krna pdta hai.
            return b - a; // other - this, reverse of default.
        }); // maxPQ

        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // maxPQ.for Long , Integer , Character

        for (int ele : arr)
            pq.add(ele);
        while (pq.size() != 0) {
            System.out.print(pq.remove() + " ");
        }
    }

    public static class pair implements Comparable<pair> { // Comparable is a abstract class(also called interface)
        int val1 = 0;
        int val2 = 0;

        pair(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        @Override  //it tell us if there is a spelling error or not.The function compareTo is defined in Comparable we are overriding it according to our logic
        public int compareTo(pair o) {                   //compareTo pq mein jaayga usme jo data pdaa hogya vahan se us data ko hmare data se compare kregaa...compareTo se faayda ye hua ki isko override krke hum koi sa bhi ds mein comparison kr skte hai.
            // return o.val1 - this.val1; // other - this.
            return this.val1 - o.val1; // this - other.
        }
    }

    public static void set2(int[][] arr) {
        // PriorityQueue<pair> pq = new PriorityQueue<>((a,b)->{      //this is lambda function tarikka--comparator
        // return b.val2 - a.val2; // other - this, reverse of default.
        // }); // maxPQ....agr aese likhenge tou upr wala override hojayga

        PriorityQueue<pair> pq = new PriorityQueue<>();
        for (int[] ele : arr)
            pq.add(new pair(ele[0], ele[1]));

        while (pq.size() != 0) {
            pair p = pq.peek();
            System.out.println(p.val1 + "," + p.val2);
            pq.remove();
        }
    }

    public static void main(String[] args) {
        // int[] arr = {10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13};
        int[][] arr = { { 10, 20 }, { 30, -2 }, { -3, -4 }, { 5, 6 }, { 7, 8 }, { 9, 22 }, { 11, 13 } };
        // set1(arr);
        // set2(arr);
    }
}
