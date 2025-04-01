# 🚀 Longest Consecutive Sequence - HashSet Solution

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/longest-consecutive-sequence/description/)

Given an unsorted array of integers `nums`, find the length of the longest sequence of consecutive elements.

**Constraints:**
- 0 ≤ nums.length ≤ 10⁵
- -10⁹ ≤ nums[i] ≤ 10⁹

**Example 1:**
```text
Input: [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive sequence is [1,2,3,4]
```

**Example 2:**
```text
Input: [0,3,7,2,5,8,4,6,0,1]
Output: 9
Explanation: The longest consecutive sequence is [0,1,2,3,4,5,6,7,8]
```

---

## 🧠 Intuition
The optimal solution uses:
1. **HashSet for O(1) lookups** of elements
2. **Sequence detection** by checking for sequence starts
3. **Length calculation** by expanding from starts

Key Insights:
- A number is a sequence start if `n-1` doesn't exist
- Only need to check sequences from their starting points
- Each element is processed at most twice (O(n) time)

---

## ⚙️ Approach
### **1️⃣ HashSet Initialization**
- Convert array to HashSet for O(1) lookups

### **2️⃣ Sequence Detection**
- For each unique number:
  - If it's a sequence start (`n-1` not in set):
    - Count consecutive numbers after it
    - Update maximum length found

### **3️⃣ Optimization**
- Avoids duplicate checks by only processing sequence starts
- Each sequence is processed exactly once

---

## ✅ Code Implementation

### Optimal Solution
```java
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums) {
            numSet.add(num);
        }
        
        int longest = 0;
        
        for(int num : numSet) {
            // Check if current number is a sequence start
            if(!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                // Count consecutive numbers
                while(numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }
}
```

Key Components:
1. **HashSet Conversion**: Enables O(1) lookups
2. **Sequence Start Check**: Identifies potential sequences
3. **Streak Calculation**: Counts consecutive elements
4. **Max Tracking**: Maintains longest sequence found

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Each element processed twice |
| **Space**       | O(n)       | HashSet storage |

---

## 📊 Example Walkthrough

**Input:** `[100,4,200,1,3,2]`

**Execution Steps:**
1. HashSet: {100,4,200,1,3,2}
2. Check 100: (99 not in set)
   - Sequence: 100 → length=1
3. Check 4: (3 in set) → skip
4. Check 200: (199 not in set)
   - Sequence: 200 → length=1
5. Check 1: (0 not in set)
   - Sequence: 1,2,3,4 → length=4
6. Check 3: (2 in set) → skip
7. Check 2: (1 in set) → skip

**Output:** `4` (sequence [1,2,3,4])

---

## 💡 Key Features
- **Efficiency**: Optimal O(n) time
- **Simplicity**: Clear logic with HashSet
- **Completeness**: Handles all edge cases
- **No Sorting**: Avoids O(n log n) complexity

---

## 🚀 When to Use
- **Unsorted sequence problems**
- **Finding consecutive patterns**
- **Data preprocessing tasks**
- **As building block** for more complex algorithms

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| HashSet (this)  | O(n)            | O(n)             | Optimal    |
| Sorting         | O(n log n)      | O(1)             | Simpler but slower |
| Union-Find      | O(n α(n))       | O(n)             | Overkill   |

## ⚠️ Edge Cases
- **Empty array**: Returns 0
- **All duplicates**: Returns 1
- **Single element**: Returns 1
- **Large ranges**: Handles efficiently

## 🛠 Variations
1. **Return Sequence Values**:
```java
// Track start/end of sequences instead of just length
```

2. **Consecutive Difference**:
```java
// Find sequences with fixed difference other than 1
```

3. **Multiple Sequences**:
```java
// Return all sequences of maximum length
```

This solution demonstrates how clever use of data structures can optimize sequence detection problems, providing an elegant O(n) solution to what might initially appear to require sorting.