import java.io.*;
import java.util.*;
public class getsubseq {

    public static List<List<String>>ans =new ArrayList<>();

  public static void main(String[] args) throws Exception {
       Scanner scn=new Scanner(System.in);
       String s=scn.next();
        subsequence(s);
       System.out.print(ans);
    }

    
    public static void subsequence(String str){
       // if(str.length()==0) return new ArrayList<>();
      //  List<List<String>> ans=new ArrayList<>();
        List<String> result=helper(str);
       // return ans;
    }
    public static List<String> helper(String str){
        if(str.length()==0){
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        
        char ch=str.charAt(0);
        List<String>rres=helper(str.substring(1));

        ArrayList<String> mres=new ArrayList<>();
        ans=new ArrayList<>();
        for(String r:rres){
            mres.add(r);
        }
        for(String r:rres){
            mres.add(ch+r);
        }
        return mres;
        
    }

}