// prims algo-tell MST,tell path

package Basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
public class prims {
    
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

    public static class primsPair{
        int vtx=0;
        int par=0;
        int wt=0;

        primsPair(int vtx,int par,int wt)
        {
            this.vtx=vtx;
            this.par=par;
            this.wt=wt;
        }
    }

    public static void primsAlgo_01(int src,boolean []vis,int N,ArrayList<Edge>[]graph)
    {
        ArrayList<Edge>[]MST=new ArrayList[N];
        for(int i=0;i<N;i++)
        MST[i]=new ArrayList<>();
    
        PriorityQueue<primsPair>que=new PriorityQueue<>((a,b)->{
            return a.wt-b.wt;
        });

        que.add(new primsPair(src, -1, 0));
        int numberofEdges=0;
        int totalWeight=0;
        //for connected numberofEdges<N-1
        // while(que.size()>0)  //take more time but generic way
        while(que.size()>0 && numberofEdges<N-1)   //for disconnected graph
        {
            primsPair p=que.remove();
            if(vis[p.vtx])
            continue;   //cycle
            totalWeight+=p.wt;

            if(p.par!=-1)
            {
                addEdge(MST, p.vtx, p.par, p.wt);
                numberofEdges++;
            }

            vis[p.vtx]=true;
            for(Edge e:graph[p.vtx])
            {
                if(!vis[e.v])
                que.add(new primsPair(e.v, p.vtx, e.w));
            }
        }

        System.out.println(totalWeight);
        display(N, MST);
    }

    public static void primsAlgo_02(int src,boolean []vis,int []dis,int []par,int N,ArrayList<Edge>[]graph)
    {
        ArrayList<Edge>[]MST=new ArrayList[N];
        for(int i=0;i<N;i++)
        MST[i]=new ArrayList<>();
    
        PriorityQueue<primsPair>que=new PriorityQueue<>((a,b)->{
            return a.wt-b.wt;
        });

        que.add(new primsPair(src, -1, 0));
        int numberofEdges=0;
        int totalWeight=0;

        while(que.size()>0 && numberofEdges<N-1)  
        {
            //remove
            primsPair p=que.remove();

            //check if already visited else visit
            if(vis[p.vtx])
            continue;  

            //work
            totalWeight+=p.wt;

            if(p.par!=-1)
            {
                addEdge(MST, p.vtx, p.par, p.wt);
                numberofEdges++;
            }

            vis[p.vtx]=true;

            // add unvisited
            for(Edge e:graph[p.vtx])
            {
                if(!vis[e.v] && e.w<dis[e.v])
                {
                    dis[e.v]=e.w;
                    par[e.v]=p.vtx;
                    que.add(new primsPair(e.v, p.vtx, e.w));
                }
            }
        }

        System.out.println(src+" "+totalWeight);
        display(N, MST);
        System.out.println();
    }

    public static void primsAlgo02(int src,boolean[]visited,int N,ArrayList<Edge>[]graph)
    {
        int []distance=new int[N];
        int []par=new int[N];

        Arrays.fill(distance, (int)1e9);
        Arrays.fill(par, -1);

        for(int i=0;i<N;i++)
        {
            if(!visited[i])
            primsAlgo_02(i, visited, distance, par, N, graph);
        }
    }

    public static void main(String []args)
    {
        int src=8;
        int N=9;
        boolean []visited=new boolean[N];
        ArrayList<Edge>[]graph=new ArrayList[N];
        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        // int [][]edges={{0,1,4},{0,7,8},{1,2,8},{1,7,11},{7,6,1},{7,8,7},{6,8,6},{2,8,2},{2,3,7},{2,5,4},{3,4,9},{3,5,14},{5,4,10},{6,5,2}};

        int [][]edges={{0,1,4},{0,7,8},{1,2,8},{1,7,11},{7,6,1},{7,8,7},{6,8,6},{2,8,2},{3,4,9},{3,5,14},{5,4,10}};

        for(int []edge:edges)
        {
            addEdge(graph, edge[0], edge[1], edge[2]);
        }

        // display(N, graph);
        // primsAlgo_01(src, visited, N, graph);
        primsAlgo02(src,visited,N, graph);
    }
}
