# 🔍 Search in Linked List - Recursive Approach

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/search-in-linked-list-1664434326/1)

Given a linked list of size `n` and a key, determine if the key exists in the linked list.

**Constraints:**
- 1 ≤ n ≤ 10^5
- 1 ≤ key ≤ 10^5

**Example 1:**
```text
Input: 
LinkedList: 1->2->3->4->5
Key: 3
Output: true
```

**Example 2:**
```text
Input:
LinkedList: 1->2->3->4->5
Key: 6
Output: false
```

---

## 🧠 Intuition
The solution uses these key insights:
1. **Recursive Traversal**: Checks each node sequentially using recursion
2. **Base Cases**: 
   - Empty list → return false
   - Key found → return true immediately
3. **Progressive Search**: Recursively checks remaining nodes if key not found

Key Observations:
- Recursion naturally follows linked list structure
- Worst-case scenario requires full traversal (O(n) time)
- Space complexity is O(n) due to recursion stack

---

## ⚙️ Approach
### **1️⃣ Base Case Handling**
1. If head is null → return false (end of list)
2. If current node's data matches key → return true

### **2️⃣ Recursive Step**
1. Call function recursively with next node
2. Propagate the result back through call stack

### **3️⃣ Termination**
- Returns as soon as key is found
- Returns false only after full traversal without finding key

---

## ✅ Code Implementation

### Recursive Solution
```java
class Solution {
    static boolean searchKey(int n, Node head, int key) {
        // Base case: empty list
        if (head == null) {
            return false;
        }
        // Base case: key found
        if (head.data == key) {
            return true;
        }
        // Recursive case: check next node
        return searchKey(n, head.next, key);
    }
}
```

Key Features:
1. **Clear Base Cases**: Direct handling of termination conditions
2. **Tail Recursion**: Can be optimized by compiler
3. **Parameter `n`**: Included but unused (could be removed)
4. **Simple Logic**: Easy to understand and verify

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Worst case traverses entire list |
| **Space**       | O(n)       | Recursion stack depth |

---

## 📊 Example Walkthrough

**Input:** 1→2→3→4→5, key = 3

**Execution Steps:**
1. Check node 1 (≠3) → recurse
2. Check node 2 (≠3) → recurse
3. Check node 3 (==3) → return true
4. True propagates back through call stack

**Result:** true

---

## 💡 Key Features
- **Natural Fit**: Recursion matches linked list structure
- **Early Termination**: Returns immediately on finding key
- **Readable Code**: Clear expression of search logic
- **Constraint Handling**: Works within given bounds

---

## 🚀 When to Use
- Linked list traversal problems
- When recursive solutions are preferred
- Problems requiring simple element search

## ⚠️ Edge Cases
- **Empty list**: Handled by first base case
- **Key at head**: Returns immediately
- **Key at tail**: Requires full traversal
- **Duplicate keys**: Returns on first occurrence

## 🛠 Variations
1. **Iterative Version**:
```java
// Uses constant space
while (head != null) {
    if (head.data == key) return true;
    head = head.next;
}
return false;
```

2. **Count Occurrences**:
```java
// Modify to count how many times key appears
```

3. **Position Tracking**:
```java
// Return position of key instead of boolean
```

4. **Tail Recursion Optimization**:
```java
// Java doesn't optimize, but clearer structure
```