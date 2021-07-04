import java.lang.reflect.Array;
import java.util.*;
public class exe {
  public static void main(String[] args) {
     HashMap<String,Integer>props=new HashMap<>();
     props.put("key45", 12);
     props.put("key12", 122);
     Set s=props.keySet();
     Array.sort(s);
     System.out.println(s);
  }
}
