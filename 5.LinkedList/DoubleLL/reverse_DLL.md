# 🔍 Reverse a Doubly Linked List - Recursive Approach

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1)

Given a doubly linked list, reverse the list in-place and return the new head node.

**Constraints:**
- 1 ≤ size of linked list ≤ 1000
- 1 ≤ node.data ≤ 1000

**Example 1:**
```text
Input:
LinkedList: 1<->2<->3<->4  
Output: 4<->3<->2<->1
```

**Example 2:**
```text
Input:
LinkedList: 1<->2  
Output: 2<->1
```

---

## 🧠 Intuition
The solution uses recursion with these key insights:
1. **Pointer Swapping**: At each node, swap `next` and `prev` pointers
2. **Recursive Propagation**: Process the list recursively until the end
3. **Base Case Handling**: Single node case terminates recursion

Key Observations:
- Recursion naturally follows the linked list structure
- Each recursive call handles one node's pointer reversal
- The last node becomes the new head

---

## ⚙️ Approach
### **1️⃣ Base Case**
1. If single node remains, swap its pointers and return it as new head

### **2️⃣ Recursive Step**
1. Store original `next` node temporarily
2. Swap current node's `next` and `prev` pointers
3. Recursively process the stored next node
4. Return the final node from recursion as new head

### **3️⃣ Pointer Updates**
1. Each node's `prev` becomes its original `next`
2. Each node's `next` becomes its original `prev`

---

## ✅ Code Implementation

### Recursive Solution
```java
class Solution {
    public DLLNode reverseDLL(DLLNode head) {
        // Base case: single node or empty list
        if (head == null || head.next == null) {
            if (head != null) {
                DLLNode temp = head.next;
                head.next = head.prev;
                head.prev = temp;
            }
            return head;
        }
        
        // Store next node before pointer swap
        DLLNode nextNode = head.next;
        
        // Swap pointers
        head.next = head.prev;
        head.prev = nextNode;
        
        // Recursively reverse remaining list
        return reverseDLL(nextNode);
    }
}
```
---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Visits each node once |
| **Space**       | O(n)       | Recursion stack depth |

---

## 📊 Example Walkthrough

**Input:** 1<->2<->3<->4

**Execution Steps:**
1. Node 1: swap pointers (next=null, prev=2)
   - Recursively process node 2
2. Node 2: swap pointers (next=1, prev=3)
   - Recursively process node 3
3. Node 3: swap pointers (next=2, prev=4)
   - Recursively process node 4
4. Node 4: base case (next=3, prev=null)
   - Return node 4 as new head

**Result:** 4<->3<->2<->1

---

## 💡 Key Features
- **In-Place Reversal**: Modifies list without extra space
- **Recursive Elegance**: Naturally follows list structure
- **Complete Pointer Handling**: Properly updates both directions
- **Base Case Clarity**: Clean termination condition

---

## 🚀 When to Use
- Doubly linked list reversal
- When recursive solutions are preferred
- Problems requiring in-place modifications

## ⚠️ Edge Cases
- **Empty list**: Returns null
- **Single node**: Returns node with swapped pointers
- **Large lists**: Works within stack limits
- **Circular lists**: Would cause infinite recursion (not handled)

## 🛠 Variations
1. **Iterative Version**:
```java
// Uses constant space
DLLNode current = head;
DLLNode temp = null;
while (current != null) {
    temp = current.prev;
    current.prev = current.next;
    current.next = temp;
    current = current.prev;
}
return temp != null ? temp.prev : head;
```

2. **Partial Reversal**:
```java
// Reverse only a portion of the list
```

3. **Circular List Handling**:
```java
// Additional checks for circular references
```