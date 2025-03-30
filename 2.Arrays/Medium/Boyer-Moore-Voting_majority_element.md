# 🚀 Majority Element - Boyer-Moore Voting Algorithm

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/majority-element/description/)

Given an array `nums` of size `n`, return the majority element (appears more than ⌊n/2⌋ times).

**Constraints:**
- `n == nums.length`
- `1 <= n <= 5 * 10⁴`
- `-10⁹ <= nums[i] <= 10⁹`
- Majority element always exists

**Example:**
```text
Input: nums = [3,2,3]
Output: 3
```

---

## 🧠 Intuition
The Boyer-Moore algorithm:
1. **Elects a candidate** element
2. **Counts votes** for/against the candidate
3. **Switches candidates** when count drops to zero
4. **Guarantees** the last remaining candidate is the majority

Key Insights:
- Majority element will survive the voting process
- Other elements can't cancel all its occurrences
- Works in O(1) space with single pass

---

## ⚙️ Approach
### **1️⃣ Voting Process**
- Initialize `candidate` and `count = 0`
- For each element:
  - If `count == 0`, elect current element as new `candidate`
  - If element == `candidate`, increment `count`
  - Else decrement `count`

### **2️⃣ Verification**
- Final `candidate` is guaranteed to be majority
- (Note: Verification step not needed per problem constraints)

---

## 📐 Mathematical Derivation
### **1. Correctness Proof**
- Let `m` be majority element (appears > n/2 times)
- Each non-m element can cancel at most one `m`
- At least one `m` remains uncanceled

### **2. Time Complexity**
- Single pass through array: O(n)
- Constant time operations per element

### **3. Space Complexity**
- Only two variables used: O(1)

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        
        return candidate; // Guaranteed majority per problem
    }
}
```

Key Components:
1. **Candidate Tracking**: Potential majority element
2. **Count Management**: Net votes for current candidate
3. **Election Process**: Resets on zero count
4. **Early Termination**: Not needed but possible with verification

---

## ⏳ Complexity Analysis
### **Time Complexity: O(n)**
- Single iteration through array
- Constant time operations per element

### **Space Complexity: O(1)**
- Only two variables maintained
- No additional data structures

---

## 📊 Example Walkthrough

**Input:** `[3,2,3]`

**Execution Steps:**
1. num=3: count=0 → candidate=3, count=1
2. num=2: ≠3 → count=0
3. num=3: count=0 → candidate=3, count=1

**Output:** `3`

**Input:** `[2,2,1,1,1,2,2]`

**Execution Steps:**
1. num=2: count=0 → candidate=2, count=1
2. num=2: count=2
3. num=1: count=1
4. num=1: count=0
5. num=1: count=0 → candidate=1, count=1
6. num=2: count=0
7. num=2: count=0 → candidate=2, count=1

**Output:** `2` (Actual majority)

---

## 💡 Key Features
- **One-pass algorithm**: Extremely efficient
- **Constant space**: Minimal memory usage
- **No sorting/hashing**: Avoids O(n log n) or O(n) space
- **Mathematically proven**: Guaranteed correctness

---

## 🚀 When to Use
- **Finding dominant elements** in streams
- **Memory-constrained** environments
- **Real-time processing** requirements
- **Problems with majority guarantees**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Requirements |
|------------------|-----------------|------------------|--------------|
| Boyer-Moore (this)| O(n)            | O(1)             | Majority exists |
| Hash Map        | O(n)            | O(n)             | None         |
| Sorting         | O(n log n)      | O(1) or O(n)     | None         |
| Bit Manipulation| O(n log C)      | O(1)             | Numeric only |

## ⚠️ Edge Cases
- **All elements same**: Returns that element
- **Min/Max integer values**: Handled normally
- **Single element array**: Returns that element
- **Alternating elements**: Last candidate wins

## 🛠 Variations
1. **Verification Step** (when majority not guaranteed):
```java
int count = 0;
for (int num : nums) if (num == candidate) count++;
return (count > nums.length/2) ? candidate : -1;
```

2. **Stream Processing**:
- Handles infinite streams
- Maintains current candidate and count

3. **Generalized Version** (for n/k majorities):
- Uses k-1 counters
- More complex candidate management

This algorithm demonstrates how clever counting can solve problems efficiently without additional memory, making it ideal for constrained environments and large datasets.