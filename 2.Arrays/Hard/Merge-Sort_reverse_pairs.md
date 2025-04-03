# 🔄 Count Reverse Pairs - Modified Merge Sort Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/reverse-pairs/description/)

Given an integer array `nums`, return the number of **reverse pairs** where a reverse pair is a pair `(i, j)` where:
- `0 ≤ i < j < nums.length`
- `nums[i] > 2 * nums[j]`

**Constraints:**
- 1 ≤ nums.length ≤ 5 × 10⁴
- -2³¹ ≤ nums[i] ≤ 2³¹ - 1

**Example 1:**
```text
Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are (1,4) and (3,4)
```

**Example 2:**
```text
Input: nums = [2,4,3,5,1]
Output: 3
Explanation: The reverse pairs are (1,4), (2,4), and (3,4)
```

---

## 🧠 Intuition
The solution uses a modified merge sort with these key insights:
1. **Divide and Conquer**: Splits the array into halves
2. **Count During Merge**: Counts reverse pairs while merging sorted halves
3. **Two-Pointer Technique**: Efficiently counts pairs where `nums[i] > 2*nums[j]`

Key Observations:
- Reverse pairs can be counted during the merge process
- Sorting helps efficiently count pairs using two pointers
- The count from left, right, and cross pairs are summed

---

## ⚙️ Approach
### **1️⃣ Modified Merge Sort**
1. **Divide Phase**:
   - Recursively split the array into halves
   - Count reverse pairs in left and right halves

2. **Count Phase**:
   - Use two pointers to count cross reverse pairs
   - For each element in right half, count how many in left half satisfy `nums[i] > 2*nums[j]`

3. **Merge Phase**:
   - Merge the two sorted halves
   - Maintain sorted order for higher levels

### **2️⃣ Counting Logic**
- Before merging, scan with two pointers:
  - `i` in left half, `j` in right half
  - When `nums[i] > 2*nums[j]`, all remaining left elements also satisfy
  - Increment count by `mid - i + 1` and move `j`
  - Else move `i`

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int reversePairs(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        return dividePairs(nums, 0, nums.length - 1);
    }

    private int dividePairs(int[] nums, int start, int end) {
        if (start >= end) {
            return 0;
        }

        int mid = start + (end - start) / 2;
        int count = dividePairs(nums, start, mid) + dividePairs(nums, mid + 1, end);

        // Count reverse pairs
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if ((long)nums[i] > 2L * (long)nums[j]) {
                count += mid - i + 1;
                j++;
            } else {
                i++;
            }
        }

        // Merge the sorted halves
        int[] sorted = new int[end - start + 1];
        i = start;
        j = mid + 1;
        int k = 0;
        
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                sorted[k++] = nums[i++];
            } else {
                sorted[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            sorted[k++] = nums[i++];
        }
        while (j <= end) {
            sorted[k++] = nums[j++];
        }

        System.arraycopy(sorted, 0, nums, start, sorted.length);
        return count;
    }
}
```

Key Components:
1. **Recursive Division**: Splits array into halves
2. **Reverse Pair Counting**: Two-pointer technique
3. **Merge Operation**: Combines sorted halves
4. **Type Safety**: Uses `long` to prevent overflow

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n log n) | Same as merge sort |
| **Space**       | O(n)       | Temporary array for merging |

---

## 📊 Example Walkthrough

**Input:** `nums = [1,3,2,3,1]`

**Execution Steps:**
1. **Divide**:
   - Split into [1,3,2] and [3,1]
   - Further split into [1,3], [2] and [3], [1]

2. **Count and Merge**:
   - Merge [1,3] and [2]: count reverse pairs (none)
   - Merge [3] and [1]: count 1 reverse pair (3 > 2*1)
   - Merge [1,2,3] and [1,3]: count 1 reverse pair (3 > 2*1)

3. **Total Reverse Pairs**: 2

---

## 💡 Key Features
- **Efficient Counting**: O(n log n) time complexity
- **In-Place Sorting**: Modifies original array
- **Accurate Counting**: Handles all reverse pair cases
- **Overflow Protection**: Uses long for comparisons

---

## 🚀 When to Use
- **Counting special pairs in arrays**
- **When O(n²) is too slow**
- **Problems requiring sorted subarrays**
- **As building block** for more complex algorithms

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Merge Sort (this) | O(n log n) | O(n) | Optimal for large n |
| Brute Force | O(n²) | O(1) | Simple but impractical |
| Binary Indexed Tree | O(n log n) | O(n) | Alternative approach |

## ⚠️ Edge Cases
- **All equal numbers**: Returns 0
- **Descending order**: Maximum reverse pairs
- **Large numbers**: Handles with long type
- **Minimum input size (1)**: Returns 0

## 🛠 Variations
1. **Count Inversions**:
```java
// Count where nums[i] > nums[j] without 2* factor
```

2. **Triplet Reverse Pairs**:
```java
// Extend to three indices i<j<k
```

3. **Sliding Window Count**:
```java
// Count pairs within window constraints
```

This solution demonstrates an efficient divide-and-conquer approach to count reverse pairs, leveraging merge sort's natural properties while adding specialized counting during the merge phase. The algorithm handles all edge cases and operates within optimal time complexity.