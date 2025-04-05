# 🔄 Search in Rotated Sorted Array - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/search-in-rotated-sorted-array/description/)

Given a sorted array `nums` that has been rotated at an unknown pivot, and a target value `target`, return the index of `target` in `nums` or `-1` if not found.

**Constraints:**
- 1 ≤ nums.length ≤ 5000
- -10⁴ ≤ nums[i], target ≤ 10⁴
- All values in `nums` are unique
- `nums` is guaranteed to be rotated at some pivot

**Example 1:**
```text
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
```

**Example 2:**
```text
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Pivot Handling**: The array has two sorted segments
2. **Segment Identification**: Determine which half is properly sorted
3. **Target Range Check**: Decide which segment could contain the target

Key Observations:
- At least one half of the array is always sorted
- Compare target with the sorted half's endpoints to determine search direction
- Standard binary search with additional rotation checks

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize `start = 0`, `end = nums.length - 1`
2. While `start ≤ end`:
   - Calculate `mid = start + (end - start)/2`
   - Return `mid` if `nums[mid] == target`

### **2️⃣ Left-Sorted Check**
- If `nums[start] ≤ nums[mid]`:
  - Left half is sorted
  - If target is within `[nums[start], nums[mid])`, search left
  - Else search right

### **3️⃣ Right-Sorted Check**
- Else (right half must be sorted):
  - If target is within `(nums[mid], nums[end]]`, search right
  - Else search left

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return mid;
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
        return -1;
    }
}
```

Key Components:
1. **Standard Binary Search**: With rotation handling
2. **Sorted Half Check**: Determines which segment is properly sorted
3. **Target Range Comparison**: Decides search direction
4. **Early Termination**: Returns immediately on finding target

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Binary search halves problem space |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [4,5,6,7,0,1,2], target = 0`

**Execution Steps:**
1. start=0, end=6
2. mid=3 (7) → left sorted, target not in [4,7) → start=4
3. mid=5 (1) → right sorted, target in (1,2] → end=4
4. mid=4 (0) → match found
5. **Returns:** 4

---

## 💡 Key Features
- **Rotation Handling**: Works regardless of pivot position
- **Optimal Efficiency**: O(log n) time complexity
- **Clear Logic**: Well-structured decision making
- **No Preprocessing**: Works directly on rotated array

---

## 🚀 When to Use
- **Searching in rotated sorted arrays**
- **When standard binary search needs adaptation**
- **Problems requiring O(log n) search time**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for rotated arrays |
| Linear Search | O(n) | O(1) | Simple but inefficient |
| Find Pivot First | O(log n) | O(1) | Two-pass approach |

## ⚠️ Edge Cases
- **Target at rotation point**: Handled correctly
- **Single-element array**: Direct comparison
- **Target not present**: Returns -1
- **Fully rotated array**: Works as normal sorted array

## 🛠 Variations
1. **Find Minimum in Rotated Array**:
```java
// Adapt to find rotation pivot
```

2. **Search with Duplicates**:
```java
// Handle non-unique elements
```

3. **Count Target Occurrences**:
```java
// Extend to count all appearances
```

This solution demonstrates an efficient adaptation of binary search for rotated sorted arrays, providing optimal O(log n) performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.