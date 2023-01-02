/**
 * MinHeap.java
 * A generic MinHeap implementation. Is probably trash.
 * @author Mr. Kramer
 */
import java.util.ArrayList;
public class MinHeap <T extends Comparable>
{
    ArrayList <T> arr = new ArrayList<T>();
    
    /**
     * insert an object into the heap
     * @param object to insert
     */
    void insert(T value) {
        // add value to end
        arr.add(value);
        int curr = arr.size() - 1;
        // sift the value up
        int parent = ( curr - 1 ) / 2;
        while (parent >= 0 && arr.get(curr).compareTo(arr.get(parent)) < 0){
            T temp = arr.get(curr);
            arr.set(curr, arr.get(parent));
            arr.set(parent, temp);
            curr = parent;
            parent = ( curr - 1 ) / 2;
        }
    }

    /**
     * delete the top of the heap and return it
     * @return object at the top of the heap or null if heap is empty
     */
    T delete() {
        if (arr.size() == 0) return null;
        
        T returnMe = arr.get(0);
        
        // replace root with last element
        arr.set(0, arr.get(arr.size() - 1));
        //remove the last element
        arr.remove(arr.size() - 1);
        
        int curr = 0;
        
        // sift down
        boolean done = false;
        while (!done) {
            int leftChild = curr * 2 + 1;
            int rightChild = curr * 2 + 2;
            int swapChild = arr.size() - 1 ;
            if (leftChild >= arr.size()) {
                done = true;
            } else if (rightChild >= arr.size() ||
                arr.get(leftChild).compareTo(arr.get(rightChild)) < 0) {
                // left child is smaller
                swapChild = leftChild;
            } else {
                // right child is smaller
                swapChild = rightChild;
            }
            
            if (!done && arr.get(swapChild).compareTo(arr.get(curr)) < 0) {
                T temp = arr.get(curr);
                arr.set(curr,arr.get(swapChild));
                arr.set(swapChild, temp);
                curr = swapChild;
            } else {
                done = true;
            }
        }
        
        return returnMe;
    }

    /**
     * return the object at the top of the heap without deleting
     * @return object at top of heap or null if heap is empty
     */
    T peek() {
        if (arr.size() == 0) return null;
        return arr.get(0);
    }
}
