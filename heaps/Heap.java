public class Heap{

    private int[] heap;
    private int size;

    public Heap(int capacity){
      heap = new int[capacity];
    }

    public void insert(int value){
      if(isFull()){
        throw new IndexOutOfBoundsException("Heap is full");
      }

      heap[size] = value;

      siftUp(size);
      size++;
    }

    public int peek(){
      if(isEmpty()){
        throw new IndexOutOfBoundsException("Heap is Empty");
      }

      return heap[0];

    }

    public int remove(int index){
      if(isEmpty()){
        throw new IndexOutOfBoundsException("Heap is Empty");
      }

      int parent = getParent(index);
      int deletedValue = heap[index];

      heap[index] = heap[size-1];

      if(index == 0 || heap[index] < heap[parent]){
        siftDown(index, size - 1);
      }
      else
      {
        siftUp(index);
      }
      size--;

      return deletedValue;

    }

    private void siftDown(int index, int lastHeapIndex){
      int childToSwap;

      while(index <= lastHeapIndex){
        int leftChild = getChild(index,true);
        int rightChild = getChild(index,false);
        //check to see if have children
        if (leftChild <= lastHeapIndex){
          // if only have left pick index
            if(rightChild > lastHeapIndex){
              childToSwap = leftChild;
            }
            //if have right pick index of largest to swap
            else
            {
              childToSwap = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
            }
            //compare values
            if(heap[index] < heap[childToSwap]){
              int tmp = heap[index];
              heap[index] = heap[childToSwap];
              heap[childToSwap] = tmp;
            }
            else
            {
              break;
            }
            index = childToSwap;
        }
        else{
            break;
        }
      }
    }

    public void printHeap(){
      for(int i =0; i<size; i++){
        System.out.print(heap[i]);
        System.out.print(", ");
      }
        System.out.println();
    }
    //sifting up till greater than parent or at root
    private void siftUp(int index){
      //new value entered
      int newValue = heap[index];
      //is index of new value  index greater than root
      // is value greater than parent
      while(index > 0 && newValue > heap[getParent(index)]){
        //swap parent down if smaller
        heap[index] = heap[getParent(index)];
        //new index of new value is now parents
        index = getParent(index);
      }
      //insert new value into heap where none is greater
      heap[index] = newValue;
    }

    public boolean isFull(){
      return size == heap.length;
    }

    public int getParent(int index){
      return (index - 1) / 2;
    }

    public boolean isEmpty(){
      return size == 0;
    }

    public int getChild(int index, boolean left){

        return 2* index *(left ? 1 : 2);

    }
}
