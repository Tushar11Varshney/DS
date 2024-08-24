package 7.HashMap-F&L;

// Anagram
// 1.Find Anagram(2-hmap,array)
// 2.Valid anagram(2-compare both map together, compare 2nd with 1st map)
// 3.K anagram
// 4.Anagram mapping
// 5.Group Anagram(2-hashmap inside hashmap, sorting)
// 6.Group shifted string
// 7.Word Pattern
// 8.Isomorphic String


public class set2 {
    
     //438
    public boolean compare(int []smap,int []pmap)
    {
        for(int i=0;i<26;i++)
        {
            if(pmap[i]!=smap[i])
            return false;
        }
        
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {  
        ArrayList<Integer>result=new ArrayList<>();   //refrence arraylist ya list
                
        if(s.length()<p.length())return result;
        
        int []pmap=new int[26];
        for(int i=0;i<p.length();i++)
        {
            char ch=p.charAt(i);
            pmap[ch-'a']++;
        }
        
        int []smap=new int[26];
        for(int i=0;i<p.length();i++)
        {
            char ch=s.charAt(i);
            smap[ch-'a']++;
        }
        
        for(int i=p.length();i<s.length();i++)
        {
            boolean compareAns=compare(smap,pmap);
            if(compareAns)
                result.add(i-p.length());
            
            char ch=s.charAt(i-p.length());
            smap[ch-'a']--;
            
            ch=s.charAt(i);
            smap[ch-'a']++;
        }
        
        if(compare(smap,pmap))
            result.add(s.length()-p.length());
        
        return result;
    }

    public static boolean compare(HashMap<Character,Integer>smap,HashMap<Character,Integer>pmap)
    {
        //if ch not present in map return null
        for(char ch:smap.keySet())  //ya p bcz ek tym pr dono mein p jitne hi character hain
        {
            if(smap.get(ch)!=pmap.get(ch))
            return false;
        }   
        return true;
    }

    public static void findAnagrams(String s, String p) {
         
         HashMap<Character,Integer>pmap=new HashMap<>();
         for(int i=0;i<p.length();i++)
         {
             char ch=p.charAt(i);
             pmap.put(ch,pmap.getOrDefault(ch,0)+1);
         }
         
         HashMap<Character,Integer>smap=new HashMap<>();
         for(int i=0;i<p.length();i++)
         {
             char ch=s.charAt(i);
             smap.put(ch,smap.getOrDefault(ch,0)+1);
         }
         
         int count=0;
         String str="";
         for(int i=p.length();i<s.length();i++)
         {
             if(compare(smap,pmap))
             {
                 count++;
                 str+=i-p.length()+" ";
             }
             
             char ch=s.charAt(i-p.length());
             if(smap.get(ch)==1)
             smap.remove(ch);
             else
             smap.put(ch,smap.get(ch)-1);
             
             ch=s.charAt(i);
             smap.put(ch,smap.getOrDefault(ch,0)+1);
         }
         
         if(compare(smap,pmap))
         {
             count++;
             str+=s.length()-p.length()+" ";
         }
         
         System.out.println(count);
         System.out.println(str);
    }
    
    public static boolean validAnagrams(String s, String p){
        if(s.length()!=p.length())return false;
        
        HashMap<Character,Integer>pmap=new HashMap<>();
        for(int i=0;i<p.length();i++)
        {
            char ch=p.charAt(i);
            pmap.put(ch,pmap.getOrDefault(ch,0)+1);
        }
        
        // HashMap<Character,Integer>smap=new HashMap<>();
        // for(int i=0;i<s.length();i++)
        // {
        //     char ch=s.charAt(i);
        //     smap.put(ch,smap.getOrDefault(ch,0)+1);
        // }
        
        // return compare(smap,pmap);

        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(pmap.containsKey(ch)==false)
            return false;
            else if(pmap.get(ch)==1)
            pmap.remove(ch);
            else
            pmap.put(ch,pmap.get(ch)-1);
        }    

        return true;
    }

    //1347
    public static boolean areKAnagrams(String str1, String str2, int k) {
        if(str1.length()!=str2.length())   //sirf replace krna allowed hai not add insert remove
        return false;
        
        HashMap<Character,Integer>map=new HashMap<>();
        for(int i=0;i<str1.length();i++)
        {
            char ch=str1.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }    
        
        for(int i=0;i<str2.length();i++)
        {
            char ch=str2.charAt(i);
            if(map.containsKey(ch))
            {
                if(map.get(ch)==1)
                map.remove(ch);
                else
                map.put(ch,map.get(ch)-1);
            }
        }
        
        int count=0;
        for(char ch:map.keySet())
        count+=map.get(ch);
        
        if(count<=k)return true;
        else return false;
    }
 
    public static int[] anagramMappings(int[] arr1, int[] arr2) {
        HashMap<Integer,ArrayList<Integer>>map=new HashMap<>();
        
        for(int i=0;i<arr2.length;i++)
        {
            if(map.containsKey(arr2[i]))
                map.get(arr2[i]).add(i);
            else{
                map.put(arr2[i],new ArrayList<>());
                map.get(arr2[i]).add(i);
            }
        }
        
        int []result=new int[arr1.length];
        for(int i=0;i<arr1.length;i++)
        {
            result[i]=map.get(arr1[i]).get(0);
            map.get(arr1[i]).remove(0);
            if(map.get(arr1[i]).size()==0)
                map.remove(arr1[i]);
        }
        return result;
    }

    public static ArrayList<ArrayList<String>> groupAnagrams(String[] strs) {
        HashMap<HashMap<Character,Integer>,ArrayList<String>>bigMap=new HashMap<>();
        
        for(int i=0;i<strs.length;i++)
        {
            String str=strs[i];
            HashMap<Character,Integer>fmap=new HashMap<>();
            for(int j=0;j<str.length();j++)
            {
                char ch=str.charAt(j);
                fmap.put(ch,fmap.getOrDefault(ch,0)+1); 
            }
            
            if(bigMap.containsKey(fmap)==false)
            {
                bigMap.put(fmap,new ArrayList<>());
                bigMap.get(fmap).add(str);
            }
            else{
                bigMap.get(fmap).add(str);
            }
        }
        
        ArrayList<ArrayList<String>>result=new ArrayList<>();
        for(HashMap<Character,Integer>map:bigMap.keySet())
            result.add(bigMap.get(map));
        
        return result;
    }

    // 49
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArr = str.toCharArray(); 
            Arrays.sort(charArr);                   
            String s = new String(charArr);
            map.putIfAbsent(s, new ArrayList<>());          
            map.get(s).add(str);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String str : map.keySet()) {
            ans.add(map.get(str));
        }

        return ans;
    }
 
    public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) {
        HashMap<String,ArrayList<String>>map=new HashMap<>();
        for(int i=0;i<strs.length;i++)
        {
            String str=strs[i];
            String key="";
            for(int j=0;j<str.length()-1;j++)
            {
                int val=str.charAt(j+1)-str.charAt(j);
                if(val<0)val+=26;
                key+=val+"#";
            }
            
            if(map.containsKey(key)==false)
                map.put(key,new ArrayList<>());
            
            map.get(key).add(str);
        }
        
        ArrayList<ArrayList<String>>result=new ArrayList<>();
        for(String str:map.keySet())
        {
            result.add(map.get(str));
        }
        return result;
    }
 
    //290
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character,String>map=new HashMap<>();
        HashSet<String>hs=new HashSet<>();
        String[] words= str.split(" ");
        
        if(pattern.length()!=words.length)return false;
        for(int i=0;i<pattern.length();i++)
        {
            String word=words[i];
            char letter=pattern.charAt(i);
            if(map.containsKey(letter) && (map.get(letter)).equals(word))continue;
            else if(hs.contains(word))return false;
            else if(map.containsKey(letter))return false;
            else {
                map.put(letter,word);
                hs.add(word);
            }    
        }
        return true;
    }
 
    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character>map=new HashMap<>();
        HashSet<Character>hs=new HashSet<>();
        
        if(s.length()!=t.length())return false;
        for(int i=0;i<s.length();i++)
        {
            char key=s.charAt(i);
            char value=t.charAt(i);
            
            if(map.containsKey(key) && (map.get(key)).equals(value))continue;
            else if(hs.contains(value))return false;
            else if(map.containsKey(key))return false;
            else {
                map.put(key,value);
                hs.add(value);
            }    
        }
        return true;
    }

    
}
