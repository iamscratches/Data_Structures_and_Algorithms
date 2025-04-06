# 🔍 Find the Smallest Divisor Given a Threshold - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/)

Given an array of integers `nums` and an integer `threshold`, find the smallest positive integer divisor such that the sum of division results (rounded up) is less than or equal to the threshold.

**Constraints:**
- 1 ≤ nums.length ≤ 5 × 10^4
- 1 ≤ nums[i] ≤ 10^6
- nums.length ≤ threshold ≤ 10^6

**Example 1:**
```text
Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation:
- With divisor 1: 1+2+5+9 = 17 > 6
- With divisor 2: 1+1+3+5 = 10 > 6
- With divisor 3: 1+1+2+3 = 7 > 6
- With divisor 4: 1+1+2+3 = 7 > 6
- With divisor 5: 1+1+1+2 = 5 ≤ 6
```

**Example 2:**
```text
Input: nums = [44,22,33,11,1], threshold = 5
Output: 44
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Divisor Range**: The smallest possible divisor is 1, largest is max(nums)
2. **Monotonicity**: If sum ≤ threshold at divisor `d`, it will be ≤ threshold for all divisors > `d`
3. **Binary Search**: Efficiently search for minimal divisor between 1 and max(nums)

Key Observations:
- The problem is about finding the minimal divisor where the sum of ceilings ≤ threshold
- For each candidate divisor, we can calculate the sum
- The validity function (sum ≤ threshold) is monotonic

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize `low = 1`, `high = max(nums)`
2. Initialize `result` with worst-case solution (max divisor)

### **2️⃣ Binary Search Execution**
1. Calculate `mid` divisor
2. Check if sum of ceilings ≤ threshold at this divisor
3. Adjust search space:
   - If valid, try smaller divisors (search left)
   - If invalid, try larger divisors (search right)

### **3️⃣ Optimal Solution**
- Track the minimal valid divisor throughout search
- Returns the smallest valid divisor found

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1, high = 0;
        for (int num : nums) {
            high = Math.max(high, num);
        }
        int res = high;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(nums, mid, threshold)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }
    
    private boolean isValid(int[] nums, int divisor, int threshold) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + divisor - 1) / divisor; // Ceiling division
            if (sum > threshold) {
                return false;
            }
        }
        return sum <= threshold;
    }
}
```

Key Improvements:
1. **Efficient Ceiling Calculation**: Uses integer math for ceiling division
2. **Early Termination**: Stops sum calculation when threshold exceeded
3. **Clean Implementation**: Simple binary search structure

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n log m) | n = nums.length, m = max(nums) |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [1,2,5,9], threshold = 6`

**Execution Steps:**
1. Initialize: low=1, high=9, res=9
2. mid=5 → sum=1+1+1+2=5 ≤ 6 → res=5, high=4
3. mid=2 → sum=1+1+3+5=10 > 6 → low=3
4. mid=3 → sum=1+1+2+3=7 > 6 → low=4
5. mid=4 → sum=1+1+2+3=7 > 6 → low=5
6. Exit loop (low > high)

**Result:** 5

---

## 💡 Key Features
- **Binary Search Efficiency**: O(log m) iterations
- **Optimal Validation**: O(n) sum calculation per iteration
- **Early Termination**: Stops when threshold exceeded
- **Edge Handling**: Works for all constraint ranges

---

## 🚀 When to Use
- Finding minimal parameter satisfying threshold
- Problems with monotonic validity functions
- When O(n) validation is acceptable within binary search

## ⚠️ Edge Cases
- **Single element array**: Returns the element if threshold ≥ 1
- **All elements equal**: Returns first valid divisor
- **Large threshold**: Returns divisor=1
- **Large numbers**: Handled by efficient ceiling calculation

## 🛠 Variations
1. **Different Rounding**:
```java
// If using floor division instead of ceiling
```

2. **Weighted Elements**:
```java
// If elements have different weights
```

3. **Multiple Thresholds**:
```java
// If different thresholds for array segments
```