package Basic;
import java.util.*;
public class graphDFS {
    
    
    //If you are using global variables make sure to clear them.
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void constructGraph()
    {   
        // ArrayList<Integer>a[]=new ArrayList[3];
        // a[2] = new ArrayList<Double>();  //compilation error

        //new ArrayList[N] ko ek unchecked conversion chahiye hota h to confirm to A<I>.humara program bolta h ki shayad aapka program kuch exception phenk skta hai cast krne se hum is annotation se btate hai ki nhi hum believe krte h ye code safe hai aur koi exception ni phekega
        // ArrayList<Integer>al[]=new ArrayList[5];

        // @SuppressWarnings("unchecked")  
        // ArrayList<Edge>[] mygraph = new ArrayList[7];
        // let's say 1,00,000 vertices hain to 2d array mei cells ho jaenge 10 ki power 10. 10 ki power 9 integers ko 4 gb RAM chaie hoti hai. 10 ki power 10 integers ko 40 GB RAM chaie hogi. aur online judge tumhe deta hai 250 MB.
        // for (int i = 0; i < 7; i++) {
        //     mygraph[i] = new ArrayList<Edge>();
        // }
        // graphJava j=new graphJava();
        // Edge e=j.new Edge(0,0,9);
        // mygraph[0].add(e); //agr static ni lgaya tou

        // mygraph[0].add(new Edge(0, 1, 10));

        // mygraph[1].add(new Edge(1, 0, 10));
        // mygraph[1].add(new Edge(1, 2, 10));

        // mygraph[2].add(new Edge(2, 3, 10));
        // mygraph[2].add(new Edge(2, 1, 10));

        // mygraph[3].add(new Edge(3, 4, 2));
        // mygraph[3].add(new Edge(3, 0, 40));
        // mygraph[3].add(new Edge(3, 2, 10));

        // mygraph[4].add(new Edge(4, 3, 2));
        // mygraph[4].add(new Edge(4, 5, 3));
        // mygraph[4].add(new Edge(4, 6, 8));

        // mygraph[5].add(new Edge(5, 4, 3));
        // mygraph[5].add(new Edge(5, 6, 3));

        // mygraph[6].add(new Edge(6, 4, 8));
        // mygraph[6].add(new Edge(6, 5, 3));

        // ArrayList<ArrayList<Edge>>mygraph=new ArrayList<ArrayList<Edge>>(7);
        // ArrayList<Edge>a=new ArrayList<Edge>();
        // a.add(new Edge(0,3,40));
        // mygraph.add(a);   //0th index pr ye list dalegi
        // mygraph.get(0).add(new Edge(1,2,100));
        // System.out.println(mygraph.get(0).get(1).src);
    }

    //1791
    public int findCenter(int[][] edges) {
        int n=edges.length;
        List<Integer>graph[]=new ArrayList[n+2];
        for(int i=0;i<n+2;i++)
            graph[i]=new ArrayList<>();
        
        for(int []e:edges)
        {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
            
            if(graph[e[1]].size()==n-1)
                return e[1];
            else if(graph[e[0]].size()==n-1)
                return e[0];
        }
        return -1;
    }

    public int findCenter2(int[][] edges) {
        int a=edges[0][0];
        int b=edges[0][1];
        
        if(a==edges[1][0])  //The given edges represent a valid star graph.
            return a;
        else if(a==edges[1][1])
            return a;
        else if(b==edges[1][0])
            return b;
        return b;
    }
    

    static class Pair3 implements Comparable<Pair3> { 
        int wsf; String psf;
        Pair3(int wsf, String psf)
        {    this.wsf = wsf; 
             this.psf = psf; 
        }
        public int compareTo(Pair3 o) { return this.wsf - o.wsf; } 
    }

    static String spath;
    static Integer spathwt = Integer.MAX_VALUE;
    static String lpath;
    static Integer lpathwt = Integer.MIN_VALUE;
    static String cpath;
    static Integer cpathwt = Integer.MAX_VALUE;
    static String fpath;
    static Integer fpathwt = Integer.MIN_VALUE;
    static PriorityQueue<Pair3> pq = new PriorityQueue<>(); 
    public static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, int criteria, int k,
            String psf, int wsf) {
                   
        if (src == dest) {
            if (wsf < spathwt) // wsf-weight so far,spathwt-smallest path weight
            {
                spath = psf; // spath-smallest path
                spathwt = wsf;
            }

            if (wsf > lpathwt) // lpathwt-largest path weight
            {
                lpath = psf; // lpath-largest path
                lpathwt = wsf;
            }

            if (wsf > criteria && wsf < cpathwt) { 
                cpath = psf; // cpath-ceal path
                cpathwt = wsf; // cpathwt-ceil path wt 
            }

            if (wsf < criteria && wsf > fpathwt) {
                fpath = psf; // fpath-floor path
                fpathwt = wsf; // fpathwt-floor path wt 
            }

            if (pq.size() < k) {
                pq.add(new Pair3(wsf, psf));
            } else if (wsf > pq.peek().wsf) {
                pq.remove(); // pq.poll
                pq.add(new Pair3(wsf, psf));
            }
        }

        visited[src] = true;
        for (Edge e : graph[src]) {
            if (visited[e.nbr] == false) {
                multisolver(graph, e.nbr, dest, visited, criteria, k, psf + e.nbr, wsf + e.wt);
            }
        }
        visited[src] = false;
    }
   
    public static class heavyPair{
        int weight;
        String psf;

        heavyPair(int weight,String psf)
        {
            this.weight=weight;
            this.psf=psf;
        }
    }

    public static heavyPair heavyPath(int src,int dest,boolean []visited)
    {
        if(src==dest)
        {
            heavyPair base=new heavyPair(0, dest+" ");   //6 to 6 jaane kelie weight will be 0
            return base;
        }

        visited[src]=true;
        heavyPair ans=new heavyPair(-(int)1e8, "");
        for(Edge e:graph[src])
        {
            if(!visited[e.v])
            {
                heavyPair recAns=heavyPath(e.v, dest, visited);
                if(recAns.weight!= -(int)1e8 && recAns.weight+e.w>ans.weight)
                {
                    ans.weight=recAns.weight+e.w;
                    ans.psf=src+" "+recAns.psf;
                }
            }
        }
        visited[src]=false;
        return ans;
    }

}
