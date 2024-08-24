package basicImplementation;
public class client{
    public static void main(String[] args)throws Exception{
        /*stack st = new stack();

        st.push(10);
        System.out.println(st.peek());
        System.out.println(st.pop()); */
        // System.out.println(st.peek());
        // System.out.print("Hello");

        dynamicStack st= new dynamicStack();
        st.push(10);st.push(20);st.push(30);st.push(40);st.push(60);
        System.out.println(st.pop());System.out.println(st.pop());
        System.out.println(st.pop());System.out.println(st.pop());
        System.out.println(st.pop());
    }
}