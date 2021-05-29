import java.util.*;
public class duplicatebracket { 
     
    public static boolean isdup(String str)
    {
        Stack<Character> st=new Stack<>();

        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(ch!=')')
              st.push(ch);
             else{
                 if(st.peek()=='(')
                  return true;

                  while(st.peek()!='(')
                  {
                      st.pop();
                  }

                  st.pop();
             }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String s=scn.nextLine();

        System.out.println(isdup(s));

    }
    
}
