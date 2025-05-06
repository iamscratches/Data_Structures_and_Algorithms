# 📊 Maximum Frequency with K Operations - Sliding Window Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/frequency-of-the-most-frequent-element/description/)

Given an array `nums` and an integer `k`, find the maximum frequency of any element after performing at most `k` increment operations (each operation increments an element by 1).

**Constraints:**
- 1 ≤ nums.length ≤ 10⁵
- 1 ≤ nums[i] ≤ 10⁵
- 1 ≤ k ≤ 10⁹

**Example:**
```text
Input: nums = [1,2,4], k = 5
Output: 3
Explanation: Increment 1 to 4 (3 operations) and 2 to 4 (2 operations)
```

## 🧠 Intuition
The solution uses:
1. **Sorting**: Enables efficient window operations
2. **Sliding Window**: Maintains valid subarrays
3. **Prefix Sum**: Tracks operations needed

Key Insights:
- Target is to maximize frequency of some element
- Operations needed = sum of differences from target
- Window expands when operations ≤ k, contracts otherwise

## ⚙️ Approach
1. **Initialization**:
   - Sort the array
   - Initialize window pointers and sum
2. **Window Expansion**:
   - Expand right while operations ≤ k
   - Update maximum frequency
3. **Window Contraction**:
   - Contract left when operations > k
4. **Result**:
   - Return maximum frequency found

## ✅ Optimized Solution
```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int maxFreq = 0;
        long windowSum = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            
            // Check if current window is valid
            while ((long)nums[right] * (right - left + 1) > windowSum + k) {
                windowSum -= nums[left];
                left++;
            }
            
            maxFreq = Math.max(maxFreq, right - left + 1);
        }
        
        return maxFreq;
    }
}
```

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n log n) | Sorting + O(n) sliding window |
| **Space**       | O(1)       | Constant extra space |

## 📊 Example Walkthrough
**Input:** nums = [1,2,4], k = 5

**Execution:**
1. Sorted array: [1,2,4]
2. Window [1]: sum=1, ops=0 → max=1
3. Window [1,2]: sum=3, ops=1 → max=2
4. Window [1,2,4]: sum=7, ops=11 > 5 → contract
5. Window [2,4]: sum=6, ops=4 ≤ 5 → max=3
6. Result: 3

## 💡 Key Features
- **Efficiency**: Handles large input sizes
- **Optimality**: Guarantees maximum frequency
- **Numerical Safety**: Uses long to prevent overflow
- **Simplicity**: Clean sliding window logic

## 🚀 When to Use
- Frequency maximization problems
- When operations can modify elements
- Sliding window applicable scenarios
- Problems with similar increment constraints

## ⚠️ Edge Cases
- **All identical**: Returns array length
- **k=0**: Returns max natural frequency
- **Single element**: Returns 1
- **Large k**: Can convert all to max element

## 🛠 Variations
1. **Decrement Operations**:
```java
// Allow decrement operations
```

2. **Different Costs**:
```java
// Varying operation costs
```

3. **Non-Integer Elements**:
```java
// Handle floating-point numbers
```

4. **2D Array**:
```java
// Extend to matrix operations
```

5. **Parallel Processing**:
```java
// Process array segments in parallel
```

6. **Visualization**:
```java
// Generate operation sequence visualization
```

## Mathematical Explanation
The key formula:
```
nums[right] * windowLength ≤ windowSum + k
```
Calculates if current window can be converted to all `nums[right]` with ≤k operations

## Alternative Implementation (Prefix Sum)
```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i+1] = prefix[i] + nums[i];
        }
        
        int max = 0;
        for (int right = 0; right < nums.length; right++) {
            int left = findLeft(nums, prefix, k, right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
    
    private int findLeft(int[] nums, long[] prefix, int k, int right) {
        int left = 0, res = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long cost = nums[res] * (res - mid + 1L) - (prefix[res+1] - prefix[mid]);
            if (cost <= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```