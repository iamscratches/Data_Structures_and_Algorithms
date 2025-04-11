# 🔍 Find Pairs with Given Sum in Doubly Linked List - Two Pointer Approach

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1)

Given a sorted doubly linked list of distinct integers and a target sum, find all pairs of nodes that sum to the target value.

**Constraints:**
- 1 ≤ Number of nodes ≤ 10^5
- 1 ≤ Node value ≤ 10^5
- 1 ≤ target ≤ 2*10^5

**Example:**
```text
Input: 
List: 1<->2<->4<->5<->6<->8<->9
Target: 7
Output: [[1,6], [2,5]]
```

---

## 🧠 Intuition
The solution leverages the sorted property of the doubly linked list with these key insights:
1. **Two Pointers**: Start with pointers at head (smallest) and tail (largest)
2. **Sum Comparison**:
   - If sum > target → move tail backward
   - If sum < target → move head forward
   - If sum == target → record pair and move both
3. **Distinct Values**: No duplicate pairs since values are distinct

Key Observations:
- O(n) time complexity with O(1) space (excluding output)
- Naturally handles sorted order using two pointers
- Efficiently finds all possible pairs

---

## ⚙️ Approach
### **1️⃣ Initial Setup**
1. Find tail node (last node)
2. Initialize result list

### **2️⃣ Two Pointer Traversal**
1. While head hasn't passed tail:
   - Calculate current sum
   - Adjust pointers based on sum comparison
   - Record valid pairs

### **3️⃣ Result Compilation**
1. Return all found pairs
2. Empty list if no pairs found

---

## ✅ Optimized Solution
```java
class Solution {
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        // Find tail node
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        
        // Two pointer approach
        while (head != null && tail != null && head != tail && head.prev != tail) {
            int sum = head.data + tail.data;
            
            if (sum == target) {
                // Found a pair
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(head.data);
                pair.add(tail.data);
                result.add(pair);
                head = head.next;
                tail = tail.prev;
            } 
            else if (sum < target) {
                head = head.next;
            } 
            else {
                tail = tail.prev;
            }
        }
        
        return result;
    }
}
```
---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass to find tail + two-pointer traversal |
| **Space**       | O(1)       | Constant extra space (output excluded) |

---

## 📊 Example Walkthrough

**Input:** 1<->2<->4<->5<->6<->8<->9, target=7

**Execution:**
1. Initialize: head=1, tail=9
2. Iteration 1: 1+9=10>7 → tail=8
3. Iteration 2: 1+8=9>7 → tail=6
4. Iteration 3: 1+6=7 → add [1,6], head=2, tail=5
5. Iteration 4: 2+5=7 → add [2,5], head=4, tail=4 (stop)

**Result:** [[1,6], [2,5]]

---

## 💡 Key Features
- **Efficient Pair Finding**: Leverages sorted order
- **No Extra Memory**: Only uses two pointers
- **Complete Search**: Finds all valid pairs
- **Early Termination**: Stops when pointers meet

---

## 🚀 When to Use
- Sorted doubly linked list problems
- Pair finding with sum constraints
- When O(n) time is required

## ⚠️ Edge Cases
- **No pairs**: Returns empty list
- **Single pair**: Returns single entry
- **All pairs sum to target**: Handles multiple pairs
- **Small list**: Works with minimal nodes

## 🛠 Variations
1. **Count Pairs**:
```java
// Return count instead of pairs
```

2. **Triplet Sum**:
```java
// Extend to find triplets summing to target
```

3. **Nearest Sum**:
```java
// Find pairs closest to target
```