package algo;
import java.util.*;

public class articulationPB {
    
    public static class Edge {
        int v = 0;

        Edge(int v) {
            this.v = v;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v) {
        graph[u].add(new Edge(v));
        graph[v].add(new Edge(u));
    }

    public static void display(int N, ArrayList<Edge>[] graph) {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print(e.v+" ");
            }
            System.out.println();
        }
    }

    static int[]disT,lowT,Ap;
    static boolean[] isAp,visited;
    static int totalCallsfromRoot=0,time=0;

    public static void dfs(int src,int par,int N,ArrayList<Edge>graph[])
    {
        disT[src]=lowT[src]=time++;
        visited[src]=true;

        for(Edge e:graph[src])
        {
            if(!visited[e.v])
            {
                if(par==-1)
                totalCallsfromRoot++;

                dfs(e.v, src, N, graph);

                if(disT[src]<=lowT[e.v])  //why not lowT[src]<=lowT[nbr]
                {
                    isAp[src]=true;
                    Ap[src]++;
                }

                // if(disT[src]<lowT[e.v])
                // System.out.println("Articulation bridge: "+src+" -> "+e.v);

                lowT[src]=Math.min(lowT[src],lowT[e.v]);
            }
            else if(e.v!=par)
            {
                lowT[src]=Math.min(lowT[src],disT[e.v]); //why not lowT[src]=Math.min(lowT[src],lowT[nbr])
            }
        }
    }

    public static void articulationPointandBridge(int N,ArrayList<Edge>[]graph)
    {
        disT=new int[N];
        lowT=new int[N];
        visited=new boolean[N];  //mandatory

        Ap=new int[N];   //optional depends on qs
        isAp=new boolean[N];

        int components=0;
        for(int i=0;i<N;i++)
        {
            if(!visited[i])
            {
                dfs(i,-1,N,graph);
                if(totalCallsfromRoot==1)
                {
                    isAp[i]=false;
                    Ap[0]=0;
                }
                totalCallsfromRoot=0;
                components++;
            }
        }

        int countofAp=0;
        for(int i=0;i<N;i++)
        {
            if(isAp[i])
            {
                countofAp++;
                System.out.println("AP: " + i + " @ " + "Increase in No Of components: " + Ap[i]);
                //the total component will ap[i]+1 but increase is ap[i]
            }
        }
    }

    public static void main(String[] args) {
        int N=9;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[]graph=new ArrayList[N];
        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        int [][]edges={{0,1},{1,2},{1,4},{3,4},{4,5},{1,5},{4,6},{6,8},{8,7},{4,7}};

        for(int []edge:edges)
        {
            addEdge(graph, edge[0], edge[1]);
        }

        articulationPointandBridge(N, graph);
    }   

    // 1192
  /*static int[] disT, lowT;
  static boolean[] visited;
  static int time = 0;
  static List<List<Integer>> res;

  public static void dfs(int src, int par, int N, ArrayList<Integer> graph[]) {
    disT[src] = lowT[src] = time++;
    visited[src] = true;

    for (int nbr : graph[src]) {
      if (!visited[nbr]) {
        dfs(nbr, src, N, graph);

        if (disT[src] < lowT[nbr])
          res.add(new ArrayList<Integer>(Arrays.asList(src,nbr)));

        lowT[src] = Math.min(lowT[src], lowT[nbr]);
      } else if (nbr != par) {
        lowT[src] = Math.min(lowT[src], disT[nbr]);
      }
    }
  }

  public List<List<Integer>> criticalConnections(int N, List<List<Integer>> connections) {
    disT = new int[N];
    lowT = new int[N];
    visited = new boolean[N];
    res = new ArrayList<>();

    ArrayList<Integer>[] graph = new ArrayList[N];
    for (int i = 0; i < N; i++)
      graph[i] = new ArrayList<>();

    for (List<Integer> cn : connections) {
      graph[cn.get(0)].add(cn.get(1));
      graph[cn.get(1)].add(cn.get(0));
    }
    dfs(0, -1, N, graph);
    return res;
  }*/
}
