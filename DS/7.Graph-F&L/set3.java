// union find
// find redundant conn
// Check if eqn are possible
// Number of island
// Max area of island
// No of cities connected
// No of extra wire reqd to connect graph
// Similar string groups
// Lexographically smallest eqvn string


//6
package algo;
import java.util.ArrayList;
public class unionFind {
    
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void display(int N, ArrayList<Edge>[] graph) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    static int[] par;
    static int[] size;
    
    public static int findPar(int u)
    {
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }

    // O(1)
    public static void merge(int p1,int p2)
    {
        // kam size wale ko bde pr kro taaki height tree ki increase na ho and find bhi tezz chle
        if(size[p1]>size[p2])   
        {
            par[p2]=p1;
            size[p1]+=size[p2];
        }
        else{
            par[p1]=p2;
            size[p2]+=size[p1];
        }
    }

    // Edges : {{u,v,w}.....}
    // without Path Compression and size : V + E*V
    // Path Compression: V + E(alpha(n))
    // without Path Compression with size: V + ELog(V)

    public static void unionFind(int [][]edges,int N)
    {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[]graph=new ArrayList[N];  //isme warning isliye aati h kyunki hum right side pr ye bolrhe h ki N size ki arrayList bnani h pr usme koi bhi object aa skta hai..pr left side mein specifically bolrhe hai it is of Edge type so 
        // java give us warning ki bhai kuch gadBad hoskti hai,..then hum is warning ko suppressed krte hain..

        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        par = new int[N];
        size = new int[N];
        for(int i=0;i<N;i++)
        {
            par[i]=i;
            size[i]=1;
        }

        boolean isCycle=false;

        for(int []edge:edges)
        {
            int u=edge[0],v=edge[1],w=edge[2];

            int p1=findPar(u);
            int p2=findPar(v);
 
            if(p1!=p2)
            {
                merge(p1,p2);
                addEdge(graph, u, v, w);
            }
            else isCycle=true;
        }

        System.out.println(isCycle);
        display(N, graph);
    }

    public static void main(String []args)
    {
        int N=9;
        int [][]edges={{7,6,10},{5,6,10},{5,3,10},{6,8,10},{7,8,10},{2,5,10},{0,7,10},{0,1,10},{1,7,10},{1,2,10},{2,3,10},{3,4,10},{4,5,10}};

        unionFind(edges, N);
    }

    //684
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        par = new int[n+1];
        size = new int[n+1];
        for(int i=0;i<n+1;i++)
        {
            par[i]=i;
            size[i]=1;
        }

        for(int []edge:edges)
        {
            int u=edge[0],v=edge[1];

            int p1=findPar(u);
            int p2=findPar(v);

            if(p1!=p2)
                merge(p1,p2);
            else
                return new int[]{u,v};
        }
        return new int[]{};
    }

    //990
    public boolean equationsPossible(String[] equations) {
        int n=26;
        int []par=new int[n];
        for(int i=0;i<n;i++)
            par[i]=i;
        
        for(int i=0;i<equations.length;i++)
        {
            if(equations[i].charAt(1)=='=')
            {
                int u=equations[i].charAt(0)-'a';
                int v=equations[i].charAt(3)-'a';
                
                int p1=findParent(u,par);
                int p2=findParent(v,par);
                
                if(p1!=p2)
                    par[p1]=p2;
            }
        }
        
        for(int i=0;i<equations.length;i++)
        {
            if(equations[i].charAt(1)=='!')
            {
                int u=equations[i].charAt(0)-'a';
                int v=equations[i].charAt(3)-'a';
                
                int p1=findParent(u,par);
                int p2=findParent(v,par);
                
                if(p1==p2)
                    return false;
            }
        }
        
        return true;
    }

    //200
    int numIslands(vector<vector<char>> &grid)
    {
        int m = grid.size();
        int n = grid[0].size();
        par.resize(m * n);
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
                par[i * n + j] = i * n + j;
        }
        vector<vector<int>> dirs = {{1, 0}, {0, 1}};

        int count = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == '1')
                {
                    count++;
                    for (int d = 0; d < dirs.size(); d++)
                    {
                        int x = i + dirs[d][0];
                        int y = j + dirs[d][1];

                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1')
                        {
                            int p1 = findPar(i * n + j);
                            int p2 = findPar(x * n + y);

                            if (p1 != p2)
                            {
                                count--;
                                par[p1] = p2;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    //695
    int maxAreaOfIsland(vector<vector<int>> &grid)
    {
        int m = grid.size();
        int n = grid[0].size();
        par.resize(m * n);
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
                par[i * n + j] = i * n + j;
        }
        vector<vector<int>> dirs = {{1, 0}, {0, 1}};
        vector<int>componentSize(m*n,1);

        int maxArea=0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == 1)
                {
                    int p1 = findPar(i * n + j);  //isme hume p1 bahaar hi rkhna pdega for testcase like[[1]] because agr andar rkhenge tou kabhi ye if condn mein jaayga ni tou p1 undefined reh jaayga..
                    for (int d = 0; d < dirs.size(); d++)
                    {
                        int x = i + dirs[d][0];
                        int y = j + dirs[d][1];

                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                        {
                            int p2 = findPar(x * n + y);
                            if (p1 != p2)
                            {
                                par[p2] = p1;  //dont change p1 parent.
                                componentSize[p1]+=componentSize[p2];
                            }
                        }
                        maxArea=max(maxArea,componentSize[p1]);
                    }
                }
                else 
                componentSize[i*n+j]=0;
            }
        }
        
        for(int i=0;i<m*n;i++)
            cout<<componentSize[i]<<" ";
        return maxArea;
    }

    //547
    public int findParent(int u,int []par)
    {
        return par[u]==u?u:(par[u]=findParent(par[u],par));
    }
    
    public int findCircleNum(int[][] isConnected) {
        int n=isConnected.length;
        int []par=new int[n];
        for(int i=0;i<n;i++)
            par[i]=i;
        
        int component=n;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=j && isConnected[i][j]==1)
                {
                    int u=i;
                    int v=j;
                    
                    int p1=findParent(u,par);
                    int p2=findParent(v,par);
                    
                    if(p1!=p2)
                    {
                        par[p1]=p2;
                        component--;
                    }
                }
            }
        }
        return component;
    }
    
    //1319
    public int makeConnected(int n, int[][] connections) {
        int []par=new int[n];
        for(int i=0;i<n;i++)
            par[i]=i;
        
        int extrawire=0,component=n;
        for(int []c:connections)
        {
            int u=c[0];
            int v=c[1];

            int p1=findParent(u,par);
            int p2=findParent(v,par);

            if(p1!=p2)
            {
                par[p1]=p2;
                component--;
            }
            else
                extrawire++;
        }
                
        if(extrawire>=component-1)
            return component-1;
        else if(component==1)
            return 0;
    }
    
    //839
    int []parentArray;
    public int findParent(int u)
    {
        return parentArray[u]==u?u:(parentArray[u]=findParent(parentArray[u]));
    }

    public boolean isSimilar(String str1,String str2)
    {
        int count=0;
        for(int i=0;i<str1.length();i++)
        {
            if(str1.charAt(i)!=str2.charAt(i) && ++count>2)return false;
        }
        return true;
    }
    
    public int numSimilarGroups(String[] strs) {
        parentArray=new int[strs.length];
        for(int i=0;i<strs.length;i++)parentArray[i]=i;
        int group=strs.length;
        for(int i=0;i<strs.length-1;i++)
        {
            for(int j=i+1;j<strs.length;j++)
            {
                if(isSimilar(strs[i],strs[j]))
                {
                    int par1=findParent(i);
                    int par2=findParent(j);

                    if(par1!=par2)
                    {
                        parentArray[par1]=par2;
                        group--;
                    }
                }
            }
        }
        return group;
    }

    //lexicographically-smallest-equivalent-string
    //https://www.codingninjas.com/codestudio/problem-details/smallest-equivalent-string_1381859
    vector<int> parent(26);
    int findParent(int u)
    {
        return parent[u] == u ? u : parent[u] = findParent(parent[u]);
    }

    string smallestEqString(string a, string b, string s)
    {
        for (int i = 0; i < 26; i++)
            parent[i] = i;

        for (int i = 0; i < a.length(); i++)
        {
            int p1 = findParent(a[i] - 'a');
            int p2 = findParent(b[i] - 'a');

            parent[p1] = min(p1, p2);
            parent[p2] = min(p1, p2);
        }

        string res = "";
        for (int i = 0; i < s.length(); i++)
            res += (char)(findParent(s[i] - 'a') + 'a');

        cout << res << endl;
        // for (int i = 0; i < 26; i++)
        //     cout<<parent[i]<<" ";
        return res;
    }

    //========================
    //924
    //https://leetcode.com/problems/minimize-malware-spread/discuss/614031/C%2B%2B-%3A-Union-Find-reframe-the-question-on-%22CORONA%22-with-relatable-explanation
    int []par;
    int []size;
    //[[1,1,1,0,0,0],[1,1,1,0,0,0],[1,1,1,0,0,0],[0,0,0,1,1,1],[0,0,0,1,1,1],[0,0,0,1,1,1]] [3,1]
    public int findPar(int u)
    {
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n=graph.length;
        par=new int[n];
        size=new int[n];
        
        for(int i=0;i<n;i++)
        {
            par[i]=i;
            size[i]=1;
        }
        
        Arrays.sort(initial); //if multiple node can be removed return with smallest index
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=j && graph[i][j]==1)
                {
                    int p1=findPar(i);
                    int p2=findPar(j);
                    
                    if(p1!=p2)
                    {
                        par[p1]=p2;
                        size[p2]+=size[p1];  //size of country
                    }
                }
            }
        }
        
        int []infectedNodesinCity=new int[n];  //initially
        for(int i:initial)
        {
            int leader=findPar(i);
            infectedNodesinCity[leader]++;  //patient of each country
        }
        
        int ans=initial[0];  //agr ek network mei 3 nodes hain aapas mei saari connected aur 1,2 affected hain tou 1 ans h leetcode pr
        int maxPopulatedCity=0;
        for(int i:initial)
        {
            int noofInfectedPeople=infectedNodesinCity[findPar(i)];
            if(noofInfectedPeople==1 && size[findPar(i)]>maxPopulatedCity)
            {
                maxPopulatedCity=size[findPar(i)];
                ans=i;
            }
        }
        return ans;    
    }

    //959
    int []par;
    int count=1;
    public int findPar(int u)
    {
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }
    public void merge(int idx1,int idx2)
    {
        int p1=findPar(idx1);
        int p2=findPar(idx2);
        
        if(p1!=p2)
        {
            par[p1]=p2;
        }
        else count++;
    }
    public int regionsBySlashes(String[] grid) {
        int n=grid.length;
        par=new int[(n+1)*(n+1)];
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=n;j++)
            {   
                int idx=i*(n+1)+j;
                if(i==0||j==0||i==n||j==n)
                {
                    par[idx]=0;
                }
                else 
                par[idx]=idx;
            }
        }

        for(int i=0;i<n;i++)
        {
            char[] ch = grid[i].toCharArray(); 
            for(int j=0;j<n;j++)
            {
                if(ch[j]==' ')continue;
                else if(ch[j]=='/'){
                    int idx1=i*(n+1)+(j+1);
                    int idx2=(i+1)*(n+1)+j;
                    
                    merge(idx1,idx2);
                }
                else if(ch[j]=='\\'){
                    int idx1=i*(n+1)+j;
                    int idx2=(i+1)*(n+1)+(j+1);
                    
                    merge(idx1,idx2);
                }
            }
        }
        return count;
    }

    //redundant connection 2
    int n;
    int []par;
    public int findParent(int u)
    {
        return par[u]=(par[u]==u)?u:findParent(par[u]);
    }
    
    public boolean union(int u,int v) {
        int p1=findParent(u);
        int p2=findParent(v);

        if(p1!=p2)
        {
           par[p1]=p2;
           return false;
        }  
        return true;
    }
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        n=edges.length;
        int []indegree=new int[n+1];
        par=new int[n+1];
        
        for(int i=0;i<n+1;i++)
            par[i]=i;
        
        Arrays.fill(indegree,-1);
        int idx1=-1,idx2=-1;
        for(int i=0;i<n;i++)
        {
            int v=edges[i][1];
            
            if(indegree[v]==-1)
            indegree[v]=i;
            else
            {
                idx1=i;
                idx2=indegree[v];
                break;
            }
        }
        
        for(int i=0;i<n;i++)
        {
            if(i==idx1)continue;
            
            int u=edges[i][0];
            int v=edges[i][1];
            
            boolean flag=union(u,v);  //ek ek krke dkhna pdega direct union find on directed graph cant apply.
            if(flag==true)  //cycle present
            {
                if(idx1==-1) //cycle present but not 2 parent
                    return edges[i]; 
                else  //cycle present + 2 parent 
                    return edges[idx2]; //but galat blacklisted
            }
        }
        return edges[idx1]; 
    } 

    //https://www.hackerrank.com/challenges/journey-to-the-moon/problem
    //journey to moon
    vector<int>par,size;
    int findPar(int u)
    {
        return par[u]==u?u:par[u]=findPar(par[u]);
    }
    long journeyToMoon(int n, vector<vector<int>> astronaut) {  //n-number of astronauts
        
        for(int i=0;i<n;i++)
        {
            par.push_back(i);
            size.push_back(1);
        }
        
        for(vector<int>&ast:astronaut)
        {
            int p1=findPar(ast[0]);
            int p2=findPar(ast[1]);
            
            if(p1!=p2)
            {
                par[p1]=p2;
                size[p2]+=size[p1];
            }
        }
        
        long sum=0,totalPair=0;
        for(int i=0;i<n;i++)
        {
            if(par[i]==i)
            {
                totalPair+=sum*size[i];
                sum+=size[i];
            }
        }     
        return totalPair;
    } 


    int main()
    {
        // smallestEqString("leetcode","programs","sourcecode");
        // smallestEqString("parker","morris","parser");

        // minCostTosupplyWater(3,{1,2,2},{{1,2,1},{2,3,1}});
        return 0;
    }

    //305
    //https://www.lintcode.com/problem/434/
    struct Point
    {
        int x;
        int y;
        Point() : x(0), y(0) {}
        Point(int a, int b) : x(a), y(b) {}
    };
    vector<int> par;
    int findPar(int u)
    {
        // return par[u]==-1?u:(par[u]=findPar(par[u]));
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }
    vector<int> numIslands2(int m, int n, vector<Point> &position)
    { //mrows n column
        par.resize(m * n);
        // par.resize(m*n,-1);
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
                par[i * n + j] = i * n + j;
        }

        vector<vector<int>> grid(m, vector<int>(n, 0));
        int count = 0;
        vector<vector<int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        vector<int> ans;

        for (Point p : position)
        {
            int i = p.x;
            int j = p.y;

            if (grid[i][j] != 1)  //agr repeat hui koi edge tou uska tou parent same hi pichli wali se so count decr nhi hoga
            // if(par[i*n+j]==-1)
            {
                grid[i][j] = 1;
                // par[i*n+j]=(i*n+j);
                count++;

                for (int d = 0; d < dirs.size(); d++)
                {
                    int x = i + dirs[d][0];
                    int y = j + dirs[d][1];

                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1)
                    // if (x >= 0 && x < m && y >= 0 && y < n && par[x*n+y]!=-1)
                    {
                        int p1 = findPar(i * n + j);
                        int p2 = findPar(x * n + y);

                        if (p1 != p2)
                        {
                            count--;
                            par[p1] = p2;
                        }
                    }
                }
            } 
            ans.push_back(count);
        }

        for (int ele : ans)
            cout << ele << " ";
        return ans;
    }


}

// https://leetcode.ca/all/1101.html
// https://www.geeksforgeeks.org/problems/union-find/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

