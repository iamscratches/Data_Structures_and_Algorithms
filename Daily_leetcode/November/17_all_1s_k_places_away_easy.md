# 📏 Check if All 1's Are At Least Length K Places Away - Distance Tracking

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/description/?envType=daily-question&envId=2025-11-17)

Given:
- An array `nums` of 0s and 1s
- An integer `k`

Find:
- Whether all 1's in the array are **at least k places apart** from each other
- Return `true` if all 1's satisfy the distance condition, `false` otherwise

**Distance Condition:**
- For any two consecutive 1's in the array, the distance between them must be **at least k+1**
- Distance = number of indices between them = `current_index - previous_index - 1 ≥ k`
- Or equivalently: `current_index - previous_index > k`

**Constraints:**
- `1 <= nums.length <= 10^5`
- `0 <= k <= nums.length`
- `nums[i]` is 0 or 1

**Example:**
```text
Input: nums = [1,0,0,0,1,0,0,1], k = 2
Output: true

Explanation:
- First 1 at index 0
- Second 1 at index 4 → distance = 4-0-1 = 3 ≥ 2 ✓
- Third 1 at index 7 → distance = 7-4-1 = 2 ≥ 2 ✓

Input: nums = [1,0,0,1,0,1], k = 2
Output: false

Explanation:
- First 1 at index 0  
- Second 1 at index 3 → distance = 3-0-1 = 2 ≥ 2 ✓
- Third 1 at index 5 → distance = 5-3-1 = 1 < 2 ✗
```

## 🧠 Intuition
The solution uses:
1. **Previous Position Tracking**: Keep track of the last seen 1's position
2. **Distance Calculation**: Check distance between consecutive 1's
3. **Early Termination**: Return false as soon as any pair violates the condition
4. **Initial Sentinel**: Start with a virtual 1 at position `-k-1`

Key Insights:
- Initialize `prev` to `-k-1` so the first real 1 is always valid
- For each new 1, check if distance from previous 1 is > k
- If distance ≤ k, immediately return false
- Update `prev` to current position when we find a 1

## ⚙️ Approach
1. **Initialize**: `prev = -k-1` (virtual 1 far to the left)
2. **Iterate Through Array**:
   - If current element is 1:
     - Check if `current_index - prev ≤ k` (violation)
     - If violation, return false immediately
     - Otherwise, update `prev = current_index`
3. **Return True**: If all 1's pass the distance check

## ✅ Optimized Solution
```java
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        // Initialize previous 1's position to a virtual position
        // that makes the first real 1 always valid
        int prev = -k - 1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // Check if current 1 is too close to previous 1
                if (i - prev <= k) {
                    return false;
                }
                // Update previous position to current
                prev = i;
            }
        }
        
        return true;
    }
}
```

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through the array |
| **Space**       | O(1)       | Only a few variables |

## 📊 Example Walkthrough
**Input:** nums = [1,0,0,0,1,0,0,1], k = 2

**Step-by-step:**
1. **Initialize**: prev = -2-1 = -3
2. **i=0**: nums[0]=1 → check: 0 - (-3) = 3 > 2 ✓ → update prev=0
3. **i=1**: nums[1]=0 → skip
4. **i=2**: nums[2]=0 → skip  
5. **i=3**: nums[3]=0 → skip
6. **i=4**: nums[4]=1 → check: 4-0=4 > 2 ✓ → update prev=4
7. **i=5**: nums[5]=0 → skip
8. **i=6**: nums[6]=0 → skip
9. **i=7**: nums[7]=1 → check: 7-4=3 > 2 ✓ → update prev=7

**Output:** true ✓

**Another Example:** nums = [1,0,0,1,0,1], k = 2
1. **Initialize**: prev = -3
2. **i=0**: nums[0]=1 → 0-(-3)=3>2 ✓ → prev=0
3. **i=1**: 0 → skip
4. **i=2**: 0 → skip
5. **i=3**: nums[3]=1 → 3-0=3>2 ✓ → prev=3
6. **i=4**: 0 → skip
7. **i=5**: nums[5]=1 → 5-3=2 ≤ 2 ✗ → return false

**Output:** false ✓

## 💡 Key Features
- **Efficient**: Single pass through array
- **Early Termination**: Stops at first violation
- **Smart Initialization**: Virtual previous position handles edge case
- **Simple Logic**: Easy to understand and implement

## 🚀 When to Use
- Distance checking in arrays
- Validation problems with spacing constraints
- When you need to check relationships between specific elements
- Problems involving consecutive element conditions

## ⚠️ Edge Cases
- **No 1's**: Empty or all zeros → return true (vacuously true)
- **Single 1**: Only one 1 in array → return true
- **k = 0**: 1's can be adjacent (distance ≥ 0 always true)
- **k = array length**: Only possible with at most two 1's
- **All 1's**: Must be spaced by at least k zeros between them

## 🛠 Variations
1. **Explicit Distance Calculation**:
```java
public boolean kLengthApart(int[] nums, int k) {
    int prev = -1;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] == 1) {
            if (prev != -1 && i - prev - 1 < k) {
                return false;
            }
            prev = i;
        }
    }
    return true;
}
```

2. **Count Zeros Between**:
```java
public boolean kLengthApart(int[] nums, int k) {
    int count = k; // Start with k so first 1 is always valid
    for (int num : nums) {
        if (num == 1) {
            if (count < k) return false;
            count = 0;
        } else {
            count++;
        }
    }
    return true;
}
```

3. **Two Pointer Approach**:
```java
public boolean kLengthApart(int[] nums, int k) {
    int left = 0;
    while (left < nums.length && nums[left] != 1) {
        left++;
    }
    
    for (int right = left + 1; right < nums.length; right++) {
        if (nums[right] == 1) {
            if (right - left - 1 < k) return false;
            left = right;
        }
    }
    return true;
}
```

## Mathematical Insight
The solution leverages:
- **Relative Positioning**: Only care about distances between consecutive 1's
- **Transitive Property**: If all consecutive pairs satisfy the condition, all pairs satisfy it
- **Early Pruning**: Can stop checking once any violation is found
- **Boundary Handling**: Virtual initial position simplifies edge case

**Key Formula:**
For consecutive 1's at positions `i` and `j` (with `i < j`):
```
Required: j - i - 1 ≥ k
Equivalent: j - i > k
```

The algorithm efficiently verifies the condition by maintaining a running record of the last 1's position and checking each new 1 against this reference point. The clever initialization with `-k-1` ensures that the first real 1 always passes the distance check, while subsequent 1's are validated against their immediate predecessor.