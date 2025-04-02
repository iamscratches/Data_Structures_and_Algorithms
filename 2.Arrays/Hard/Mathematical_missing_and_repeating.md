# 🔍 Find Missing and Repeating Numbers - Mathematical Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/find-missing-and-repeating2512/1)

Given an unsorted array of size `n` containing numbers from 1 to `n`, find:
1. The number that appears twice (repeating)
2. The number that is missing

**Constraints:**
- 2 ≤ n ≤ 10⁵
- 1 ≤ a[i] ≤ n

**Example 1:**
```text
Input: [1, 3, 3]
Output: [3, 2]
Explanation: 3 appears twice, 2 is missing
```

**Example 2:**
```text
Input: [2, 2]
Output: [2, 1]
Explanation: 2 appears twice, 1 is missing
```

---

## 🧠 Intuition
The solution uses mathematical properties:
1. **Sum of Numbers**: Calculates expected vs actual sums
2. **Sum of Squares**: Provides second equation for solving
3. **Equation Solving**: Derives repeating and missing numbers

Key Insights:
- Sum of first n natural numbers: `n(n+1)/2`
- Sum of squares: `n(n+1)(2n+1)/6`
- The difference between actual and expected sums reveals the solution

---

## ⚙️ Approach
### **1️⃣ Mathematical Formulation**
1. Calculate:
   - `SN`: Sum of first n natural numbers
   - `S2N`: Sum of squares of first n natural numbers
   - `S`: Sum of array elements
   - `S2`: Sum of squares of array elements

2. Compute differences:
   - `val1 = S - SN` (x - y)
   - `val2 = S2 - S2N` (x² - y²)

3. Solve equations:
   - `x + y = val2 / val1`
   - `x - y = val1`

### **2️⃣ Solution Derivation**
- `x = (val1 + val2/val1)/2`
- `y = x - val1`

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public ArrayList<Integer> findTwoElement(int a[]) {
        long n = a.length;
        
        // Calculate theoretical sums
        long SN = (n * (n + 1)) / 2;
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;
        
        // Calculate actual sums
        long S = 0, S2 = 0;
        for (int i = 0; i < n; i++) {
            S += a[i];
            S2 += (long)a[i] * (long)a[i];
        }
        
        // Compute differences
        long val1 = S - SN;       // x - y
        long val2 = S2 - S2N;     // x² - y²
        
        // Solve equations
        long X = (val1 + val2 / val1) / 2;  // Repeating
        long Y = X - val1;                  // Missing
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add((int)X);
        ans.add((int)Y);
        return ans;
    }
}
```

Key Components:
1. **Sum Calculations**: Theoretical vs actual sums
2. **Equation Setup**: Creates solvable system
3. **Precision Handling**: Uses long to prevent overflow
4. **Result Extraction**: Derives both numbers

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single array traversal |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `[1, 3, 3]`

**Calculations:**
1. n = 3
2. SN = 6, S2N = 14
3. S = 7, S2 = 19
4. val1 = 7-6 = 1 (x-y)
5. val2 = 19-14 = 5 (x²-y²)
6. val2/val1 = 5/1 = 5 (x+y)
7. x = (1+5)/2 = 3
8. y = 3-1 = 2

**Result:** `[3, 2]`

---

## 💡 Key Features
- **Mathematical Elegance**: Uses algebraic identities
- **Efficient Computation**: O(n) time complexity
- **No Extra Data Structures**: Constant space usage
- **Precision Handling**: Uses long to avoid overflow

---

## 🚀 When to Use
- **Array element analysis problems**
- **When mathematical properties can be leveraged**
- **Space-constrained solutions**
- **As building block** for more complex number problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Mathematical (this) | O(n) | O(1) | Most space efficient |
| Hash Map | O(n) | O(n) | Simpler but more space |
| Sorting | O(nlogn) | O(1) | Modifies input |

## ⚠️ Edge Cases
- **Small arrays**: Handles minimum n=2
- **Large numbers**: Uses long to prevent overflow
- **First/last element missing**: Correctly identifies
- **All elements same**: Returns [1,1] for [1,1]

## 🛠 Variations
1. **Multiple Repeats/Missing**:
```java
// Requires more complex mathematical modeling
```

2. **Streaming Data Version**:
```java
// Handle data arriving in streams
```

3. **Find All Duplicates**:
```java
// Extend to find all repeating numbers
```

This solution demonstrates an elegant mathematical approach to solving the missing/repeating number problem with optimal efficiency. The method cleverly uses algebraic identities to derive the solution without additional data structures.