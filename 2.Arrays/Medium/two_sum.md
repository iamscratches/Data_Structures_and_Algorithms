# 🚀 Two Sum - Optimal Solution Breakdown

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/two-sum/)

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

**Constraints:**
- 2 ≤ nums.length ≤ 10⁴
- -10⁹ ≤ nums[i] ≤ 10⁹
- -10⁹ ≤ target ≤ 10⁹
- Exactly one valid solution exists

**Example:**
```text
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
```

---

## 🧠 Intuition
The optimal solution uses a hash map to:
1. Store each number's complement (target - nums[i]) as we iterate
2. Check if current number exists in the map (meaning its complement was seen earlier)
3. Return indices immediately when a match is found

Key Insights:
- One-pass hash table provides O(1) lookups
- Trade space for time efficiency
- Early termination when solution is found

---

## ⚙️ Approach
### **1️⃣ Hash Map Utilization**
- Initialize empty hash map to store complements
- Iterate through array once
- For each element:
  - Check if it exists in map (is it someone's complement?)
  - If yes, return current index and stored index
  - If no, store its complement (target - nums[i]) with current index

### **2️⃣ One-Pass Efficiency**
- Solves problem in single iteration
- No need for nested loops
- Immediate return upon finding solution

---

## 📐 Mathematical Derivation
### **1. Time Complexity**
- Single loop through n elements: O(n)
- HashMap operations (put and get): O(1) average case
- **Total: O(n)**

### **2. Space Complexity**
- HashMap stores at most n-1 complements
- **Total: O(n)**

### **3. Correctness Proof**
- For solution indices (i, j) where i < j:
  - When processing nums[j], nums[i] will already be in map as (target - nums[j])
  - Guaranteed to find the pair in single pass

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++) {
            // Check if current number completes any previous number
            if(map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            }
            
            // Store the complement (target - current number) with current index
            map.put(target - nums[i], i);
        }
        
        return null; // As per constraints, this line is theoretically unreachable
    }
}
```

Key Components:
1. **HashMap Initialization**: Stores complements and indices
2. **Single Pass Loop**: Processes each element exactly once
3. **Early Return**: Immediately returns upon finding solution
4. **Complement Storage**: Maps (target - nums[i]) → current index

---

## ⏳ Complexity Analysis
### **Time Complexity: O(n)**
- Single iteration through array
- Constant time HashMap operations

### **Space Complexity: O(n)**
- HashMap stores up to n-1 complements

---

## 📊 Example Walkthrough

**Input:** nums = [3,2,4], target = 6

**Execution Steps:**
1. i=0 (nums[0]=3):
   - Not in map
   - Store (6-3=3) → map = {3:0}

2. i=1 (nums[1]=2):
   - Not in map
   - Store (6-2=4) → map = {3:0, 4:1}

3. i=2 (nums[2]=4):
   - Found in map (key 4 exists)
   - Return [current index=2, stored index=1]

**Output:** [2,1]

---

## 💡 Key Features
- **Optimal Efficiency**: O(n) time with one pass
- **Early Termination**: Returns immediately on solution
- **Clear Logic**: Easy to understand and implement
- **Generalizable**: Pattern applies to similar problems

---

## 🚀 When to Use
- **Unsorted arrays** where sorting would take O(n log n)
- **When index information** needs to be preserved
- **Problems requiring** value-to-index mapping
- **Competitive programming** with tight constraints

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Best Use Case |
|------------------|-----------------|------------------|---------------|
| Hash Map (this)  | O(n)            | O(n)             | General case  |
| Brute Force      | O(n²)           | O(1)             | Small n only  |
| Two-pointer      | O(n log n)      | O(1)             | Sorted arrays |

## ⚠️ Edge Cases
- **Duplicate values**: Handled correctly (stores most recent complement)
- **Negative numbers**: Works seamlessly
- **Zero values**: No special handling needed
- **Large numbers**: Handled within integer limits

## 🛠 Variations
1. **Return Values Instead of Indices**:
```java
return new int[]{nums[i], nums[map.get(nums[i])]};
```

2. **Multiple Solutions**:
```java
List<int[]> results = new ArrayList<>();
// ... add all solutions to results
```

3. **Three Sum Extension**:
- Can be adapted as base for 3Sum problem
- Requires additional loop and careful deduplication

This solution represents the optimal balance between time and space complexity for the Two Sum problem, demonstrating the power of hash-based lookups in algorithm design.