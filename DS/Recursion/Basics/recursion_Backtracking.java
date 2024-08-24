import java.util.*;

public class recursion_Backtracking {

static int counter = 1;
public static void friendPairing(int i, int n, boolean[] used, String asf) {
    if (i > n) {
        System.out.println(counter + "." + asf);
        counter++;
        return;
    }

    if (used[i] == true)
        friendPairing(i + 1, n, used, asf);  //pass as it is
    else {
        used[i] = true; // node pre
        //mein akela rhunga
        friendPairing(i + 1, n, used, asf + "(" + i + ") "); // ye bhi ek edge hai but iska edge pre edge post node pre node post ke saath merge hogya.

        //mein dosto ke saath rhunga
        for (int j = i + 1; j <= n; j++) {
            if (used[j] == false) {
                used[j] = true; // edge pre
                friendPairing(i + 1, n, used, asf + "(" + i + "," + j + ") ");  //call repr edges and paramter repr nodes(here i is node)
                used[j] = false; // edge post
            }
        }
        used[i] = false; // node post
    }
}

public static void abreviation(String str, String asf, int count, int pos) {
    if (pos == str.length()) {
        if (count == 0) System.out.println(asf);
        else System.out.println(asf + count);
        return;
    }

    if (count == 0) // aane waali call
        abreviation(str, asf + str.charAt(pos), 0, pos + 1);
    else abreviation(str, asf + count + str.charAt(pos), 0, pos + 1);

    abreviation(str, asf, count + 1, pos + 1); // na aane waali call
}

static int max = 0;
public  static int getMaxGold(int[][] arr,int [][]dirn,int r,int c,int []sum){
    sum[0]+=arr[r][c];
    arr[r][c]=-1;
    for(int i=0;i<dirn.length;i++)
    {
        int x=r+dirn[i][0];
        int y=c+dirn[i][1];
        
        if(x>=0 && y>=0 && x<arr.length && y<arr[0].length && arr[x][y]!=-1 && arr[x][y]!=0)
            getMaxGold(arr,dirn,x,y,sum);
    }
    return sum[0];
}

public static int getMaximumGold(int[][] arr) {
    int [][]dirn={{1,0},{0,1},{-1,0},{0,-1}};   
    for(int i=0;i<arr.length;i++)
    {
        for(int j=0;j<arr[0].length;j++)
        {
            if(arr[i][j]!=0 && arr[i][j]!=-1)
            {
                int []sum=new int[1];
                getMaxGold(arr,dirn,i,j,sum);
                // int sum=0;
                // for(int ele:list)
                //     sum+=ele;
                max=Math.max(max,sum[0]);
            }
        }
    }
    return max;
}

// 1255
public static int maxScore(String[] words, int[] farr, int[] score, int idx) { 
    if (idx == words.length)
        return 0;

    int notIncluded = 0 + maxScore(words, farr, score, idx + 1);

    int flag = 0, wScore = 0, included = 0;
    String w = words[idx];
    for (int i = 0; i < w.length(); i++) {
        char ch = w.charAt(i);
        farr[ch - 'a']--;
        if (farr[ch - 'a'] < 0)
            flag = 1;

        wScore += score[ch - 'a'];
    }

    if (flag == 0)
        included = wScore + maxScore(words, farr, score, idx + 1);
    for (int i = 0; i < w.length(); i++) {  //undo changes in freq array
        char ch = w.charAt(i);
        farr[ch - 'a']++;
    }
    return Math.max(notIncluded, included);
}

public int maxScoreWords(String[] words, char[] letters, int[] score) {
    if (words == null || words.length == 0 || letters == null || letters.length == 0 || score == null|| score.length == 0) {
        return 0;
    }

    int[] farr = new int[26];
    for (char ch : letters) {
        farr[ch - 'a']++;
    }

    // no need to make a different array for words we cant use ...for eg in this we
    // cant use cat but we can manage it in recursion.
    // no need to calculate score in different array calculate it at time when we
    // will be checking if word can be used or not.
    return maxScore(words, farr, score, 0);
}

// cs:current spot
//https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/recursion-and-backtracking/all-palindromic-permutations-official/ojquestion
public static void generatePalindromicPermutation(int cs, int ts, HashMap<Character, Integer> fmap, Character oddc,String asf) {
    if (cs > ts) {
        String rev = "";
        for (int j = asf.length() - 1; j >= 0; j--) {
            rev += asf.charAt(j);
        }

        if (oddc != null) {
            asf += oddc;
        }

        System.out.println(asf + rev);
        return;
    }

    for (char ch : fmap.keySet()) {
        int freq = fmap.get(ch);
        if (freq > 0) {
            fmap.put(ch, freq - 1);
            generatePalindromicPermutation(cs + 1, ts, fmap, oddc, asf + ch);
            fmap.put(ch, freq);
        }
    }

    // HashMap<Character, Integer> fmap = new HashMap<>();
    // for (int i = 0; i < str.length(); i++) {
    // char ch = str.charAt(i);
    // fmap.put(ch, fmap.getOrDefault(ch, 0) + 1);
    // }

    // int oddCount=0,len=0;
    // Character oddc=null;
    // for(char ch:fmap.keySet())
    // {
        // if(fmap.get(ch)%2!=0)
        // {
        // oddCount++;
        // oddc=ch;
        // }
        // if(oddCount>1)
        // {
        // System.out.println(-1);
        // return;
        // }

        // fmap.put(ch,fmap.get(ch)/2);
        // len+=fmap.get(ch);
    // }
    // System.out.println(len);
    // generatePalindromicPermutation(1,len,fmap,oddc,"");
}

public static void patternMatching(String str, String pattern, HashMap<Character, String> map, String op) {
    if (pattern.length() == 0) {
        if (str.length() == 0) {
            HashSet<Character> alreadyPrintedCharacter = new HashSet<>();
            for (int i = 0; i < op.length(); i++) {
                char ch = op.charAt(i);
                if (alreadyPrintedCharacter.contains(ch) == false) {
                    String mapping = map.get(ch);
                    System.out.print(ch + " -> " + mapping + ", ");
                    alreadyPrintedCharacter.add(ch);
                }
            }
            System.out.println(".");
        }
        return;
    }

    char ch = pattern.charAt(0);
    String rop = pattern.substring(1);

    if (map.containsKey(ch)) {
        String previousMapping = map.get(ch);

        if (str.length() >= previousMapping.length()) {
            String left = str.substring(0, previousMapping.length());
            if (!left.equals(previousMapping))
                return;

            String right = str.substring(previousMapping.length());
            patternMatching(right, rop, map, op);
        }
    } else {
        for (int i = 0; i < str.length(); i++) {
            String left = str.substring(0, i + 1);
            String right = str.substring(i + 1);

            map.put(ch, left);
            patternMatching(right, rop, map, op);
            map.remove(ch);
        }
    }
}

