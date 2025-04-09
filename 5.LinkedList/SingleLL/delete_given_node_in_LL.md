# 🔍 Delete Node in a Linked List - Efficient Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/delete-node-in-a-linked-list/description/)

Given a node in a singly-linked list, delete that node from the list. You are given only access to that node (not the head of the list).

**Constraints:**
- The number of nodes in the list is in the range [2, 1000]
- -1000 ≤ Node.val ≤ 1000
- The given node is not the tail node (it will always have a next node)

**Example 1:**
```text
Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: Copy 1 to 5's position, then skip original 1
```

**Example 2:**
```text
Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: Copy 9 to 1's position, then set next to null
```

---

## 🧠 Intuition
The solution uses these key insights:
1. **Node Replacement**: Since we can't access previous nodes, we copy the next node's value to the current node
2. **Pointer Adjustment**: We then skip the next node by pointing to node.next.next
3. **Recursive Approach**: The given solution uses recursion to handle the deletion

Key Observations:
- This is an in-place operation with O(1) space complexity
- The recursive approach works but can be simplified iteratively
- We don't need to explicitly handle the tail case due to constraints

---

## ⚙️ Approach
### **1️⃣ Value Copy**
1. Copy the value from the next node to the current node
2. This effectively "deletes" the current node by overwriting its value

### **2️⃣ Pointer Adjustment**
1. Set the current node's next pointer to skip the next node
2. This removes the duplicate value we just copied

### **3️⃣ Edge Handling**
1. The problem guarantees the node isn't tail, so no null checks needed
2. Recursive base case handles the second-to-last node

---

## ✅ Code Implementation

### Optimized Solution (Iterative)
```java
class Solution {
    public void deleteNode(ListNode node) {
        // Copy next node's value
        node.val = node.next.val;
        // Skip the next node
        node.next = node.next.next;
    }
}
```
---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(1)       | Single constant-time operation |
| **Space**       | O(1)       | No extra space used |

---

## 📊 Example Walkthrough

**Input:** head = [4,5,1,9], node = 5

**Execution Steps:**
1. Copy next node's value (1) to current node (5)
   - List becomes [4,1,1,9]
2. Skip the next node (second 1)
   - List becomes [4,1,9]
3. Node "deleted" in place

**Result:** [4,1,9]

---

## 💡 Key Features
- **In-Place Operation**: Modifies list without access to head
- **Efficient**: Single operation completes deletion
- **Constraint Utilization**: Leverages problem guarantees
- **Clean Implementation**: Simple and readable

---

## 🚀 When to Use
- Linked list node deletion problems
- When only node access is available
- Problems requiring in-place modifications

## ⚠️ Edge Cases
- **Second-to-last node**: Handled by problem constraints
- **Minimum list size**: Works for 2-node lists
- **Maximum values**: Handles all value ranges

## 🛠 Variations
1. **Double Linked List**:
```java
// Additional prev pointer adjustment
```

2. **Circular List**:
```java
// Special handling for circular references
```

3. **Delete Multiple Nodes**:
```java
// Extend to delete multiple specified nodes
```