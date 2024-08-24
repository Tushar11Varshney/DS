import java.util.LinkedList;

public class adapter {

    public static class LLToStackAdapter { 
        LinkedList<Integer> list;

        public LLToStackAdapter() {
            list = new LinkedList<>();
        }

        int size() {
            return list.size();
        }

        void push(int val) {
            list.add(val); // addFirst
        }

        int pop() {
            if (size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return list.pollLast(); // removeFirst/pollFirst //removeLast throw exception if list empty and this return null.
        }

        int top() {
            if (size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return list.peekLast();  //return null if list empty
        } // getFirst()
    }

    public static class LLToQueueAdapter {
        LinkedList<Integer> list;

        public LLToQueueAdapter() {
            list = new LinkedList<>();
        }

        int size() {
            return list.size();
        }

        void add(int val) {
            list.add(val);
        }

        int remove() {
            if (size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return list.removeFirst();
        }

        int peek() {
            if (size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return list.peekFirst();
        }
    } 
}
