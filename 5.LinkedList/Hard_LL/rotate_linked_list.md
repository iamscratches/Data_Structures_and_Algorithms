# 🔍 Rotate Linked List - Circular List Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/rotate-list/description/)

Given the head of a linked list, rotate the list to the right by `k` places.

**Constraints:**
- Number of nodes: 0 ≤ n ≤ 500
- -100 ≤ Node.val ≤ 100
- 0 ≤ k ≤ 2×10⁹

**Example:**
```text
Input: head = [1,2,3,4,5], k = 2
Output: [4,5,1,2,3]
Explanation: Rotate right twice moves last two nodes to front
```

## 🧠 Intuition
The solution uses:
1. **Circular Linking**: Connects list end to head
2. **Effective Rotation Calculation**: Handles k > length cases
3. **New Head Identification**: Finds break point after rotation

Key Insights:
- Rotating by list length results in original list
- Circular linking simplifies rotation logic
- Only need to find new head and break the circle

## ⚙️ Approach
1. **Edge Handling**: Return if empty list
2. **Length Calculation**: Traverse to find length and tail
3. **Circularization**: Connect tail to head
4. **Effective Rotation**: Compute k % length
5. **New Head Location**: Traverse to (length - k)th node
6. **List Reconstruction**: Break circle at new tail

## ✅ Optimized Solution
```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Calculate length and find tail
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // Make circular
        tail.next = head;
        
        // Calculate effective rotations
        k %= length;
        if (k == 0) {
            tail.next = null; // Undo circular
            return head;
        }
        
        // Find new tail (length - k - 1 steps from head)
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++) {
            newTail = newTail.next;
        }
        
        // Break circle and return new head
        ListNode newHead = newTail.next;
        newTail.next = null;
        return newHead;
    }
}
```

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Two passes through list |
| **Space**       | O(1)       | Constant extra space |

## 📊 Example Walkthrough
**Input:** head = [1,2,3,4,5], k = 2

**Execution:**
1. Length = 5, tail = node 5
2. Make circular: 5 → 1
3. Effective k = 2 % 5 = 2
4. New tail at position 5-2-1 = 2 (node 3)
5. New head = node 4
6. Break circle at node 3
7. Result: [4,5,1,2,3]

## 💡 Key Features
- **Efficiency**: Linear time complexity
- **Edge Handling**: Proper null checks
- **Large k Handling**: Uses modulo operation
- **Space Optimization**: No additional data structures

## 🚀 When to Use
- Linked list rotation problems
- When k can be very large
- Problems requiring circular list manipulation
- In-place operations on linked structures

## ⚠️ Edge Cases
- **Empty List**: Returns null
- **Single Node**: Returns as-is
- **k = 0**: Returns original list
- **k = length**: Returns original list

## 🛠 Variations
1. **Left Rotation**:
```java
// Modify to rotate left instead of right
```

2. **Multiple Rotations**:
```java
// Optimize for multiple rotation requests
```

3. **Doubly Linked List**:
```java
// Implement for doubly linked lists
```

4. **Visualization**:
```java
// Print list before/after rotation
```

5. **Recursive Version**:
```java
// Implement rotation recursively
```

6. **Bulk Rotation**:
```java
// Handle batch rotation operations
```