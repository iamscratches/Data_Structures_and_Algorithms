# 🔍 Reverse Linked List - Iterative Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/reverse-linked-list/description/)

Given the head of a singly linked list, reverse the list and return the new head.

**Constraints:**
- The number of nodes in the list is in the range [0, 5000]
- -5000 <= Node.val <= 5000

**Example:**
```text
Input: 1→2→3→4→5
Output: 5→4→3→2→1
```

---

## 🧠 Intuition
The iterative approach uses three pointers to reverse the list in-place:
1. **Current Node (head)**: The node being processed
2. **Previous Node (prev)**: The already reversed part
3. **Next Node (next)**: Temporary storage for the next node

Key Observations:
- Processes one node at a time in O(1) space
- Each iteration reverses one link
- The last node becomes the new head

---

## ⚙️ Approach
### **1️⃣ Initialization**
1. Set `prev` to null (end of reversed list)
2. Start with `head` as current node

### **2️⃣ Iterative Reversal**
1. Store `next` node temporarily
2. Reverse current node's pointer
3. Move `prev` and `head` pointers forward

### **3️⃣ Termination**
1. When `head` becomes null, `prev` is the new head
2. Return `prev`

---

## ✅ Optimized Iterative Solution
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next; // Store next node
            current.next = prev;             // Reverse link
            prev = current;                  // Move prev forward
            current = nextTemp;              // Move current forward
        }
        
        return prev; // New head of reversed list
    }
}
```
---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through list |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** 1→2→3→4→5

**Execution:**
1. Initialize: prev = null, current = 1
2. Iteration 1:
   - nextTemp = 2
   - 1→null
   - prev = 1, current = 2
3. Iteration 2:
   - nextTemp = 3
   - 2→1
   - prev = 2, current = 3
4. Continues until:
   - 5→4→3→2→1
5. Final state: current = null, prev = 5

**Result:** Node(5)

---

## 💡 Key Features
- **In-Place Reversal**: Modifies list without extra space
- **Single Pass**: Efficient O(n) time
- **Pointer Manipulation**: Direct link rewiring
- **Edge Case Handling**: Naturally handles empty/single-node lists

---

## 🚀 When to Use
- Memory-constrained environments
- Large lists where recursion depth is a concern
- Problems requiring O(1) space solutions

## ⚠️ Edge Cases
- **Empty list**: Returns null
- **Single node**: Returns the node itself
- **Large lists**: Handles maximum constraint (5000 nodes)

## 🛠 Variations
1. **Print Reversal Steps**:
```java
// Add print statements to visualize pointer changes
```

2. **Partial Reversal**:
```java
// Reverse only a specified portion of the list
```

3. **Doubly Linked List**:
```java
// Additional prev pointer updates for doubly linked lists
```