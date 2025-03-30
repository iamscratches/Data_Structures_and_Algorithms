# 🚀 Set Matrix Zeroes - Optimal In-Place Solution

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/set-matrix-zeroes/description/)

Given an m×n matrix, if an element is 0, set its entire row and column to 0. Do this in-place.

**Constraints:**
- m == matrix.length
- n == matrix[0].length
- 1 ≤ m, n ≤ 200
- -2³¹ ≤ matrix[i][j] ≤ 2³¹ - 1

**Example 1:**
```text
Input: [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
```

**Example 2:**
```text
Input: [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
```

---

## 🧠 Intuition
The optimal solution:
1. Uses **first row and column as markers** for zero rows/columns
2. **Separately tracks** if first row/column itself needs zeroing
3. **Two-pass approach**:
   - First pass: Mark rows and columns to be zeroed
   - Second pass: Apply the zeroes

Key Insights:
- Avoids O(m+n) space by reusing matrix borders
- Handles edge cases for first row/column
- Maintains in-place modification requirement

---

## ⚙️ Approach
### **1️⃣ Initial Checks**
1. Check if first row contains any zeros (`isRowZero`)
2. Check if first column contains any zeros (`isColZero`)

### **2️⃣ Marking Phase**
1. Use first row/column to mark other zeros:
   - If `matrix[i][j] == 0`, set `matrix[i][0] = 0` and `matrix[0][j] = 0`

### **3️⃣ Zeroing Phase**
1. Zero rows based on first column marks
2. Zero columns based on first row marks
3. Zero first row/column if needed

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean isRowZero = false, isColZero = false;
        
        // Check if first row needs zeroing
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                isRowZero = true;
                break;
            }
        }
        
        // Check if first column needs zeroing
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                isColZero = true;
                break;
            }
        }
        
        // Mark zeros on first row and column
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // Zero rows based on first column
        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Zero columns based on first row
        for (int j = 1; j < cols; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Zero first row if needed
        if (isRowZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
        
        // Zero first column if needed
        if (isColZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

Key Components:
1. **First Row/Column Checks**: Special handling for borders
2. **Marking Phase**: Uses borders as zero indicators
3. **Zeroing Phase**: Applies changes based on marks
4. **Border Zeroing**: Final cleanup of first row/column

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(m×n)     | Two full passes through matrix |
| **Space**       | O(1)       | Uses only constant extra space |

---

## 📊 Example Walkthrough

**Input:** `[[0,1,2,0],[3,4,5,2],[1,3,1,5]]`

**Execution Steps:**
1. Detect first row/column zeros:
   - First row has zeros → `isRowZero=true`
   - First column has zero → `isColZero=true`
2. Mark other zeros:
   - No additional zeros found
3. Zero rows/columns:
   - First row and column zeroed due to flags
4. Final matrix: `[[0,0,0,0],[0,4,5,0],[0,3,1,0]]`

---

## 💡 Key Features
- **In-Place Modification**: No additional matrix needed
- **Efficiency**: Optimal O(m×n) time
- **Edge Handling**: Special care for first row/column
- **Readability**: Clear separation of marking and zeroing phases

---

## 🚀 When to Use
- **Matrix manipulation problems**
- **In-place algorithm requirements**
- **Problems with space constraints**
- **As preprocessing step** for other matrix operations

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Border Marking (this) | O(m×n)    | O(1)             | Optimal space |
| Additional Storage | O(m×n)    | O(m+n)           | Simpler logic |
| Brute Force      | O(m×n(m+n))    | O(1)             | None       |

## ⚠️ Edge Cases
- **Single row/column matrices**: Handled correctly
- **All zeros**: Entire matrix zeroed
- **No zeros**: Matrix remains unchanged
- **First row/column zeros**: Special flags ensure correctness

## 🛠 Variations
1. **Return New Matrix** (non-in-place):
```java
// Create new matrix instead of modifying input
```

2. **Multiple Zero Passes**:
```java
// For matrices that can't be modified
```

3. **Different Marker Values**:
```java
// Use special values other than 0 for marking
```

This solution demonstrates an elegant approach to matrix modification problems, balancing efficiency with space constraints while maintaining clear, logical code structure.