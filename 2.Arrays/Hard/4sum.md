# 4️⃣ 4Sum - K-Sum Generalization

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/4sum/description/)

Given an array `nums` of `n` integers and an integer `target`, return all unique quadruplets `[nums[a], nums[b], nums[c], nums[d]]` such that:
- `0 <= a, b, c, d < n`
- `a, b, c, d` are distinct
- `nums[a] + nums[b] + nums[c] + nums[d] == target`

**Constraints:**
- 1 <= nums.length <= 200
- -10⁹ <= nums[i] <= 10⁹
- -10⁹ <= target <= 10⁹

**Example 1:**
```text
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
```

**Example 2:**
```text
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
```

---

## 🧠 Intuition
The solution extends the 3Sum approach using:
1. **Recursive K-Sum Reduction**: Breaks down 4Sum into 3Sum, then 2Sum
2. **Sorting**: Enables efficient two-pointer technique
3. **Early Pruning**: Skips impossible branches
4. **Duplicate Avoidance**: Skips identical elements

Key Insights:
- Generalizes to any K-Sum problem
- Sorting enables O(n^(k-1)) time complexity
- Long type prevents integer overflow

---

## ⚙️ Approach
### **1️⃣ Recursive K-Sum Reduction**
1. **Base Case**: When k=2, use two-pointer technique
2. **Recursive Case**:
   - For each element (skipping duplicates)
   - Reduce problem to (k-1)Sum with updated target
   - Backtrack after recursion

### **2️⃣ Optimization Techniques**
- **Sorting**: Enables efficient searching
- **Early Pruning**: Skip when:
  - Current element × k > target (too large)
  - Last element × k < target (too small)
- **Duplicate Skipping**: Avoids identical solutions

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;
        
        Arrays.sort(nums);
        findSum(res, new ArrayList<>(), 4, (long)target, 0, nums);
        return res;
    }
    
    private void findSum(List<List<Integer>> res, List<Integer> current, 
                        int k, long target, int start, int[] nums) {
        if (k != 2) {
            for (int i = start; i < nums.length - k + 1; i++) {
                // Skip duplicates
                if (i > start && nums[i] == nums[i-1]) continue;
                
                // Early pruning
                if ((long)nums[i] * k > target) break;
                if ((long)nums[nums.length-1] * k < target) continue;
                
                current.add(nums[i]);
                findSum(res, current, k-1, target-nums[i], i+1, nums);
                current.remove(current.size()-1);
            }
        } 
        else {
            // Two-pointer technique
            int left = start, right = nums.length - 1;
            while (left < right) {
                long sum = (long)nums[left] + nums[right];
                
                if (sum == target) {
                    current.add(nums[left]);
                    current.add(nums[right]);
                    res.add(new ArrayList<>(current));
                    current.remove(current.size()-1);
                    current.remove(current.size()-1);
                    
                    left++;
                    // Skip duplicates
                    while (left < right && nums[left] == nums[left-1]) left++;
                } 
                else if (sum < target) {
                    left++;
                } 
                else {
                    right--;
                }
            }
        }
    }
}
```

Key Components:
1. **Recursive Framework**: Handles arbitrary K-Sum
2. **Two-Pointer Base Case**: Efficient O(n) 2Sum
3. **Backtracking**: Manages current combination
4. **Type Safety**: Uses long for overflow protection

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n³)      | O(nlogn) sort + O(n³) recursion |
| **Space**       | O(n)       | Recursion stack depth (output excluded) |

---

## 📊 Example Walkthrough

**Input:** `nums = [1,0,-1,0,-2,2], target = 0`

**Execution Steps:**
1. **Sort**: [-2, -1, 0, 0, 1, 2]
2. **First Level** (4Sum → 3Sum):
   - i=0 (-2): target becomes 2
   - Recursive call with k=3
3. **Second Level** (3Sum → 2Sum):
   - i=1 (-1): target becomes 3
   - Two-pointer finds [-1,0,0,1] and [-2,0,0,2]
4. **Result**: [[-2,-1,1,2], [-2,0,0,2], [-1,0,0,1]]

---

## 💡 Key Features
- **General K-Sum Solution**: Works for 2Sum, 3Sum, etc.
- **Efficient Pruning**: Eliminates impossible branches early
- **Duplicate Handling**: Skips identical elements
- **Overflow Protection**: Uses long arithmetic

---

## 🚀 When to Use
- **K-Sum problems** (especially when K > 2)
- **When sorted input can be leveraged**
- **Problems requiring unique combinations**
- **As building block** for complex combination problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Recursive K-Sum (this) | O(n³) | O(n) | Generalizable, optimal |
| Iterative Nested Loops | O(n⁴) | O(1) | Simple but inefficient |
| Hash Set       | O(n³) | O(n²) | Complex duplicate handling |

## ⚠️ Edge Cases
- **Small arrays**: Returns empty if length < 4
- **All identical elements**: Handles single solution case
- **Large numbers**: Uses long to prevent overflow
- **No solution**: Returns empty list

## 🛠 Variations
1. **K-Sum General Solution**:
```java
// Adjust k parameter for any K-Sum
```

2. **Closest 4Sum**:
```java
// Track closest sum instead of exact match
```

3. **Unique Pairs Count**:
```java
// Return count instead of actual quadruplets
```

This solution demonstrates an optimal pattern for solving K-Sum problems, combining recursive reduction with efficient two-pointer technique and careful pruning of the search space.