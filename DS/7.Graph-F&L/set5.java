package algo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


// djikstra-tell smallest wght to reach every node from src, tell path
// tell min time to reach signal to every node
// Swim in water
// cheapest flight with atmost k stop
public class djikstra {
    
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

    public static class djikstraPair{
        int vtx=0;
        int par=0;
        int wt=0;
        int wsf=0;

        //if we want to create graph then we req parent and wt

        djikstraPair(int vtx,int par,int wt,int wsf)
        {
            this.vtx=vtx;
            this.par=par;
            this.wt=wt;
            this.wsf=wsf;
        }
    }

    public static void djikstraAlgo_01(int src,boolean []vis,int N,ArrayList<Edge>[]graph)
    {   
        ArrayList<Edge>[]ST=new ArrayList[N];
        for(int i=0;i<N;i++)
        ST[i]=new ArrayList<>();

        PriorityQueue<djikstraPair>que=new PriorityQueue<>((a,b)->{
            return a.wsf-b.wsf;
        });

        que.add(new djikstraPair(src,-1,0,0));
        int numberofEdges=0;

        while(numberofEdges<N-1)     //if graph is connected
        {
            djikstraPair p=que.remove();
            if(vis[p.vtx])
            continue;   //cycle

            if(p.vtx!=src)
            {
                addEdge(ST, p.vtx, p.par, p.wt);
                numberofEdges++;
            } 

            vis[p.vtx]=true;
            for(Edge e:graph[p.vtx])
            {
                if(!vis[e.v])
                que.add(new djikstraPair(e.v,p.vtx,e.w,e.w+p.wsf));
            }
        }

        display(N, ST);
    }

    public static void djikstraAlgo_02(int src,boolean []vis,int []dis,int []par,int N,ArrayList<Edge>[]graph)
    {   
        ArrayList<Edge>[]ST=new ArrayList[N];
        for(int i=0;i<N;i++)
        ST[i]=new ArrayList<>();

        PriorityQueue<djikstraPair>que=new PriorityQueue<>((a,b)->{
            return a.wsf-b.wsf;
        });

        que.add(new djikstraPair(src,-1,0,0));
        int numberofEdges=0;

        while(numberofEdges<N-1)     //if graph is connected
        {
            djikstraPair p=que.remove();
            if(vis[p.vtx])
            continue;   //cycle

            if(p.vtx!=src)
            {
                addEdge(ST, p.vtx, p.par, p.wt);
                numberofEdges++;
            }

            vis[p.vtx]=true;
            for(Edge e:graph[p.vtx])
            {
                if(!vis[e.v] && e.w+p.wsf<dis[e.v])
                {
                    dis[e.v]=e.w+p.wsf;
                    par[e.v]=p.vtx;
                    que.add(new djikstraPair(e.v,p.vtx,e.w,e.w+p.wsf));
                }
            }
        }

        display(N, ST);
    }

    public static void djikstraAlgo02(int src,boolean[]visited,int N,ArrayList<Edge>[]graph)
    {
        int []distance=new int[N];
        int []par=new int[N];

        Arrays.fill(distance, (int)1e9);
        Arrays.fill(par, -1);

        djikstraAlgo_02(src, visited, distance, par, N, graph);
    }

    public static void main(String []args)
    {
        int src=8;
        int N=9;
        boolean []visited=new boolean[N];
        ArrayList<Edge>[]graph=new ArrayList[N];
        for(int i=0;i<N;i++)
        graph[i]=new ArrayList<>();

        int [][]edges={{0,1,4},{0,7,8},{1,2,8},{1,7,11},{7,6,1},{7,8,7},{6,8,6},{2,8,2},{2,3,7},{2,5,4},{3,4,9},{3,5,14},{5,4,10},{6,5,2}};

        for(int []edge:edges)
        {
            addEdge(graph, edge[0], edge[1], edge[2]);
        }

        // display(N, graph);
        // djikstraAlgo_01(src, visited, N, graph);
        djikstraAlgo02(src, visited, N, graph);
    }

    //743-djisktra
    public int networkDelayTime(int[][] times, int n, int src) {
        ArrayList<int[]>[]graph=new ArrayList[n+1];
        for(int i=0;i<=n;i++)
        graph[i]=new ArrayList<>();

        //{u->{v,w}}
        for(int []time:times)
        graph[time[0]].add(new int[]{time[1], time[2]});

        boolean []visited=new boolean[n+1];
        //{vtx,wsf}
        PriorityQueue<int[]>que=new PriorityQueue<>((a,b)->{
            return a[1]-b[1];
        });

        int noofEdges=0;
        int totalTime=0;
        que.add(new int[]{src,0});
        while(que.size()>0)
        {
            int []p=que.remove();
            int vtx=p[0],wsf=p[1];
            if(visited[vtx])continue;

            if(vtx!=src)
            noofEdges++;

            totalTime=Math.max(totalTime,wsf);
            visited[vtx]=true;
            for(int []ar:graph[vtx])
            {
                if(!visited[ar[0]])
                    que.add(new int[]{ar[0],ar[1]+wsf});
            }
        }

        if(noofEdges!=n-1)
        return -1;

        return totalTime;
    }

    //778
    public int swimInWater(int[][] grid) {  // 1 23 4 
                                            // 2 3  4  //23 pr jaane kelie 23 min tk wait aur 2 pr jaane kelie2 min tk ka wait
        int n=grid.length;
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->{
            return grid[a/n][a%n]-grid[b/n][b%n]; 
        });
        
        int [][]dirs={{1,0},{0,1},{0,-1},{-1,0}};
        int [][]visited=new int[n][n];
        visited[0][0]=1;int time=0;
        pq.add(0);
        while(pq.size()>0)
        {
            int idx=pq.poll();
            int r=idx/n,c=idx%n;
            time=Math.max(time,grid[r][c]);
            
            if(r==n-1 && c==n-1)
            return time;
            for(int i=0;i<dirs.length;i++)
            {
                int x=r+dirs[i][0];
                int y=c+dirs[i][1];
                
                if(x>=0 && x<n && y>=0 && y<n && visited[x][y]==0)
                {
                    pq.add(x*n+y);
                    visited[x][y]=1;
                }
            }
        return time;
}
    
    //787   
    static class edge{
        int v;
        int w;
        
        edge(int v,int w)
        {
            this.v=v;
            this.w=w;
        }
    }
    
    static class Pair{
        int u;
        int wsf;
        int edgeCount;
        
        Pair(int u,int wsf,int edgeCount)
        {
            this.u=u;
            this.wsf=wsf;
            this.edgeCount=edgeCount;
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        
        ArrayList<edge>[]graph=new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i]=new ArrayList<>();
        
        for(int i=0;i<flights.length;i++)
        {
            graph[flights[i][0]].add(new edge(flights[i][1],flights[i][2]));
        }
        
        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->{
            return a.wsf-b.wsf;
        });

        pq.add(new Pair(src,0,0));
        int stop=K+1; // k stop means k+1 edges
        while(pq.size()>0)
        {
            Pair rm=pq.remove();
            int u=rm.u,wsf=rm.wsf,edgeCount=rm.edgeCount;

            if(u==dst && edgeCount<=stop)
            return wsf;

            else if(edgeCount==stop)
            continue;

            for(edge e:graph[u])
            {
                pq.add(new Pair(e.v,wsf+e.w,edgeCount+1));
            }
        }
      return -1;  
    }

}

