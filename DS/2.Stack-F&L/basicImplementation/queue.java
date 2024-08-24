package basicImplementation;
public class queue{   //622
    private int[] arr;
    private int front = 0;   
    private int back = 0;
    private int elementCount; // total elements present in queue

    private void intializeValues(int size){
        this.arr = new int[size+1];
        this.elementCount = 0;
        this.front = 0;
        this.back = 0;
    }
    
    public queue(int k) {
        intializeValues(k);
    }

    public boolean isEmpty(){
        return this.elementCount == 0;
    }

    private int capacity(){
        return this.arr.length-1;
    }

    public boolean isFull() {
        return this.elementCount==this.capacity();
    }

    public boolean enQueue(int data) throws Exception{
        if(this.isFull()){
           return false;
        //    throw new Exception("QueueIsFull");
       }

       this.back = (this.back + 1) % this.capacity();
       this.arr[this.back] = data;
       this.elementCount++;
       
       return true;
   }

    public int Front() throws Exception{
        if(this.isEmpty()){
            // throw new Exception("QueueIsEmpty");
            return -1;
        }
        
        return this.arr[(this.front + 1) % this.capacity()];  //front nikalne kelie front sirf aage bdana hai change ni
    }

    public int Rear() {
        if(this.isEmpty()){
            return -1;
        }
        
        return this.arr[this.back];
    }

    public boolean deQueue() throws Exception{
        if(this.isEmpty()){
            return false;
            // throw new Exception("QueueIsEmpty");
        }

        this.front = (this.front + 1) % this.capacity();
        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.elementCount--;

        return true;
    }
}