# 🚀 Rearrange Array Elements by Sign - Optimal In-Place Solution

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/rearrange-array-elements-by-sign/description/)

Given an array of integers `nums` with equal number of positive and negative integers, rearrange them such that:
1. Every consecutive pair has opposite signs
2. Positive numbers appear first in their relative order
3. Negative numbers appear after in their relative order

**Constraints:**
- 2 ≤ nums.length ≤ 2×10⁵ (always even)
- -10⁹ ≤ nums[i] ≤ 10⁹
- Equal number of positives and negatives

**Example 1:**
```text
Input: [3,1,-2,-5,2,-4]
Output: [3,-2,1,-5,2,-4]
```

**Example 2:**
```text
Input: [-1,1]
Output: [1,-1]
```

---

## 🧠 Intuition
The optimal solution:
1. Uses **two pointers** (even for positives, odd for negatives)
2. **Maintains relative order** by processing sequentially
3. **One-pass operation** with O(n) time and space

Key Insights:
- Array length is always even (equal positives/negatives)
- Positive numbers occupy even indices (0, 2, 4...)
- Negative numbers occupy odd indices (1, 3, 5...)
- Relative order preserved by sequential placement

---

## ⚙️ Approach
### **1️⃣ Two Pointer Placement**
1. Initialize:
   - `posIndex = 0` (even indices for positives)
   - `negIndex = 1` (odd indices for negatives)
2. Iterate through array:
   - Place positives at `posIndex` and increment by 2
   - Place negatives at `negIndex` and increment by 2
3. Return new array

### **2️⃣ Why It Works**
- Maintains exact alternating pattern
- Preserves relative order via sequential processing
- Handles all valid inputs within constraints

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] result = new int[nums.length];
        int posPtr = 0, negPtr = 1;
        
        for (int num : nums) {
            if (num > 0) {
                result[posPtr] = num;
                posPtr += 2;
            } else {
                result[negPtr] = num;
                negPtr += 2;
            }
        }
        return result;
    }
}
```

Key Components:
1. **Result Array**: Stores rearranged elements
2. **Dual Pointers**: Separate indices for positives/negatives
3. **Sequential Processing**: Maintains relative order
4. **Increment by 2**: Ensures proper spacing

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through array |
| **Space**       | O(n)       | New array storage |

---

## 📊 Example Walkthrough

**Input:** `[3,1,-2,-5,2,-4]`

**Execution Steps:**
1. 3 (positive): result[0]=3, posPtr=2
2. 1 (positive): result[2]=1, posPtr=4
3. -2 (negative): result[1]=-2, negPtr=3
4. -5 (negative): result[3]=-5, negPtr=5
5. 2 (positive): result[4]=2, posPtr=6
6. -4 (negative): result[5]=-4

**Output:** `[3,-2,1,-5,2,-4]`

---

## 💡 Key Features
- **Order Preservation**: Maintains original relative order
- **Efficiency**: Optimal O(n) time complexity
- **Readability**: Clear pointer management
- **Constraint Handling**: Works within all problem limits

---

## 🚀 When to Use
- **Alternating pattern problems**
- **In-order element rearrangement**
- **Positive-negative segregation tasks**
- **As preprocessing step** for other algorithms

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Two Pointer (this) | O(n)          | O(n)             | Preserves order |
| Sort + Rearrange | O(n log n)      | O(1)             | Doesn't preserve order |
| Stacks           | O(n)            | O(n)             | More complex |

## ⚠️ Edge Cases
- **All positives then negatives**: Handled correctly
- **Minimum/Maximum values**: Works within integer limits
- **Large arrays**: Efficient within constraints
- **Single pair**: Works trivially ([+,-])

## 🛠 Variations
1. **Unequal Positives/Negatives**:
```java
// Would require different handling
// Either pad with zeros or modify pattern
```

2. **Different Patterns**:
```java
// e.g., negative-first alternation
int posPtr = 1, negPtr = 0;
```

3. **In-Place Rearrangement** (If space constrained):
```java
// More complex O(1) space solutions exist
// Using cyclic rotations or similar
```

This solution demonstrates an elegant approach to element rearrangement problems, balancing efficiency with clear logic while respecting all problem constraints.