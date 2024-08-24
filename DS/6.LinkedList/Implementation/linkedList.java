// Construction-make all properties class,data,next=null,head,tail,numberofnodes private..constructor mein value ko set krdo.

// size,isEmpty(return size()==0)

// handleZeroSize(head aur tail dono us node pr set krdega)
// addFirstNode(agr size 0 tou call handleZerosize) (public)addFirst(node bnao and call addFirstNode)
// (public)addLast,addLastNode(protected ya private kyunki node tou mil hi ni skti bahar se)
// addNodeAt(Call at idx=0 addFirstNode,idx=size pr addLastNode,use getNodeAt to bring prev node),addAt(throws exception if idx<0 &&idx>size()..make new node and pass to addNodeAt(node,idx))

// handlesize1(head tail dono null),removefirstNode,removeFirst(throws exception if size 0),removelastnode,removelast(throws exception if size 0),removeNodeAt(use remove first,remove last),removeAt(exception for < 0 and >=size and size=0)
// getFirst,getLast(check in both size=0),getAt(Size=0,idx<0 && idx>=size).


//stack mein humara program execute hota hai aur heap external memory hoti hai tou vo sasti but stack is on ram.int arr[n] in c++ is a costly operation because made on stack.aur ram ke andar jo data hoga bijli udne pr ud jayga 
public class linkedList {

    private class Node {           //we dont want anybody to access our node so made it private
        private int data = 0;  
        private Node next = null;      //as node is private so we will not be given any other node address so make it default null

        Node(int data) {          //constructor bnane se baar baar new node bnane kelie Node temp=new Node();temp.data=val;temp.next=null; ye ni krna pdega
            this.data = data;
        }
    }

    private Node head = null;        //we dont want somebody to change head,tail,numberofNodes so make it private
    private Node tail = null;
    private int NumberOfNodes = 0;  //for size

    public int size() {
        return this.NumberOfNodes;
    }

    public void display() {
        Node temp = head;          // Node temp=new Node(); //hume tou bs ek refrence chahiye
        while (temp != null) {
            System.out.printf("%d ", temp.data);
            temp = temp.next;
        }
        System.out.println();  //same("")
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    //======Add fn
    protected void handelZeroSize(Node node) {   //handleZeroSize addfirst aur addLast dono mein use hojaega
        this.head = node;
        this.tail = node;
    }

    protected void addFirstNode(Node node) {  //if we are given a node then we will call addFirstNode and if given a value then addFirst.It solves two purpose.and also good for future developer so that they can easily implement the fn like reverse List fn.
        if (size() == 0)
            handelZeroSize(node);
        else {
            node.next = this.head;
            this.head = node;
        }

        this.NumberOfNodes++;
    }

    public void addFirst(int data) {  //the work is broken down in the fn because our program is modular now and we can easily find error if occur and can also implement exception handling easily now.
        Node node = new Node(data);
        addFirstNode(node);
    }

    protected void addLastNode(Node node) {
        if (size() == 0)
            handelZeroSize(node);
        else {
            this.tail.next = node;
            this.tail = node;
        }

        this.NumberOfNodes++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        addLastNode(node);
    }

    protected Node getNodeAt(int idx) {  //should be valid idx
        Node temp = this.head;
        while (idx-- > 0) {
            temp = temp.next;
        }
        // idx=4;while(idx-->0)print(idx) o/p:3,2,1,0
        return temp;
    }

    protected void addNodeAt(Node node, int idx) {
        if (idx == 0)
            addFirstNode(node);
        else if (idx == size())
            addLastNode(node);
        else {
            Node prev= getNodeAt(idx - 1);
            Node forw = prev.next;

            prev.next = node;
            node.next = forw;
            this.NumberOfNodes++;
        }
    }

    public void addAt(int data, int idx) throws Exception {
        if (idx < 0 || idx > size()) {
            throw new Exception("Invalid Index");   //user defined exception must tell the fn that this fn can throw exception which is defined by user and also use it in calling fn.
        }

        Node node = new Node(data);
        addNodeAt(node, idx);
    }

    //======Remove fn
    protected void handelSize1() {
        this.head = null;
        this.tail = null;
    }

    protected Node removeFirstNode() {
        Node temp = head;
        if (size() == 1)
            handelSize1();
        else
            this.head = this.head.next;

        temp.next = null;
        this.NumberOfNodes--;
        return temp;
    }

    public int removeFirst() throws Exception {
        if (size() == 0) {
            throw new Exception("LinkedList Is Empty");
        }

        Node node = removeFirstNode();
        return node.data;
    }

    protected Node removeLastNode() {
        Node temp = tail;
        if (size() == 1)
            handelSize1();
        else {
            Node secondLastNode = getNodeAt(size() - 2);   //yahan loop se bdia getNodeAt call
            secondLastNode.next = null;
            this.tail = secondLastNode;
        }

        this.NumberOfNodes--;
        return temp;
    }

    public int removeLast() throws Exception {
        if (size() == 0) {
            throw new Exception("LinkedList Is Empty");
        }

        Node node = removeLastNode();
        return node.data;
    }

    protected Node removeNodeAt(int idx) {
        if (idx == 0)
            return removeFirstNode();
        else if (idx == size() - 1)
            return removeLastNode();
        else {
            Node prevNode = getNodeAt(idx - 1);
            Node removeNode = prevNode.next;
            Node forwardNode = removeNode.next;

            prevNode.next = forwardNode;
            removeNode.next = null;

            this.NumberOfNodes--;
            return removeNode;
        }

    }

    public int removeAt(int idx) throws Exception {
        if (size() == 0)
            throw new Exception("LinkedList Is Empty");
        else if (idx < 0 || idx >= size())
            throw new Exception("Invalid Index");

        Node node = removeNodeAt(idx);
        return node.data;
    }

    public int getFirst() throws Exception {
        if (size() == 0)
            throw new Exception("LinkedList Is Empty");

        return this.head.data;

    }

    public int getLast() throws Exception {
        if (size() == 0)
            throw new Exception("LinkedList Is Empty");
    
        return this.tail.data;
    }

    public int getAt(int idx) throws Exception {
        if (size() == 0)
            throw new Exception("LinkedList Is Empty");
        else if (idx < 0 || idx >= size())
            throw new Exception("Invalid Index");

        return getNodeAt(idx).data;
    }

}