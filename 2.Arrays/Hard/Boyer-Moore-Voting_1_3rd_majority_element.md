# 🗳 Majority Element II - Boyer-Moore Voting Algorithm

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/majority-element-ii/)

Given an integer array of size `n`, find all elements that appear more than `⌊n/3⌋` times.

**Constraints:**
- 1 ≤ nums.length ≤ 5 * 10⁴
- -10⁹ ≤ nums[i] ≤ 10⁹

**Example 1:**
```text
Input: nums = [3,2,3]
Output: [3]
```

**Example 2:**
```text
Input: nums = [1]
Output: [1]
```

**Example 3:**
```text
Input: nums = [1,2]
Output: [1,2]
```

---

## 🧠 Intuition
The solution extends the Boyer-Moore voting algorithm:
1. **At most two candidates**: Since ⌊n/3⌋ frequency implies ≤2 possible results
2. **Two-phase voting**:
   - First phase: Identify potential candidates
   - Second phase: Verify candidate frequencies
3. **Count management**: Maintains two counters with careful candidate replacement

Key Insights:
- Maximum of two elements can satisfy the frequency condition
- The algorithm efficiently narrows down candidates in O(1) space

---

## ⚙️ Approach
### **1️⃣ Candidate Selection Phase**
1. Initialize two candidates and their counts
2. For each number:
   - If matches either candidate: increment count
   - If count zero: claim new candidate
   - Else: decrement both counts

### **2️⃣ Verification Phase**
1. Reset counts
2. Re-count occurrences of potential candidates
3. Add to result if frequency > ⌊n/3⌋

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // Initialize candidates and counters
        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;
        
        // First pass: Find potential candidates
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        
        // Second pass: Verify candidates
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }
        
        return result;
    }
}
```

Key Components:
1. **Candidate Tracking**: Maintains two potential majority elements
2. **Count Management**: Handles candidate replacement strategically
3. **Verification Pass**: Confirms actual frequencies
4. **Result Building**: Only includes validated candidates

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Two linear passes |
| **Space**       | O(1)       | Constant extra space |

---

## 📊 Example Walkthrough

**Input:** `[1,1,1,3,3,2,2,2]`

**First Pass:**
1. Initialize: c1=0, c2=0, cnt1=0, cnt2=0
2. Process 1: c1=1, cnt1=1
3. Process 1: cnt1=2
4. Process 1: cnt1=3
5. Process 3: c2=3, cnt2=1
6. Process 3: cnt2=2
7. Process 2: cnt1=2, cnt2=1
8. Process 2: cnt1=1, cnt2=0
9. Process 2: c2=2, cnt2=1

**Second Pass:**
- Count c1=3 (1 appears 3 times)
- Count c2=3 (2 appears 3 times)
- n/3=2 → Both qualify

**Result:** `[1,2]`

---

## 💡 Key Features
- **Optimal Space**: Uses only constant extra space
- **Mathematically Sound**: Based on voting algorithm theory
- **Efficient Verification**: Two-pass approach ensures accuracy
- **Generalizable**: Can extend to ⌊n/k⌋ cases

---

## 🚀 When to Use
- **Frequency counting problems**
- **When O(1) space is required**
- **Finding dominant elements in data streams**
- **As building block** for more complex voting problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Boyer-Moore (this) | O(n) | O(1) | Optimal space |
| Hash Map | O(n) | O(n) | Simple but more space |
| Sorting | O(nlogn) | O(1) | Modifies input |

## ⚠️ Edge Cases
- **All identical elements**: Returns single element
- **No majority elements**: Returns empty list
- **Exactly ⌊n/3⌋ occurrences**: Doesn't qualify
- **Two majority elements**: Both returned

## 🛠 Variations
1. **Majority Element I (⌊n/2⌋)**:
```java
// Single candidate version
```

2. **Majority Element III (⌊n/k⌋)**:
```java
// Generalize to k-1 candidates
```

3. **Data Stream Majority**:
```java
// Handle infinite streams
```

This solution demonstrates an elegant application of the Boyer-Moore algorithm for efficient majority element detection with optimal space complexity. The two-phase approach ensures accuracy while maintaining O(1) space usage.