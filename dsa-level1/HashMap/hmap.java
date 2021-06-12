import java.util.*;

import java.io.*;
class hashhmap{
    private class Node
    {
        String key;
        int value;
        public Node(String key,int value)
        {
            this.key=key;
            this.value=value;
        }
    }
    private int size=0;
    private LinkedList<Node>[]bucket;

    private void init(int cap)
    {
        bucket=new LinkedList[cap];
        //initialisze every location of buckect 
        for(int i=0;i<cap;i++)
        {
            bucket[i]=new LinkedList<>();
        }
    }
    public hashhmap()
    {
        init(4);
    }

    private int hashFunction(String key)
    {
        int bi=Math.abs(key.hashCode()) % bucket.length;
        //0<=bi<bucket.length
        return bi;
    }

    private int searchInBucket(String key,int bi)
    {
        int di=0;
        for(Node node:bucket[bi])
        {
           
            if(node.key.equals(key)==true)
            {
                return di;
            }
            di++;
        }
        return -1;
    }

    public void put(String key,int value)
    {

        int bi=hashFunction(key);

        int di=searchInBucket(key,bi);

       
        if(di==-1)
        {//not present-insert
            Node nn=new Node(key,value);
            bucket[bi].addLast(nn);
            this.size++;
        }

        else
        {
            //present->update value
            bucket[bi].get(di).value=value;
        }

        int n=size;
        int N=bucket.length;
        double lambda=n*1.0/N ;
        if(lambda>2)
        {
            rehash();
        }
    }

    private void rehash()
    {
        LinkedList<Node>[]ob=bucket;
        init(2*bucket.length);

        //travel on old bucket and fill in buckert
        for(int i=0;i<ob.length;i++)
        {
            for(Node node:ob[i])
            {
                put(node.key, node.value);
            }
        }
    }

    public int remove(String key)
    {
        int bi=hashFunction(key);
        int di=searchInBucket(key, bi);

        if(di==-1)
         return -1;  

         else
         {
             //remove node in linked list
             Node rem=bucket[bi].remove(di);
             this.size--;

             //return value
             return rem.value;
         }

    }

    public int get(String key)
    {

        int bi=hashFunction(key);
        int di=searchInBucket(key, bi);

        if(di==-1)
         return -1;  

         else
         {
             //remove node in linked list
             Node node=bucket[bi].get(di);

             //return value
             return node.value;
         }


    }

    public boolean containsKey(String key)
    {
        int bi=hashFunction(key);
        int di=searchInBucket(key, bi);

        if(di==-1)
         return false;  

         else
         {
             //remove node in linked list
             return true;
         }

    }
    public ArrayList<String>keySet()
    {
        ArrayList<String>list=new ArrayList<>();
        for(int bi=0;bi<bucket.length;bi++)
        {
            for(Node node:bucket[bi])
            {
                list.add(node.key);
            }
        }

        return list;
    }
    public int size()
    {
        return this.size;
    }

    public void display()
    {
        for(int bi=0;bi<bucket.length;bi++)
        {
            for(Node node:bucket[bi])
            {
                System.out.println("["+node.key+"="+node.value+"],");
            }
        }

    }

    public void hashmapView()
    {
        for(int bi=0;bi<bucket.length;bi++)
        {
            System.out.print("bucket:"+bi+"->");
            for(Node node:bucket[bi])
            {
                System.out.print("["+node.key+"="+node.value+"],");
            }
            System.out.println();
        }
    }
}
public class hmap {
    public static void fun()
    {
        hashhmap map=new hashhmap();
        map.put("India",125);
        map.put("pak", 90);
        map.put("US", 70);
        map.put("Australia", 100);
        map.display();
        map.hashmapView();
        map.put("India", 135);
        map.put("Australia", 99);
        map.display();
        map.hashmapView();
    }
    public static void main(String[] args) {
        fun();
    }
    
}
