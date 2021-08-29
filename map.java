import java.util.*;
public class HelloWorld{

==============Question one================
import java.util.*;
public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        TimeMap1 timeMap1 = new TimeMap1();
        System.out.println(timeMap1.get(""));
        System.out.println(timeMap1.get("hell0"));
        
        
        timeMap1.set("hello", "hello_one");
        System.out.println(timeMap1.get("hello"));
        timeMap1.set("hello", "hello_two");
        System.out.println(timeMap1.get("hello"));
        
     }
    // question here
    // if same key, value will override????
    // if there is no key, return null string???
  static class TimeMap1 {
    Map<String, String> map;
    /** Initialize your data structure here. */
    public TimeMap1() {
       map = new HashMap<>();
    }
    
    public void set(String key, String value) {
      map.put(key, value);
    }
    
    public String get(String key) {
        if (!map.containsKey(key)){
            return "";
        }
        return map.get(key);
    }
  }
}    
===============Question two ===================
     // question here
     // what is the timeStamp type, string or integer
     // what able the same key, return latest value????
     
     // get(key) time is o(n)
     // get(key, timestamp) time is o(1)
  static class TimeMap2 {
    
    Map<String, HashMap<Long, String>> map;
    
    /** Initialize your data structure here. */
    public TimeMap2() {
       map = new HashMap<>();
    }
    
    public void set(String key, String value) {
        // timeStamp is system timeStamp
         
        Long timestamp = System.currentTimeMillis();
        map.putIfAbsent(key, new HashMap<>());
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key) {
        if (!map.containsKey(key)){
            return "";
        }
         String result = "";
         HashMap<Long, String> currMap = map.get(key);
         for(Map.Entry m : currMap.entrySet()){    
             result = (String)m.getValue();
          }  
         
         return result;
    }
    public String get(String key, long timestamp) {
        if (!map.containsKey(key)){
            return "";
        }
        Map<Long, String> currMap = map.get(key);
        
        if (currMap.containsKey(timestamp)){
           return  currMap.get(timestamp);
        }
          
        return  "";
    }
  }
  
 ***************solution two get(key) is o(1)***************
  
import java.util.*;
public class HelloWorld{

     public static void main(String []args)  throws InterruptedException{
        System.out.println("Hello World");
        TimeMap2 timeMap2 = new TimeMap2();
        System.out.println(timeMap2.get(""));
        System.out.println(timeMap2.get("hello"));
        
        
        timeMap2.set("hello", "hello_one");
        System.out.println(timeMap2.get("hello"));
        
        
        Long timeStamp1 = System.currentTimeMillis();
        System.out.println(timeStamp1);
        System.out.println( timeMap2.set("hello", "hello_two"));
        
         Thread.sleep(4000);
        
        
        Long timeStamp2 = System.currentTimeMillis();
        System.out.println(timeStamp2);
        System.out.println(timeMap2.set("hello", "hello_three"));
        
        
         System.out.println(timeMap2.get("hello", timeStamp1));
        
         System.out.println(timeMap2.get("hello", timeStamp2));
         
         
          System.out.println(timeMap2.set("hello", "hello_four"));
          System.out.println(timeMap2.get("hello"));
        
     }
     
     
   static class TimeMap2 {
      static class Node {
        String key;
        String value;
        long timeStamp;
        Node (String key, String value, long timeStamp) {
            this.key = key;
            this.value = value;
            this.timeStamp = timeStamp;
        }
    }
    
    Map<String, List<Node>> map = new HashMap<>();
    Map<String, HashMap<Long, String>> timeToValueMap = new HashMap<>();
    
   
   
    public Long set(String key, String value) {
         // using sytem time stamp
        Long timeStamp = System.currentTimeMillis();
        List<Node> list = map.getOrDefault(key, new ArrayList<>());
        list.add(new Node(key, value, timeStamp));
        map.put(key, list);
        
        timeToValueMap.putIfAbsent(key, new HashMap<>());
        timeToValueMap.get(key).put(timeStamp, value);
        
        return timeStamp;
    }
    
    public  String get(String key, long timeStamp) {
         List<Node> list = map.get(key);
        if (list == null || list.isEmpty()){
            return "";
        }
        Map<Long, String> currMap = timeToValueMap.get(key);
        
        if (!currMap.containsKey(timeStamp)){
            return "";
        }
          
        return  currMap.get(timeStamp);
                     
    }
    public  String get(String key) {
        List<Node> list = map.get(key);
        if (list == null || list.isEmpty()){
            return "";
        }
        return list.get(list.size() - 1).value; 
    }
      
  }
}
  
  
  //==============================question three==================================
     
     
    class TimeMap {
    
    // treemap search is O(logn)
    // treemap insert is o(nlogn)
    Map<String, TreeMap<Integer, String>> map;
    /** Initialize your data structure here. */
    public TimeMap() {
       map = new HashMap<>();
    }
    // treemap set is O(logn )
    
    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)){
            return "";
        }
        TreeMap<Integer, String> treeMap = map.get(key);
        
        
        // using Integer to avoid NPE
        Integer target = treeMap.floorKey(timestamp); 
        return target == null ? "" : treeMap.get(target);
    }
  }
  
 // ***************solution two without treemap***************
// 时间复杂度：set 操作的复杂度为 O(1)O(1)；get 操作的复杂度为 O(\log{n})O(logn)
// 空间复杂度：O(n)O(n)
   public class HelloWorld{

     public static void main(String []args)  throws InterruptedException{
        
        TimeMap3 timeMap3 = new TimeMap3();
        
        System.out.println(timeMap3.get("hello"));
        
        long time1 = timeMap3.set("hello", "one");
        System.out.println(time1);
        
        Thread.sleep(4000);
        long time2 = timeMap3.set("hello", "two");
        System.out.println(time2);
        
        System.out.println("get is " + timeMap3.get("hello", time2));
         System.out.println("get is " + timeMap3.get("hello", time2 + 100));
        
        
         System.out.println("get is" + timeMap3.get("hello", time1 - 100));
         System.out.println("get is " + timeMap3.get("hello", time1 + 1));
         
          System.out.println("get is " + timeMap3.get("hello"));
        
     }
     
     
  static class TimeMap3 {
    private Map<String, List<Node>> map;
    public TimeMap3() {
        map = new HashMap<>();
    }
 
    public Long set(String key, String value) {
        List<Node> nodes = map.get(key);
        if (nodes == null) {
            nodes = new ArrayList<Node>();
            map.put(key, nodes);
        }
        Long t = System.currentTimeMillis();
        nodes.add(new Node(key, value, t));
        return t;
    }
     
    public String get(String key, long timestamp) {
        List<Node> nodes = map.get(key);
        if (nodes == null || nodes.isEmpty()) {
            return "";
        } else {
            return search(nodes, timestamp);
        }
    }
     
    public String get(String key) {
        List<Node> nodes = map.get(key);
        if (nodes == null || nodes.isEmpty()) {
            return null;
        } else {
            return nodes.get(nodes.size() - 1).value;
        }
    }
     
  public String search(List<Node> nodes, long timeStamp) {
       
        int low =0;
         int high = nodes.size() -1;

        // Binary search through
         while(low + 1 < high){
             int mid = (high + low)/2;
             Node midTerm = nodes.get(mid);
             if(midTerm.timeStamp == timeStamp){
                 return midTerm.value;
             } else if( midTerm.timeStamp < timeStamp){
                 low = mid;
             }else{
                 high = mid;
             }
         }
         // Now left pointer is less than right pointer
         // Check right pointer <= timerstamp as we want to get the greatest time smaller than the timestamp
        if(nodes.get(high).timeStamp <= timeStamp){
            return nodes.get(high).value;
        //  Check left
        }else if(nodes.get(low).timeStamp <= timeStamp){
            return nodes.get(low).value;
        }
         // Must be lower than the lowest possible value
            return "";                
    }
}
 
 static class Node {
        String key;
        String value;
        long timeStamp;
        Node (String key, String value, long timeStamp) {
            this.key = key;
            this.value = value;
            this.timeStamp = timeStamp;
        }
    }


}
