# 🔍 Reverse Linked List - Recursive Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/reverse-linked-list/)

Given the head of a singly linked list, reverse the list and return the new head.

**Constraints:**
- The number of nodes in the list is in the range [0, 5000]
- -5000 <= Node.val <= 5000

**Example:**
```text
Input: 1->2->3->4->5
Output: 5->4->3->2->1
```

---

## 🧠 Intuition
The recursive approach works by:
1. **Base Case**: Returning the last node as new head
2. **Recursive Step**: Reversing the remaining list and linking back
3. **Pointer Adjustment**: Making current node point backward

Key Observations:
- Recursion naturally follows the linked list structure
- Each call handles one node's reversal
- The last node becomes the new head

---

## ⚙️ Approach
### **1️⃣ Base Cases**
1. Empty list → return null
2. Single node → return itself as new head

### **2️⃣ Recursive Reversal**
1. Save next node before reversal
2. Recursively reverse remaining list
3. Link next node back to current node
4. Return final head from base case

### **3️⃣ Pointer Management**
1. Break original next link to prevent cycles
2. Build reversed links through recursion

---

## ✅ Optimized Recursive Solution
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        // Base cases
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursive step: reverse remaining list
        ListNode reversedHead = reverseList(head.next);
        
        // Link current node to reversed list
        head.next.next = head;
        head.next = null;
        
        return reversedHead;
    }
}
```

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Visits each node once |
| **Space**       | O(n)       | Recursion stack depth |

---

## 📊 Example Walkthrough

**Input:** 1→2→3→4→5

**Execution:**
1. Recursive calls until node 5 (base case returns 5)
2. At node 4:
   - 4.next.next = 4 → 5→4
   - 4.next = null
3. At node 3:
   - 3.next.next = 3 → 4→3
   - 3.next = null
4. Continues until original head
5. Final list: 5→4→3→2→1

**Result:** Node(5)

---

## 💡 Key Insights
- **Last Node is New Head**: Base case captures it
- **Pointer Rewiring**: Each call handles one link reversal
- **Stack Unwinding**: Recursion builds the reversed list backward

---

## 🚀 When to Use
- Recursive practice problems
- When stack space isn't a concern
- Problems requiring elegant recursive solutions

## ⚠️ Edge Cases
- **Empty list**: Handled by first base case
- **Single node**: Returns itself
- **Large lists**: Stack overflow possible (5000 nodes max)

## 🛠 Variations
1. **Iterative Version**:
```java
ListNode prev = null;
ListNode curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
return prev;
```

2. **Print Reversal Steps**:
```java
// Add print statements to visualize each step
```

3. **Partial Reversal**:
```java
// Reverse only a portion of the list
```