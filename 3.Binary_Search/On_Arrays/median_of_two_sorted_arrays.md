# 🔍 Median of Two Sorted Arrays - Binary Search Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/median-of-two-sorted-arrays/description/)

Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return the median of the two sorted arrays.

**Constraints:**
- nums1.length == m
- nums2.length == n
- 0 <= m <= 1000
- 0 <= n <= 1000
- 1 <= m + n <= 2000
- -10^6 <= nums1[i], nums2[i] <= 10^6

**Example 1:**
```text
Input: nums1 = [1,3], nums2 = [2]
Output: 2.0
Explanation: merged array = [1,2,3] and median is 2.
```

**Example 2:**
```text
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.5
Explanation: merged array = [1,2,3,4] and median is (2 + 3)/2 = 2.5.
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Partitioning**: We can partition both arrays such that:
   - Left half contains (m+n+1)/2 elements
   - All elements on left are ≤ all elements on right
2. **Binary Search**: Perform binary search on the smaller array to find the correct partition
3. **Edge Handling**: Special cases when partition is at array boundaries

Key Observations:
- Median depends on finding the correct partition point
- We only need to find the partition in the smaller array
- The solution works in O(log(min(m,n))) time

---

## ⚙️ Approach
### **1️⃣ Initial Setup**
1. Ensure nums1 is the smaller array (swap if needed)
2. Calculate total length and median position

### **2️⃣ Binary Search Execution**
1. Partition the smaller array (nums1)
2. Calculate corresponding partition in nums2
3. Check partition validity:
   - If nums1's left > nums2's right → move partition left
   - If nums2's left > nums1's right → move partition right
4. When valid partition found:
   - Calculate max of left halves and min of right halves
   - Return median based on total length parity

### **3️⃣ Edge Cases Handling**
- When partition is at start/end of either array
- When one array is completely in left/right half

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public double findMedianSortedArrays(int[] a, int[] b) {
        // Ensure a is the smaller array
        if(a.length > b.length) {
            return findMedianSortedArrays(b, a);
        }
        
        int start = 0;
        int end = a.length;
        int total = a.length + b.length;
        int medianPos = (total + 1) / 2;
        
        while(start <= end) {
            int partitionA = start + (end - start) / 2;
            int partitionB = medianPos - partitionA;
            
            // Handle edge cases for left and right values
            int leftA = (partitionA == 0) ? Integer.MIN_VALUE : a[partitionA - 1];
            int rightA = (partitionA == a.length) ? Integer.MAX_VALUE : a[partitionA];
            
            int leftB = (partitionB == 0) ? Integer.MIN_VALUE : b[partitionB - 1];
            int rightB = (partitionB == b.length) ? Integer.MAX_VALUE : b[partitionB];
            
            if(leftA <= rightB && leftB <= rightA) {
                // Correct partition found
                if(total % 2 == 1) {
                    return Math.max(leftA, leftB);
                } else {
                    return (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2.0;
                }
            } 
            else if(leftA > rightB) {
                end = partitionA - 1;
            } 
            else {
                start = partitionA + 1;
            }
        }
        
        return -1; // Should never reach here for valid inputs
    }
}
```

Key Improvements:
1. **Cleaner Edge Handling**: Uses MIN/MAX_VALUE for boundary cases
2. **Clear Variable Naming**: Better reflects partition logic
3. **Simpler Logic Flow**: More straightforward condition checks
4. **Consistent Formatting**: Improved code readability

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log(min(m,n))) | Binary search on smaller array |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums1 = [1,3], nums2 = [2]`

**Execution Steps:**
1. nums1 is smaller (length 2), no swap needed
2. total = 3, medianPos = 2
3. Initial partitionA range: 0 to 2
4. partitionA=1 → partitionB=1
   - leftA=1, rightA=3
   - leftB=2, rightB=∞
   - Valid partition (1 ≤ ∞ and 2 ≤ 3)
5. Total length odd → return max(1,2) = 2.0

**Result:** 2.0

---

## 💡 Key Features
- **Optimal Partitioning**: Finds median without merging arrays
- **Efficient Search**: O(log(min(m,n))) time complexity
- **Edge Handling**: Properly manages array boundaries
- **Space Efficiency**: Uses constant extra space

---

## 🚀 When to Use
- Finding median/percentiles in sorted datasets
- Merged array statistics without actual merging
- Problems requiring O(log n) search in sorted data

## ⚠️ Edge Cases
- **Empty arrays**: Handled by boundary checks
- **All elements in one array**: Proper partition still found
- **Duplicate values**: Works with standard comparison
- **Negative numbers**: Handled by normal comparison

## 🛠 Variations
1. **Find Kth Element**:
```java
// Generalize to find any kth smallest element
```

2. **Multiple Arrays**:
```java
// Extend to handle more than two sorted arrays
```

3. **Weighted Median**:
```java
// Calculate median with element weights
```