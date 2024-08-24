import java.util.Collection;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class sumeesirHash {
    
    // 1817
    // beekaar array ki jgah hashset rkho because array mein unique hai ya ni linear search chalan pdega so do it using hashset wo kisi duplicate ko ni aanedega.
    //Return 1 indexed array->0th idx pr kitne logon ka UAM 1 Hai
    public int[] findingUsersActiveMinutes2(int[][] logs, int k) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int[] result = new int[k];
        for (int i = 0; i < logs.length; i++) {
            int userId = logs[i][0];
            int activityMinute = logs[i][1];
            if (!map.containsKey(userId)) {
                map.put(userId, new HashSet<>());
            }
            map.get(userId).add(activityMinute);
        }

        Set<Integer> keyset = map.keySet();
        for (int ele : keyset) {
            int size = map.get(ele).size();
            result[size - 1]++;
        }

        return result;
    }

    //complete on leetcode 332
    public static void findPathfromtickets(HashMap<String, String> map) {
        HashMap<String,Boolean>m=new HashMap<>();  //m:gives source
        for(String str:map.keySet())  //map given in ques
        {
            String d=map.get(str);   //d->dstn
            if(!m.containsKey(str))
            m.put(str,true);
            m.put(d,false);
        }
        
        String src="";
        for(String str:map.keySet())
        {
            if(m.get(str))
            {
                src=str;
                break;
            }
        }
        
        while(true)
        {
            if(map.containsKey(src))
            {
                System.out.print(src+" -> ");
                src=map.get(src);
            }
            else break;
        }
        System.out.print(src+".");
    }

    //complete on leetcode 1497
    public static boolean ArrayDivideInPair(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 0; i < arr.length; i++) {
            int rem = arr[i] % k;
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        if (map.get(0) % 2 != 0)
            return false;
        else
            map.remove(0);
        if (k % 2 == 0) {  //k=11 agr 5 h ek rem then agla 6 hona chahiye
            if (map.containsKey(k / 2) && map.get(k / 2) % 2 != 0)
                return false;
            else
                map.remove(k / 2);
        }

        for (Integer rem : map.keySet()) {
            int remainingRem = k - rem;
            if (map.get(rem) != map.get(remainingRem))
                return false;
        }
        return true;
    }

    // leetcode 706-we have to take a fixed size list vrna bucket index change hota rhega
    class MyHashMap {

        private class HMNode {
            int key;
            int value;

            HMNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size;
        private LinkedList<HMNode>[] buckets;

        public MyHashMap() {
            initbuckets(4);
            size = 0;
        }

        private void initbuckets(int N) {
            buckets = new LinkedList[N];
            for (int bi = 0; bi < buckets.length; bi++) {
                buckets[bi] = new LinkedList<>();
            }
        }

        public int size() {
            return size;
        }

        private int hashfn(int key) {
            int hashcode = key % 10;
            return Math.abs(hashcode) % buckets.length;
        }

        public void put(int key, int value) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di);
                node.value = value;
            } else {
                HMNode node = new HMNode(key, value);
                buckets[bi].add(node);
                size++;
            }

            double lambda = size() * 1.0 / buckets.length;
            if (lambda > 2.0)
                rehash();
        }

        private int getIndexWithinBucket(int Key, int bi) {
            int di = 0;
            for (HMNode node : buckets[bi]) {
                if (node.key == Key)
                    return di;
                di++;
            }
            return -1;
        }

        private void rehash() {
            LinkedList<HMNode>[] oba = buckets;
            initbuckets(oba.length * 2);
            size = 0;
            for (int i = 0; i < oba.length; i++) {
                for (HMNode node : oba[i])
                    put(node.key, node.value);
            }
        }

        public int get(int key) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di);
                return node.value;
            } else
                return -1;
        }

        public void remove(int key) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                buckets[bi].remove(di);
                size--;
            }
        }
    }

    public static class HashMap<K, V> {
        private class HMNode {
            K key;
            V value;

            HMNode(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size; // n
        private LinkedList<HMNode>[] buckets; // N = buckets.length    //Array of LinkedList

        public HashMap() {
            initbuckets(4);
            size = 0;
        }

        private void initbuckets(int N) {
            buckets = new LinkedList[N];
            for (int bi = 0; bi < buckets.length; bi++) {
                buckets[bi] = new LinkedList<>();
            }
        }

        public void put(K key, V value) {
            int bi = hashfn(key); // bucketIndex
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di); // updation
                node.value = value;
            } else {
                HMNode node = new HMNode(key, value); // insertion
                buckets[bi].add(node);
                size++;
            }

            double lambda = size() * 1.0 / buckets.length; // rehashing
            if (lambda > 2.0)
                rehash();
        }

        private int hashfn(K key) { // gives bucketIndex
            int hashcode = key.hashCode();
            return Math.abs(hashcode) % buckets.length; // hashCode can be negative
        }

        private int getIndexWithinBucket(K Key, int bi) { // gives data index
            int di = 0;
            for (HMNode node : buckets[bi]) {
                if (node.key.equals(Key))
                    return di;
                di++;
            }
            return -1;
        }

        private void rehash() {
            LinkedList<HMNode>[] oba = buckets; // oba-old bucket array
            initbuckets(oba.length * 2);
            size = 0;
            for (int i = 0; i < oba.length; i++) {
                for (HMNode node : oba[i])
                    put(node.key, node.value);
            }
        }

        public V get(K key) throws Exception {
            int bi = hashfn(key); // bucketIndex
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].get(di);
                return node.value;
            } else
                return null;
        } 

        public boolean containsKey(K key) {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1)
                return true;
            else
                return false;
        }

        public V remove(K key) throws Exception {
            int bi = hashfn(key);
            int di = getIndexWithinBucket(key, bi);

            if (di != -1) {
                HMNode node = buckets[bi].remove(di);
                size--;
                return node.value;
            } else
                return null;
        }

        public ArrayList<K> keyset() throws Exception {
            ArrayList<K> keys = new ArrayList<>();

            for (int i = 0; i < buckets.length; i++) {
                for (HMNode node : buckets[i])
                    keys.add(node.key);
            }

            return keys;
        }

        public int size() {
            return size;
        }
    }

    //705
    class MyHashSet {
        
        List<Integer>[]map;
        int totalValues;
        
        public void initialiseList(int n,List<Integer>[]map)
        {
            for(int i=0;i<n;i++)
                map[i]=new ArrayList<>();
            totalValues=0;
        }
        
        public MyHashSet() {
            map=new ArrayList[4];
            initialiseList(4,map);
        }
        
        public int getBucketIndex(int key,List<Integer>[]map)
        {
            int bi=Integer.hashCode(key);
            return Math.abs(bi)%map.length;
        }
            
        public int getDataIndex(int key,int bi)
        {
            int idx=-1;
            for(int i=0;i<map[bi].size();i++)
            {
                if(key==map[bi].get(i))
                {
                    idx=i;
                    break;
                }
            }
            return idx;
        }
        
        public void add(int key) {
            int bi=getBucketIndex(key,map);
            int di=getDataIndex(key,bi);
            if(di==-1)
            {
                map[bi].add(key);
                totalValues++;
            }
            else
                map[bi].set(di,key);
            
            if(reHash()>2.0)        
                reassign();
        }
        
        public double reHash()
        {
            return (totalValues*1.0)/map.length;    
        }
        
        public void reassign()
        {
            List<Integer>[]newMap=new ArrayList[2*map.length];
            initialiseList(2*map.length,newMap);
            for(int i=0;i<map.length;i++)
            {
                for(int j=0;j<map[i].size();j++)
                {
                    int key=map[i].get(j);
                    int bi=getBucketIndex(key,newMap);
                    newMap[bi].add(key);
                }
            }
            map=newMap;
        }
        
        public void remove(int key) {
            int bi=getBucketIndex(key,map);
            int di=getDataIndex(key,bi);
            if(di!=-1)
                map[bi].remove(di);
        }
        
        public boolean contains(int key) {
            int bi=getBucketIndex(key,map);
            int di=getDataIndex(key,bi);
            if(di==-1)
                return false;
            return true;
        }
    }

   
//========
/*
static class pair{
    int a=0;
    String s="";
    
    pair(int a,String s)
    {
        this.a=a;
        this.s=s;
    }
    
    @Override
    public int hashCode()   //taaki equal waale same bucket mein jaaye..iske bina sb agl alg bucket mein jaaynge
    {
        return this.a%29;    
    }
    
    @Override
    public boolean equals(Object o)  //this will tell it how to compare two pair..agr hashCode ni hoga pr ye fn hoga then aesa hoga ki kuch same or kuch diff bucket mein jaaynge
    {
        if(o==null)return false;  //no need found
        pair p=(pair)o;
        
        if(this.a!=p.a)return false;
        if(!this.s.equals(p.s))return false;
        
        return true;
    }
    
    @Override
    public String toString()  //ye ni denge tou address print krega
    {
        return "("+this.a+" "+this.s+")";
    }
}

public static void main(String[] args) {
    HashMap<pair,Integer>map=new HashMap<>();
    for(int i=0;i<5;i++){
        pair p=new pair(10,"tushar");
        map.put(p,map.getOrDefault(p,0)+1);
    //   System.out.println(p);
    }
    map.put(null,1);
    pair p=new pair(10,"tushar");
    map.put(p,1);
    System.out.println(map.size());
    System.out.println(map);
}

 public boolean doublePairArray(int[] arr) {
        
        if(arr.length==0)return true;
        
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int ele:arr)map.put(ele,map.getOrDefault(ele,0)+1);
        
        Integer ar[]=new Integer[arr.length];
        for(int i=0;i<arr.length;i++)ar[i]=arr[i];
        
        Arrays.sort(ar,(a,b)->{   // lambda fn used with capital INTEGER
            return Math.abs(a)-Math.abs(b);
        });
        
        for(int ele:ar)
        {
            if(map.get(ele)==0)continue;
            
            if(map.getOrDefault(2*ele,0)==0)return false;
            
            
            map.put(ele,map.get(ele)-1);
            map.put(2*ele,map.get(2*ele)-1);
        }
        
        return true;
} */

    //166 leetcode
    //-1,-2147483648(iska abs int se bhaar) take long
    public String fractionToDecimal(int n, int d) {
        long num=Math.abs((long)n);
        long den=Math.abs((long)d);
        
        long q = num / den;
        long r = num % den;  //long lena pdega idhr bhi hashmap bhi else conversion prob
        
        StringBuilder ans = new StringBuilder();
        ans.append(q);
        
        if(n<0 && d>0 || d<0 && n>0)
            ans.insert(0,"-");
        
        if (r == 0) {
            return ans.toString();
        } 
        else {
            HashMap<Long, Integer> map = new HashMap<>(); 
            ans.append(".");
            while (r != 0) {
                if (map.containsKey(r)) {
                    int len = map.get(r);
                    ans.insert(len, "(");
                    ans.append(")");
                    break;
                } else {
                    r *= 10;
                    map.put(r, ans.length());
                    q = r / den;
                    r = r % den;
                    ans.append(q);
                }
            }
        }
        
        return ans.toString();
    }

    public static void main(String args[]) throws Exception {
        // fun1();
        // int arr[]={10,5,9,1,11,8,6,15,3,12,2};
        // longestConsecutiveSeq(arr, 11);

        // fun2();

        // HashMap<String, String> map = new HashMap<>(); // employee,manager
        // for (int i = 0; i < n; i++) {
        // String employee = sc.next(), manager = sc.next();
        // map.put(employee, manager);
        // }

        // HashMap<String, HashSet<String>> tree = new HashMap<>(); // manager,employee
        // fun(tree, map);

        Scanner scn = new Scanner(System.in);
        int noofpairs_src_des = scn.nextInt();
        HashMap<String, String> map = new HashMap<>();
        map.put("chennai", "bangalore");
        map.put("bombay", "delhi");
        map.put("goa", "chennai");
        map.put("delhi", "goa");

        findPathfromtickets(map);
    }
}


static class ListComparator implements Comparator<List<String>> {   //to  make answer unique
    @Override
    public int compare(List<String> l1, List<String> l2) {
        if (l1.size() != l2.size()) {   //descending mein krne kelie
            return l2.size() - l1.size();
        }

        String l1str = l1.get(0);
        String l2str = l2.get(0);
        return l1str.compareTo(l2str);  //sort on basis of 1st string if same then second

    }
}
	
public static void main(String[] args) {
    ArrayList<ArrayList<String>>ans=new ArrayList<>();
    ans.add(new ArrayList<>());ans.add(new ArrayList<>());ans.add(new ArrayList<>());
    ans.get(2).add("a");
    ans.get(2).add("z");
    ans.get(2).add("y");
    ans.get(1).add("m");
    ans.get(1).add("e");
    ans.get(0).add("a");
    ans.get(0).add("q");
    ans.get(0).add("j");
    for (ArrayList<String> a : ans) {  //within the list sorting kelie
        Collections.sort(a);
    }
    display(ans);	
    ans.sort(new ListComparator());
    System.out.println();
    display(ans);

    //a j q //em //ayz
    //ajq //ayz //em
}
