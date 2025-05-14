/*
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
Implement the LRUCache class:
LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.
 
Example 1:
Input["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]Output[null, null, null, 1, null, -1, null, -1, 3, 4]
 */

import java.util.*;

 class LRUCache{
    int capacity;
    Map<Integer, Dequeue> map;
    Dequeue dList=null;

    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key){
        if(map.containsKey(key)){
            Dequeue q = map.get(key);
            if(q.next!=null){
                q.next.prev = q.prev;
            }
            if(q.prev!=null){
                q.prev.next = q.next;
            }
            if(q!=dList){
                dList.prev = q;
                q.next = dList;
                q.prev = null;
                dList = q;
            }
            return q.val;
        }
        return -1;
    }
// 9 6 4 5 ->4
    public void put(int key, int value){
        Dequeue q = null;
        if(map.containsKey(key)){
            q = map.get(key);
            q.val = value;
            if(q.prev!=null){
                q.prev.next = q.next;
            }
            if(q.next!=null){
                q.next.prev = q.prev;
            }
            if(q!=dList){
                dList.prev = q;
                q.next = dList;
                q.prev = null;
                dList = q;
            }
        }
        else{
            q = new Dequeue(value);
            q.key = key;
            map.put(key, q);
            if(dList!=null){
                dList.prev=q;
            }
            q.next = dList;
            dList = q;
            if(map.size()>capacity){
                q = dList;
                while(q.next!=null){
                    q=q.next;
                }
                map.remove(q.key);
                if(q.prev!=null){
                    q.prev.next=null;
                }
                else{
                    dList=null;
                }
            }
            
        }
    }

    public void removeKey(int key){
        if(map.containsKey(key)){
            Dequeue q = map.get(key);
            if(q.prev!=null){
                q.prev.next = q.next;
            }
            if(q.next!=null){
                q.next.prev = q.prev;
            }
            map.remove(key);
        }
        else{
            System.out.println("No key-value pair present");
        }
    }

    public void display(){
        System.out.println(map);
        Dequeue q = dList;
        while(q!=null){
            System.out.print("\t"+q.key+":"+q.val);
            q=q.next;
        }
        System.out.println();
    }
 }

 class Dequeue{
    int val;
    int key;
    Dequeue prev, next;
    public Dequeue(int val){
        this.val = val;
        prev=null;
        next=null;
    }
 }

 class dummy{
    public static void main(String args[]){
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.display();
        System.out.println(cache.get(2));
        cache.display();
        System.out.println(cache.get(1));
        cache.display();
        // cache.put(3,3);
        // System.out.println("working");
        // cache.display();
        // DummyFunction df = new DummyFunction();
        // System.out.println(df.recursive(0));
    }
 }

/*
 * You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 
Example 1:
Input: n = 2 Output: 2 Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:
Input: n = 3Output: 3Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 
 */

class DummyFunction{
    public int recursive(int n){
        if(n==0){
            return 1;
        }
        if(n-2>=0){
            return recursive(n-1) + recursive(n-2);
        }
        else{
            return recursive(n-1);
        }
    }
}