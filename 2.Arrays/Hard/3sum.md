# 🔢 3Sum - Triplet Sum to Zero

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/3sum/description/)

Given an integer array nums, return all unique triplets `[nums[i], nums[j], nums[k]]` such that `i != j != k` and `nums[i] + nums[j] + nums[k] == 0`.

**Constraints:**
- 0 ≤ nums.length ≤ 3000
- -10⁵ ≤ nums[i] ≤ 10⁵

**Example 1:**
```text
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
```

**Example 2:**
```text
Input: nums = []
Output: []
```

---

## 🧠 Intuition
The optimal solution uses:
1. **Sorting**: Enables efficient two-pointer technique
2. **Fixed Pointer**: For the first element in the triplet
3. **Two Pointers**: To find complementary pairs for the remaining sum
4. **Duplicate Skipping**: Ensures unique triplets

Key Insights:
- Sorting helps avoid duplicates naturally
- Fixed pointer reduces problem to two-sum
- Early termination when first element becomes positive

---

## ⚙️ Approach
### **1️⃣ Sorting + Two Pointers**
1. **Sort** the input array
2. **Iterate** with fixed pointer `i`:
   - Skip duplicates of `nums[i]`
   - Initialize two pointers `j` (i+1) and `k` (end)
3. **Two-pointer phase**:
   - Calculate current sum
   - Adjust pointers based on sum comparison with zero
   - Record valid triplets and skip duplicates

### **2️⃣ Optimization Techniques**
- Early termination when `nums[i] > 0`
- Efficient duplicate skipping
- Pointer adjustments based on sum comparison

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i-1]) continue;
            
            // Early termination
            if (nums[i] > 0) break;
            
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                
                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    
                    // Skip duplicates for j and k
                    while (j < k && nums[j] == nums[j-1]) j++;
                    while (j < k && nums[k] == nums[k+1]) k--;
                }
            }
        }
        return result;
    }
}
```

Key Components:
1. **Sorting**: Enables two-pointer approach
2. **Fixed Pointer Loop**: Handles first element
3. **Two Pointers**: Efficiently finds pairs
4. **Duplicate Handling**: Skips identical elements
5. **Early Termination**: When first element becomes positive

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n²)      | O(nlogn) sort + O(n²) two-pointer |
| **Space**       | O(1)       | Constant space (output excluded) |

---

## 📊 Example Walkthrough

**Input:** `nums = [-1,0,1,2,-1,-4]`

**Execution Steps:**
1. **Sort**: [-4, -1, -1, 0, 1, 2]
2. **i=0** (-4):
   - j=1 (-1), k=5 (2) → sum=-3 → j++
   - j=2 (-1), k=5 (2) → sum=-3 → j++
   - j=3 (0), k=5 (2) → sum=-2 → j++
3. **i=1** (-1):
   - j=2 (-1), k=5 (2) → sum=0 → add [-1,-1,2]
   - j=3 (0), k=4 (1) → sum=0 → add [-1,0,1]
4. **i=2** (-1): skipped (duplicate)
5. **i=3** (0): 
   - j=4 (1), k=5 (2) → sum=3 → k--
6. **Result**: [[-1,-1,2],[-1,0,1]]

---

## 💡 Key Features
- **Efficient Duplicate Handling**: Skips identical elements
- **Early Termination**: When first element > 0
- **Optimal Time Complexity**: O(n²) is best for this problem
- **Clear Pointer Logic**: Easy to understand adjustments

---

## 🚀 When to Use
- **Finding triplets with specific sum**
- **Problems requiring unique combinations**
- **As building block** for K-sum problems
- **When sorted input can be leveraged**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Two Pointers (this) | O(n²) | O(1) | Most efficient |
| Hash Set       | O(n²) | O(n) | Simpler but more space |
| Brute Force    | O(n³) | O(1) | Only for very small n |

## ⚠️ Edge Cases
- **Empty array**: Returns empty list
- **All zeros**: Returns single triplet `[0,0,0]`
- **No valid triplets**: Returns empty list
- **All positive/negative numbers**: Early termination

## 🛠 Variations
1. **K-Sum Generalization**:
```java
// Recursive approach for K-sum problems
```

2. **Closest 3Sum**:
```java
// Track closest sum instead of exact match
```

3. **Unique Quadruplets**:
```java
// Extend to 4Sum problem
```

This solution demonstrates an optimal pattern for finding unique triplets with a target sum, combining sorting efficiency with careful pointer management and duplicate handling.