// pair with equal sum
// Finding pair with certain sum
// intersection of two array(return only once, return all occurence) //349 //350
// max frequency character
// Sort Characters By Frequency
// contains duplicate[sort and check size of set/ hashmap]
// contain nearby duplicate(for 1st element map[ele] will be 0) - 2(i, map.find() == end())


// min cost to join ropes
// kth largest
// kth largest in stream
// Top k frequent
// kth smallest in matrix
// sort k sorted array
// merge k list
// Max sliding window

//3

//1512 nc
public int numIdenticalPairs(int[] nums) {
    HashMap<Integer,Integer>map=new HashMap<>();
    for(int ele:nums)
        map.put(ele,map.getOrDefault(ele,0)+1);
    
    int count=0;
    for(int key:map.keySet())
    {
        int freq=map.get(key);
        count+=(freq*(freq-1))/2;
    }
    return count;
}


//1865
HashMap<Integer,Integer>map=new HashMap<>();
int []nums1,nums2;

public FindSumPairs(int[] nums1, int[] nums2) {
    this.nums1=nums1;
    this.nums2=nums2;
    for(int ele:nums2)
    {
        map.put(ele,map.getOrDefault(ele,0)+1);
    }
}

public void add(int index, int val) {
    map.put(nums2[index],map.get(nums2[index])-1);
    nums2[index]+=val;
    map.put(nums2[index],map.getOrDefault(nums2[index],0)+1);
}

public int count(int target) {
    
    int c=0;
    // 1 <= nums1.length <= 1000
    // 1 <= nums2.length <= 10^5 loop on smaller array will take less time so put nums2 in hashmap.
    for(int i=0;i<nums1.length;i++)
    {
        if(map.containsKey(target-nums1[i]))
            c+=map.get(target-nums1[i]);
    }
    
    return c;
}

//yc
public static boolean pairsWithEqualSum(int[] arr) { 
    int n = arr.length;
    HashSet<Integer> hs = new HashSet<>();
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            int sum = arr[i] + arr[j];
            if (hs.contains(sum))
                return true;
            hs.add(sum);
        }
    }
    return false;
}

//349
vector<int> intersection(vector<int> &nums1, vector<int> &nums2)
{
    unordered_map<int, int> map;
    for (int ele : nums1)
        map[ele] = 1;
    vector<int> res;
    for (int ele : nums2)
    {
        if (map.find(ele) != map.end())
        {
            res.push_back(ele);
            map.erase(ele);
        }
    }
    return res;

    // HashMap<Integer, Integer> map = new HashMap<>();
    //     for (int i = 0; i < n1; i++) {
    //         //dont do old frequency+1 because not needed in this qs
    //         map.put(arr1[i], 1);
    //     }

    //     for (int i = 0; i < n2; i++) {
    //         if (map.containsKey(arr2[i])) {
    //             System.out.println(arr2[i]);
    //             map.remove(arr2[i]);
    //         }
    // }
}

//350
vector<int> intersection(vector<int> &nums1, vector<int> &nums2)
{
    unordered_map<int, int> map;
    for (int ele : nums1)
    {
        map[ele]++;
    }
    vector<int> res;
    for (int ele : nums2)
    {
        if (map.find(ele) != map.end())
        {
            res.push_back(ele);
            map[ele]--;
            if (map[ele] == 0)
                map.erase(ele);
        }
    }
    return res;

    //         HashMap<Integer, Integer> map = new HashMap<>();
    //         List<Integer>ans=new ArrayList<>();
    //         int n1=arr1.length,n2=arr2.length;
    //         for (int i = 0; i < n1; i++) {
    //             map.put(arr1[i],map.getOrDefault(arr1[i],0)+1);
    //         }

    //         for (int i = 0; i < n2; i++) {
    //             if (map.containsKey(arr2[i])) {
    //                 ans.add(arr2[i]);
    //                 map.put(arr2[i],map.get(arr2[i])-1);
    //                 if(map.get(arr2[i])==0)
    //                     map.remove(arr2[i]);
    //             }
    //         }
            
    //         int []res=new int[ans.size()];
    //         for(int i=0;i<ans.size();i++)
    //         {
    //             res[i]=ans.get(i);
    //         }
            
    //         return res;
}

// https://www.pepcoding.com/resources/online-java-foundation/hashmap-and-heap/hfc-official/ojquestion
public static void maxfreqchar(String str)
    {
        HashMap<Character,Integer>map=new HashMap<>();
        char mfc=str.charAt(0);
        int maxFreq=0;
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
            if(map.get(ch)>maxFreq)
            {
                maxFreq=map.get(ch);
                mfc=ch;
            }
        }

        System.out.println(mfc);
    }

// 451
public class pair{
    char ch;
    int freq;
    
    pair(char ch,int freq)
    {
        this.ch=ch;
        this.freq=freq;
    }
}

public String frequencySort(String s) {
    HashMap<Character,Integer>map=new HashMap<>();
    for(int i=0;i<s.length();i++)
    {
        char ch=s.charAt(i);
        map.put(ch,map.getOrDefault(ch,0)+1);
    }
    
    ArrayList<pair>arr=new ArrayList<>();
    for(char ch:map.keySet())
        arr.add(new pair(ch,map.get(ch)));
    
    Collections.sort(arr,(a,b)->{
        if(b.freq>a.freq)return 1;
        return -1;
    });
    
    String ans="";
    for(int i=0;i<arr.size();i++)
    {
        int freq=arr.get(i).freq;
        char ch=arr.get(i).ch;
        while(freq-->0)
            ans=ans+ch;
    }
    
    return ans;
}

//217
bool containsDuplicate(vector<int> &nums)
{
    return nums.size() > set(nums.begin(), nums.end()).size();  
}

bool containsDuplicate(vector<int> &nums)
{
    unordered_map<int, int> map;
    for (int ele : nums)
    {
        if (map[ele] == 0)
            map[ele] = 1;
        else
            return true;
    }
    return false;
}

//219
// nums[i] == nums[j] and abs(i - j) <= k.
bool containsNearbyDuplicate(vector<int>& nums, int k) { 
    unordered_map<int,int>map;
    for(int i=0;i<nums.size();i++)
    {
        int ele=nums[i];
        if(map[ele]==0) //i+1 because 1st element kelie tou 0 hi hoga...[1,2,3,1] 3
            map[ele]=i+1;
        else if( (i+1) - map[ele] <= k)
            return true;
        else 
            map[ele]=i+1;
    }
    return false;
}

bool containsNearbyDuplicate(vector<int>& nums, int k) {
    unordered_map<int,int>map;
    for(int i=0;i<nums.size();i++)
    {
        int ele=nums[i];
        if(map.find(ele)==map.end())  //slow because of this O(n)
            map[ele]=i;
        else if( i - map[ele] <= k)
            return true;
        else map[ele]=i;
    }
    return false;
}


// ===============
// https://practice.geeksforgeeks.org/problems/minimum-cost-of-ropes-1587115620/1#
long minCost(long arr[], int n) {
    //multiset giving tle
    PriorityQueue<Long> pq = new PriorityQueue<>();

    for (int i = 0; i < n; i++)
        pq.add(arr[i]);                 //insert,remove=logn,get-o(1)
    long sum = 0;
    while (pq.size() != 1) {
        Long v1 = pq.remove();
        Long v2 = pq.remove();
        sum += v1 + v2;
        pq.add(v1 + v2);
    }

    return sum;
}


//215
int findKthLargest(vector<int> &nums, int k)
{
    priority_queue<int, vector<int>, greater<int>> pq;   //min pq
    for (int ele : nums)
    {
        pq.push(ele);
        if (pq.size() > k)
            pq.pop();
    }
    return pq.top();

    //  PriorityQueue<Integer> pq = new PriorityQueue<>(); // k largest mein aesa sochlo 4 sabse acche batsman chahhie then we want ki humesha min waale log bahar niklte rhein and that is what min pq do.
    //     for (int val : arr) {
    //         pq.add(val);
    //         if (pq.size() > k)
    //             pq.remove();
    //     }
    //     while (pq.size() > 0) {
    //         System.out.println(pq.peek());
    //         pq.remove();
    //     }
}

public static void sortKSortedArray(int n, int arr[], int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int val : arr) {
        pq.add(val);
        if (pq.size() > k) {
            System.out.println(pq.peek());
            pq.remove();
        }
    }
    while (pq.size() > 0) {
        System.out.println(pq.peek());
        pq.remove();
    }
}

//703
class KthLargest
{
    // if suppose 2nd largest, initialized by 1, added 2 then 2nd largest is 1
public:
    priority_queue<int, vector<int>, greater<int>> pq;
    int K;
    KthLargest(int k, vector<int> &nums)
    {
        this->K = k;
        for (int ele : nums)
        {
            pq.push(ele);
            if (pq.size() > k)
                pq.pop();
        }
    }

    int add(int val)
    {
        pq.push(val);
        if (pq.size() > K)
            pq.pop();
        return pq.top();
    }
};

//347
vector<int> topKFrequent(vector<int> &nums, int k)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele]++;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; //min pq

    for (pair<int, int> key : map)
    {
        pq.push({key.second, key.first}); //pehle frequency then key
        if (pq.size() > k)
            pq.pop();
    }

    vector<int> ans;
    while (k > 0)
    {
        ans.push_back(pq.top().second);
        pq.pop();
        k--;
    }
    return ans;
}

//378
public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
        int r1 = a / n;
        int c1 = a % n;
        int r2 = b / n;
        int c2 = b % n;

        return matrix[r1][c1] - matrix[r2][c2];
    });
    for (int i = 0; i < n; i++)
        pq.add(i * n + 0);

    while (k-- > 1) {
        int idx = pq.remove();
        int r = idx / n;
        int c = idx % n;
        if (c + 1 < n)
            pq.add(r * n + (c + 1));
    }

    int kthSmallestIdx = pq.peek();
    return matrix[kthSmallestIdx / n][kthSmallestIdx % n];
}


public static class Pair implements Comparable<Pair> { 
    int li;
    int di;
    int val;

    Pair(int li, int di, int val) {
        this.li = li;
        this.di = di;
        this.val = val;
    }

    public int compareTo(Pair o) {
        return this.val - o.val;
    }
}

public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    ArrayList<Integer> result = new ArrayList<>();
    for (int i = 0; i < lists.size(); i++)
        pq.add(new Pair(i, 0, lists.get(i).get(0)));

    while (pq.size() > 0) {
        Pair p = pq.remove();
        result.add(p.val);
        p.di++;

        if (p.di < lists.get(p.li).size()) {
            Pair r = new Pair(p.li, p.di, lists.get(p.li).get(p.di));
            pq.add(r);
        }
    }
    return result;
}

//yc
public ListNode mergeKLists(ListNode[] lists) {
    if(lists.length==0)return null;
    PriorityQueue<ListNode>pq=new PriorityQueue<>((a,b)->{
        return a.val-b.val; 
    });
    
    for(int i=0;i<lists.length;i++)
    {   
        if(lists[i]!=null)
        pq.add(lists[i]);
    }
    
    ListNode dummy=new ListNode(-1);
    ListNode prev=dummy;
    
    while(pq.size()!=0)
    {
        ListNode node=pq.poll();
        if(node.next!=null)
            pq.add(node.next);
        
        prev.next=node;
        prev=node;
    }
    return dummy.next;
}

//239
vector<int> maxSlidingWindow(vector<int> &nums, int k)
{
    if (nums.size() == 1 || k == 1)
        return nums;
    priority_queue<pair<int, int>> pq;
    int i = 0, n = nums.size();
    vector<int> ans; //n-k+1 size ki array bnadi aur pushback kiya then 0,0,0,ans aayga
    while (i < n)
    {
        while (pq.size() != 0 && pq.top().second <= i - k) //out of range waale bnde nhi htaye yahan jo top pr the pr range se bahaar
            pq.pop();
        pq.push({nums[i], i});

        if (i >= k - 1)
            ans.push_back(pq.top().first);
        i++;
    }
    return ans;
}

//1642 nc
public int furthestBuilding(int[] heights, int bricks, int ladders) {
    PriorityQueue<Integer>pq=new PriorityQueue<>();
    for(int i=0;i<heights.length-1;i++)
    {
        int diff=heights[i+1]-heights[i];
        if(diff>0)
            pq.add(diff);
        if(pq.size()>ladders)
            bricks-=pq.poll();
        if(bricks<0)
            return i;
    }
    return heights.length-1;
}


//1046
public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
        return b-a;
    });
    for(int s:stones)
        pq.add(s);
    
    while(pq.size()>1)
    {
        int s1=pq.poll(),s2=pq.poll();
        if(s1-s2!=0)pq.add(s1-s2);
    }
    return pq.size()==0?0:pq.peek();
}

//1647
public int minDeletions(String s) {        
//         HashMap<Character,Integer>map2=new HashMap<>();
//         HashMap<Integer,Integer>map1=new HashMap<>();
//         int max=0;
        
//         for(int i=0;i<s.length();i++)
//         {
//             char ch=s.charAt(i);
//             int freq=map2.getOrDefault(ch,0)+1;
//             map2.put(ch,freq);
//             max=Math.max(max,freq);
//         }
        
//         for(char ch:map2.keySet())
//         {
//             int freq=map2.get(ch);
//             map1.put(freq,map1.getOrDefault(freq,0)+1);
//         }
        
//         int remove=0;
//         for(int i=max;i>=1;i--)
//         {
//             if(map1.getOrDefault(i,0)>1)
//             {
//                 int count=map1.get(i)-1;
//                 remove+=count;
//                 map1.put(i-1,map1.getOrDefault(i-1,0)+count);
//             }
//         }
//         return remove;
        
        int []freq=new int[26];
        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            freq[ch-'a']++;
        }
        
        Set<Integer>hs=new HashSet<>();
        int remove=0;
        for(int count:freq)
        {
            while(count!=0 && hs.contains(count)) 
            {
                count--;
                remove++;
            }
            hs.add(count);
        }
        
        return remove;
}
 

