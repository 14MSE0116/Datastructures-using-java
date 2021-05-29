import java.util.*;


import java.io.*;

public class evaluate {
    // infix
    public static int infixEvaluation(String str) {

        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<Integer> vstack = new Stack<>(); // value stack

       for(int i=0;i<str.length();i++)
       {
           char ch=str.charAt(i);
           if(ch==' ')
            continue;

            else if(ch >= '0' && ch <= '9') {
               vstack.push(ch-'0');
            }

           else if(ch=='(')
            ostack.push(ch);

            else if(ch==')')
            {
                while(ostack.peek()!='(')
                {
                    int val2=vstack.pop();
                    int val1=vstack.pop();
                    char op=ostack.pop();

                    int res=solveinfix(val1, val2, op);
                     vstack.push(res);
                }
                ostack.pop();
            }

            else{
                while(ostack.size()>0 && ostack.peek()!='(' && priority(ostack.peek()) >= priority(ch) )
                {
                    int val2=vstack.pop();
                    int val1=vstack.pop();
                    char op=ostack.pop();

                    int res=solveinfix(val1, val2, op);
                     vstack.push(res);

                }
                ostack.push(ch);
            }

       }
       while(ostack.size()>0)
       {
        int val2=vstack.pop();
        int val1=vstack.pop();
        char op=ostack.pop();

        int res=solveinfix(val1, val2, op);
         vstack.push(res);
       }
       return vstack.pop();
    }

    public static int priority(char ch)
    {
        if(ch=='*' || ch=='/')
         return 2;
         
         else if(ch=='+' || ch=='-')
         return 1;

         return 0;

    }

    public static int solveinfix(int val1,int val2,char op)
    { 
       
        if(op=='+')
         return val1+val2;

         else if(op=='-')
         return val1-val2;

         else if(op=='*')
         return val1*val2;

         else if(op=='/')
         return val1/val2;

         return 0;
    }

    public static void infixToPrefix(String str) {

        Stack<Character>ostack=new Stack<>();
        Stack<String>vstack=new Stack<>();
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(ch==' ')
            continue;

            else if(ch>='a' && ch<='z')
             vstack.push(""+ch);

            else if(ch=='(')
            ostack.push(ch);
            
            else if(ch==')')
            {
                while(ostack.peek()!='(')
                {
                    String val2=vstack.pop();
                    String val1=vstack.pop();
                    char op=ostack.pop();

                    String res=op+val1+val2;
                    vstack.push(res);
                }
                ostack.pop();
            }
            else{
                while(ostack.size()>0 && ostack.peek()!='(' && priority(ostack.peek()) >= priority(ch))
                {
                    String val2=vstack.pop();
                    String val1=vstack.pop();
                    char op=ostack.pop();

                    String res=op+val1+val2;
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }
        while(ostack.size()>0)
        {
            String val2=vstack.pop();
                    String val1=vstack.pop();
                    char op=ostack.pop();

                    String res=op+val1+val2;
                    vstack.push(res);
        }
        System.out.println(vstack.pop());
    }

    public static void infixToPostfix(String str) {

        Stack<Character>ostack=new Stack<>();
        Stack<String>vstack=new Stack<>();

       for(int i=0;i<str.length();i++)
       {
           char ch=str.charAt(i);

           if(ch==' ')
           continue;

           else if(ch >= 'a' && ch <= 'z') {
            vstack.push(""+ch);
           }

           else if(ch=='(')
           ostack.push(ch);

           else if(ch==')')
           {
               while(ostack.peek()!='(')
               {
                String val2=vstack.pop();
                String val1=vstack.pop();
                    char op=ostack.pop();

                    String res=val1+val2+op;
                    vstack.push(res);
               }
               ostack.pop();
           }
           else{
               while(ostack.size()>0 && ostack.peek()!='(' && priority(ostack.peek()) >= priority(ch) )
               {
                String val2=vstack.pop();
                String val1=vstack.pop();
                   char op=ostack.pop();
                   String res=val1+val2+op;
                   vstack.push(res);

               }
               ostack.push(ch); 

           }
       }
       while(ostack.size()>0)
       {
        String val2=vstack.pop();
        String val1=vstack.pop();
        char op=ostack.pop();
        String res=val1+val2+op;
        vstack.push(res);
       }
       System.out.println(vstack.pop());

    }

    // prefix
    public static void prefixEvaluation(String str) {

        Stack<Integer>vstack=new Stack<>();
        for(int i=str.length()-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch>='0' && ch<='9')
             {
                 vstack.push((int)ch-'0');
             }
             else
             {
                 int val1=vstack.pop();
                 int val2=vstack.pop();
                 int res=solveinfix(val1,val2,ch);
                 vstack.push(res);
             }
        }
        System.out.println(vstack.pop());

    }

    public static void prefixToInfix(String str) {
        Stack<String>vStack=new Stack<>();
        for(int i=str.length()-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch>='0' && ch<='9')
            {
                vStack.push(""+ch);
            }
            else
            {
               String val1=vStack.pop();
               String val2=vStack.pop();
               String res="("+val1+ch+val2+")" ;
               vStack.push(res);
            }
        }
        System.out.println(vStack.pop());

    }

    public static void prefixToPostfix(String str) {
        Stack<String>vStack=new Stack<>();
        for(int i=str.length()-1;i>=0;i--)
        {
            char ch=str.charAt(i);
            if(ch>='0' && ch<='9')
            {
                vStack.push(""+ch);
            }
            else
            {
               String val1=vStack.pop();
               String val2=vStack.pop();
               String res=val1+val2+ch ;
               vStack.push(res);
            }
        }
        System.out.println(vStack.pop());

    }

    // postfix
    public static void postfixEvaluation(String str) {

        Stack<Integer>ostack=new Stack<>();
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(ch>='0' && ch<='9')
              ostack.push(ch-'0');
            
              else
                   {
                  
                      int val2=ostack.pop();
                      int val1=ostack.pop();
                       int res=solveinfix(val1,val2,ch);
                       ostack.push(res);
                  }
        }
        System.out.println(ostack.pop());



        }

    

    public static void postfixToPrefix(String str) {

        Stack<String>vstack=new Stack<>();
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(ch>='0' && ch<='9')
             vstack.push(""+ch);

             else{
               String val2=vstack.pop();
               String val1=vstack.pop();
               String res=ch+val1+val2;
               vstack.push(res);
             }
        }
        System.out.println(vstack.pop());

    }

    public static void postixToinfix(String str) {

        Stack<String>ostack=new Stack<>();
        for(int i=0;i<str.length();i++)
        {
            char ch=str.charAt(i);
            if(ch>='0' && ch<='9')
            {
                ostack.push(""+ch);
            }
            else
            {
               String val2=ostack.pop();
               String val1=ostack.pop();
               String res="("+val1+ch+val2+")";
               ostack.push(res);



               
            }
        }
        System.out.println(ostack.pop());

    }
    
    // public static void evaluation() {
    //     String str = "";

    // }
    
    public static void main(String[] args) {
        // System.out.println(infixEvaluation("2 + 6 * 4 / 8 - 3"));    
        // infixToPostfix("a*(b-c+d)/e");
        // infixToPrefix("a*(b-c+d)/e");
        // postfixEvaluation("264*8/+3-");
        // postixToinfix("264*8/+3-");
        // postfixToPrefix("264*8/+3-");
        prefixEvaluation("-+2/*6483");
        prefixToInfix("-+2/*6483");
        prefixToPostfix("-+2/*6483");
    }
}
