# 🔍 Search a 2D Matrix - Efficient Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/search-a-2d-matrix/description/)

Given an m x n integer matrix where:
- Each row is sorted in non-decreasing order
- The first integer of each row is greater than the last integer of the previous row

Write an efficient algorithm to search for a target value.

**Constraints:**
- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 100
- -10^4 <= matrix[i][j], target <= 10^4

**Example 1:**
```text
Input: matrix = [[1,3,5,7],
                 [10,11,16,20],
                 [23,30,34,60]], 
       target = 3
Output: true
```

**Example 2:**
```text
Input: matrix = [[1,3,5,7],
                 [10,11,16,20],
                 [23,30,34,60]], 
       target = 13
Output: false
```

---

## 🧠 Intuition
The solution leverages the matrix's strict sorting properties with these key insights:
1. **Flattened Array View**: The matrix can be treated as one continuous sorted array
2. **Binary Search Adaptation**: Perform binary search by converting 1D index to 2D coordinates
3. **Efficiency**: Achieves O(log(mn)) time complexity

Key Observations:
- The entire matrix is effectively sorted in one dimension
- Standard binary search can be adapted using index conversion
- More efficient than the O(m+n) solution for larger matrices

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize pointers for virtual "flattened" array
2. Calculate total elements = rows × columns

### **2️⃣ Binary Search Execution**
1. While left ≤ right:
   - Calculate mid point
   - Convert mid index to 2D matrix coordinates
   - Compare matrix value with target
   - Adjust search range accordingly

### **3️⃣ Termination**
- Return true if target found
- Return false if search completes without finding target

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Handle empty matrix case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Convert 1D index to 2D coordinates
            int midValue = matrix[mid / cols][mid % cols];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
```

Key Improvements:
1. **Edge Case Handling**: Explicit check for empty matrix
2. **Index Conversion**: Clear 1D-to-2D coordinate mapping
3. **Standard Binary Search**: Familiar pattern with matrix adaptation
4. **Early Termination**: Returns immediately when target found

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log(mn)) | Binary search on m×n elements |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `matrix = [[1,3,5,7],
                     [10,11,16,20],
                     [23,30,34,60]], 
       target = 3`

**Execution Steps:**
1. left=0, right=11 (total 12 elements)
2. mid=5 → matrix[1][1]=11 > 3 → right=4
3. mid=2 → matrix[0][2]=5 > 3 → right=1
4. mid=0 → matrix[0][0]=1 < 3 → left=1
5. mid=1 → matrix[0][1]=3 == target → return true

**Result:** true

---

## 💡 Key Features
- **Optimal Search**: Leverages complete matrix sorting
- **Efficient Indexing**: Simple 1D-to-2D conversion
- **Clean Implementation**: Standard binary search pattern
- **Worst-Case Guarantee**: Always O(log(mn)) time

---

## 🚀 When to Use
- Searching in strictly sorted 2D matrices
- Problems requiring O(log n) search in 2D data
- When matrix can be treated as flattened sorted array

## ⚠️ Edge Cases
- **Empty matrix**: Handled by initial check
- **Single element matrix**: Works normally
- **Target at boundaries**: Properly detected
- **Negative numbers**: Handled by normal comparison

## 🛠 Variations
1. **Range Search**:
```java
// Find all elements in a given range
```

2. **Nearest Element**:
```java
// Find closest element to target
```

3. **Count Occurrences**:
```java
// Count how many times target appears
```