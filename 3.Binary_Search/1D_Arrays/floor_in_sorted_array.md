# 🔍 Floor in a Sorted Array - Binary Search Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1)

Given a sorted array `arr` of size `n` and an integer `x`, find the floor of `x` in the array. The floor of `x` is defined as the largest element in the array which is smaller than or equal to `x`.

**Constraints:**
- 1 ≤ n ≤ 10⁷
- 1 ≤ arr[i] ≤ 10¹⁸
- 1 ≤ x ≤ 10¹⁸

**Example 1:**
```text
Input: arr = [1,2,8,10,11,12,19], x = 5
Output: 1
Explanation: The largest element ≤ 5 is 2 (index 1)
```

**Example 2:**
```text
Input: arr = [1,2,8,10,11,12,19], x = 20
Output: 6
Explanation: The largest element ≤ 20 is 19 (index 6)
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Sorted Array Property**: Enables efficient search using binary search
2. **Floor Condition**: Track elements ≤ `x` during search
3. **Binary Search Adaptation**: Modified to find the largest element ≤ `x`

Key Observations:
- The floor is either the element itself (if present) or the largest smaller element
- Binary search's O(log n) complexity is optimal for large sorted arrays

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize:
   - `start` = 0 (first index)
   - `end` = arr.length - 1 (last index)
   - `ans` = -1 (default if no floor found)

### **2️⃣ Modified Binary Search**
1. Calculate `mid` index
2. If `arr[mid]` ≤ `x`:
   - Update `ans` to `mid`
   - Search right half (`start = mid + 1`)
3. Else:
   - Search left half (`end = mid - 1`)

### **3️⃣ Termination**
- Returns `ans` (index of floor element)
- Returns -1 if all elements > `x`

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    static int findFloor(int[] arr, int x) {
        int start = 0, end = arr.length - 1;
        int ans = -1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2; // Prevents overflow
            
            if (arr[mid] <= x) {
                ans = mid; // Update potential answer
                start = mid + 1; // Search right half
            } else {
                end = mid - 1; // Search left half
            }
        }
        
        return ans;
    }
}
```

Key Components:
1. **Binary Search Setup**: Initializes search boundaries
2. **Mid Calculation**: Uses safe formula to prevent overflow
3. **Condition Check**: Updates answer when valid floor found
4. **Search Space Reduction**: Halves search space each iteration

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Halves search space each iteration |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `arr = [1,2,8,10,11,12,19], x = 5`

**Execution Steps:**
1. Initial: start=0, end=6
2. Iteration 1: mid=3 (10) > 5 → end=2
3. Iteration 2: mid=1 (2) ≤ 5 → ans=1, start=2
4. Iteration 3: mid=2 (8) > 5 → end=1
5. Terminate (start > end)
6. **Result:** 1 (value 2)

---

## 💡 Key Features
- **Optimal Efficiency**: O(log n) time
- **Minimal Space**: No extra data structures
- **Overflow Protection**: Safe mid calculation
- **Clear Logic**: Easy to understand and implement

---

## 🚀 When to Use
- **Searching in sorted arrays**
- **Finding floor/ceiling values**
- **When O(n) search is too slow**
- **As building block** for more complex search problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for sorted arrays |
| Linear Search | O(n) | O(1) | Works on unsorted arrays |
| TreeMap | O(log n) | O(n) | Java-specific, more overhead |

## ⚠️ Edge Cases
- **x smaller than all elements**: Returns -1
- **x larger than all elements**: Returns last index
- **x equals an element**: Returns that index
- **Empty array**: Constraint says n ≥ 1

## 🛠 Variations
1. **Ceiling Search**:
```java
// Find smallest element ≥ x
```

2. **Range Queries**:
```java
// Find floor and ceiling together
```

3. **Floating Point Numbers**:
```java
// Adapt for non-integer arrays
```

This solution demonstrates an efficient binary search adaptation to find floor values in sorted arrays, providing optimal search performance while being simple to implement and understand. The safe mid calculation ensures correctness even with large array indices.