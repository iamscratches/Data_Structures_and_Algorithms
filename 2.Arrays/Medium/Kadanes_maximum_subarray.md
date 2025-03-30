# 🚀 Maximum Subarray - Kadane's Algorithm

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/maximum-subarray/description/)

Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum.

**Constraints:**
- `1 <= nums.length <= 10⁵`
- `-10⁴ <= nums[i] <= 10⁴`

**Example:**
```text
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6
```

---

## 🧠 Intuition
Kadane's Algorithm:
1. **Tracks maximum sum** ending at current position
2. **Resets running sum** when it becomes negative
3. **Maintains global maximum** throughout the process

Key Insights:
- Negative prefixes reduce the maximum sum (can be discarded)
- Local maximum is either current element or current element + previous maximum
- Single pass through array suffices

---

## ⚙️ Approach
### **1️⃣ Initialization**
- `max` = -∞ (global maximum)
- `currentVal` = 0 (running sum)

### **2️⃣ Iteration**
- For each element:
  - Add to `currentVal`
  - Update `max` if `currentVal` is greater
  - Reset `currentVal` to 0 if it becomes negative

### **3️⃣ Termination**
- Return `max` after full iteration

---

## 📐 Mathematical Derivation
### **1. Recurrence Relation**
```
dp[i] = max(nums[i], nums[i] + dp[i-1])
```
Where `dp[i]` is maximum subarray ending at i

### **2. Time Complexity**
- Single pass through array: O(n)
- Constant time operations per element

### **3. Space Complexity**
- O(1) space (only two variables needed)
- No additional data structures

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int currentVal = 0;
        
        for (int num : nums) {
            currentVal += num;
            max = Math.max(max, currentVal);
            if (currentVal < 0) {
                currentVal = 0;
            }
        }
        
        return max;
    }
}
```

Key Components:
1. **Initialization**: Sets up tracking variables
2. **Running Sum**: Accumulates contiguous sum
3. **Global Maximum**: Constantly updated
4. **Negative Reset**: Discards reducing prefixes

---

## ⏳ Complexity Analysis
### **Time Complexity: O(n)**
- Processes each element exactly once
- Constant work per iteration

### **Space Complexity: O(1)**
- Only two integer variables used
- No recursion or additional arrays

---

## 📊 Example Walkthrough

**Input:** `[-2,1,-3,4,-1,2,1,-5,4]`

**Execution Steps:**
1. num=-2: currentVal=-2, max=-2, reset to 0
2. num=1: currentVal=1, max=1
3. num=-3: currentVal=-2, max=1, reset to 0
4. num=4: currentVal=4, max=4
5. num=-1: currentVal=3, max=4
6. num=2: currentVal=5, max=5
7. num=1: currentVal=6, max=6
8. num=-5: currentVal=1, max=6
9. num=4: currentVal=5, max=6

**Output:** `6` (from subarray [4,-1,2,1])

---

## 💡 Key Features
- **One-pass efficiency**: Optimal O(n) time
- **Constant space**: Minimal memory footprint
- **Elegant reset logic**: Handles negative prefixes
- **Handles all negatives**: Returns least negative

---

## 🚀 When to Use
- **Contiguous subarray problems**
- **Real-time data processing**
- **Streaming algorithms**
- **Maximum profit calculations**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|------------------|-----------------|------------------|------------|
| Kadane's (this)  | O(n)            | O(1)             | Optimal    |
| Divide & Conquer | O(n log n)      | O(log n)         | Parallelizable |
| Brute Force      | O(n²)           | O(1)             | Simple     |

## ⚠️ Edge Cases
- **All negatives**: Returns max single element
- **Single element**: Returns that element
- **All positives**: Returns entire array sum
- **Alternating signs**: Correctly finds maximum

## 🛠 Variations
1. **Return Subarray Indices**:
```java
int start = 0, end = 0, tempStart = 0;
// Update indices when max changes
```

2. **Circular Array**:
```java
// Calculate both max subarray and min subarray
int total = Arrays.stream(nums).sum();
return Math.max(max, total - min);
```

3. **2D Version** (Maximum Submatrix):
- Applies Kadane's to rows/columns
- O(n³) time for n×n matrix

This algorithm demonstrates how intelligent tracking of local and global maxima can solve complex problems efficiently, making it a cornerstone of array processing techniques.