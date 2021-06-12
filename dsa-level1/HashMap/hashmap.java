import java.io.*;
import java.util.*;
public class hashmap {

    public static void getfreq(String str)
    {
        HashMap<Character,Integer>map=new HashMap<>();
        for(int i=0;i<str.length();i++)
        {
            if(map.containsKey(str.charAt(i)))
            {
                map.put(str.charAt(i),map.get(str.charAt(i))+1);
            }
            else
            {
                map.put(str.charAt(i),1);
            }
        }
        
        int max=0;
        char ch='a';
        for(char key:map.keySet())
        {
            if(map.get(key)>max)
            {
             max=map.get(key);
             ch=key;
            }
        }
        System.out.println(ch);
    }

    public static void getcommonelement1(int arr1[],int arr2[])
    {
        HashMap<Integer,Integer>map1=new HashMap<>();
        HashMap<Integer,Integer>map2=new HashMap<>();

        for(int i=0;i<arr1.length;i++)
        {
         map1.put(arr1[i], 1);
        }

       for(int i=0;i<arr2.length;i++)
       {
           int d=arr2[i];
           if(map1.get(d)!=null)
           {
               System.out.println(d);
               map1.remove(d);
           }
       }
        
    }

    public static void lcs(int arr[])
    {
        HashMap<Integer,Boolean>map=new HashMap<>();
        
        //set hashmap
        for(int i=0;i<arr.length;i++)
         map.put(arr[i], true);

         //check if arr[i]-1 exisi and\ set false accordingly

         for(int val:arr)
         {
            if(map.containsKey(val-1))
             map.put(val, false);
         }

         //get starting poing and length;
         int msp=0;
         int ml=0;
         for(int val:arr)
         {
             if(map.get(val)==true)
             {
                 int sp=val;
                 int l=1;
                 while(map.containsKey(sp+1))
                 {
                     sp=sp+1;
                     l=l+1;
                 }
                 if(l>ml)
                 {
                     ml=l;
                     msp=val;
                 }
             }
         }

         for(int i=0;i<ml;i++)
          System.out.println(msp+i);

    }

    public static void getcommonelement2(int arr1[],int arr2[])
    {
        HashMap<Integer,Integer>map1=new HashMap<>();

        for(int i=0;i<arr1.length;i++)
        {
          int d=arr1[i];
          if(map1.get(d)!=null)
          {
              map1.put(d, map1.get(d)+1);

          }
          else
          {
              map1.put(d, 1);
          }
        }

       for(int i=0;i<arr2.length;i++)
       {
           int d=arr2[i];
           if(map1.get(d)!=null && map1.get(d)>=1)
           {
               System.out.println(d);
               map1.put(d, map1.get(d)-1);
           }
       }
        
    }
    
    public static void demo()
    {
        HashMap<Character,Integer>map=new HashMap<>();
        //frequency map
        map.put('a', 1);
        map.put('b', 2);
        map.put('c', 3);
        map.put('d', 4);
        System.out.println(map);
        map.put('a', 5);
        map.put('b', 20);
        System.out.println(map);
        System.out.println(map.get('m'));

        System.out.println(map.containsKey('a'));

        //3get all keys and iterate
        Set<Character>st=map.keySet();
        for(Character key : st)
        {
            System.out.println(key+" "+map.get(key));
        }

        //removal 
        System.out.println(map.remove('a'));

    }

    public static void ques()
    {
        String str="sefds";
        getfreq(str);
    }
    public static void main(String[] args) {
        // demo();
        ques();
    }
}
