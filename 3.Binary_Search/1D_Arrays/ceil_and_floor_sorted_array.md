# 🔍 Ceiling and Floor in Sorted Array - Binary Search Approach

## 📜 Problem Statement
**Link:** [Naukri Code360 Problem](https://www.naukri.com/code360/problems/ceiling-in-a-sorted-array_1825401?leftPanelTabValue=PROBLEM)

Given a sorted array `a` of size `n` and an integer `x`, find both the floor and ceiling of `x` in the array. 
- Floor: Largest element ≤ `x`
- Ceiling: Smallest element ≥ `x`

**Constraints:**
- 1 ≤ n ≤ 10⁵
- 1 ≤ a[i], x ≤ 10⁹
- Array is sorted in non-decreasing order

**Example 1:**
```text
Input: a = [3, 4, 7, 8, 8, 10], x = 5
Output: [4, 7]
Explanation: Floor is 4, Ceiling is 7
```

**Example 2:**
```text
Input: a = [3, 4, 4, 7, 8, 10], x = 8
Output: [8, 8]
Explanation: Both floor and ceiling are 8
```

---

## 🧠 Intuition
The solution leverages binary search with these key insights:
1. **Sorted Array Property**: Enables efficient O(log n) search
2. **Dual Tracking**: Simultaneously tracks floor and ceiling candidates
3. **Early Termination**: Returns immediately if exact match found

Key Observations:
- Binary search naturally finds the insertion point for `x`
- The last "less than" position gives floor
- The first "greater than" position gives ceiling

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize:
   - `start = 0`, `end = n-1`
   - `floor = -1`, `ceil = -1`

### **2️⃣ Modified Binary Search**
1. Calculate `mid` index
2. If `a[mid] == x`:
   - Return `[x, x]` (exact match)
3. If `a[mid] > x`:
   - Update `ceil` to `a[mid]`
   - Search left half (`end = mid - 1`)
4. Else (`a[mid] < x`):
   - Update `floor` to `a[mid]`
   - Search right half (`start = mid + 1`)

### **3️⃣ Termination**
- Returns `[floor, ceil]` after search completes
- Unfound values remain -1

---

## ✅ Code Implementation

### Optimal Solution
```java
public class Solution {
    public static int[] getFloorAndCeil(int[] a, int n, int x) {
        int start = 0, end = a.length - 1;
        int ceil = -1, floor = -1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2; // Prevents overflow
            
            if (a[mid] == x) {
                // Exact match found
                return new int[]{x, x};
            } else if (a[mid] > x) {
                // Update ceiling and search left
                ceil = a[mid];
                end = mid - 1;
            } else {
                // Update floor and search right
                floor = a[mid];
                start = mid + 1;
            }
        }
        
        return new int[]{floor, ceil};
    }
}
```

Key Components:
1. **Binary Search Setup**: Initializes search boundaries
2. **Mid Calculation**: Uses safe formula to prevent overflow
3. **Three-Way Comparison**: Handles equal, less-than, greater-than cases
4. **Dual Tracking**: Maintains both floor and ceiling candidates

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Halves search space each iteration |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `a = [3,4,7,8,8,10], x = 5`

**Execution Steps:**
1. Initial: start=0, end=5
2. Iteration 1: mid=2 (7) > 5 → ceil=7, end=1
3. Iteration 2: mid=0 (3) < 5 → floor=3, start=1
4. Iteration 3: mid=1 (4) < 5 → floor=4, start=2
5. Terminate (start > end)
6. **Result:** [4, 7]

---

## 💡 Key Features
- **Optimal Efficiency**: O(log n) time
- **Minimal Space**: No extra data structures
- **Overflow Protection**: Safe mid calculation
- **Early Termination**: For exact matches

---

## 🚀 When to Use
- **Searching in sorted arrays**
- **Finding both floor and ceiling**
- **When O(n) search is too slow**
- **As building block** for range queries

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for sorted arrays |
| Linear Search | O(n) | O(1) | Works on unsorted arrays |
| TreeSet | O(n log n) | O(n) | Java-specific, more overhead |

## ⚠️ Edge Cases
- **x smaller than all elements**: floor=-1
- **x larger than all elements**: ceil=-1
- **Exact match exists**: returns [x, x]
- **Duplicate elements**: Handles multiple same values

## 🛠 Variations
1. **Lower Bound Search**:
```java
// Find first occurrence ≥ x
```

2. **Upper Bound Search**:
```java
// Find first occurrence > x
```

3. **Count Elements in Range**:
```java
// Use floor/ceil to count elements between [a,b]
```

This solution demonstrates an efficient binary search adaptation to find both floor and ceiling values in sorted arrays, providing optimal search performance while being simple to implement and understand. The algorithm handles all edge cases while maintaining O(log n) time complexity.