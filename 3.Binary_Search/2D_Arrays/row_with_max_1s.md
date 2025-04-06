# 🔍 Row with Maximum 1s - Binary Search Approach

## 📜 Problem Statement
**Link:** [GFG Problem](https://www.geeksforgeeks.org/problems/row-with-max-1s0023/1)

Given a boolean 2D array (matrix) where each row is sorted, find the row with the maximum number of 1s.

**Constraints:**
- N, M ≤ 10^3
- Each row is sorted in non-decreasing order
- 0s appear before 1s in each row

**Example 1:**
```text
Input: 
N = 4, M = 4
Arr[][] = {{0, 1, 1, 1},
           {0, 0, 1, 1},
           {1, 1, 1, 1},
           {0, 0, 0, 0}}
Output: 2
Explanation: Row 2 contains the maximum number of 1s (4).
```

**Example 2:**
```text
Input: 
N = 2, M = 2
Arr[][] = {{0, 0}, {1, 1}}
Output: 1
Explanation: Row 1 contains the maximum number of 1s (2).
```

---

## 🧠 Intuition
The solution leverages the sorted row property with these key insights:
1. **Binary Search per Row**: For each row, find the first occurrence of 1
2. **Count Calculation**: Number of 1s = row length - first 1's index
3. **Tracking Maximum**: Keep track of row with maximum 1s count

Key Observations:
- Each row is sorted with all 0s first followed by 1s
- Binary search efficiently finds the transition point
- Solution works in O(N log M) time

---

## ⚙️ Approach
### **1️⃣ Initial Setup**
1. Initialize variables to track maximum count and corresponding row
2. Set initial maximum count to 0

### **2️⃣ Row Processing**
1. For each row:
   - Perform binary search to find first occurrence of 1
   - Calculate count of 1s in the row
   - Update maximum count and row index if current row has more 1s

### **3️⃣ Result**
- Return the row index with maximum 1s
- Returns 0 if no 1s found (as per problem constraints)

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public int rowWithMax1s(int arr[][]) {
        int maxRow = -1;  // Initialize to -1 for case with no 1s
        int maxCount = 0;
        
        for(int i = 0; i < arr.length; i++) {
            int firstOneIndex = findFirstOne(arr[i]);
            int currentCount = arr[i].length - firstOneIndex;
            
            if(currentCount > maxCount) {
                maxCount = currentCount;
                maxRow = i;
            }
        }
        
        return maxRow;
    }
    
    private int findFirstOne(int[] row) {
        int left = 0;
        int right = row.length - 1;
        int result = row.length; // Default if no 1s found
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(row[mid] == 1) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
```

Key Improvements:
1. **Separate Helper Function**: Clean `findFirstOne` method for binary search
2. **Better Initialization**: Starts with `maxRow = -1` for no-1s case
3. **Clear Variable Names**: More descriptive than original
4. **Modular Design**: Easier to maintain and understand

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(N log M) | N rows with binary search (log M) each |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `arr = [[0, 1, 1, 1],
                   [0, 0, 1, 1],
                   [1, 1, 1, 1],
                   [0, 0, 0, 0]]`

**Execution Steps:**
1. Row 0: First 1 at index 1 → 3 ones
   - Update maxCount=3, maxRow=0
2. Row 1: First 1 at index 2 → 2 ones
   - No update (3 > 2)
3. Row 2: First 1 at index 0 → 4 ones
   - Update maxCount=4, maxRow=2
4. Row 3: No 1s found → 0 ones
   - No update (4 > 0)
5. Return maxRow=2

**Result:** 2

---

## 💡 Key Features
- **Efficient Search**: Binary search per row for transition point
- **Optimal Performance**: Beats O(N*M) brute-force solution
- **Clean Implementation**: Well-structured and readable
- **Edge Case Handling**: Properly handles all-0 rows

---

## 🚀 When to Use
- Searching in sorted 2D arrays
- Problems requiring row/column statistics
- When binary search can optimize comparisons

## ⚠️ Edge Cases
- **All zeros**: Returns -1 (or 0 as per problem)
- **Single row**: Works normally
- **All ones**: Properly detected
- **Single column**: Handled correctly

## 🛠 Variations
1. **Count All 1s**:
```java
// Modify to return total count of 1s in matrix
```

2. **Column with Max 1s**:
```java
// Adapt for column-wise search
```

3. **Find Transition Rows**:
```java
// Identify rows where values change from 0 to 1
```