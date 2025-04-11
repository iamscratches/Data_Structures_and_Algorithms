# 🔍 Delete All Occurrences of a Key in Doubly Linked List - Efficient Approach

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1)

Given a doubly linked list and a key `x`, delete all nodes containing the key `x` and return the modified list.

**Constraints:**
- 1 ≤ Number of nodes ≤ 10^5
- 1 ≤ Node value ≤ 10^9
- 1 ≤ x ≤ 10^9

**Example:**
```text
Input: 2<->2<->10<->8<->4<->2<->5<->2, x = 2
Output: 10<->8<->4<->5
```

---

## 🧠 Intuition
The solution handles these key aspects:
1. **Head Node Handling**: Special case for removing occurrences at head
2. **Pointer Adjustment**: Properly updates both `next` and `prev` pointers
3. **In-Place Modification**: Operates directly on the list without extra space

Key Observations:
- Must handle consecutive occurrences of the key
- Need to maintain doubly linked list integrity
- Edge cases include empty list and all nodes containing the key

---

## ⚙️ Approach
### **1️⃣ Remove Head Occurrences**
1. Skip all head nodes matching `x`
2. Set new head's `prev` to null if exists

### **2️⃣ Traverse and Remove**
1. Iterate through the list
2. For each node matching `x`:
   - Bypass it by connecting its neighbors
   - Update both forward and backward pointers

### **3️⃣ Return Modified List**
1. Return the (potentially new) head node
2. Handles case where all nodes were removed

---

## ✅ Optimized Solution
```java
class Solution {
    static Node deleteAllOccurOfX(Node head, int x) {
        // Remove all occurrences at head
        while (head != null && head.data == x) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        }
        
        // Early return if list becomes empty
        if (head == null) {
            return null;
        }
        
        // Traverse and remove remaining occurrences
        Node current = head;
        while (current != null) {
            if (current.data == x) {
                // Connect previous node to next node
                if (current.prev != null) {
                    current.prev.next = current.next;
                }
                // Connect next node to previous node
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
            }
            current = current.next;
        }
        
        return head;
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

**Input:** 2<->2<->10<->8<->4<->2<->5<->2, x = 2

**Execution:**
1. Remove head nodes (two 2s):
   - New head becomes 10
   - Set 10.prev = null
2. Traverse:
   - Skip 10, 8, 4
   - Remove 2 after 4:
     - Connect 4.next = 5
     - Connect 5.prev = 4
   - Remove final 2:
     - Set 5.next = null
3. Final list: 10<->8<->4<->5

**Result:** Head pointing to 10

---

## 💡 Key Features
- **Complete Removal**: Handles all occurrences including head
- **Pointer Safety**: Null checks before dereferencing
- **In-Place Operation**: Modifies list without extra memory
- **Edge Case Handling**: Empty list and all-nodes-match cases

---

## 🚀 When to Use
- Bulk removal operations in doubly linked lists
- When maintaining list integrity is critical
- Problems requiring in-place modifications

## ⚠️ Edge Cases
- **Empty list**: Returns null
- **All nodes match key**: Returns empty list
- **Consecutive matches**: Properly skips all
- **Single node list**: Handles removal correctly

## 🛠 Variations
1. **Delete First Occurrence**:
```java
// Stop after first deletion
```

2. **Count Deleted Nodes**:
```java
// Track how many nodes were removed
```

3. **Multiple Key Removal**:
```java
// Accept array of keys to remove
```