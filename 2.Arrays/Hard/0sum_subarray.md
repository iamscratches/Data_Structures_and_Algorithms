# 🔍 Largest Subarray with 0 Sum - Prefix Sum Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1)

Given an array of integers, find the length of the longest subarray with a sum of 0.

**Constraints:**
- 1 ≤ N ≤ 10⁵
- -10⁵ ≤ A[i] ≤ 10⁵

**Example 1:**
```text
Input: [15,-2,2,-8,1,7,10,23]
Output: 5
Explanation: The longest subarray with sum 0 is [-2,2,-8,1,7]
```

**Example 2:**
```text
Input: [1,2,3]
Output: 0
Explanation: No subarray exists with sum 0
```

---

## 🧠 Intuition
The optimal solution uses:
1. **Prefix Sum Tracking**: Calculate cumulative sums
2. **Hash Map Storage**: Store first occurrence of each prefix sum
3. **Zero Sum Detection**: When prefix sum repeats or becomes zero

Key Insights:
- If prefix sum repeats, the subarray between these indices sums to zero
- The longest subarray will be between the first occurrence and farthest repetition
- Zero prefix sum means the entire subarray from start sums to zero

---

## ⚙️ Approach
### **1️⃣ Prefix Sum with Hash Map**
1. Initialize:
   - Hash map to store prefix sums and their first indices
   - Running prefix sum variable
   - Maximum length tracker

2. For each element:
   - Update prefix sum
   - If prefix sum == 0: update max length
   - If prefix sum exists in map: calculate subarray length
   - Store prefix sum if not already in map

### **2️⃣ Mathematical Foundation**
- Subarray sum from i to j = prefixSum[j] - prefixSum[i-1]
- For zero sum: prefixSum[j] == prefixSum[i]
- The longest subarray maximizes (j - i)

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    int maxLen(int arr[]) {
        Map<Long, Integer> prefixMap = new HashMap<>();
        long prefixSum = 0;
        int maxLength = 0;
        
        for (int i = 0; i < arr.length; i++) {
            prefixSum += arr[i];
            
            // Case 1: Entire subarray from start sums to 0
            if (prefixSum == 0) {
                maxLength = i + 1;
            }
            // Case 2: Prefix sum seen before
            else if (prefixMap.containsKey(prefixSum)) {
                maxLength = Math.max(maxLength, i - prefixMap.get(prefixSum));
            }
            // Store first occurrence only
            if (!prefixMap.containsKey(prefixSum)) {
                prefixMap.put(prefixSum, i);
            }
        }
        return maxLength;
    }
}
```

Key Components:
1. **Prefix Sum Calculation**: Tracks cumulative sum
2. **Hash Map Lookup**: O(1) time complexity for sum checks
3. **Length Tracking**: Maintains maximum valid subarray length
4. **First Occurrence Storage**: Ensures maximum possible length

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through array |
| **Space**       | O(n)       | Worst-case hash map storage |

---

## 📊 Example Walkthrough

**Input:** `[15,-2,2,-8,1,7,10,23]`

**Execution Steps:**
1. Initialize: map = {}, prefixSum = 0, max = 0
2. i=0 (15):
   - prefixSum = 15
   - map = {15:0}
3. i=1 (-2):
   - prefixSum = 13
   - map = {15:0, 13:1}
4. i=2 (2):
   - prefixSum = 15
   - Found in map → max = 2-0 = 2
5. i=3 (-8):
   - prefixSum = 7
   - map = {15:0, 13:1, 7:3}
6. i=4 (1):
   - prefixSum = 8
   - map = {15:0, 13:1, 7:3, 8:4}
7. i=5 (7):
   - prefixSum = 15
   - Found in map → max = max(2,5-0) = 5
8. Final result: 5

---

## 💡 Key Features
- **Single Pass Solution**: Efficient O(n) time
- **Mathematical Insight**: Uses prefix sum difference
- **Early Zero Detection**: Handles edge cases
- **Generalizable**: Works for any target sum (with modification)

---

## 🚀 When to Use
- **Subarray sum problems**
- **When optimal O(n) solution is needed**
- **Problems requiring maximum length tracking**
- **As building block** for more complex sum problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Prefix Sum + Hash (this) | O(n) | O(n) | Optimal for arbitrary numbers |
| Brute Force | O(n²) | O(1) | Simple but inefficient |
| Sliding Window | O(n) | O(1) | Only works for positive numbers |

## ⚠️ Edge Cases
- **All zeros**: Returns array length
- **No zero sum**: Returns 0
- **Single zero element**: Returns 1
- **Large arrays**: Handles within constraints

## 🛠 Variations
1. **Target Sum Subarray**:
```java
// Track (prefixSum - target) instead of just prefixSum
```

2. **Count Zero Sum Subarrays**:
```java
// Maintain count of prefix sum occurrences
```

3. **Longest Subarray with Sum K**:
```java
// Modify to track (prefixSum - K) occurrences
```

This solution demonstrates an elegant application of prefix sums and hash maps for efficient subarray sum calculation, handling all cases including negative numbers and zeros. The O(n) time complexity makes it optimal for large input sizes.