# 🚀 Next Permutation - Lexicographical Order Algorithm

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/next-permutation/description/)

Given an array of integers `nums`, rearrange its elements to form the lexicographically next greater permutation. If no such permutation exists, rearrange to the lowest possible order (sorted ascending).

**Constraints:**
- 1 ≤ nums.length ≤ 100
- 0 ≤ nums[i] ≤ 100

**Example 1:**
```text
Input: [1,2,3]
Output: [1,3,2]
```

**Example 2:**
```text
Input: [3,2,1]
Output: [1,2,3] (no greater permutation exists)
```

---

## 🧠 Intuition
The algorithm follows these key insights:
1. **Find the pivot**: The first decreasing element from the end
2. **Find the successor**: Smallest element > pivot in the suffix
3. **Swap and reverse**: Swap pivot with successor, then reverse the suffix

Key Observations:
- The suffix after the pivot is always non-increasing
- Swapping with the successor guarantees the smallest possible increase
- Reversing the suffix gives the minimal configuration

---

## ⚙️ Approach
### **1️⃣ Three-Step Process**
1. **Check for descending order** (special case)
2. **Find pivot point** where sequence decreases
3. **Find successor** and swap with pivot
4. **Reverse suffix** to get minimal increase

### **2️⃣ Why It Works**
- Maintains lexicographical order
- Guarantees next permutation when possible
- Handles edge cases (single element, descending order)

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public void nextPermutation(int[] nums) {
        // Handle single element case
        if(nums.length == 1) return;
        
        // Check if already in descending order
        boolean isDescending = true;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i-1] < nums[i]) {
                isDescending = false;
                break;
            }
        }
        if(isDescending) {
            reverse(nums, 0, nums.length-1);
            return;
        }
        
        // Find pivot and successor
        int pivot = nums.length - 2;
        while(pivot >= 0 && nums[pivot] >= nums[pivot+1]) {
            pivot--;
        }
        
        if(pivot >= 0) {
            int successor = nums.length - 1;
            while(successor > pivot && nums[successor] <= nums[pivot]) {
                successor--;
            }
            swap(nums, pivot, successor);
        }
        
        // Reverse the suffix
        reverse(nums, pivot+1, nums.length-1);
    }
    
    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums, start++, end--);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

Key Components:
1. **Edge Case Handling**: Single element and descending order
2. **Pivot Detection**: Finds first decreasing element
3. **Successor Search**: Finds optimal swap candidate
4. **Suffix Reversal**: Minimizes the suffix

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | At most two passes through array |
| **Space**       | O(1)       | In-place modifications |

---

## 📊 Example Walkthrough

**Input:** `[1,3,5,4,2]`

**Execution Steps:**
1. Find pivot: 3 (at index 1)
2. Find successor: 4 (first >3 from end)
3. Swap: [1,4,5,3,2]
4. Reverse suffix: [1,4,2,3,5]

**Output:** `[1,4,2,3,5]`

---

## 💡 Key Features
- **Lexicographical Order**: Maintains dictionary ordering
- **Efficiency**: Optimal O(n) time complexity
- **In-Place**: Modifies array without extra space
- **Completeness**: Handles all edge cases

---

## 🚀 When to Use
- **Permutation generation** problems
- **Combinatorial algorithms**
- **Number sequence manipulation**
- **As building block** for more complex problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| This Algorithm  | O(n)            | O(1)             | Optimal    |
| Brute Force     | O(n!)           | O(n)             | None       |
| STL next_permutation | O(n)      | O(1)             | Language specific |

## ⚠️ Edge Cases
- **Single element**: Returns as-is
- **Descending order**: Wraps to ascending
- **Duplicate values**: Handled correctly
- **All identical**: Returns same array

## 🛠 Variations
1. **Previous Permutation**:
```java
// Similar logic but find increasing sequence
```

2. **K-th Permutation**:
```java
// Requires factorial number system approach
```

3. **Permutation Ranking**:
```java
// Calculate position of current permutation
```

This solution demonstrates a classic algorithm for permutation generation, combining careful observation with efficient implementation to solve the problem optimally.