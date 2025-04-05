# 🔄 Find Minimum in Rotated Sorted Array - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/)

Given a sorted rotated array `nums` of unique elements, return the minimum element of the array.

**Constraints:**
- 1 ≤ nums.length ≤ 5000
- -5000 ≤ nums[i] ≤ 5000
- All elements are unique
- The array is sorted and rotated between 1 and n times

**Example 1:**
```text
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The minimum element is 1.
```

**Example 2:**
```text
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The minimum element is 0.
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Pivot Identification**: The minimum element is the only point where the next element is smaller
2. **Sorted Half Check**: The unsorted half always contains the minimum
3. **Boundary Adjustment**: Narrow down search space to the unsorted half

Key Observations:
- In a rotated sorted array, one half is always sorted
- The minimum lies in the unsorted half
- Standard binary search with comparison to the rightmost element

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize `low = 0`, `high = nums.length - 1`
2. Handle edge cases for arrays of length 1 and 2

### **2️⃣ Modified Binary Search**
1. Calculate `mid`
2. Compare `nums[mid]` with `nums[high]`:
   - If `nums[mid] ≤ nums[high]`: minimum is in left half (including mid)
   - Else: minimum is in right half (excluding mid)
3. Terminate when `low == high`

### **3️⃣ Result Extraction**
- The minimum is at `nums[low]` when search converges

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int findMin(int[] nums) {
        // Edge cases
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);
        
        int low = 0;
        int high = nums.length - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] <= nums[high]) {
                // Minimum is in left half (including mid)
                high = mid;
            } else {
                // Minimum is in right half (excluding mid)
                low = mid + 1;
            }
        }
        
        return nums[low];
    }
}
```

Key Components:
1. **Edge Handling**: Directly returns for small arrays
2. **Binary Search**: Efficient O(log n) search
3. **Mid Comparison**: Decides which half contains the minimum
4. **Termination**: When `low == high`, we've found the minimum

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Binary search halves problem space |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [4,5,6,7,0,1,2]`

**Execution Steps:**
1. Initial: low=0, high=6
2. mid=3 (7) > nums[6] → low=4
3. mid=5 (1) ≤ nums[6] → high=5
4. mid=4 (0) ≤ nums[5] → high=4
5. Terminate (low=4 == high=4)
6. **Returns:** 0

---

## 💡 Key Features
- **Rotation Handling**: Works regardless of rotation count
- **Optimal Efficiency**: O(log n) time complexity
- **No Preprocessing**: Works directly on rotated array
- **Clear Logic**: Simple comparison with rightmost element

---

## 🚀 When to Use
- **Finding rotation pivot points**
- **Problems requiring minimum/maximum in rotated arrays**
- **When O(log n) search is needed**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for rotated arrays |
| Linear Search | O(n) | O(1) | Simple but inefficient |
| Find Pivot First | O(log n) | O(1) | Similar performance |

## ⚠️ Edge Cases
- **Single-element array**: Returns the element
- **Two-element array**: Returns the minimum
- **Already sorted array**: Returns first element
- **Full rotation**: Returns first element

## 🛠 Variations
1. **Find Maximum in Rotated Array**:
```java
// Similar logic but track maximum
```

2. **Find Rotation Count**:
```java
// Minimum's index equals rotation count
```

3. **Search in Rotated Array**:
```java
// First find minimum then binary search
```

This solution demonstrates an efficient binary search adaptation to find the minimum in rotated sorted arrays, providing optimal O(log n) performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.