package algo;
import java.util.ArrayList;
import java.util.Arrays;


// kruskal
// min cost to supply water to all house

public class kruskal {
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

    public static void merge(int p1,int p2)
    {
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

    public static void unionFind(int [][]edges,int N)
    {
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[]graph=new ArrayList[N];  

        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        par = new int[N];
        size = new int[N];
        for(int i=0;i<N;i++)
        {
            par[i]=i;
            size[i]=1;
        }

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
        }
        display(N, graph);
    }

    public static void solve()
    {
        int N=9;
        int [][]edges={{0,7,8},{7,6,1},{6,5,2},{5,4,10},{4,3,9},{3,2,7},{2,1,8},{1,0,4},{7,1,11},{7,8,7},{6,8,6},{8,2,2},{5,2,4},
        {5,3,14}};

        Arrays.sort(edges,(a,b)->{
            return a[2]-b[2]; //this-other  //default behavious increasing.
        });

        unionFind(edges, N);
    }

    public static void main(String []args)
    {
        solve();
    }

    //https://www.codingninjas.com/codestudio/problem-details/water-supply-in-a-village_1380956
    //1168
    // vector<int> par;
    // int findPar(int u)
    // {
    //     return par[u] == u ? u : (par[u] = findPar(par[u]));
    // }

    int unionFind(vector<vector<int>> edges, int N)
    {
        par.resize(N + 1);
        for (int i = 0; i <= N; i++)
            par[i] = i;

        int cost = 0;
        for (vector<int> &edge : edges)
        {
            int u = edge[0], v = edge[1], w = edge[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2)
            {
                par[p1] = p2;
                cost += w;
            }
        }

        cout << cost;
        return cost;
    }

    int minCostTosupplyWater(int n, vector<int> wells, vector<vector<int>> pipes)
    {
        for (int i = 0; i < wells.size(); i++)
            pipes.push_back({0, i + 1, wells[i]});

        sort(pipes.begin(), pipes.end(), [](vector<int> &a, vector<int> &b) {
            return a[2] < b[2];
        });

        return unionFind(pipes, n);
    }

    //https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
    vector<int> par;
    int findPar(int u)
    {
        return par[u] == u ? u : par[u] = findPar(par[u]);
    }

    int mrPresident(int n, long k, vector<vector<int>> &edges)
    {
        for (int i = 0; i <= n; i++)
            par.push_back(i);

        vector<int> mstEdgeWeight;
        long overallWeightSum = 0;
        for (vector<int> &edge : edges)
        {
            int p1 = findPar(edge[0]);
            int p2 = findPar(edge[1]);

            if (p1 != p2)
            {
                par[p1] = p2;
                mstEdgeWeight.push_back(edge[2]);
                overallWeightSum += edge[2];
                n--;
            }
        }

        if (n > 1)   //can only demolish road cant connect...so if component greater than 1 r->-1
            return -1;
        if (overallWeightSum <= k)  //do min transformation
            return 0;

        int transform = 0;
        for (int i = mstEdgeWeight.size() - 1; i >= 0; i--)
        {
            transform++;
            overallWeightSum = overallWeightSum - mstEdgeWeight[i] + 1;
            if (overallWeightSum <= k)
                return transform; 
        }

        return -1;  //edge case see in copy
    }

    void mrPresident()
    {
        ios_base::sync_with_stdio(false);
        int n, m;
        long k; //n-cities,m-roads,k-cost of maintenance
        cin >> n >> m >> k;
        vector<vector<int>> edges;
        for (int i = 0; i < m; i++)
        {
            int u, v, w;
            cin >> u >> v >> w;
            edges.push_back({u, v, w});
        }

        sort(edges.begin(), edges.end(), [](vector<int> &a, vector<int> &b) {
            return a[2] < b[2];
        });

        cout << mrPresident(n, k, edges);
    }
}
