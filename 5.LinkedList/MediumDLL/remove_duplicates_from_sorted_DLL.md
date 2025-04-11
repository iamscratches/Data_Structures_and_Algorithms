# 🔍 Remove Duplicates from Sorted Doubly Linked List - Efficient Approach

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/1)

Given a sorted doubly linked list, remove all duplicate nodes such that each node appears only once.

**Constraints:**
- 1 ≤ Number of nodes ≤ 10^5
- 1 ≤ Node value ≤ 10^5

**Example:**
```text
Input: 1<->1<->2<->3<->3
Output: 1<->2<->3
```

---

## 🧠 Intuition
The solution leverages the sorted property of the list with these key insights:
1. **Adjacent Comparison**: Duplicates will be adjacent in a sorted list
2. **Pointer Adjustment**: Skip duplicate nodes by adjusting next/prev pointers
3. **Single Pass**: Processes the list in one traversal

Key Observations:
- O(n) time complexity with O(1) space
- Maintains doubly linked list integrity
- Handles consecutive duplicates efficiently

---

## ⚙️ Approach
### **1️⃣ Initialization**
1. Start with temp pointer at head

### **2️⃣ Traversal and Deduplication**
1. While current node has next node:
   - If next node is duplicate:
     - Skip next node by adjusting pointers
     - Update next node's prev pointer if exists
   - Else:
     - Move to next node

### **3️⃣ Result**
1. Return modified head (unchanged unless head was duplicate)

---

## ✅ Optimized Solution
```java
class Solution {
    Node removeDuplicates(Node head) {
        if (head == null) return null;
        
        Node current = head;
        
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                // Skip duplicate node
                Node duplicate = current.next;
                current.next = duplicate.next;
                
                // Update prev pointer of next node if exists
                if (current.next != null) {
                    current.next.prev = current;
                }
                
                // Clean up references (optional)
                duplicate.next = null;
                duplicate.prev = null;
            } else {
                // Move to next distinct node
                current = current.next;
            }
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

**Input:** 1<->1<->2<->3<->3

**Execution:**
1. current=1 (first)
   - next is duplicate (1)
   - Skip second 1: 1.next=2, 2.prev=1
2. current=2
   - next (3) is distinct → move current to 3
3. current=3 (first)
   - next is duplicate (3)
   - Skip second 3: 3.next=null
4. Terminate (current.next=null)

**Result:** 1<->2<->3

---

## 💡 Key Features
- **In-Place Modification**: Operates directly on list
- **Single Pass Efficiency**: Processes list in O(n) time
- **Pointer Safety**: Maintains proper prev/next links
- **Edge Case Handling**: Empty list and all duplicates

---

## 🚀 When to Use
- Deduplication in sorted doubly linked lists
- When O(1) space is required
- Problems requiring in-place modifications

## ⚠️ Edge Cases
- **Empty list**: Returns null
- **All duplicates**: Returns single node
- **No duplicates**: Returns original list
- **Single node**: Returns unchanged

## 🛠 Variations
1. **Unsorted List Deduplication**:
```java
// Would require hash set for O(n) time
```

2. **Count Duplicates**:
```java
// Track how many nodes were removed
```

3. **Remove All Instances**:
```java
// Remove all nodes with duplicate values
```