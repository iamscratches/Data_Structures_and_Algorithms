# 🔄 Find Rotation Count in Sorted Array - Binary Search Approach

## 📜 Problem Statement
**Link:** [GeeksforGeeks Problem](https://www.geeksforgeeks.org/problems/rotation4723/1)

Given a sorted rotated array `nums` of unique elements, return the number of times the array has been rotated (clockwise).

**Constraints:**
- 1 ≤ nums.length ≤ 10⁵
- 1 ≤ nums[i] ≤ 10⁶
- All elements are unique
- The array is sorted and rotated between 1 and n-1 times

**Example 1:**
```text
Input: nums = [5, 1, 2, 3, 4]
Output: 1
Explanation: The array was rotated once clockwise.
```

**Example 2:**
```text
Input: nums = [2, 3, 4, 5, 1]
Output: 4
Explanation: The array was rotated 4 times clockwise.
```

---

## 🧠 Intuition
The solution uses binary search with these key insights:
1. **Pivot Identification**: The rotation count equals the index of the minimum element
2. **Sorted Half Check**: The unsorted half always contains the minimum
3. **Boundary Adjustment**: Narrow down search space to the unsorted half

Key Observations:
- In a rotated sorted array, one half is always sorted
- The minimum element's index gives the rotation count
- Standard binary search with comparison to the rightmost element

---

## ⚙️ Approach
### **1️⃣ Binary Search Setup**
1. Initialize `low = 0`, `high = nums.size() - 1`
2. Handle edge cases for arrays of length 1 and 2

### **2️⃣ Modified Binary Search**
1. Calculate `mid`
2. Compare `nums.get(mid)` with `nums.get(high)`:
   - If `nums.get(mid) ≤ nums.get(high)`: minimum is in left half (including mid)
   - Else: minimum is in right half (excluding mid)
3. Terminate when `low == high`

### **3️⃣ Result Extraction**
- The rotation count is `low` (index of minimum element)

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int findKRotation(List<Integer> nums) {
        // Edge cases
        if (nums.size() == 1) return 0;
        if (nums.size() == 2) return nums.get(0) < nums.get(1) ? 0 : 1;
        
        int low = 0;
        int high = nums.size() - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (nums.get(mid) <= nums.get(high)) {
                // Minimum is in left half (including mid)
                high = mid;
            } else {
                // Minimum is in right half (excluding mid)
                low = mid + 1;
            }
        }
        
        return low;
    }
}
```

Key Components:
1. **Edge Handling**: Directly returns for small arrays
2. **Binary Search**: Efficient O(log n) search
3. **Mid Comparison**: Decides which half contains the minimum
4. **Termination**: When `low == high`, we've found the rotation count

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(log n)   | Binary search halves problem space |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `nums = [5, 1, 2, 3, 4]`

**Execution Steps:**
1. Initial: low=0, high=4
2. mid=2 (2) ≤ nums[4] → high=2
3. mid=1 (1) ≤ nums[2] → high=1
4. mid=0 (5) > nums[1] → low=1
5. Terminate (low=1 == high=1)
6. **Returns:** 1 (rotation count)

---

## 💡 Key Features
- **Rotation Count**: Directly returns the number of rotations
- **Optimal Efficiency**: O(log n) time complexity
- **No Preprocessing**: Works directly on rotated array
- **Clear Logic**: Simple comparison with rightmost element

---

## 🚀 When to Use
- **Finding rotation count in sorted arrays**
- **Problems requiring pivot point identification**
- **When O(log n) search is needed**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Binary Search (this) | O(log n) | O(1) | Optimal for rotated arrays |
| Linear Search | O(n) | O(1) | Simple but inefficient |
| Find Minimum First | O(log n) | O(1) | Similar performance |

## ⚠️ Edge Cases
- **Single-element array**: Returns 0 (no rotation)
- **Two-element array**: Returns 0 or 1 based on order
- **Already sorted array**: Returns 0
- **Full rotation**: Returns 0 (appears sorted)

## 🛠 Variations
1. **Find Minimum in Rotated Array**:
```java
// Returns the minimum value instead of count
```

2. **Counter-clockwise Rotation**:
```java
// Similar logic but different interpretation
```

3. **Search in Rotated Array**:
```java
// First find rotation count then binary search
```

This solution demonstrates an efficient binary search adaptation to find the rotation count in rotated sorted arrays, providing optimal O(log n) performance while handling all edge cases. The implementation maintains O(1) space complexity and works within the problem constraints.