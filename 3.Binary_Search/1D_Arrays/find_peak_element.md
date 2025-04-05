# 🔍 Find Peak Element - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/find-peak-element/description/)

A peak element is an element that is strictly greater than its neighbors. Given a 0-indexed integer array `nums`, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

**Constraints:**
- 1 ≤ nums.length ≤ 1000
- -2³¹ ≤ nums[i] ≤ 2³¹ - 1
- nums[i] != nums[i + 1] for all valid i

**Example 1:**
```text
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
```

**Example 2:**
```text
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Peak Guarantee**: There must exist at least one peak in the array due to the boundary conditions (nums[-1] = nums[n] = -∞)
2. **Ascending Trend**: If nums[mid] < nums[mid+1], a peak must exist on the right side
3. **Descending Trend**: If nums[mid] > nums[mid+1], a peak must exist on the left side or mid could be the peak

Key Observations:
- The array is not necessarily sorted, but binary search can still be applied
- We only need to find any one peak, not necessarily the highest one
- The solution works for both increasing and decreasing sequences

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize `start = 0`, `end = nums.length - 1`
2. Handle edge case for single-element array

### **2️⃣ Modified Binary Search**
1. Calculate `mid`
2. Check if `mid` is a peak:
   - Greater than both neighbors (for non-boundary elements)
   - Greater than single neighbor (for boundary elements)
3. Determine search direction:
   - If ascending (nums[mid] < nums[mid+1]), search right
   - Else search left

### **3️⃣ Early Termination**
- Returns immediately when peak is found
- Eventually converges to a peak due to problem constraints

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int findPeakElement(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int start = 0, end = nums.length-1, mid;
        while(start<=end){
            mid = start+(end-start)/2;
            if(mid>0 && mid<nums.length-1 && nums[mid-1]<nums[mid] && nums[mid]>nums[mid+1]){
                return mid;
            }
            else if(mid==0 && nums[mid]>nums[mid+1]){
                return mid;
            }
            else if(mid==nums.length-1 && nums[mid]>nums[mid-1]){
                return mid;
            }
            else if((mid>0 && nums[mid]>nums[mid-1]) || (mid<nums.length-1 && nums[mid]<nums[mid+1])){
                start = mid+1;
            }
            else{
                end = mid-1;
            }
        }
        return -1;
    }
}
```

Key Components:
1. **Edge Handling**: Direct return for single-element array
2. **Peak Detection**: Checks all possible peak conditions (left, right, and middle peaks)
3. **Search Direction**: Uses local gradient to determine search direction
4. **Termination**: Returns when peak is found or search space exhausted

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Binary search halves problem space |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [1,2,3,1]`

**Execution Steps:**
1. Initial: start=0, end=3
2. mid=1 (2) → nums[1] < nums[2] → search right (start=2)
3. mid=2 (3) → peak found (nums[1] < 3 > nums[3]) → return 2

**Result:** 2

---

## 💡 Key Features
- **Gradient Following**: Uses local slope to guide search
- **Optimal Efficiency**: O(log n) time complexity
- **Boundary Handling**: Properly checks array edges
- **Early Termination**: Returns immediately upon finding a peak

---

## 🚀 When to Use
- **Finding local maxima in arrays**
- **When O(n) linear search is too slow**
- **Problems with unimodal or mountain-like patterns**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for large arrays |
| Linear Search | O(n) | O(1) | Simple but inefficient |
| Recursive Divide & Conquer | O(log n) | O(log n) | Similar time but uses stack space |

## ⚠️ Edge Cases
- **Single-element array**: Returns index 0
- **Peak at start/end**: Handled correctly
- **Strictly increasing/decreasing**: Returns last/first element respectively
- **Large input size**: Efficient performance guaranteed

## 🛠 Variations
1. **Finding All Peaks**:
```java
// Modify to collect all peak indices
```

2. **2D Peak Finding**:
```java
// Extend to 2D matrix using similar concepts
```

3. **Plateau Handling**:
```java
// Modified version for arrays with equal adjacent elements
```

This solution demonstrates an efficient binary search adaptation to find peak elements in an array, providing optimal O(log n) performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.