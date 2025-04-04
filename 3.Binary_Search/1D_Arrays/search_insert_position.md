# 🔍 Search Insert Position - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/search-insert-position/description/)

Given a sorted array of distinct integers `nums` and a target value `target`, return the index where `target` would be inserted to maintain the sorted order. If `target` exists in `nums`, return its index.

**Constraints:**
- 1 ≤ nums.length ≤ 10⁴
- -10⁴ ≤ nums[i], target ≤ 10⁴
- `nums` contains distinct values sorted in ascending order

**Example 1:**
```text
Input: nums = [1,3,5,6], target = 5
Output: 2
Explanation: 5 exists at index 2
```

**Example 2:**
```text
Input: nums = [1,3,5,6], target = 2
Output: 1
Explanation: 2 would be inserted at index 1
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Sorted Array Property**: Enables efficient O(log n) search
2. **Insert Position Logic**: The insert position is where `nums[i-1] < target ≤ nums[i]`
3. **Edge Handling**: Special cases when target is outside array bounds

Key Observations:
- Binary search naturally finds either the target or its insertion point
- The loop condition `start < end` helps converge to the correct position
- Final position adjustment handles cases where target isn't found

---

## ⚙️ Approach
### **1️⃣ Edge Case Handling**
1. If `target > nums[last]`: return `nums.length`
2. If `target < nums[0]`: return `0`

### **2️⃣ Binary Search**
1. Initialize `start = 0`, `end = nums.length - 1`
2. While `start < end`:
   - Calculate `mid`
   - If `nums[mid] == target`: return `mid`
   - If `nums[mid] < target`: search right (`start = mid + 1`)
   - Else: search left (`end = mid - 1`)

### **3️⃣ Position Determination**
- After loop: if `nums[start] < target`, return `start + 1`
- Else return `start`

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        // Handle edge cases
        if (target > nums[nums.length - 1]) {
            return nums.length;
        } else if (target < nums[0]) {
            return 0;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2; // Prevents overflow
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        // Final position adjustment
        return nums[start] < target ? start + 1 : start;
    }
}
```

Key Components:
1. **Edge Checks**: Handles targets outside array bounds
2. **Binary Search**: Efficient O(log n) search
3. **Safe Mid Calculation**: Prevents integer overflow
4. **Position Logic**: Determines insert position when target not found

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Halves search space each iteration |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [1,3,5,6], target = 2`

**Execution Steps:**
1. Edge checks passed (2 is within bounds)
2. Initial: start=0, end=3
3. Iteration 1: mid=1 (3) > 2 → end=0
4. Loop ends (start=0, end=0)
5. nums[0]=1 < 2 → return 1

**Result:** 1

---

## 💡 Key Features
- **Optimal Efficiency**: O(log n) time
- **Minimal Space**: No extra data structures
- **Overflow Protection**: Safe mid calculation
- **Clear Edge Handling**: Special cases handled upfront

---

## 🚀 When to Use
- **Searching in sorted arrays**
- **Finding insertion positions**
- **When O(n) search is too slow**
- **As building block** for more complex search problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for sorted arrays |
| Linear Search | O(n) | O(1) | Simple but inefficient |
| TreeSet | O(n log n) | O(n) | Java-specific, more overhead |

## ⚠️ Edge Cases
- **Target at first/last index**: Handled by initial checks
- **Target not found**: Returns correct insert position
- **Empty array**: Constraint says n ≥ 1
- **Duplicate values**: Constraint says distinct values

## 🛠 Variations
1. **Leftmost Insert Position**:
```java
// Find first occurrence if target exists
```

2. **Rightmost Insert Position**:
```java
// Find last occurrence if target exists
```

3. **Range Insert Positions**:
```java
// Find insert positions for multiple targets
```

This solution demonstrates an efficient binary search adaptation to find either the target's index or its proper insert position in a sorted array, providing optimal O(log n) performance while handling all edge cases. The implementation is straightforward and works with the given constraints.