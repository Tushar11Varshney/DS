// package Construction&Basic;
import java.util.*;
public class PqConstruct {
    public static class PriorityQueue {
        ArrayList<Integer> data;
        boolean pqType;
        public PriorityQueue(boolean pqType) {
          data = new ArrayList<>();
          this.pqType=pqType;
        }
    
        public void add(int val) {
            data.add(val);
            upHeapify(data.size()-1);
        }
        
        public boolean isCompare(int x,int y)
        {
            if(this.pqType)
            return x<y;
            return x>y;
        }
        
        public void upHeapify(int i)
        {
            if(i==0)return;  //no need but agr ni lgaynge tou ye baar neche jaayga har addition pr
            int pi=(i-1)/2;
            if(isCompare(data.get(pi), data.get(i)))
            {
                swap(pi,i);
                upHeapify(pi);
            }    
        }
        
        public void swap(int i,int j)
        {
            int ithVal=data.get(i);    
            int jthVal=data.get(j);
            data.set(j,ithVal);
            data.set(i,jthVal);
        }
        
        public int remove() {
          if(data.size()==0)
          {
              System.out.println("Underflow");
              return -1;
          }
          
          swap(0,data.size()-1);
          int val=data.remove(data.size()-1);
          downHeapify(0);
          return val;
        }

        public void downHeapify(int i)
        {
            int mini=i;   //i-parent index
            int lchild=2*i+1;
            int rchild=2*i+2;
            
            if(lchild<data.size() && isCompare(data.get(mini), data.get(lchild)))
            mini=lchild;
            
            if(rchild<data.size() && isCompare(data.get(mini), data.get(rchild)))
            mini=rchild;
            
            if(mini!=i)
            {
                swap(mini,i);
                downHeapify(mini);
            }
        }
        
        public int peek() {
          if(data.size()==0)
          {
              System.out.println("Underflow");
              return -1;
          }
          return data.get(0);
        }
    
        public int size() {
            return data.size();
        }
      }
    
    public static void main(String args[])
    {
        PriorityQueue pq=new PriorityQueue(false);
        //true:max //false:min
        pq.add(-2);
        // System.out.println(pq.peek());
        pq.add(8);
        // System.out.println(pq.peek());
        pq.add(-4);
        // System.out.println(pq.peek());
        pq.add(10);
        // System.out.println(pq.peek());
        pq.add(20);
        // System.out.println(pq.peek());
        pq.add(-30);
        System.out.println(pq.peek());
        pq.remove();
        System.out.println(pq.peek());
        pq.remove();
        System.out.println(pq.peek());
    }
}
