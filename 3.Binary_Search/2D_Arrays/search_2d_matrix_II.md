# 🔍 Search a 2D Matrix II - Efficient Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/search-a-2d-matrix-ii/description/)

Given an m x n integer matrix where each row and column is sorted in ascending order, write an efficient algorithm to search for a target value.

**Constraints:**
- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 300
- -10^9 <= matrix[i][j] <= 10^9
- All integers in each row are sorted in ascending order
- All integers in each column are sorted in ascending order
- -10^9 <= target <= 10^9

**Example 1:**
```text
Input: matrix = [[1,4,7,11,15],
                 [2,5,8,12,19],
                 [3,6,9,16,22],
                 [10,13,14,17,24],
                 [18,21,23,26,30]], 
       target = 5
Output: true
```

**Example 2:**
```text
Input: matrix = [[1,4,7,11,15],
                 [2,5,8,12,19],
                 [3,6,9,16,22],
                 [10,13,14,17,24],
                 [18,21,23,26,30]], 
       target = 20
Output: false
```

---

## 🧠 Intuition
The solution leverages the matrix's sorted properties with these key insights:
1. **Starting Point**: Begin at top-right corner (or bottom-left)
2. **Elimination Strategy**:
   - If current value > target → eliminate current column
   - If current value < target → eliminate current row
3. **Efficiency**: Each comparison eliminates either a row or column

Key Observations:
- The algorithm resembles a "staircase" search pattern
- Works efficiently without binary search due to sorted properties
- Worst-case visits at most m + n elements

---

## ⚙️ Approach
### **1️⃣ Initial Setup**
1. Start at top-right corner (row 0, column n-1)
2. Initialize search within matrix bounds

### **2️⃣ Search Execution**
1. Compare current element with target:
   - If equal → found target
   - If greater → move left (eliminate column)
   - If smaller → move down (eliminate row)
2. Continue until target found or out of bounds

### **3️⃣ Termination**
- Return true if target found
- Return false if search exits matrix bounds

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
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Start from top-right corner
        int row = 0;
        int col = n - 1;
        
        while (row < m && col >= 0) {
            int current = matrix[row][col];
            if (current == target) {
                return true;
            } else if (current > target) {
                col--; // Eliminate current column
            } else {
                row++; // Eliminate current row
            }
        }
        return false;
    }
}
```

Key Improvements:
1. **Edge Case Handling**: Explicit check for empty matrix
2. **Clear Variable Naming**: Better reflects matrix dimensions
3. **Simpler Logic Flow**: Direct comparison with early return
4. **Consistent Formatting**: Improved readability

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(m + n)   | Worst-case visits each row and column once |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `matrix = [[1,4,7,11,15],
                     [2,5,8,12,19],
                     [3,6,9,16,22],
                     [10,13,14,17,24],
                     [18,21,23,26,30]], 
       target = 5`

**Execution Steps:**
1. Start at (0,4)=15 > 5 → move left to (0,3)=11
2. 11 > 5 → move left to (0,2)=7
3. 7 > 5 → move left to (0,1)=4
4. 4 < 5 → move down to (1,1)=5
5. Found target → return true

**Result:** true

---

## 💡 Key Features
- **Efficient Elimination**: Each step discards a row or column
- **No Preprocessing**: Works directly on given matrix
- **Optimal Worst-Case**: Guaranteed O(m+n) performance
- **Space Efficiency**: Uses only constant extra space

---

## 🚀 When to Use
- Searching in row/column sorted matrices
- Problems requiring efficient 2D search
- When binary search would be less efficient

## ⚠️ Edge Cases
- **Empty matrix**: Handled by initial check
- **Target at boundaries**: Properly detected
- **Single row/column**: Works normally
- **Negative numbers**: Handled by normal comparison

## 🛠 Variations
1. **Count Occurrences**:
```java
// Modify to count all target occurrences
```

2. **Find Closest Element**:
```java
// Track closest element during search
```

3. **Diagonal Search**:
```java
// Alternative search patterns for different matrix properties
```