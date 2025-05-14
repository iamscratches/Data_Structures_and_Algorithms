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
    Map<Integer, Node> map;
    Node heaNode, tailNode;

    public LRUCache(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node q = map.get(key);
        moveToHead(q);
        return q.val;
    }
// 9 6 4 5 ->4
    public void put(int key, int value){
        Node q = null;
        if(map.containsKey(key)){
            q = map.get(key);
            q.val = value;
            moveToHead(q);
        }
        else{
            q = new Node(key, value);
            map.put(key, q);
            addToHead(q);
            if(map.size()>capacity){
                map.remove(tailNode.key);
                removeTail();
            }
        }
    }
    
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.next = heaNode;
        node.prev = null;
        if (heaNode != null) heaNode.prev = node;
        heaNode = node;
        if (tailNode == null) tailNode = node;
    }

    private void removeNode(Node node) {
        if (node.prev != null) node.prev.next = node.next;
        else heaNode = node.next;

        if (node.next != null) node.next.prev = node.prev;
        else tailNode = node.prev;
    }

    private void removeTail() {
        if (tailNode == null) return;
        if (tailNode.prev != null) tailNode.prev.next = null;
        else heaNode = null;
        tailNode = tailNode.prev;
    }

    public void removeKey(int key){
        if(map.containsKey(key)){
            Node q = map.get(key);
            removeNode(q);
        }
        else{
            System.out.println("No key-value pair present");
        }
    }

    public void display(){
        System.out.println(map);
        Node q = heaNode;
        while(q!=null){
            System.out.print("\t"+q.key+":"+q.val);
            q=q.next;
        }
        System.out.println();
    }
    class Node{
        int val;
        int key;
        Node prev, next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
            prev=null;
            next=null;
        }
    }
 }



 class Tejas_interview{
    public static void main(String args[]){
        LRUCache cache = new LRUCache(4);
        cache.put(1,1);
        cache.display();
        cache.put(2,2);
        cache.display();
        System.out.println(cache.get(2));
        cache.display();
        System.out.println(cache.get(1));
        cache.display();
        cache.put(3,3);
        cache.display();
        cache.put(4,4);
        cache.display();
        cache.put(5,5);
        cache.display();
        RecursiveApproach df = new RecursiveApproach();

        System.out.println(df.recursive(1));
        System.out.println(df.recursive(2));
        System.out.println(df.recursive(3));
        System.out.println(df.recursive(4));
        System.out.println(df.recursive(5));

        DPApproach obApproach = new DPApproach();
        System.out.println(obApproach.dpApproach(1));
        System.out.println(obApproach.dpApproach(2));
        System.out.println(obApproach.dpApproach(3));
        System.out.println(obApproach.dpApproach(4));
        System.out.println(obApproach.dpApproach(5));
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
// n=5
// 1 1 1 1 1
// 1 1 1 2
// 1 1 2 1
// 1 2 1 1
// 2 1 1 1
// 1 2 2
// 2 1 2
// 2 2 1

class RecursiveApproach{
    public int recursive(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(n-2>0){
            return recursive(n-1) + recursive(n-2);
        }
        else if(n-2==0){
            return recursive(n-1) + 1;
        }
        else{
            return recursive(n-1);
        }
    }
}

class DPApproach{
    public int dpApproach(int n){
        if(n==0){
            return 0;
        }
        int[] dp = new int[n+1];
        dp[0]= 1;
        dp[1]= 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n];
    }
}