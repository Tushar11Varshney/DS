// topological sort
// Cycle present
// Can finish courses, and in which order?


//kahn algo
//Can finish courses, and in which order?
//Longest Increasing Path
package Basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
public class directedGraph {
    
    public static class Edge{
        int v;
        int w;

        Edge(int v,int w)
        {
            this.v=v;
            this.w=w;
        }
    }

    static int N=5;
    @SuppressWarnings("unchecked")
    static ArrayList<Edge>[]graph=new ArrayList[N];

    public static void addEdge(int u,int v,int w)
    {
        graph[u].add(new Edge(v,w));
    }

    public static void display(ArrayList<Edge>[]graph)
    {
        for(int i=0;i<N;i++)
        {
            System.out.print(i+" -> ");
            for(Edge e:graph[i])
            {
                System.out.print(e.v+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void topologicalOrder_DFS_helper(int vtx,int []visited,ArrayList<Integer>ans)
    {
        visited[vtx]=1;
        for(Edge e:graph[vtx])
        {
            if(visited[e.v]==0)
            topologicalOrder_DFS_helper(e.v, visited,ans);
        }
        ans.add(vtx);
    }

    public static void topologicalOrder_DFS()   
    {
        int []visited=new int[N];
        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=0;i<N;i++)
        {
            if(visited[i]==0)
            topologicalOrder_DFS_helper(i, visited, ans);
        }

        for(int ele:ans)
        System.out.print(ele+" ");
    }

    public static boolean isCyclePresent_DfsTopo(int []visited,ArrayList<Integer>ans,int src)
    {
        visited[src]=0;
        boolean res=false;
        for(Edge e:graph[src])
        {
            if(visited[e.v]==-1)
            {
                //unvisited
                res=res||isCyclePresent_DfsTopo(visited, ans, e.v);
            }
            else if(visited[e.v]==0)
            return true;  //cycle present
        }
        visited[src]=1;
        ans.add(src);
        return res;
    }

    public static ArrayList<Integer> isCyclePresent_Dfs()
    {
        boolean res=false;
        ArrayList<Integer>ans=new ArrayList<>();
        int []visited=new int[N];
        Arrays.fill(visited, -1);
        for(int i=0;i<N;i++)
        {
            if(visited[i]==-1)
            {
                res=res||isCyclePresent_DfsTopo(visited,ans,i);  
            }
        }

        if(res)ans.clear();
        return ans;
    }

    // 207
    public static boolean isCyclePresent_DfsTopo(int[] visited, ArrayList<Integer>[] graph, int src) {
        visited[src] = 0;
        boolean res = false;
        for (int e : graph[src]) {
        if (visited[e] == -1) {
            // unvisited
            res = res || isCyclePresent_DfsTopo(visited, graph, e);
        } else if (visited[e] == 0)
            return true; // cycle present
        }
        visited[src] = 1;
        return res;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) { 

        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++)
        graph[i] = new ArrayList<>();
        for (int[] ar : prerequisites) {
            graph[ar[0]].add(ar[1]);
        }

        int[] visited = new int[numCourses];
        Arrays.fill(visited, -1);
        for (int i = 0; i < numCourses; i++) {
        if (visited[i] == -1)
            if (isCyclePresent_DfsTopo(visited, graph, i))
            return false;  //cant finish course
        }
        return true;
  }

    // 210
    static int k = 0;
    public static boolean isCyclePresent_DfsTopo(int[] visited, ArrayList<Integer>[] graph, int src, int[] ans) {   
        visited[src] = 0;
        boolean res = false;
        for (int e : graph[src]) {
        if (visited[e] == -1) {
            // unvisited
            res = res || isCyclePresent_DfsTopo(visited, graph, e, ans);
        } else if (visited[e] == 0)
            return true; // cycle present
        }
        visited[src] = 1;
        ans[k++] = src;
        return res;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++)
        graph[i] = new ArrayList<>();
        for (int[] ar : prerequisites) {
        graph[ar[0]].add(ar[1]);
        }

        int[] visited = new int[numCourses];
        Arrays.fill(visited, -1);
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
        if (visited[i] == -1)
            if (isCyclePresent_DfsTopo(visited, graph, i, ans)) {
            return new int[] {};
            }
        }

        return ans;
  }

    // =====================================[]
    public static void kahnsAlgo()
    {
        int []indegree=new int[N];
        for(int i=0;i<N;i++)
            for(Edge e:graph[i])
            indegree[e.v]++;

        LinkedList<Integer>que=new LinkedList<>();
        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=0;i<N;i++)
            if(indegree[i]==0)
            que.addLast(i);

        int level=0;
        while(que.size()>0)
        {
            int size=que.size();
            while(size-->0)
            {
                int vtx=que.removeFirst();
                ans.add(vtx);
                for(Edge e:graph[vtx])
                {
                    if(--indegree[e.v]==0)
                    {
                        que.addLast(e.v);
                    }
                }
            }
            level++;
        }

        if(ans.size()!=N)
        System.out.println("Cycle is present");

        for(int ele:ans)
        System.out.print(ele+" ");
    }
    
    //207-graph bnao phle kahn kelie
    public static boolean courseSchedule(ArrayList<Integer>[]graph,int N)
    {
        int []indegree=new int[N];
        for(int i=0;i<N;i++)
            for(int e:graph[i])
            indegree[e]++;

        LinkedList<Integer>que=new LinkedList<>();
        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=0;i<N;i++)
            if(indegree[i]==0)
            que.addLast(i);

        while(que.size()>0)
        {
            int size=que.size();
            while(size-->0)
            {
                int vtx=que.removeFirst();
                ans.add(vtx);
                for(int e:graph[vtx])
                {
                    if(--indegree[e]==0)
                    {
                        que.addLast(e);
                    }
                }
            }
        }

        if(ans.size()!=N)
        return false;
        else return true;
    }

    //210
    public static int[] courseSchedule2(ArrayList<Integer>[]graph,int N)
    {
        int []indegree=new int[N];
        for(int i=0;i<N;i++)
            for(int e:graph[i])
            indegree[e]++;

        LinkedList<Integer>que=new LinkedList<>();
        int[]ans=new int[N];
        for(int i=0;i<N;i++)
            if(indegree[i]==0)
            que.addLast(i);

        int i=0;
        while(que.size()>0)
        {
            int size=que.size();
            while(size-->0)
            {
                int vtx=que.removeFirst();
                ans[i++]=vtx;
                for(int e:graph[vtx])
                {
                    if(--indegree[e]==0)
                    {
                        que.addLast(e);
                    }
                }
            }
        }

        if(i!=N)
        return new int[]{};
        else
        {
            //reverse array
            int k=0,j=N-1;
            while(k<j)
            {
                int temp=ans[k];
                ans[k]=ans[j];
                ans[j]=temp;
                k++;j--;
            }
            return ans;
        }
    }

    public boolean canFinish(int numCourses,int[][] prerequisites) {
        
        ArrayList<Integer>[]graph=new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
        graph[i]=new ArrayList<>();
        for(int []ar:prerequisites)
        {
            graph[ar[0]].add(ar[1]);
        }
        return courseSchedule(graph,numCourses);
        // return courseSchedule2(graph,numCourses);
    }

    //329
    int longestIncreasingPath(vector<vector<int>> &matrix)
    {

        int n = matrix.size();
        int m = matrix[0].size();

        vector<vector<int>> indegree(n, vector<int>(m, 0));
        vector<vector<int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                for (int d = 0; d < dirs.size(); d++)
                {
                    int x = i + dirs[d][0];
                    int y = j + dirs[d][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[i][j])
                        indegree[x][y]++;
                }
            }
        }

        queue<int> que;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (indegree[i][j] == 0)
                    que.push(i * m + j);
            }
        }

        int level = 0;
        while (que.size() > 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int idx = que.front();
                que.pop();
                int r = idx / m;
                int c = idx % m;
                for (int d = 0; d < dirs.size(); d++)
                {
                    int x = r + dirs[d][0];
                    int y = c + dirs[d][1];

                    if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c] && --indegree[x][y] == 0)
                        que.push(x * m + y);
                }
            }
            level++;
        }
        return level;
    }

    //kosa raju
    public static void dfs_scc(ArrayList<Integer>scc,int []visited,int src,ArrayList<Edge>[]ngraph)
    {
        visited[src]=1;

        for(Edge e:ngraph[src])
        {
            if(visited[e.v]==-1)
            dfs_scc(scc,visited,e.v,ngraph);
        }
        scc.add(src);
    }

    public static void kosaRaju()
    {
        int []visited=new int[N];
        Arrays.fill(visited, -1);
        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=0;i<N;i++)
        {
            if(visited[i]==-1)
            topologicalOrder_DFS(i, visited, ans);
        }

        //inverse graph
        ArrayList<Edge>[]ngraph=new ArrayList[N];
        for(int i=0;i<N;i++)
        ngraph[i]=new ArrayList<>();
        for(int i=0;i<N;i++)
        {
            for(Edge e:graph[i])
            ngraph[e.v].add(new Edge(i,1));
        }

        Arrays.fill(visited,-1);
        for(int i=ans.size()-1;i>=0;i--)
        {
            int ele=ans.get(i);
            ArrayList<Integer>scc=new ArrayList<>();

            if(visited[ele]==-1)
            dfs_scc(scc, visited, ele, ngraph);

            for(int x:scc)
            System.out.print(x+"  ");
            System.out.println();
        }
    }

    // ===
    public static void iterativeDepthFirstTraversal(Stack < Pair > st, int[] visited,ArrayList < Edge > [] graph) {
        while (st.size() > 0) {
            Pair p = st.pop();
            if (visited[p.vertex] == 1) continue;
            visited[p.vertex] = 1;
            System.out.println(p.vertex + "@" + p.psf);
            for (Edge e: graph[p.vertex]) {
                if (visited[e.nbr] == 0)
                    st.push(new Pair(e.nbr, p.psf + e.nbr));
            }
        }

        // Stack < Pair > st = new Stack < > ();
        // int visited[] = new int[vtces];
        // st.push(new Pair(src, "" + src));
        // iterativeDepthFirstTraversal(st, visited,graph);
    }
   
    public static void constructGraph()
    {
        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        // addEdge(5, 4, 10);
        // addEdge(4, 0, 10);
        // addEdge(5, 1, 10);
        // addEdge(1, 2, 10);
        // addEdge(2, 3, 10);
        // addEdge(0, 6, 10);
        // addEdge(6, 7, 10);
        // addEdge(7, 3, 10);

        addEdge(0, 1, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 10);
        addEdge(3, 4, 10);
        addEdge(4, 1, 10);

        // topologicalOrder_DFS();
        // kahnsAlgo();
        // ArrayList<Integer>ans=isCyclePresent_Dfs();
        // for(int ele:ans)
        // System.out.print(ele+" ");

        // kosaRaju();

    }    
    public static void main(String []args)
    {
        constructGraph();
    }

}
