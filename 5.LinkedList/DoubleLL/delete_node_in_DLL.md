# 🔍 Delete Node in Doubly Linked List - Efficient Approach

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/delete-node-in-doubly-linked-list/1)

Given a doubly linked list and a position `x`, delete the node at position `x` (1-based indexing).

**Constraints:**
- 1 ≤ size of linked list ≤ 1000
- 1 ≤ x ≤ size of linked list
- 1 ≤ node.data ≤ 1000

**Example 1:**
```text
Input: 
LinkedList: 1<->5<->2<->9  
x = 1
Output: 5<->2<->9
```

**Example 2:**
```text
Input:
LinkedList: 1<->5<->2<->9  
x = 3
Output: 1<->5<->9
```

---

## 🧠 Intuition
The solution handles these key cases:
1. **Head Deletion**: Special case when deleting first node
2. **Middle/Tail Deletion**: Standard doubly linked list node removal
3. **Position Validation**: Ensures position `x` is within bounds

Key Observations:
- Doubly linked lists require updating both `next` and `prev` pointers
- Position indexing starts at 1 (not 0)
- The head pointer may need updating if deleting first node

---

## ⚙️ Approach
### **1️⃣ Head Node Deletion**
1. If `x == 1`, update head to next node
2. Set new head's `prev` to null (if exists)

### **2️⃣ Traversal to Target Node**
1. Move temp pointer to node at position `x`
2. Maintain count to track position

### **3️⃣ Node Removal**
1. Connect previous node to next node
2. Connect next node to previous node (if exists)
3. Bypass target node in both directions

### **4️⃣ Edge Handling**
1. Invalid position → return original list
2. Single node list → return null if deleting only node

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public Node deleteNode(Node head, int x) {
        // Case 1: Delete head node
        if (x == 1) {
            if (head.next != null) {
                head.next.prev = null;
            }
            return head.next;
        }
        
        // Traverse to target node
        Node temp = head;
        int count = 1;
        while (count < x && temp != null) {
            temp = temp.next;
            count++;
        }
        
        // Case 2: Position out of bounds
        if (temp == null) {
            return head;
        }
        
        // Update adjacent nodes' pointers
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        
        return head;
    }
}
```
---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Worst case traverses entire list |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** 1<->5<->2<->9, x = 3

**Execution Steps:**
1. x ≠ 1 → skip head case
2. Traverse:
   - temp=1 (count=1)
   - temp=5 (count=2)
   - temp=2 (count=3) → target found
3. Update pointers:
   - 5.next → 9 (bypass 2)
   - 9.prev → 5 (bypass 2)
4. Return original head

**Result:** 1<->5<->9

---

## 💡 Key Features
- **Complete Handling**: Covers all deletion scenarios
- **Pointer Safety**: Robust null checking
- **Clear Separation**: Distinct cases for head/middle/tail
- **Efficient Traversal**: Only goes as far as needed

---

## 🚀 When to Use
- Doubly linked list manipulation
- Problems requiring node deletion
- When position-based operations are needed

## ⚠️ Edge Cases
- **Single node list**: Returns null when deleting head
- **Last position**: Properly updates tail pointers
- **Invalid position**: Returns unmodified list
- **Consecutive deletions**: Works with multiple calls

## 🛠 Variations
1. **Delete by Value**:
```java
// Delete first node matching value (not position)
```

2. **Circular Doubly List**:
```java
// Additional handling for circular references
```

3. **Bulk Deletion**:
```java
// Delete multiple nodes in one operation
```