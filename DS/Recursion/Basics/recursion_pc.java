import java.util.*;

public class recursion_pc {
    //base case-jo choose //loop-dusra wala
    public static void permutations(int[] visited, int ci, int ti) { // 1 waala pepcoding pr..visited boxes length brabr
        if (ci == ti) { // ci:current item ti:total item  //item chooses
            for (int i = 0; i < visited.length; i++) {
                System.out.print(visited[i]);
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                visited[i] = ci + 1;
                permutations(visited, ci + 1, ti);
                visited[i] = 0;
            }
        }
    }

    public static void KLengthWord2(int k, String allDistinct, int ssf, int[] visited, String asf) {
        if (ssf == k) {  //spot chooses
            System.out.println(asf);
            return;
        }

        for (int i = 0; i < allDistinct.length(); i++) {
            char ch = allDistinct.charAt(i);
            if (visited[ch - 'a'] == 0) {
                visited[ch - 'a'] = 1;
                KLengthWord2(k, allDistinct, ssf + 1, visited, asf + ch);
                visited[ch - 'a'] = 0;
            }
        }
        // int []visited=new int[26];
    }

    public static void combinations(int cb, int tb, int ssf, int ts, String asf) { // combination 1-box chooses
        if (cb > tb) { // cb:current box tb:total box
            if (ssf == ts) { // ssf:selection so far ts:total selection
                System.out.println(asf);
            }
            return;
        }

        combinations(cb + 1, tb, ssf + 1, ts, asf + "i");
        combinations(cb + 1, tb, ssf, ts, asf + "-");
    }

    public static void kSelectionI(int select, String allDistinct, int k, String asf, int idx) {
        if (idx == allDistinct.length()) {
            if (select == k) {
                System.out.println(asf);
            }
            return;
        }

        kSelectionI(select + 1, allDistinct, k, asf + allDistinct.charAt(idx), idx + 1);
        kSelectionI(select, allDistinct, k, asf, idx + 1);

        // HashSet<Character> unique = new HashSet<>();
        // String allDistinct = "";
        // for(char ch : str.toCharArray()){
            // if(unique.contains(ch)== false){
            // unique.add(ch);
            // allDistinct+=ch;
            // }
        // }

        // kSelectionI(0,allDistinct,k,"",0);
    }

    public static void permutations(int cb, int tb, int[] visited, String ans, int ci, int ti) { // 2 waala pepcoding pr
        if (cb > tb) {  //box choose
            if (ci == ti) {
                System.out.println(ans);
            }
            return;
        }

        for (int i = 0; i < ti; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                permutations(cb + 1, tb, visited, ans + (i + 1), ci + 1, ti);
                visited[i] = 0;
            }
        }

        permutations(cb + 1, tb, visited, ans + 0, ci, ti);
    }

    public static void KLengthWord(int k, String allDistinct, int ssf, Character[] spots, int character) {
        if (character == allDistinct.length()) {  //character chooses
            if (ssf == k) {
                for (int i = 0; i < spots.length; i++) {
                    System.out.print(spots[i]);
                }
                System.out.println();
            }
            return;
        }

        char ch = allDistinct.charAt(character);
        for (int i = 0; i < spots.length; i++) {
            if (spots[i] == null) {
                spots[i] = ch;
                KLengthWord(k, allDistinct, ssf + 1, spots, character + 1);
                spots[i] = null;
            }
        }

        KLengthWord(k, allDistinct, ssf, spots, character + 1);
        // Character spots[]=new Character[k];
    }

    public static void combinations2(int[] boxes, int ci, int ti, int lb) {  //item chooses
        if (ci > ti) {
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i] == 1)System.out.print("i");
                else  System.out.print("-");
            }
            System.out.println();
            return;
        }

        for (int i = lb + 1; i < boxes.length; i++) {
            boxes[i] = 1;
            combinations2(boxes, ci + 1, ti, i);
            boxes[i] = 0;
        }
    }

    public static void kSelectionII(int select, String allDistinct, int k, String asf, int idx) {
        if (select == k) {
            System.out.println(asf);
            return;
        }

        for (int i = idx + 1; i < allDistinct.length(); i++) {
            kSelectionII(select + 1, allDistinct, k, asf + allDistinct.charAt(i), i);
        }
    }


    public static int queenPermutation1d(boolean[] visited, int totalQueen, int queenPlaced, String ans, int size) {
        if (queenPlaced == totalQueen) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (visited[i] == false) {
                visited[i] = true; // kyunki kahi aesa na ho pehle hi box pr saari queen baith jaaye
                count += queenPermutation1d(visited, totalQueen, queenPlaced + 1,ans + "b" + i + "q" + queenPlaced + " ", size);
                visited[i] = false;
            }
        }
        return count;
    }

    public static int queenPermutation2d(boolean[][] visited, int totalQueen, int queenPlaced, String ans, int m,int n) {
            if (queenPlaced == totalQueen) // can also be totalQueen==0 if in argument totalQueen-1 is passed so no need for queenPlaced
            {
                System.out.println(ans);
                return 1;
            }
            int count = 0;
            for (int i = 0; i < m * n; i++) {
                int r = i / n;
                int c = i % m;
                {
                    if (visited[r][c] == false) {
                        visited[r][c] = true;
                        count += queenPermutation2d(visited, totalQueen, queenPlaced + 1, ans + "(" + r + "," + c + ")", m,
                                n);
                        visited[r][c] = false;
                    }
                }
            }
            return count;
        }
    }

    public static int queenCombination1d(int idx, int totalQueen, int queenPlaced, String ans, int size) {
        if (queenPlaced == totalQueen) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = idx; i < size; i++)
            count += queenCombination1d(i + 1, totalQueen, queenPlaced + 1, ans + "b" + i + "q" + queenPlaced + " ",size);
        return count;
    }

    public static int queenCombination2d(int idx, int totalQueen, int queenPlaced, String ans, int m, int n) {
        if (queenPlaced == totalQueen) {
            System.out.println(ans);
            // make char array of chess and print it or if bool array then--
            // if(chess[i][j]==true) System.out.pr/int("q\t");
            // else System.out.print("-\t");

            return 1;
        }
        int count = 0;
        for (int i = idx; i < m * n; i++) {
            int r = i / m;
            int c = i % m;
            count += queenCombination2d(i + 1, totalQueen, queenPlaced + 1, ans + "(" + r + "," + c + ")", m, n);
            // chess[r][c] = 'q';
            // // chess[r][c]=true;
            // count += queenCombination2d(chess, i + 1, totalQueen, queenPlaced + 1, n);
            // chess[r][c] = '-';
            // // chess[r][c]=false;
        }
        return count;
    }

    //ib
    public static void subSeq(String str, String psf, int idx) {    
    if (idx == str.length()) {
        System.out.println(psf);
        return;
    }

    System.out.println(psf);
    for (int i = idx; i < str.length(); i++) {
        // System.out.println(psf); //ye yahan ni hoskta because jb loop kisi call kelie khtm hoga and next kelie chlega then it will print usse pehla waala ans also

        // System.out.println(psf+str.charAt(i)); //just return in base case else jab jab base case hit print twice also print empty before
        subSeq(str, psf + str.charAt(i), i + 1);
    }
}

    // Syntax-ArrayList<Integer> coins = new ArrayList<>(Arrays.asList(2, 3, 5, 7)); 
    public static int permutationInfiniteCoins(int target, ArrayList<Integer> coins, String arrangement) {
        if (target == 0) {
            System.out.println(arrangement);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < coins.size(); i++) {
            if (target - coins.get(i) >= 0) {
                count += permutationInfiniteCoins(target - coins.get(i), coins, arrangement + coins.get(i));
            }
        }
        return count;
    }

    // yhan pr idx == coins.size() dena pdega because wahan loop chlrha tha tou jb idx=size brabar hota tha tou loop apne aap bnd hojaata tha yhaan koi loop nhi hai.
    public static int permutationInfineCoinsSubseq(ArrayList<Integer> coins, int idx, int target, String arrangement) {
    if (target == 0 || idx == coins.size()) {
        if (idx == coins.size())
            return 0;
        System.out.println(arrangement);
        return 1;
    }

    int count = 0;
    if (target - coins.get(idx) >= 0)
        count += permutationInfineCoinsSubseq(coins, 0, target - coins.get(idx), arrangement + coins.get(idx));
    count += permutationInfineCoinsSubseq(coins, idx + 1, target, arrangement);
    return count;
}

    // Permutation mein we are allowed to go back so i in loop start form 0(no need of idx) and pass substring and if we dont want to pass substring then pass visited.
    public static int printPermutations(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            String restofString = str.substring(0, i) + str.substring(i + 1);
            count += printPermutations(restofString, ans + str.charAt(i));
        }
        return count;
    }

    public static int printPermutationsSubstringKaatnaNotAllowed(String str, String ans, boolean[] visited) {
        if (ans.length() == str.length()) {   //character chooses
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (visited[i] == false) {
                visited[i] = true;
                count += printPermutationsSubstringKaatnaNotAllowed(str, ans + str.charAt(i), visited);
                visited[i] = false;
            }
        }
        return count;
    }

    // 46
    List<List<Integer>> result=new ArrayList<>();
    List<Integer> smallAns=new ArrayList<>();
    public void perMutation(int[] nums, int count, int[] visited) {
        if (count == nums.length) {   //number chooses
            List<Integer> base = new ArrayList<>(smallAns);
            result.add(base);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                smallAns.add(nums[i]);
                perMutation(nums, count + 1, visited);
                smallAns.remove(smallAns.size() - 1);
                visited[i] = 0;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        int[] visited = new int[nums.length];
        perMutation(nums, 0, visited);
        return result;
    } 

    public static int permutationSingleCoins(int target, ArrayList<Integer> coins, String arrangement, int visited[]) {
        if (target == 0) {
            System.out.println(arrangement);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < coins.size(); i++) {
            if (target - coins.get(i) >= 0 && visited[i] == 0) {
                visited[i] = 1;
                count += permutationSingleCoins(target - coins.get(i), coins, arrangement + coins.get(i), visited);
                visited[i] = 0;
            }
        }
        return count;
    }

    public static int permutationSingleCoinsWithoutVisited(int target, ArrayList<Integer> coins, String arrangement) {
        if (target == 0) {
            System.out.println(arrangement);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < coins.size(); i++) {
            int element = coins.get(i);
            if (target - element >= 0 && element > 0) {
                coins.set(i, -element);
                count += permutationSingleCoinsWithoutVisited(target - element, coins, arrangement + element);
                coins.set(i, element);
            }
        }
        return count;
    }

    public static int permutationSingleCoinsSubseq(ArrayList<Integer> coins, int idx, int target, String arrangement) {
        if (target == 0 || idx == coins.size()) {
            if (idx == coins.size())
                return 0;
            System.out.println(arrangement);
            return 1;
        }

        int count = 0;
        int element = coins.get(idx);
        if (target - element >= 0 && element > 0) {
            coins.set(idx, -element);
            count += permutationSingleCoinsSubseq(coins, 0, target - element, arrangement + element);
            coins.set(idx, element);
        }
        count += permutationSingleCoinsSubseq(coins, idx + 1, target, arrangement);
        return count;
    }

    public static int printPermutationsUnique(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        boolean visited[] = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (visited[ch - 'a'] == false) {
                visited[ch - 'a'] = true;
                String restofString = str.substring(0, i) + str.substring(i + 1);
                count += printPermutationsUnique(restofString, ans + ch);
                // visited[ch - 'a'] = false; //false ni krna level mein
            }
        }
        return count;
    }

    public static int printPermutationsUniqueSubstringKatnaNotAllowed(String str, String ans, boolean[] visited) {
        if (str.length() == ans.length()) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        boolean levelvisited[] = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (levelvisited[ch - 'a'] == false && visited[i] == false) {
                levelvisited[ch - 'a'] = true;
                visited[i] = true;
                count += printPermutationsUniqueSubstringKatnaNotAllowed(str, ans + ch, visited);
                visited[i] = false;
            }
        }
        return count;
    }

    public static int printPermutationsUniqueSubstringKatnaNotAllowed2(String str, String ans, boolean[] visited) {
        if (str.length() == ans.length()) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        int prev = -1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (prev != -1 && str.charAt(prev) == str.charAt(i))
                continue;
            if (visited[i] == false) {
                visited[i] = true;
                count += printPermutationsUniqueSubstringKatnaNotAllowed2(str, ans + ch, visited);
                visited[i] = false;
                prev = i;
            }
        }
        return count;
    }

    // 47
    public void perMuteUnique(int[] nums, int count, int[] visited) {
        if (count == nums.length) {
            List<Integer> base = new ArrayList<>(smallAns);
            result.add(base);
            return;
        }
        int visitedLevel[] = new int[21]; // see range in qs
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 0 && visitedLevel[nums[i] + 10] == 0) {
                visited[i] = 1;
                visitedLevel[nums[i] + 10] = 1;
                smallAns.add(nums[i]);
                perMuteUnique(nums, count + 1, visited);
                smallAns.remove(smallAns.size() - 1);
                visited[i] = 0;
            }
        }
    }

    public void perMuteUnique2(int[] nums, int count, int[] visited) {
        if (count == nums.length) {
            List<Integer> base = new ArrayList<>(smallAns);
            result.add(base);
            return;
        }
        int prev = -1;
        for (int i = 0; i < nums.length; i++) {
            if (prev != -1 && nums[prev] == nums[i])
                continue;
            if (visited[i] == 0) {
                visited[i] = 1;
                prev = i; // previous bahaar update nhi hoga because if it is updated bahaar and suppose question is aab then in first call when visited[0]=true then it will update prev and second a will not get a chance to make a call.
                smallAns.add(nums[i]);
                perMuteUnique2(nums, count + 1, visited);
                smallAns.remove(smallAns.size() - 1);
                visited[i] = 0;
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        // int []visited=new int[nums.length];
        // perMuteUnique(nums,0,visited);
        // return result;

        Arrays.sort(nums);
        int[] visited = new int[nums.length];
        perMuteUnique2(nums, 0, visited);
        return result;
    }

    // mistake--yahan pr call lgate tym i pass krio
    public static int combinationInfiniteCoins(int target, int idx, ArrayList<Integer> coins, String combination) {
        if (target == 0) { 
            System.out.println(combination);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < coins.size(); i++) {
            if (target - coins.get(i) >= 0) {
                count += combinationInfiniteCoins(target - coins.get(i), i, coins, combination + coins.get(i));
            }
        }
        return count;
    }

    public static int combinationInfiniteCoinsSubseq(int target, int idx, ArrayList<Integer> coins,
            String combination) {
        if (target == 0 || idx == coins.size()) {
            if (target == 0) {
                System.out.println(combination);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int element = coins.get(idx);
        if (target - element >= 0) {
            count += combinationInfiniteCoinsSubseq(target - element, idx, coins, combination + element);
        }
        count += combinationInfiniteCoinsSubseq(target, idx + 1, coins, combination);
        return count;
    }

    // leetcode 39-combination infinite coins
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>(); // agar ye static kroge tou baaki test case kelie galat answer aaynge kyunki har object kelei static mein ek copy bnti hai.

    public void combSum(int[] candidates, int target, int idx) {
        if (target == 0 || idx == candidates.length) {
            if (target == 0) {
                List<Integer> base = new ArrayList<Integer>(smallAns); // agar hum yahan pr nayi list nhi bnayenge aur smallans ko hi result mein add krdenge then kyunki result mein smallans added hai then hum jab neeche jaate wqt smallans mein changes krenge then it will also change result smallAns kyunki ye tou heap pr bni hai..aur result mein address stored hai.
                
                // loophole hai isme kyunki humne address dedia maanlo google drive ka link tha vo humne baata tou agr kisi ne bhi usmechange kiye tou vo link jisko point krrha tha usme reflect hoga...but agar fir array se krenge tou ek addn o(n) ka hotaa yahan tym bach jaata hai because address add krrhe hain
                
                result.add(base);
            }
            return;
        }

        if (target - candidates[idx] >= 0) {
            smallAns.add(candidates[idx]);
            combSum(candidates, target - candidates[idx], idx);
            smallAns.remove(smallAns.size() - 1); // give index
        }
        combSum(candidates, target, idx + 1);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combSum(candidates, target, 0);
        return result;
    }

    // 39 in cpp
    public static int combinationSingleCoins(int target, int idx, ArrayList<Integer> coins, String combination) {
        if (target == 0) {
            System.out.println(combination);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < coins.size(); i++) {
            if (target - coins.get(i) >= 0) {
                count += combinationSingleCoins(target - coins.get(i), i + 1, coins, combination + coins.get(i));
            }
        }
        return count;
    }

    public static int combinationSingleCoinsSubseq(int target, int idx, ArrayList<Integer> coins, String combination) {
        if (target == 0 || idx == coins.size()) {
            if (target == 0) {
                System.out.println(combination);
                return 1;
            }
            return 0;
        }

        int count = 0;
        int element = coins.get(idx);
        if (target - element >= 0) {
            count += combinationSingleCoinsSubseq(target - element, idx + 1, coins, combination + element);
        }
        count += combinationSingleCoinsSubseq(target, idx + 1, coins, combination);
        return count;
    }

    // 216
    public static void combinationSum3(int k, int target, List<List<Integer>> result, List<Integer> smallAns, int idx) {
        if (target == 0) {
            if (smallAns.size() == k) {
                List<Integer> base = new ArrayList<>(smallAns);
                result.add(base);
                return;
            }
            return;
        }

        for (int i = idx; i < 10; i++) {
            if (target - i >= 0) {
                smallAns.add(i);
                combinationSum3(k, target - i, result, smallAns, i + 1);
                smallAns.remove(smallAns.size() - 1);
            }
        }
    }

    public List<List<Integer>> combinationSum3(int k, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combinationSum3(k, target, result, smallAns, 1);
        return result;
    }

    // 77 return all possible combinations of k numbers out of the range [1, n].
    public static void combine(int k, int n, List<List<Integer>> result, List<Integer> smallAns, int idx) {
        if (smallAns.size() == k) {
            List<Integer> base = new ArrayList<>(smallAns);
            result.add(base);
            return;
        }

        for (int i = idx; i <= n; i++) {
            smallAns.add(i);
            combine(k, n, result, smallAns, i + 1);
            smallAns.remove(smallAns.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> smallAns = new ArrayList<>();
        combine(k, n, result, smallAns, 1);
        return result;
    }

    // 40
    public void combSum2(int[] candidates, int target, int idx) // combination single coin duplicate present
    {
        if (target == 0) {
            List<Integer> base = new ArrayList<Integer>(smallAns);
            result.add(base);
        }

        int prev = -1;
        for (int i = idx; i < candidates.length; i++) {
            if (prev != -1 && candidates[prev] == candidates[i])
                continue;
            if (target - candidates[i] >= 0) {
                smallAns.add(candidates[i]);
                combSum2(candidates, target - candidates[i], i + 1);
                smallAns.remove(smallAns.size() - 1);
                prev = i;
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // can also use hashmap and array but in array we will have to find maxSize and also on each level a new array will be made.
        combSum2(candidates, target, 0);
        return result;
    }
