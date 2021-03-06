import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] q;
    private int N = 0;
   // private int startLoc = 9;
        
    public RandomizedQueue()
    {
        q = (Item[]) new Object[1];
    }
    
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    public int size()
    {
        return N;
    }
    
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for(int i = 0; i < N; i++)
        {
            copy[i] = q[i];
        }
        q = copy;
    }
    
    public void enqueue(Item item)
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        if( N == q.length)
        {
            resize(2*q.length);
        }
        q[N++] = item;
    }
    
    public Item dequeue()
    {
        if(size() == 0)
        {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(N);
        Item value = q[rand];
        //q[rand] = null;
        if(rand != N-1) {q[rand] = q[N-1];}
        q[N-1] = null;
        N--;
        if(N > 0 && N <= q.length/4)
        {
            resize(q.length/2);
        }
        return value;
    }
    
    public Item sample()
    {
        if(size() == 0)
        {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(N);
        Item value = q[rand];
        if(N > 0 && N == q.length/4)
        {
            resize(q.length/2);
        }
        return value;
    }
    
//    private void findStartLoc()
//    {
//        
//    }
    
    public Iterator<Item> iterator()
    {
        return new RandomIterator();
    }
    
    private class RandomIterator implements Iterator<Item>
    {
        private int randLoc = 0;
        //private int covered = 0;
        private Item[] copy = q;
        private int copySize = copy.length;
        
        public boolean hasNext()
        {
            return copySize > 0;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if(copySize == 0)
            {
                throw new NoSuchElementException();
            }
            randLoc = StdRandom.uniform(N);
            Item currentItem = q[randLoc];
            if(randLoc != copySize-1)
            {
                q[randLoc] = q[copySize-1];
            }
            q[copySize-1] = null;
            copySize--;
            return currentItem;
        }
    }
    
    public static void main(String[] args)
    {
//        RandomizedQueue<String> test = new RandomizedQueue<String>();
//        test.enqueue("to");
//        test.enqueue("be");
//        test.enqueue("or");
//        test.enqueue("not");
//        System.out.print(test.sample());
        
         RandomizedQueue<String> test2 = new RandomizedQueue<String>();
         test2.enqueue("to");
         test2.enqueue("be");
         test2.enqueue("or");
         test2.enqueue("not");
         test2.enqueue("to2");
         test2.enqueue("lol2");
         test2.enqueue("roll2");
         test2.dequeue();
         test2.dequeue();
         
    }
}