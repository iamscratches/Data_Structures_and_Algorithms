# 🔍 Linked List Cycle Detection - Floyd's Tortoise and Hare Algorithm

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/linked-list-cycle/description/)

Given a linked list, determine if it contains a cycle. A cycle exists if a node's next pointer points to a previously visited node.

**Constraints:**
- Number of nodes: [0, 10^4]
- Node values: [-10^5, 10^5]
- pos: -1 or valid index (pos=-1 means no cycle)

**Example 1:**
```text
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: Tail connects to node at index 1
```

**Example 2:**
```text
Input: head = [1,2], pos = 0
Output: true
Explanation: Tail connects to head node
```

---

## 🧠 Intuition
The solution uses Floyd's Cycle-Finding Algorithm with these key insights:
1. **Two Pointers**: Slow pointer moves 1 step, fast moves 2 steps
2. **Cycle Detection**: If they meet, cycle exists
3. **Termination**: Fast reaches null if no cycle

Key Observations:
- O(1) space complexity (no extra memory)
- O(n) time complexity (linear traversal)
- Works for any cycle length/position

---

## ⚙️ Approach
### **1️⃣ Initialization**
1. Set slow and fast pointers to head

### **2️⃣ Traversal**
1. Move slow by 1 node, fast by 2 nodes
2. Check for pointer equality at each step

### **3️⃣ Termination Conditions**
1. If fast reaches null → no cycle
2. If pointers meet → cycle detected

---

## ✅ Code Implementation

### Optimized Solution
```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        // Initialize two pointers
        ListNode slow = head;
        ListNode fast = head;
        
        // Traverse the list
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Moves 1 step
            fast = fast.next.next;     // Moves 2 steps
            
            // Cycle detected
            if (slow == fast) {
                return true;
            }
        }
        
        // No cycle found
        return false;
    }
}
```
---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Linear traversal (n = nodes) |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** 3→2→0→-4 (cycle back to 2)

**Execution Steps:**
1. slow=3, fast=3
2. slow=2, fast=0
3. slow=0, fast=2
4. slow=-4, fast=-4 → match → cycle detected

**Result:** true

---

## 💡 Key Features
- **Floyd's Algorithm**: Proven cycle detection method
- **Optimal Performance**: Beats hash table approaches
- **Simple Implementation**: Easy to understand and verify
- **Robust Handling**: Works for all valid inputs

---

## 🚀 When to Use
- Cycle detection in linked structures
- When O(1) space is required
- Problems involving pointer chasing

## ⚠️ Edge Cases
- **Empty list**: Returns false
- **Single node**: Returns false (no next pointer)
- **Full cycle**: Tail connects to head
- **Large lists**: Handles maximum constraints

## 🛠 Variations
1. **Find Cycle Start**:
```java
// After detection, find cycle entrance node
```

2. **Cycle Length**:
```java
// Calculate cycle length after detection
```

3. **Multiple Cycles**:
```java
// For problems with potential multiple cycles
```