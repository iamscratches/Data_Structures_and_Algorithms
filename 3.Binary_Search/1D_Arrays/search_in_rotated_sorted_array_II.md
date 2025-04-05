# 🔄 Search in Rotated Sorted Array II - Modified Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/)

Given a sorted array `nums` that may contain duplicates and is rotated at an unknown pivot, determine if `target` exists in the array.

**Constraints:**
- 1 ≤ nums.length ≤ 5000
- -10⁴ ≤ nums[i], target ≤ 10⁴

**Example 1:**
```text
Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
```

**Example 2:**
```text
Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
```

---

## 🧠 Intuition
The solution adapts binary search for rotated arrays with duplicates:
1. **Pivot Handling**: Accounts for rotation point
2. **Duplicate Handling**: Skips duplicates when they prevent decision-making
3. **Two-Phase Search**: Determines which sorted half to search

Key Observations:
- When `nums[start] == nums[mid] == nums[end]`, we can't decide which half is sorted
- The array has two segments: one sorted and one potentially rotated
- Target comparison with endpoints determines search direction

---

## ⚙️ Approach
### **1️⃣ Modified Binary Search**
1. **Duplicate Handling**:
   - When endpoints and mid are equal, increment start and decrement end
2. **Left-Sorted Check**:
   - If `nums[start] ≤ nums[mid]`, left half is sorted
   - Check if target lies in this range to determine search direction
3. **Right-Sorted Check**:
   - Otherwise, right half must be sorted
   - Check if target lies in right half's range

### **2️⃣ Early Termination**
- Returns immediately if target is found at mid
- Continues until search space is exhausted

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            // Handle duplicates
            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
                continue;
            }
            
            // Left half is sorted
            if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } 
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }
}
```

Key Components:
1. **Duplicate Handling**: Skips indistinguishable duplicates
2. **Sorted Half Identification**: Checks which segment is properly sorted
3. **Target Range Check**: Determines search direction
4. **Early Return**: Returns immediately on target match

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Worst case with many duplicates |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [2,5,6,0,0,1,2], target = 0`

**Execution Steps:**
1. Initial: start=0, end=6
2. mid=3 (0) → match found
3. **Returns:** true

**Input:** `nums = [1,0,1,1,1], target = 0`

**Execution Steps:**
1. start=0, end=4
2. mid=2 (1) → duplicates at start/mid/end
3. Increment start, decrement end
4. New start=1, end=3
5. mid=2 (1) → left sorted, target in right
6. **Returns:** true (found at nums[1])

---

## 💡 Key Features
- **Duplicate Handling**: Robust against repeated values
- **Rotation Agnostic**: Works regardless of pivot position
- **Efficient Search**: Binary search adaptation
- **Clear Logic**: Well-structured decision making

---

## 🚀 When to Use
- **Rotated array searches with duplicates**
- **When standard binary search fails due to duplicates**
- **Problems requiring O(log n) average case performance**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Modified Binary Search (this) | O(n) worst, O(log n) average | O(1) | Handles duplicates |
| Linear Search | O(n) | O(1) | Simple but less efficient |
| Two-Pass Binary Search | O(log n) | O(1) | Doesn't handle duplicates well |

## ⚠️ Edge Cases
- **All elements same**: Returns true if matches target
- **Target at rotation point**: Handled correctly
- **Single-element array**: Direct comparison
- **Multiple duplicates**: Efficiently skips them

## 🛠 Variations
1. **Find Minimum in Rotated Array II**:
```java
// Adapt to find minimum with duplicates
```

2. **Count Target Occurrences**:
```java
// Extend to count all target appearances
```

3. **Non-Integer Values**:
```java
// Modify for floating-point arrays
```

This solution demonstrates an efficient adaptation of binary search for rotated arrays with duplicates, providing optimal average-case performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.