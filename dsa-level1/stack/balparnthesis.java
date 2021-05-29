import java.util.*;
public class balparnthesis {

     public static boolean check(String str)
     {
         Stack<Character> st=new Stack<>();
         for(int i=0;i<str.length();i++)
         {
             char ch=str.charAt(i);
           //  if(ch==' ') continue;

             if(ch=='(' || ch=='{' || ch=='[')
             {
                 st.push(ch);
             }

             else if(ch==')')
             {
                if(st.size()==0 || st.peek()!='(')
                  return false;

                  st.pop();
                  
             }

             else if(ch==']')
             {
                if(st.size()==0 || st.peek()!='[')
                  return false;

                  st.pop();
             }

             else if(ch=='}')
             {
                if(st.size()==0 || st.peek()!='{')
                return false;

                st.pop();
             }
             else
             continue;
          
         }

         return st.size()==0;

     }

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String str=scn.nextLine();
        System.out.println(check(str));
    }
}
