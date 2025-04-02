# 🚀 Maximum Product Subarray - Dynamic Programming Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/maximum-product-subarray/description/)

Given an integer array `nums`, find the contiguous subarray within the array (containing at least one number) which has the largest product.

**Constraints:**
- 1 ≤ nums.length ≤ 2 × 10⁴
- -10 ≤ nums[i] ≤ 10
- The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

**Example 1:**
```text
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
```

**Example 2:**
```text
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```

---

## 🧠 Intuition
The solution uses dynamic programming with these key insights:
1. **Track Both Max and Min**: Negative numbers can turn minimum into maximum when multiplied by another negative
2. **Reset on Zero**: Zero resets the current product chain
3. **Global Maximum**: Continuously update the global maximum product

Key Observations:
- Need to track both maximum and minimum products at each step
- Negative numbers can create larger products when multiplied together
- Must handle zeros appropriately

---

## ⚙️ Approach
### **1️⃣ Dynamic Programming with State Tracking**
1. Initialize:
   - `res` to track global maximum (initialized to max single element)
   - `curMax` and `curMin` to track current products

2. For each number:
   - If number is zero: reset current products
   - Else:
     - Calculate new products including current number
     - Update `curMax` and `curMin` considering all possibilities
     - Update global maximum

### **2️⃣ Three-Way Comparison**
At each step, consider:
- Current number alone
- Product with previous maximum
- Product with previous minimum

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        // Initialize res with maximum single element
        for (int num : nums) {
            res = Math.max(res, num);
        }
        
        int curMax = 1, curMin = 1;
        for (int num : nums) {
            if (num != 0) {
                int temp = curMax * num;
                // Update current max and min
                curMax = Math.max(Math.max(temp, curMin * num), num);
                curMin = Math.min(Math.min(temp, curMin * num), num);
                // Update global maximum
                res = Math.max(res, curMax);
            } else {
                // Reset on encountering zero
                curMax = 1;
                curMin = 1;
            }
        }
        return res;
    }
}
```

Key Components:
1. **Initial Maximum Check**: Handles single-element cases
2. **Product Tracking**: Maintains current max and min products
3. **Zero Handling**: Resets product chains
4. **Global Update**: Continuously tracks maximum product

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through array |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `[2,3,-2,4]`

**Execution Steps:**
1. Initialize: res = 4 (from initial scan)
2. Process 2:
   - curMax=2, curMin=2
   - res=2
3. Process 3:
   - temp=6
   - curMax=6, curMin=3
   - res=6
4. Process -2:
   - temp=-12
   - curMax=3 (from -2*3), curMin=-12
   - res remains 6
5. Process 4:
   - temp=12
   - curMax=12, curMin=-48
   - res=12

**Final Result:** 6 (from [2,3])

---

## 💡 Key Features
- **Handles Negatives**: Tracks both max and min products
- **Efficient**: Single pass O(n) solution
- **Zero Handling**: Properly resets product chains
- **Compact Code**: Minimal space usage

---

## 🚀 When to Use
- **Maximum product problems**
- **When negative numbers are present**
- **Subarray product calculations**
- **As building block** for more complex DP problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| DP with State Tracking (this) | O(n) | O(1) | Handles negatives optimally |
| Brute Force | O(n²) | O(1) | Simple but inefficient |
| Prefix Product | O(n) | O(n) | Easier but more space |

## ⚠️ Edge Cases
- **Single negative number**: Returns the number itself
- **All zeros**: Returns zero
- **Alternating positives/negatives**: Correctly computes
- **Large input size**: Handles within constraints

## 🛠 Variations
1. **Maximum Sum Subarray**:
```java
// Kadane's algorithm adaptation
```

2. **Minimum Product Subarray**:
```java
// Track minimum product similarly
```

3. **Non-Contiguous Maximum Product**:
```java
// Select any subset of numbers
```

This solution demonstrates an elegant dynamic programming approach to efficiently find the maximum product subarray, handling all edge cases including negative numbers and zeros while maintaining optimal time and space complexity.