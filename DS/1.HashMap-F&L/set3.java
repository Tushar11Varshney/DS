package 7.HashMap-F&L;


// count substring unique character
// Max vowel in k length
// count distinct in k length
// longest string with k distinct
// Count_At_Most_KUnique_Characters / Longest_At_Most_KUnique_Characters
// count string with k distinct
// Longest Substring NonRepeating Character 
// Smallest substring containing all unique character(by array also) / smallest substring containing all unique character of another string(by array also)

// Largest subarray sum 0
// count subaaray sum 0
// count/len subarray equal # of 0,1,2
// largest subarray with contiguous elements
// Count of equivalent subarray 
// smallest subarray with highest freq element

// 1 

public class set3 {
    public static int Count_Substring_Unique_Characters(String str) {
        int si = 0, ei = 0, count = 0, n = str.length();
        HashMap<Character, Integer> map = new HashMap<>();
        while (ei < n) {
            char ch = str.charAt(ei);
            ei++;
            map.put(ch,map.getOrDefault(ch,0)+1);
            while (map.get(ch) > 1) {
                char ch_ = str.charAt(si);
                map.put(ch_, map.get(ch_) - 1);
                si++;
            }
            count += ei - si;
        }
        return count;
    }

    // leetcode 1456
    int isVowel(char a)
    {
        if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u')
            return 1;
        return 0;
    }

    int maxVowels(string s, int k)
    {
        int vowelCount = 0, maxVowelCount = 0, si = 0, ei = 0, n = s.length();
        while (ei < n)
        {
            if (isVowel(s[ei++]))
                vowelCount++;

            if (ei - si == k)
            {
                maxVowelCount = max(maxVowelCount, vowelCount);
                if (isVowel(s[si++]))
                    vowelCount--;
            }
        }
        return maxVowelCount;
    }

    int maxVowels2(string s, int k)
    {
        queue<char> que; 
        int vc = 0, mvc = 0, i = 0;
        while (i < k)
        {
            if (isVowel(s[i]))
                vc++;
            que.push(s[i++]);
        }
        mvc = vc;
        while (i < s.length())
        {
            char ch = que.front();
            que.pop();
            if (isVowel(ch))
                vc--;
            if (isVowel(s[i]))
                vc++;
            que.push(s[i++]);
            mvc = max(mvc, vc);
        }
        return mvc;
    }

    public static ArrayList<Integer> countDistinctinKLength(int[] arr, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        // int freqArr[]=new int[100000+1];  //1 <= arr[i] <= 10^4 this code works for +ve value
        int si = 0, ei = 0, distinctCount = 0;
        // while (ei - si < k) {
            // if(freqArr[arr[ei++]]++ == 0)
            // distinctCount++;
        // }
        // result.add(distinctCount);
    
        // while (ei < arr.length) {
            // if(freqArr[arr[si++]]-- == 1)
            // distinctCount--;
    
            // if(freqArr[arr[ei++]]++ == 0)
            // distinctCount++;
    
            // result.add(distinctCount);
        // }

        while (ei < arr.length) {    
            int eiVal = arr[ei++];
			if (map.containsKey(eiVal)) {
                map.put(eiVal, map.get(eiVal) + 1);
            } else {
                map.put(eiVal, 1);
                distinctCount++;
            }

			if(ei-si==k)
			{
				result.add(distinctCount);
				int siVal = arr[si++];
				map.put(siVal, map.get(siVal) - 1);
				if (map.get(siVal) == 0)
				{
					distinctCount--;
					map.remove(siVal);
				} 
			}
		}
        return result;
    }

    public static int lengthOfLongestSubstringwithKDistinct(String s,int k)
    {
        int []arr=new int[128];
        int si = 0, ei = 0, len = 0, n = s.length(), distinctCount = 0;
        while (ei < n)
        {
            char ch=s.charAt(ei);ei++;
            if (arr[ch]++ == 0)
                distinctCount++;

            while (distinctCount > k)
            {
                char ch_=s.charAt(si);si++;
                if (arr[ch_]-- == 1)
                    distinctCount--;
            }

            if(distinctCount==k)
            len=Math.max(len,ei-si);
        }
        return len;
    }

    //using hashmap
    public int longestkSubstr(String s, int k) {
        HashMap<Character, Integer>map=new HashMap<>();
        int len=-1, si=0, ei=0;
        while(ei<s.length())
        {
            char end_ch = s.charAt(ei);ei++;
            if(!map.containsKey(end_ch))
            {
                k--;
                map.put(end_ch,1);
            }
            else{
                map.put(end_ch,map.get(end_ch)+1);
            }
            
            while(k==-1)
            {   
                char first_ch = s.charAt(si);
                si++;
                
                if(map.get(first_ch)>1)
                    map.put(first_ch, map.get(first_ch)-1);
                else{
                    map.remove(first_ch);
                    k = k+1;
                }
            }
            
            if(k==0)
                len=Math.max(len,ei-si);
        }
        
        return len;
    }

    public static int Longest_At_Most_KUnique_Characters(String s, int k) {
        int si=0,ei=0,n=s.length(),len=0;
        HashMap<Character,Integer>map=new HashMap<>();
    
        while(ei<n)
        {
            char ch=s.charAt(ei);ei++;
            if(map.containsKey(ch))
                map.put(ch,map.get(ch)+1);
            else{
                map.put(ch,1);
                k--;
            }
            
            while(k<0)
            {
                char ch_=s.charAt(si);si++;
                if(map.get(ch_)==1)
                {
                    k++;
                    map.remove(ch_);
                }
                else    
                map.put(ch_,map.get(ch_)-1);
            }
            
            len=Math.max(len,ei-si);
        }
        return len;
    }
    
    public static int Count_At_Most_KUnique_Characters(String s, int k) {
        int si=0,ei=0,n=s.length(),count=0;
        HashMap<Character,Integer>map=new HashMap<>();

        while(ei<n)
        {
            char ch=s.charAt(ei);ei++;
            if(map.containsKey(ch))
                map.put(ch,map.get(ch)+1);
            else{
                map.put(ch,1);
                k--;
            }
            
            while(k<0)
            {
                char ch_=s.charAt(si);si++;
                if(map.get(ch_)==1)
                {
                    k++;
                    map.remove(ch_);
                }
                else    
                map.put(ch_,map.get(ch_)-1);
            }
            count+=ei-si;
        }
        return count;
    }
    
    public static int substringwithAtMostK(String str,int k)
    {
        int []map=new int[26];
        int si=0,ei=0,disCount=0,count=0,n=str.length();
        while(ei<n)
        {
            char ch1=str.charAt(ei++);
            if(map[ch1-'a']++ == 0)
                disCount++;
                
            while(disCount>k)
            {
                char ch2 = str.charAt(si++);
                if(map[ch2-'a']-- == 1)
                disCount--;
            }
            
            count+=ei-si;
        }
        
        return count;
    }

    public static int substringwithExactlyK(String str, int k){
        return substringwithAtMostK(str,k)-(k-1!=0?substringwithAtMostK(str,k-1):0);
    }

    public static int Longest_Substring_NonRepeatingCharacter(String s) {
        if (s.length() <= 1)
            return s.length();
        int si = 0, ei = 0, count = 0, len = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (ei < s.length()) {
            char ch = s.charAt(ei);
            ei++;
            if (map.containsKey(ch) && map.get(ch) > 0)
                count++;
            map.put(ch, map.getOrDefault(ch, 0) + 1);
    
            while (count > 0) {
                char ch_ = s.charAt(si);
                si++;
                if (map.get(ch_) > 1)
                    count--;
                map.put(ch_, map.get(ch_) - 1);
            }
    
            len = Math.max(len, ei - si);  //count+=ei-si
        }
        return len;
    }

    // 3
    int lengthOfLongestSubstring(string s) {
        if (s.length() <= 1)
        return s.length();
        int si = 0, ei = 0, len = 0, count=0, maxsi, maxei;  //default value 0 in maxsi
        vector<int> arr(128, 0);
        while (ei < s.length())
        {
            char ch=s[ei]; 
            if(arr[s[ei++]]++==1)
                count++;
            while(count>0)    //==1
            {
                if(arr[s[si++]]--==2)count--;
            }    

            if(ei - si > len)
            {
                len = ei - si;
                maxsi = si;
                maxei = ei;
            }
        }
        cout << s.substr(maxsi, len);  // [pos,pos+len) 
        return len;
    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
    
        int requirement = t.length(), si = 0, ei = 0, len = (int) 1e8, head = 0;
        while (ei < s.length()) {
            char ch = s.charAt(ei);
            ei++;
    
            if (map.getOrDefault(ch, 0) > 0)
                requirement--;
            map.put(ch, map.getOrDefault(ch, 0) - 1);
    
            while (requirement == 0) {
                if (len > ei - si) {
                    len = ei - si;
                    head = si;
                }
    
                char ch_ = s.charAt(si);
                si++;
                if (map.get(ch_) == 0)
                    requirement++;
                map.put(ch_, map.get(ch_) + 1);
            }
        }
        return len == (int) 1e8 ? "" : s.substring(head, head + len);
    }
    
    // leetcode 76
    string minWindow(string s, string t)
    {
        vector<int> arr(128, 0);
        for (int i = 0; i < t.length(); i++)
            arr[t[i]] += 1;
        int requirement = t.length(), si = 0, ei = 0, len = 1e8, head = 0;  
        while (ei < s.length())
        {
            if (arr[s[ei++]]-- > 0)
                requirement--;

            while (requirement == 0)
            {
                if (len >= ei - si)
                {
                    len = ei - si;
                    head = si;
                }

                if (arr[s[si++]]++ == 0)
                    requirement++;
            }
        }
        return len == 1e8 ? "" : s.substr(head, len);
    }

    public static int smallestWindow(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, 1);
        }
        int requirement = map.size(), si = 0, ei = 0, len = (int) 1e8;  
        while (ei < s.length()) {
            char ch = s.charAt(ei);
            ei++;
            if (map.getOrDefault(ch, 0) > 0)
                requirement--;
            map.put(ch, map.getOrDefault(ch, 0) - 1);
    
            while (requirement == 0) {
                if (len > ei - si) {
                    len = ei - si;
                }
    
                char ch_ = s.charAt(si);
                si++;
                if (map.get(ch_) == 0)
                    requirement++;
                map.put(ch_, map.get(ch_) + 1);
            }
        }
        return len;
    }

    // https://www.geeksforgeeks.org/smallest-window-contains-characters-string/
    // target string jo bnegi vo bnegi distinct character of given string and we have to find a smallest string jisme vo sab distinct ho.
    string smallestWindow(string s)
    {
        vector<int> arr(128, 0);
        for (int i = 0; i < s.length(); i++)
            arr[s[i]] = 1;
        int requirement = 0, si = 0, ei = 0, len = 1e8, head = 0;
        for (int ele : arr)
            requirement += ele;
        while (ei < s.length())
        {
            if (arr[s[ei++]]-- == 1)
                requirement--;

            while (requirement == 0)
            {
                if (len >= ei - si)
                {
                    len = ei - si;
                    head = si;
                }

                if (arr[s[si++]]++ == 0)
                    requirement++;
            }
        }
        return len == 1e8 ? "" : s.substr(head, len);
    }

    // ==========================
    public static int largestSubArraySumZero(int[] arr) {
        int max = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum))
                max = Math.max(max, i - map.get(sum));
            else
                map.put(sum, i);
        }
        return max;
    }
    
    public static int CountSubArraySumZero(int[] arr) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum))
                count += map.get(sum);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static int count_and_length_SubArray012(int[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
        int Zcount = 0, Ocount = 0, tCount = 0, count = 0;
    
        String str = (Ocount - Zcount) + "#" + (tCount - Ocount);
        map.put(str, 1);
        //map.put(str,-1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1)
                Ocount++;
            else if (arr[i] == 2)
                tCount++;
            else
                Zcount++;
    
            str = (Ocount - Zcount) + "#" + (tCount - Ocount);
            if (map.containsKey(str))
                count += map.get(str);
            map.put(str, map.getOrDefault(str, 0) + 1);
    
            //if(map.containsKey(str))      //for length
            //len=Math.max(len,i-map.get(str));
            //else map.put(str,i);
        }
    
        return count;
    }

    public static int Largest_Subarray_Contiguous_Elements(int[] arr) {
        int len = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            int max = arr[i], min = arr[i];
            HashSet<Integer> hs = new HashSet<>();
            hs.add(arr[i]);
            for (int j = i + 1; j < arr.length; j++) {
                if (hs.contains(arr[j]))
                    break;
                else
                    hs.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                
                if(max-min+1>arr.length)break;  //[7 8 12 6 9] max=12,min=7 6>5
                if (max - min == j - i)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    public static int CountofEqSubArray(int[] arr) {
        // count of unique integers in the subarray = count of unique integers in the given array.
        HashSet<Integer> hs = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int si = 0, ei = 0, count = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            hs.add(arr[i]);
        }
    
        while (ei < n) {
            map.put(arr[ei], map.getOrDefault(arr[ei], 0) + 1);
            ei++;
    
            while (map.size() == hs.size()) {
                count += n - ei + 1;
                if (map.get(arr[si]) == 1)
                    map.remove(arr[si]);
                else
                    map.put(arr[si], map.get(arr[si]) - 1);
                si++;
            }
        }
        return count;
    }
    
    public static void smallestSubArrayWithMostFreqElement(int[] arr) {
        
        HashMap<Integer,Integer>Freqmap=new HashMap<>();
        HashMap<Integer,Integer>indexMap=new HashMap<>();
        
        int hf=1,sp=0,ep=0,len=1,element=arr[0];
        Freqmap.put(arr[0],1);
        indexMap.put(arr[0],0);
        
        for(int i=1;i<arr.length;i++)
        {
            int f=Freqmap.getOrDefault(arr[i],0)+1;
            if(f==1)
            {
                Freqmap.put(arr[i],1);
                indexMap.put(arr[i],i);
            }
            else{
                Freqmap.put(arr[i],f);
                
                if(f>hf)
                {
                    element=arr[i];
                    hf=f;
                    sp=indexMap.get(arr[i]);
                    ep=i;
                    len=ep-sp+1;
                }
                else if(f==hf)
                {
                    if(i-indexMap.get(arr[i])+1<len)
                    {
                        element=arr[i];
                        sp=indexMap.get(arr[i]);
                        ep=i;
                        len=ep-sp+1;
                    }
                }
                
            }
        }
        
        System.out.println(element);
        System.out.println(sp);
        System.out.println(ep);
    }

    //1658
    int minOperations(vector<int>& nums, int x) {
        
        unordered_map<int,int>map;
        int sum=0,target,len=-1;
        for(int ele:nums)
            sum+=ele;
        target=sum-x;
        
        if(target<0)return len;
        if(target==0)return nums.size();
        map[0]=-1,sum=0;
        for(int i=0;i<nums.size();i++)
        {
            sum+=nums[i];
            if(map.find(sum-target)!=map.end())
                len=max(len,i-map[sum-target]);
            map[sum]=i;
        }
        
        return len==-1?-1:nums.size()-len; 
    }
}
