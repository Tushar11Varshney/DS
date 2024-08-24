// Longest consecutive seq(2)
// Check arithmetic sequence 
// number of employee under every manager
// first uniqur char
// Sum of unique elements
// task completion
// Pair with sum in sorted matrix(2-BS, HASHMAP)
// Insert Delete GetRandom O(1)(only 1 ele inserted and removed)
// freq stack
// median priority queue
// copy node with random ptr
// Top k frequent words(I-List of priority queue, II-In 1 pq teach how to emit when same frequency then arrange hashmap by decreasing order using treeset then add in list)
// number of rabbit in forest
// trapping rain water in 2d matrix

// 1

//128
int longestConsecutive(vector<int> &nums)
{
    unordered_map<int, int> map;
    for (int ele : nums)
        map[ele] = 1;

    int len = 0;
    for (int ele : nums)
    {
        if (map.find(ele) == map.end())
            continue;

        int num = ele;
        int prev = ele - 1;
        int next = ele + 1;

        while (map.find(prev) != map.end())
        {
            map.erase(prev);
            prev--;
        }

        while (map.find(next) != map.end())
        {
            map.erase(next);
            next++;
        }

        map.erase(num);
        len = max(len, next - prev - 1);
    }

        return len;
    }

/*public static void longestConsecutiveSeq(int arr[], int n) throws Exception {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], true);
        }

        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i] - 1))
                map.put(arr[i], false);
        }

        int maxLcs = 0, sp = 0, ep = 0;
        for (int i : arr) {
            if (map.get(i) == true) {
                int j = i + 1;
                while (map.containsKey(j))
                    j = j + 1;
                if (j - i > maxLcs) {
                    maxLcs = j - i;
                    sp = i;
                    ep = j - 1;
                }
            }
        }

        for (int i = sp; i <= ep; i++)
            System.out.println(i);
} */

//1502 yc
public static boolean checkarithmetic_Seq(int[] arr) {
    if(arr.length<=1)return true;
    HashSet<Integer>hs=new HashSet<>();
    int min=arr[0],max=arr[0];
    for(int i=0;i<arr.length;i++)
    {
        min=Math.min(min,arr[i]);
        max=Math.max(max,arr[i]);
        
        hs.add(arr[i]);
    }

    int d=(max-min)/(arr.length-1);  //An=a+(n-1)d
    int term=min;
    for(int i=0;i<arr.length;i++)  //while(min<=max) fail in [0000]
    {
        if(!hs.contains(term))return false;
        term+=d;
    }

    return true;
}

public static int getSize(HashMap<String, HashSet<String>> tree, HashMap<String, String> map,
                HashMap<String, Integer> result, String key) {
        if (tree.containsKey(key) == false) {
            result.put(key, 0);
            return 1;
        }

        int sz = 0;
        for (String child : tree.get(key)) {
            sz += getSize(tree, map, result, child);
        }
        result.put(key, sz);
        return sz + 1;
}

//map is given 
public static void NumberofEmployeeUnderEveyManager(HashMap<String, HashSet<String>> tree, HashMap<String, String> map) {

    String ceo = "";
    for (String emp : map.keySet()) {
        String man = map.get(emp);
        if (emp.equals(man))
            ceo = man;
        else {
            if (tree.containsKey(man)) {
                tree.get(man).add(emp);
            } else {
                tree.put(man, new HashSet<>());
                tree.get(man).add(emp);
            }
        }
    }

    HashMap<String, Integer> result = new HashMap<>();
    getSize(tree, map, result, ceo);
    for (String emp : result.keySet()) {
        System.out.println(emp + " " + result.get(emp));
    }
}

//387
public int firstUniqChar(String s) {  //or do using array
    HashMap<Character,Integer>map=new HashMap<>();
    for(int i=0;i<s.length();i++)
    {
        char ch=s.charAt(i);
        map.put(ch,map.getOrDefault(ch,0)+1);
    }
        
    for(int i=0;i<s.length();i++) //cant travel on map,array
    {
        char ch=s.charAt(i);
        if(map.get(ch)==1)
            return i;
    }    
    
    return -1;
}

//1748
public int sumOfUnique(int[] nums) {
    HashMap<Integer,Integer>map=new HashMap<>();
    int sum=0;
    for(int i=0;i<nums.length;i++)
    {
        if(map.containsKey(nums[i]) && map.get(nums[i])>1)continue;
        else if(map.containsKey(nums[i]))
        {
            sum-=nums[i];
            map.put(nums[i],map.get(nums[i])+1);
        }
        else{
            sum+=nums[i];
            map.put(nums[i],1);
        }
    }
    return sum;
}


// arr->task completed by students who left
// n->total # of task
public static void completeTask(int n, int m, int[] arr) {
    HashSet<Integer>hs=new HashSet<>();
    for(int i=0;i<arr.length;i++)
    hs.add(arr[i]);

    int turn=0;
    String s1="",s2="";
    for(int i=1;i<=n;i++) 
    {
        if(hs.contains(i))continue;
        
        if(turn==0)
        s1+=i+" ";
        else
        s2+=i+" ";
        
        turn=(turn+1)%2;
    }

    System.out.println(s1);
    System.out.println(s2);
}

public static int firstIndex(int [][]num,int target)
{
    int n=num.length,idx=-1,si=0,ei=n*n-1;
    while(si<=ei)
    {
        int mid=(si+ei)/2;
        int r=mid/n,c=mid%n;
        if(num[r][c]==target)  
        {
            ei=mid-1;  //2d matrix cant do like mid-1>=0
            idx=mid;
        }
        else if(num[r][c]>target)
        {
            ei=mid-1;
        }
        else{
            si=mid+1;
        }
    }
    return idx;
}

public static int lastIndex(int [][]num,int target)
{
    int n=num.length,idx=-1,si=0,ei=n*n-1;
    while(si<=ei)
    {
        int mid=(si+ei)/2;
        int r=mid/n,c=mid%n;
        if(num[r][c]==target)
        {
            si=mid+1;
            idx=mid;
        }
        else if(num[r][c]>target)
        {
            ei=mid-1;
        }
        else{
            si=mid+1;
        }
    }
    return idx;
}

public static int pairWithSumIn2SortedMatrix_(int[][] num1, int[][] num2, int target) {
    int count=0;
    int n=num1.length;
    
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            int fv=firstIndex(num1,target-num2[i][j]);
            if(fv==-1)continue;
            
            int lv=lastIndex(num1,target-num2[i][j]);
            
            count+=lv-fv+1;
        }
    }     
    
    return count;
}

public static int pairWithSumIn2SortedMatrix(int[][] num1, int[][] num2, int target) {  //doesnt matter matrix sorted or not o(n^2)
    int count=0;
    int n=num1.length;
    HashMap<Integer,Integer>hmap=new HashMap<>();
    for(int i=0;i<n;i++)
    for(int j=0;j<n;j++)
    hmap.put(num1[i][j],hmap.getOrDefault(num1[i][j],0)+1);
    
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            if(hmap.containsKey(target-num2[i][j]))
                count+=hmap.get(target-num2[i][j]);   
        }
    }     
    return count;
}

// 380
// in question it is given that atleast one element will be there when getrandom
// will be called so we need not apply any exception when no element will be
// present.we need to store index because random fn generate value btw a range
// or 0to1 and we cannot know value present or not directly so we need to store
// index.
class RandomizedSet {
    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    Random rand;  

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, list.size()); // phle map.put
            list.add(val);

            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int cidx = map.get(val);
            int lastValue = list.get(list.size() - 1);
            list.set(cidx, lastValue);

            list.remove(list.size() - 1);
            map.put(lastValue, cidx); 
            // phle put dry run for given test case. ["RandomizedSet","remove","remove","insert","getRandom","remove","insert"] [[],[0],[0],[0],[],[0],[0]]
            map.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        int randomValue = rand.nextInt(list.size()); // 0-5 mein 5 exclusive 
        return list.get(randomValue);
    }
}

class RandomizedSet2 { // without list time increases
    HashMap<Integer, Integer> map;
    int count = 0;
    Random rand;

    public RandomizedSet2() {
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, count);
            count++;
            return true;
        }
        return false;
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int cidx = map.get(val);
            int lastIdx = map.size() - 1;
            for (int key : map.keySet()) {
                if (map.get(key) == lastIdx) {
                    map.put(key, cidx);
                    break;
                }
            }

            map.remove(val);
            count--;
            return true;
        }

        return false;
    }

    public int getRandom() {
        int randomValue = rand.nextInt(map.size()); //ya count
        for (int key : map.keySet()) {
            if (map.get(key) == randomValue)
                return key;
        }
        return -1;
    }
}


// 895
class FreqStack {
    HashMap<Integer, Integer> map; // number vs frequency
    ArrayList<Stack<Integer>> list;
    int maxFreq = 0;

    public FreqStack() {
        map = new HashMap<>();
        list = new ArrayList<>();

        list.add(new Stack<>()); // dummy stack
    }

    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        maxFreq = Math.max(maxFreq, map.get(x));

        if (maxFreq == list.size())
            list.add(new Stack<>());
        list.get(map.get(x)).add(x);
    }

    public int pop() {
        int value = list.get(maxFreq).pop();
        if (list.get(maxFreq).size() == 0) {
            list.remove(maxFreq);  //not compulsory
            maxFreq--;
        }
        map.put(value, map.get(value) - 1);
        if (map.get(value) == 0)   //not compulsory
            map.remove(value);
        return value;
    }
}

// 295
public static class MedianPriorityQueue { // median is middle number is ascending list.
    PriorityQueue<Integer> minPq = new PriorityQueue<>();
    PriorityQueue<Integer> maxPq = new PriorityQueue<>((a, b) -> {
        return b - a;
    });

    public void rebalance() {
        if (maxPq.size() - minPq.size() == 2)
            minPq.add(maxPq.remove());
        else if (maxPq.size() < minPq.size())
            maxPq.add(minPq.remove());
    }

    public void add(int num) {
        if (maxPq.size() == 0 || num <= maxPq.peek())
            maxPq.add(num);
        else
            minPq.add(num);

        rebalance();
    }

    public int remove() {
        int val = maxPq.remove();
        rebalance();
        return val;
    }

    public double findMedian() {

        if ((maxPq.size() + minPq.size()) % 2 == 0)
            return (maxPq.peek() + minPq.peek()) / 2.0;
        else
            return maxPq.peek();
    }

    public int size() {
        return maxPq.size() + minPq.size();
    }
}

// 138 with hashmap space
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public Node copyRandomList(Node head) {
    HashMap<Node, Node> map = new HashMap<>();
    Node curr = head;
    while (curr != null) {
        Node newNode = new Node(curr.val);
        map.put(curr, newNode);

        curr = curr.next;
    }

    curr = head;
    while (curr != null) {
        Node copyNode = map.get(curr);

        copyNode.next=curr.next!=null?map.get(curr.next):null;
        copyNode.random=curr.random!=null?map.get(curr.random):null;

        curr = curr.next;
    }

    return map.get(head);
}
 
//692
class pair{
    String str;
    int f;
    
    pair(String str,int f)
    {
        this.str=str;
        this.f=f;
    }
}

public List<String> topKFrequent(String[] words, int k) {
    PriorityQueue<pair>pq=new PriorityQueue<>((a,b)->{
        if(a.f==b.f)
        {
            int i=0,j=0;
            String as=a.str,bs=b.str;
            while(i<as.length() && j<bs.length())
            {
                if(as.charAt(i)<bs.charAt(j))return 1;
                else if(as.charAt(i)>bs.charAt(j))return -1;
                else {
                    i++;j++;
                }
            }
            if(as.length()<bs.length())return 1;  //aa aaa
            else return -1;
        }
        return a.f-b.f; 
    });
    
    List<String>ans=new ArrayList<>();
    HashMap<String,Integer>map=new HashMap<>();
    for(int i=0;i<words.length;i++)
        map.put(words[i],map.getOrDefault(words[i],0)+1);
    
    //top k freq acco to lexo order
    for(String str:map.keySet())
    {
        pq.add(new pair(str,map.get(str)));
        if(pq.size()>k)pq.poll();
    }
    
    //arrange freq in desc
    TreeMap<Integer,ArrayList<String>>smap=new TreeMap<>((a,b)->{
        return b-a;  
    });
    
    
    //freq->[str list]
    while(pq.size()!=0)
    {
        pair p=pq.poll();
        int f=p.f;
        if(!smap.containsKey(f))
            smap.put(f,new ArrayList<>());
        smap.get(f).add(p.str); // 1->[i,love]
    }
    
    for(Integer i:smap.keySet())
    {
        ArrayList<String>al=smap.get(i);
        while(al.size()!=0)
        ans.add(al.remove(al.size()-1));
    }

    return ans;
}

public List<String> topKFrequent(String[] words, int k) {
    List<PriorityQueue<String>>freq=new ArrayList<>();
    
    for(int i=0;i<words.length+1;i++)
        freq.add(new PriorityQueue<String>());
    
    List<String>ans=new ArrayList<>();
    HashMap<String,Integer>map=new HashMap<>();
    for(String str:words)
        map.put(str,map.getOrDefault(str,0)+1);
    
    for(String str:map.keySet())
    {
        int f=map.get(str);
        freq.get(f).add(str);
    }
    
    for(int i=freq.size()-1;i>=0;i--)
    {
        PriorityQueue<String> pq=freq.get(i);
        if(pq.size()!=0)
        {
            while(pq.size()!=0)
            {
                if(k==0)return ans;
                k--;
                ans.add(pq.poll());
            }
        }
    }
    return ans;
}
  
//884 - A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence. (not in copy)
public String[] uncommonFromSentences(String s1, String s2) {
    String []str1=s1.split(" ");
    String []str2=s2.split(" ");      

    HashSet<String>hs = new HashSet<>();
    for(String s: str1)
    {
        if(hs.contains(s))
        hs.remove(s);
        else
        hs.add(s);
    }

    HashSet<String>hs2 = new HashSet<>();
    for(String s: str2)
    {
        if(hs2.contains(s))
        hs2.remove(s);
        else
        hs2.add(s);
    }

    ArrayList<String>ans = new ArrayList<>();
    for(String s: str2)
    {
        if(hs.contains(s))
        {
            hs.remove(s);
            hs2.remove(s); //does not throw exception
        }
    }

    ans.addAll(hs);
    ans.addAll(hs2);
    String []res=new String[ans.size()];
    for(int i=0;i<ans.size();i++)
        res[i]=ans.get(i);
    
    return res;
}

//781
int numRabbits(vector<int> &answers)
{
    unordered_map<int, int> map;
    int count = 0, i = 0;
    while (i < answers.size())
    {
        // if(map[answers[i]]==0)
        if (map.find(answers[i]) == map.end())
        {
            map[answers[i]] = 1;
            count += answers[i] + 1;
        }
        else
            map[answers[i]]++;

        if (map[answers[i]] == answers[i] + 1)
            // map[answers[i]]=0;
            map.erase(answers[i]);
        i++;
    }
    return count;
} 

// 407
public int trapRainWater(int[][] heightMap) {

    if (heightMap.length == 0 || heightMap[0].length == 0)
        return 0;
    int n = heightMap.length;
    int m = heightMap[0].length;

    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
        return heightMap[a / m][a % m] - heightMap[b / m][b % m];
    }); 
    int[][] visited = new int[n][m];

    for (int i = 0; i < n; i++) { // boundary pr hum water store ni kra skte so we will shrink boundary and if boundary height is greater than beech mein jo building hai then we can store  water
        for (int j = 0; j < m; j++) {
            if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                pq.add(i * m + j);
                visited[i][j] = 1;
            }
        }
    }

    int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }; // ek building in chaar direction mein hi support deskti hai water fill krane kelei.
    int criticalHeight = 0; // critical height ka matlab mere aas pass jitni boundary hai un sabki min height.agar critical height 1 hai aur element jo pq se nikla hai wo bhi 1 hai then
    // critical height se upar aur brabar paani bhrna start kraa then paani beh jaayga saara.
    int totalwater = 0;
    while (pq.size() > 0) {
        int idx = pq.poll();
        int r = idx / m;
        int c = idx % m;

        if (criticalHeight > heightMap[r][c])
            totalwater += criticalHeight - heightMap[r][c];
        criticalHeight = Math.max(criticalHeight, heightMap[r][c]);
        for (int i = 0; i < dirs.length; i++) {
            int x = r + dirs[i][0];
            int y = c + dirs[i][1];

            if (x >= 0 && y >= 0 && x < n && y < m && visited[x][y] == 0) {
                pq.add(x * m + y);
                visited[x][y] = 1;
            }
        }
    }
    return totalwater;
}

