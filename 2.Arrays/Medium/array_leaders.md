# 🚀 Leaders in an Array - Optimal Solution

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/leaders-in-an-array-1587115620/1)

Given an array `arr[]` of size `N`, find all elements that are leaders. An element is a leader if it is greater than or equal to all elements to its right.

**Constraints:**
- 1 ≤ N ≤ 10⁷
- 0 ≤ arr[i] ≤ 10⁵

**Example 1:**
```text
Input: [16,17,4,3,5,2]
Output: [17,5,2]
Explanation: 
- 17 is greater than all elements to its right (4,3,5,2)
- 5 is greater than all elements to its right (2)
- 2 is the last element (always a leader)
```

**Example 2:**
```text
Input: [5,4,3,2,1]
Output: [5,4,3,2,1]
Explanation: All elements are leaders since none have greater elements to their right
```

---

## 🧠 Intuition
The optimal solution:
1. **Traverses from right to left** (since leaders are defined by right elements)
2. **Tracks maximum element** seen so far
3. **Identifies leaders** when current element ≥ current maximum
4. **Reverses result** to maintain original order

Key Insights:
- Last element is always a leader
- Only need to compare with maximum from the right
- Single pass with O(1) space (excluding output)

---

## ⚙️ Approach
### **1️⃣ Right-to-Left Traversal**
1. Initialize:
   - `max` = last element
   - Add last element to result
2. Iterate from second-last element:
   - If current element ≥ `max`:
     - Add to result
     - Update `max`
3. Reverse result before returning

### **2️⃣ Why It Works**
- Efficiently tracks leaders by comparing only with maximum
- Maintains relative order via post-reversal
- Handles edge cases (all leaders, single element)

---

## ✅ Code Implementation

### Optimal Solution
```java
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static ArrayList<Integer> leaders(int arr[]) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = arr.length;
        int max = arr[n-1];
        res.add(max);
        
        for(int i = n-2; i >= 0; i--) {
            if(arr[i] >= max) {
                res.add(arr[i]);
                max = arr[i];
            }
        }
        
        Collections.reverse(res);
        return res;
    }
}
```

Key Components:
1. **Initialization**: Start with last element as initial leader
2. **Right-to-Left Scan**: Efficient leader detection
3. **Max Tracking**: Only compare with current maximum
4. **Result Reversal**: Maintains original order

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through array |
| **Space**       | O(n)       | Output storage (O(1) extra space) |

---

## 📊 Example Walkthrough

**Input:** `[16,17,4,3,5,2]`

**Execution Steps:**
1. Initialize: max=2, res=[2]
2. i=4 (5): 5>2 → res=[2,5], max=5
3. i=3 (3): 3<5 → skip
4. i=2 (4): 4<5 → skip
5. i=1 (17): 17>5 → res=[2,5,17], max=17
6. i=0 (16): 16<17 → skip
7. Reverse: [17,5,2]

**Output:** `[17,5,2]`

---

## 💡 Key Features
- **Efficiency**: Optimal O(n) time
- **Order Preservation**: Maintains relative leader order
- **Edge Handling**: Works for all constraints
- **Readability**: Clear logic and structure

---

## 🚀 When to Use
- **Right dominance problems**
- **Finding extremal elements** in sequences
- **Preprocessing for other algorithms**
- **Competitive programming** scenarios

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Right-Left Scan | O(n)            | O(n)             | Optimal    |
| Brute Force     | O(n²)           | O(1)             | None       |
| Stack-based     | O(n)            | O(n)             | More complex |

## ⚠️ Edge Cases
- **All equal elements**: All are leaders
- **Single element**: Returns itself
- **Strictly decreasing**: All elements are leaders
- **Large arrays**: Handles within constraints

## 🛠 Variations
1. **Left Leaders** (compare with left elements):
```java
// Traverse left-to-right with min tracking
```

2. **Return Indices**:
```java
// Store indices instead of values
```

3. **K-Leaders**:
```java
// Find elements greater than k elements to their right
```

This solution demonstrates an elegant approach to leader identification, balancing efficiency with simplicity while handling all problem constraints effectively.