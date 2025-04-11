# 🔍 Reverse Nodes in k-Group - Iterative Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/reverse-nodes-in-k-group/description/)

Given a linked list, reverse the nodes of the list k at a time and return the modified list. If the number of nodes is not a multiple of k, the remaining nodes should stay in their original order.

**Constraints:**
- Number of nodes: [0, 5000]
- Node values: [-1000, 1000]
- 1 ≤ k ≤ 2000

**Example:**
```text
Input: 1→2→3→4→5, k = 2
Output: 2→1→4→3→5
```

---

## 🧠 Intuition
The solution uses these key insights:
1. **Dummy Node**: Handles edge cases where head changes
2. **Group Processing**: Processes k nodes at a time
3. **Reversal and Reconnection**: Reverses each group and connects it properly

Key Observations:
- O(n) time complexity with O(1) space
- Maintains proper links between reversed groups
- Handles remaining nodes when length isn't multiple of k

---

## ⚙️ Approach
### **1️⃣ Initialization**
1. Create dummy node pointing to head
2. Initialize pointers for group tracking

### **2️⃣ Group Processing**
1. Check if k nodes exist ahead
2. Isolate current group
3. Reverse the group
4. Reconnect reversed group to main list

### **3️⃣ Termination**
1. Return dummy.next as new head
2. Preserves remaining nodes if not enough for full group

---

## ✅ Optimized Solution
```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        
        while (true) {
            // Check if we have k nodes remaining
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;
            
            ListNode groupNext = kth.next;
            
            // Reverse current group
            ListNode prev = kth.next;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }
            
            // Reconnect the reversed group
            ListNode newGroupPrev = groupPrev.next;
            groupPrev.next = prev;
            groupPrev = newGroupPrev;
        }
        
        return dummy.next;
    }
    
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
```

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Processes each node twice |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** 1→2→3→4→5, k=2

**Execution:**
1. Initial: dummy→1→2→3→4→5
2. First group (1→2):
   - Reverse to 2→1
   - Connect: dummy→2→1→3→4→5
3. Second group (3→4):
   - Reverse to 4→3
   - Connect: 1→4→3→5
4. Final: 2→1→4→3→5

**Result:** Head pointing to 2

---

## 💡 Key Features
- **Group Processing**: Handles k nodes at a time
- **In-Place Reversal**: Modifies links directly
- **Edge Case Handling**: Preserves remaining nodes
- **Dummy Node**: Simplifies head modification

---

## 🚀 When to Use
- Batch processing of linked lists
- When k-group operations are needed
- Problems requiring in-place modifications

## ⚠️ Edge Cases
- **Empty list**: Returns dummy.next (null)
- **k=1**: Returns original list
- **k=list length**: Reverses entire list
- **Remaining nodes**: Preserves original order

## 🛠 Variations
1. **Reverse Alternating Groups**:
```java
// Reverse every alternate k-group
```

2. **Count Reversals**:
```java
// Track number of groups reversed
```

3. **Circular List Handling**:
```java
// Additional checks for circular references
```