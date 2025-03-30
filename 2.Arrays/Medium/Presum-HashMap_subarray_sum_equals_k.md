# 🔍 Subarray Sum Equals K - Prefix Sum with Hash Map

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/subarray-sum-equals-k/)

Given an array of integers `nums` and an integer `k`, return the total number of subarrays whose sum equals to `k`.

**Constraints:**
- 1 ≤ nums.length ≤ 2 * 10⁴
- -1000 ≤ nums[i] ≤ 1000
- -10⁷ ≤ k ≤ 10⁷

**Example 1:**
```text
Input: nums = [1,1,1], k = 2
Output: 2
Explanation: The subarrays [1,1] and [1,1] sum to 2
```

**Example 2:**
```text
Input: nums = [1,2,3], k = 3
Output: 2
Explanation: The subarrays [1,2] and [3] sum to 3
```

---

## 🧠 Intuition
The optimal solution uses:
1. **Prefix Sum Calculation**: Track cumulative sums
2. **Hash Map Lookup**: Store frequency of prefix sums
3. **Complementary Sum Check**: Find (current_sum - k) in map

Key Insights:
- Any subarray sum can be represented as difference of two prefix sums
- If (current_sum - k) exists in map, those starting points create valid subarrays
- Need to handle the case where prefix sum equals k itself

---

## ⚙️ Approach
### **1️⃣ Prefix Sum with Hash Map**
1. Initialize:
   - Hash map to store prefix sum frequencies (sum → count)
   - Running sum variable (prefixSum)
   - Result counter (count)

2. For each element:
   - Update running prefixSum
   - If prefixSum equals k, increment count
   - If (prefixSum - k) exists in map, add its frequency to count
   - Update map with current prefixSum

### **2️⃣ Mathematical Foundation**
- Subarray sum from i to j = prefixSum[j] - prefixSum[i-1]
- We're looking for prefixSum[j] - prefixSum[i] = k
- Which implies prefixSum[i] = prefixSum[j] - k

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int count = 0;
        int prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Case 1: Current prefix sum equals k
            if (prefixSum == k) {
                count++;
            }
            
            // Case 2: (prefixSum - k) exists in map
            if (prefixSumMap.containsKey(prefixSum - k)) {
                count += prefixSumMap.get(prefixSum - k);
            }
            
            // Update prefix sum frequency
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
```

Key Components:
1. **Prefix Sum Tracking**: Maintains running total
2. **Hash Map Lookup**: O(1) time complexity for sum checks
3. **Two Cases Handling**: Direct matches and complementary sums
4. **Frequency Counting**: Tracks all possible starting points

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through array |
| **Space**       | O(n)       | Worst-case hash map storage |

---

## 📊 Example Walkthrough

**Input:** `nums = [1,2,3], k = 3`

**Execution Steps:**
1. Initialize: map = {}, prefixSum = 0, count = 0
2. i=0 (num=1):
   - prefixSum = 1
   - No match (1≠3), (1-3=-2) not in map
   - map = {1:1}
3. i=1 (num=2):
   - prefixSum = 3
   - Direct match (3==3) → count=1
   - (3-3=0) not in map
   - map = {1:1, 3:1}
4. i=2 (num=3):
   - prefixSum = 6
   - No direct match (6≠3)
   - (6-3=3) found in map → count=2
   - map = {1:1, 3:1, 6:1}
5. Result: 2

---

## 💡 Key Features
- **Single Pass Solution**: Efficient O(n) time
- **Mathematical Insight**: Uses prefix sum difference
- **Comprehensive Counting**: Handles all possible cases
- **Generalizable**: Works for positive/negative numbers

---

## 🚀 When to Use
- **Subarray sum problems**
- **Prefix sum applications**
- **Problems requiring frequency counting**
- **When optimal O(n) solution is needed**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Prefix Sum + Hash (this) | O(n) | O(n) | Optimal for arbitrary numbers |
| Brute Force | O(n²) | O(1) | Simple but inefficient |
| Sliding Window | O(n) | O(1) | Only works for positive numbers |

## ⚠️ Edge Cases
- **Entire array sum equals k**: `nums=[1,2,3], k=6`
- **Single element equals k**: `nums=[3], k=3`
- **Negative numbers**: `nums=[-1,-2,-3], k=-3`
- **Multiple prefixes same sum**: `nums=[0,0,0], k=0`

## 🛠 Variations
1. **Longest Subarray Length**:
```java
// Track earliest index instead of count
```

2. **Binary Subarrays**:
```java
// Special case when k=1 with 0/1 arrays
```

3. **Product Equals K**:
```java
// Similar approach using logarithms
```

This solution demonstrates an elegant application of prefix sums and hash maps for efficient subarray sum calculation, handling all cases including negative numbers and zeros.