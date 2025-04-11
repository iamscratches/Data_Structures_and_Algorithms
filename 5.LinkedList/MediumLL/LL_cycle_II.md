# 🔍 Linked List Cycle II - Floyd's Tortoise and Hare Algorithm (Cycle Detection + Start Node)

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/linked-list-cycle-ii/description/)

Given a linked list, return the node where the cycle begins. If no cycle exists, return null.

**Constraints:**
- Number of nodes: [0, 10^4]
- Node values: [-10^5, 10^5]
- `pos` indicates cycle position (-1 for no cycle)

**Example:**
```text
Input: head = [3,2,0,-4], pos = 1
Output: Node(2)
Explanation: Tail connects to node at index 1
```

---

## 🧠 Intuition
The solution extends Floyd's algorithm with two phases:
1. **Cycle Detection**: Fast (2x) and slow (1x) pointers until meeting point
2. **Cycle Start Detection**: Reset one pointer to head, then move both at 1x speed until they meet

Key Mathematical Insight:
- When they meet, the distance from head to cycle start = distance from meeting point to cycle start

---

## ⚙️ Approach
### **1️⃣ Phase 1: Detect Cycle**
1. Initialize slow and fast pointers at head
2. Move slow by 1, fast by 2 until they meet or fast reaches end

### **2️⃣ Phase 2: Find Cycle Start**
1. If no meeting → return null
2. Reset fast to head
3. Move both at 1x speed until they meet again
4. Meeting point = cycle start node

---

## ✅ Optimized Code
```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        
        // Phase 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break; // Cycle detected
        }
        
        // No cycle condition
        if (fast == null || fast.next == null) return null;
        
        // Phase 2: Find cycle start
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow; // or fast (they meet at cycle start)
    }
}
```
---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Linear traversal (n = nodes) |
| **Space**       | O(1)       | Uses only two pointers |

---

## 📊 Example Walkthrough
**Input:** 3→2→0→-4→(back to 2)

**Execution:**
1. **Phase 1**:
   - slow: 3→2→0→-4→2
   - fast: 3→0→2→-4→0→2 (meet at -4)
2. **Phase 2**:
   - Reset fast to head (3)
   - Move both 1x: 
     - slow: -4→2, fast: 3→2 (meet at 2)

**Result:** Node(2)

---

## 💡 Key Insights
1. **Mathematical Proof**: Distance(head→start) = Distance(meet→start)
2. **Optimal Performance**: No extra memory needed
3. **Two-Phase Design**: Separation of concerns

---

## 🚀 When to Use
- Problems requiring cycle start detection
- When O(1) space is critical
- Situations needing mathematical proof of correctness

## ⚠️ Edge Cases
- **Empty list**: Returns null
- **Single node cycle**: Returns head
- **Full cycle**: Returns head
- **No cycle**: Returns null

## 🛠 Variations
1. **Cycle Length Calculation**:
```java
// After detection, count steps until pointers meet again
```

2. **Multiple Cycles**:
```java
// For problems with potential nested cycles
```

3. **Visualization Helper**:
```java
// Print list with cycle markers for debugging
```