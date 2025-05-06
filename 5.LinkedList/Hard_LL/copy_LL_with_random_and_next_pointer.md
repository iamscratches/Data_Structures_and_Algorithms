# 🔗 Copy List with Random Pointer - Hash Map Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/copy-list-with-random-pointer/description/)

Given a linked list where each node contains an additional random pointer which could point to any node in the list or null, return a deep copy of the list.

**Constraints:**
- 0 ≤ Number of nodes ≤ 1000
- -10⁴ ≤ Node.val ≤ 10⁴
- Node.random is null or points to a node in the linked list

**Example:**
```text
Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
```

## 🧠 Intuition
The solution uses:
1. **Hash Map**: Creates mapping between original and copied nodes
2. **Two-Pass Algorithm**: First creates nodes, then connects pointers
3. **Deep Copy**: Ensures no references to original list remain

Key Insights:
- Need to preserve relationships between nodes
- Random pointers complicate direct copying
- Hash map provides O(1) access to copied nodes

## ⚙️ Approach
1. **First Pass**:
   - Create new nodes and store original→copy mapping
2. **Second Pass**:
   - Connect next and random pointers using the map
3. **Result**:
   - Return head of copied list from the map

## ✅ Optimized Solution
```java
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        Map<Node, Node> nodeMap = new HashMap<>();
        Node current = head;
        
        // First pass: create all nodes
        while (current != null) {
            nodeMap.put(current, new Node(current.val));
            current = current.next;
        }
        
        // Second pass: connect pointers
        current = head;
        while (current != null) {
            Node copy = nodeMap.get(current);
            copy.next = nodeMap.get(current.next);
            copy.random = nodeMap.get(current.random);
            current = current.next;
        }
        
        return nodeMap.get(head);
    }
}
```

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Two passes through list |
| **Space**       | O(n)       | Hash map storage |

## 📊 Example Walkthrough
**Input:** 
```
Original: 7 → 13 → 11 → 10 → 1
Random:   7 → 13 → 11 → 10 → 1
           ↓    ↓    ↓    ↓    ↓
          null  7    1    11   7
```

**Execution:**
1. First pass creates all new nodes in hash map
2. Second pass connects:
   - Next pointers: 7→13→11→10→1→null
   - Random pointers: same as original
3. Returns deep copy with identical structure

## 💡 Key Features
- **Correctness**: Preserves exact structure
- **Efficiency**: Linear time complexity
- **Simplicity**: Clear two-step process
- **Generality**: Works for any list configuration

## 🚀 When to Use
- Linked list copying problems
- When references complicate copying
- Graph copying with cycles
- Any deep copy scenario with references

## ⚠️ Edge Cases
- **Empty List**: Returns null
- **Single Node**: Handles correctly
- **Self-Referential**: Node.random points to self
- **Cycles**: Handles circular references

## 🛠 Variations
1. **O(1) Space Solution**:
```java
// Interleave original and copied nodes
```

2. **Recursive Version**:
```java
// Use recursion with memoization
```

3. **Serialization**:
```java
// Serialize then deserialize for deep copy
```

4. **Thread-Safe**:
```java
// Make solution thread-safe
```

5. **Visualization**:
```java
// Generate graphical representation
```

6. **Generic Version**:
```java
// Extend to any object graph
```

## Alternative Implementation (O(1) Space)
```java
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // First pass: interleave nodes
        Node current = head;
        while (current != null) {
            Node copy = new Node(current.val);
            copy.next = current.next;
            current.next = copy;
            current = copy.next;
        }
        
        // Second pass: set random pointers
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
        
        // Third pass: separate lists
        Node dummy = new Node(0);
        Node copyPrev = dummy;
        current = head;
        while (current != null) {
            copyPrev.next = current.next;
            copyPrev = copyPrev.next;
            current.next = current.next.next;
            current = current.next;
        }
        
        return dummy.next;
    }
}
```