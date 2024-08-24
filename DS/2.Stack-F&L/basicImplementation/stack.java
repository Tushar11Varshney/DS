package basicImplementation;

//using array is better than linkedlist.
//this ka matlab mere class ke bnaye variable ya fn ki baat horhi h.this ka fayda jyada tab jb ek file dusri ko extend krrhi hai and if a fn is called in second file then if this is used then the program understand that hmari class ke hi fn ko call krna h.
//java mein agr dusri class ka object bnana h jo dusri file mein hai but if they belong to same folder then no need to import else import
//command- javac tushar.java && java tushar
//finally is important because like uber which is sending data to google map about location then if map not required still it is sending request then uber have to pay for it(w/o closing resource)
//though when we write try and catch no need to write throws Exception after fn name but if catch is also throwing exception then write throws exception.
//jb koi bhi constructor then default constructor chlta hai agr ek bhi(parameterised,non) then default work ni.
//constructor sirf phle call hota h zruri ni sirf whi initialise kree.
//main static isliye hota hai kyunki static global cheezein memory mein phle hi initialise hojaati h agr static na ho tou initialize memory mein kbhi object create ni hoga and if object is not created by user then we will not be able to run our program.
//in c++ if int *Arr=new int[10];
// Arr[20]=100;
// cout<<Arr[20]; this works because it works on address bases it put the value on address so we have to maintain maxSize ki agr iss capacity se exceed krte h element then show stack full.
//stack(int a),stack(int b) same constructor(compile error same name,if name diffrn still compile error asks for return type).
//copy constructor by default address copy write code yourself for data copy.

//Why can we not override static method?
// It is because the static method is bound with class whereas instance method is bound with an object. Static belongs to the class area, and an instance belongs to the heap area.

//interface(eg-just in threepin socket koi bhi taar lgao vo device chlega)eg-list interface koi bhi ds lgao arraylist,vector,linkedlist     

public class stack {
    private int[] arr;
    private int tos; // top of stack
    private int elementCount; // total elements present in stack.

    protected void intializeValues(int size) { // made protective so that child class can access
        this.arr = new int[size];
        this.tos = -1;
        this.elementCount = 0;
    }

    public stack() {
        intializeValues(3);
    }

    public stack(int size) {
        intializeValues(size);
    }

    public int size() {
        return this.elementCount;
    }

    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    protected int capacity() { // can be private if other file dont require it.
        return this.arr.length;
    }

    public void push(int data) throws Exception {
        if (this.size() == this.capacity()) {
            throw new Exception("StackIsFull");
        }

        this.arr[++this.tos] = data;
        this.elementCount++;
    }

    private void CheckException_peek()throws Exception{
        if(this.isEmpty()){
            throw new Exception("StackIsEmpty");
        }
    }

    public int peek() {
        try{
            CheckException_peek();
            // if (this.isEmpty()) {
            //     throw new Exception("StackIsEmpty");
            // }
            return this.arr[this.tos];
        }catch(Exception e){
            System.out.println(e+"Exception handle krlia bhai aapne");
        }

        return this.arr[this.tos];
    }

    public int pop() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("StackIsEmpty");
        }

        int rv = this.arr[this.tos];
        this.arr[this.tos--] = 0;
        this.elementCount--;

        return rv;
    }

    public static void main(String []args) throws Exception
    {
        // Main m=new Main();  //online gdb
        // stack s=m.new stack();
        // System.out.println(s.peek());
    }
}