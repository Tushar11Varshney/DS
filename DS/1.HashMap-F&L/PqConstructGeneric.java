
import java.util.ArrayList;
import java.util.Comparator;
public class PqConstructGeneric {
    static class Student implements Comparable<Student>{

        int rno;
        int wt;
        int ht;

        Student(int rno,int wt,int ht)
        {
            this.rno=rno;
            this.wt=wt;
            this.ht=ht;
        }

        public int compareTo(Student o)
        {
            return this.rno-o.rno;
        }

        public String toString()
        {
            return "rno="+this.rno+",wt="+this.wt+",ht="+this.ht;
        }
    }  

    static class StudentWtComparator implements Comparator<Student>  //if we want to compare at more than 1 thing then use comparator 
    {
        public int compare(Student s1,Student s2)
        {
            return s1.wt-s2.wt;
        }
    }

    static class StudentHtComparator implements Comparator<Student>
    {
        public int compare(Student s1,Student s2)
        {
            return s1.ht-s2.ht;
        }
    }
    
    public static class PriorityQueue<T> {
        ArrayList<T> data;
        Comparator comp;
        boolean pqType;
        public PriorityQueue() {
          data = new ArrayList<>();
          comp=null;
          pqType=true;
        }

        public PriorityQueue(Comparator comp,boolean pqType) {
            data = new ArrayList<>();
            this.comp=comp;
            this.pqType=pqType;
          }
    
        public void add(T val) {
            data.add(val);
            upHeapify(data.size()-1);
        }
        
        private boolean isCompare(int i,int j)
        {
            if(comp==null)  //agar comparator null hai tou do on basis of comparable
            {
                Comparable ith=(Comparable)data.get(i);
                Comparable jth=(Comparable)data.get(j);
                if(pqType)
                return ith.compareTo(jth)<0;
                return ith.compareTo(jth)>0;
            }
            else{
                T ith=data.get(i);
                T jth=data.get(j);

                if(pqType)
                return comp.compare(ith, jth)<0;
                return comp.compare(ith, jth)>0;
            }
        }

        public void upHeapify(int i)
        {
            if(i==0)return;
            int pi=(i-1)/2;
            if(isCompare(i, pi))
            {
                swap(pi,i);
                upHeapify(pi);
            }    
        }
        
        public void swap(int i,int j)
        {
            T ithVal=data.get(i);    
            T jthVal=data.get(j);
            data.set(j,ithVal);
            data.set(i,jthVal);
        }
        
        public T remove() {
          if(data.size()==0)
          {
              System.out.println("Underflow");
              return null;
          }
          
          swap(0,data.size()-1);
          T val=data.remove(data.size()-1);
          downHeapify(0);
          return val;
        }
        
        public void downHeapify(int i)
        {
            int mini=i;   //i-parent index
            int lchild=2*i+1;
            int rchild=2*i+2;
            
            if(lchild<data.size() && isCompare(lchild, mini))
            mini=lchild;
            
            if(rchild<data.size() && isCompare(rchild, mini))
            mini=rchild;
            
            if(mini!=i)
            {
                swap(mini,i);
                downHeapify(mini);
            }
        }
        
        public T peek() {
          if(data.size()==0)
          {
              System.out.println("Underflow");
              return null;
          }
          return data.get(0);
        }
    
        public int size() {
            return data.size();
        }
      }

    public static void main(String args[])
    {
        // PriorityQueue<Student>pq=new PriorityQueue<>(null,false);
        PriorityQueue<Student>pq=new PriorityQueue<>(new StudentWtComparator(),false);
        pq.add(new Student(2, 80,  180));
        pq.add(new Student(12, 56, 189));
        pq.add(new Student(5, 47, 190));
        pq.add(new Student(6, 67,176));
        pq.add(new Student(9, 90, 165));

        while(pq.size()>0)
        {
            System.out.println(pq.peek());
            pq.remove();
        }
    }

}
