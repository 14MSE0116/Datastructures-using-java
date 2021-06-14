import  java.io.*;
import java.util.*;

class h_map<k,v>
{
  private class Node{
      k key;
      v value;
      public Node(k key,v value)
      {
          this.key=key;
          this.value=value;
      }
  }

  LinkedList<Node>bucket[];
  private int size=0;
  private void init(int cap)
  {
     bucket=new LinkedList[cap];
     for(int i=0;i<cap;i++)
      bucket[i]=new LinkedList<>();
  }

  public int size()
  {
      return this.size;
  }

  public h_map()
  {
      init(4);
  }

  private int hashFunc(k key)
  {
    int bi=Math.abs(key.hashCode()) % bucket.length;
      return bi;
  }

  private int searchinbucket(k key,int bi)
  {
      int di=0;
      for(Node node:bucket[bi])
      {
        if(node.key.equals(key)==true)
         return di;

         di++;
      }
      return -1;
  }

  private void rehash()
  {
      LinkedList<Node>ob[]=bucket;
      init(2*bucket.length);
      for(int i=0;i<ob.length;i++)
      {
          for(Node node:ob[i])
          {
              put(node.key,node.value);
          }
      }
  }

  public void put(k key,v value)
  {
      int bi=hashFunc(key);
      int di=searchinbucket(key,bi);

      if(di==-1)
      {
        Node nn=new Node(key, value);
        bucket[bi].addLast(nn);
        this.size++;
      }
      else
      {
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

  public v remove(k key)
  {
      int bi=hashFunc(key);
      int di=searchinbucket(key, bi);
      if(di==-1)
      {
          return null;
      }
      else
      {
          Node rem=bucket[bi].remove(di);
          this.size--;
          return rem.value;
      }
  }

  public v get(k key)
  {
    int bi=hashFunc(key);
    int di=searchinbucket(key, bi);
    if(di==-1)
    {
        return null;
    }
    else
    {
        Node rem=bucket[bi].get(di);
        return rem.value;
    }
  }

  public boolean containsKey(k key)
  {
      int bi=hashFunc(key);
      int di=searchinbucket(key, bi);
      if(di==-1)
       return false;
       
       else
       return true;
  }

  public ArrayList<k>keySet()
  {
      ArrayList<k>list=new ArrayList<>();
      for(int bi=0;bi<bucket.length;bi++)
      {
          for (Node node : bucket[bi])
            list.add(node.key);
      }
      return list;
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
public class practice {
    public static void main(String[] args) {
        h_map<String,Integer> map=new h_map();
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
}
