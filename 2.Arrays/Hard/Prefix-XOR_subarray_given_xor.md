# 🔗 Subarray with Given XOR - Prefix XOR Approach

## 📜 Problem Statement
**Link:** [InterviewBit Problem](https://www.interviewbit.com/problems/subarray-with-given-xor/)

Given an array of integers `A` and an integer `B`, return the number of subarrays with XOR equal to `B`.

**Constraints:**
- 1 ≤ |A| ≤ 10⁵
- 1 ≤ A[i] ≤ 10⁹
- 1 ≤ B ≤ 10⁹

**Example 1:**
```text
Input: A = [4, 2, 2, 6, 4], B = 6
Output: 4
Explanation: Subarrays with XOR 6 are [4,2], [4,2,2,6,4], [2,2,6], [6]
```

**Example 2:**
```text
Input: A = [5, 6, 7, 8, 9], B = 5
Output: 2
Explanation: Subarrays with XOR 5 are [5] and [6,7,8,9]
```

---

## 🧠 Intuition
The optimal solution uses:
1. **Prefix XOR Tracking**: Calculate cumulative XORs
2. **Hash Map Storage**: Store frequency of each prefix XOR
3. **XOR Property Utilization**: If prefixXor[i] ^ B = prefixXor[j], then subarray A[j+1...i] has XOR B

Key Insights:
- Similar to prefix sum approach but using XOR properties
- Y = X ^ B ⇒ X = Y ^ B (crucial for count calculation)
- Need to track frequency of prefix XORs

---

## ⚙️ Approach
### **1️⃣ Prefix XOR with Hash Map**
1. Initialize:
   - Hash map to store prefix XOR frequencies
   - Running prefix XOR variable
   - Result counter

2. For each element:
   - Update prefix XOR
   - If prefix XOR == B: increment count
   - Add count of (prefix XOR ^ B) from map
   - Update prefix XOR frequency in map

### **2️⃣ Mathematical Foundation**
- Subarray XOR from i to j = prefixXor[j] ^ prefixXor[i-1]
- We want prefixXor[j] ^ prefixXor[i] = B
- Which implies prefixXor[i] = prefixXor[j] ^ B

---

## ✅ Code Implementation

### Optimal Solution
```java
public class Solution {
    public int solve(ArrayList<Integer> A, int B) {
        Map<Integer, Integer> prefixXorMap = new HashMap<>();
        int prefixXor = 0;
        int count = 0;
        
        for (int num : A) {
            prefixXor ^= num;
            
            // Case 1: Current prefix XOR equals B
            if (prefixXor == B) {
                count++;
            }
            
            // Case 2: (prefixXor ^ B) exists in map
            count += prefixXorMap.getOrDefault(prefixXor ^ B, 0);
            
            // Update prefix XOR frequency
            prefixXorMap.put(prefixXor, prefixXorMap.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }
}
```

Key Components:
1. **Prefix XOR Calculation**: Tracks cumulative XOR
2. **Hash Map Lookup**: O(1) time complexity for XOR checks
3. **Count Tracking**: Maintains valid subarray count
4. **Frequency Storage**: Tracks all possible starting points

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n)       | Single pass through array |
| **Space**       | O(n)       | Worst-case hash map storage |

---

## 📊 Example Walkthrough

**Input:** `A = [4, 2, 2, 6, 4], B = 6`

**Execution Steps:**
1. Initialize: map = {}, prefixXor = 0, count = 0
2. i=0 (4):
   - prefixXor = 4
   - 4 ≠ 6
   - 4^6=2 not in map
   - map = {4:1}
3. i=1 (2):
   - prefixXor = 6
   - 6 == 6 → count=1
   - 6^6=0 not in map
   - map = {4:1, 6:1}
4. i=2 (2):
   - prefixXor = 4
   - 4 ≠ 6
   - 4^6=2 not in map
   - map = {4:2, 6:1}
5. i=3 (6):
   - prefixXor = 2
   - 2 ≠ 6
   - 2^6=4 → count+=2 (now 3)
   - map = {4:2, 6:1, 2:1}
6. i=4 (4):
   - prefixXor = 6
   - 6 == 6 → count=4
   - 6^6=0 not in map
   - map = {4:2, 6:2, 2:1}
7. Final result: 4

---

## 💡 Key Features
- **Single Pass Solution**: Efficient O(n) time
- **XOR Property Utilization**: Clever mathematical insight
- **Comprehensive Counting**: Handles all cases
- **Generalizable**: Works for any XOR target

---

## 🚀 When to Use
- **Subarray XOR problems**
- **When optimal O(n) solution is needed**
- **Problems requiring frequency counting**
- **As building block** for more complex XOR problems

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Prefix XOR + Hash (this) | O(n) | O(n) | Optimal for arbitrary numbers |
| Brute Force | O(n²) | O(1) | Simple but inefficient |
| Sliding Window | Not applicable | - | Doesn't work for XOR |

## ⚠️ Edge Cases
- **All identical elements**: Special XOR properties
- **Single element equals B**: Returns 1
- **No matching subarrays**: Returns 0
- **Large arrays**: Handles within constraints

## 🛠 Variations
1. **Longest Subarray with XOR K**:
```java
// Track first occurrence instead of count
```

2. **Count Subarrays with XOR ≤ K**:
```java
// More complex prefix tree approach
```

3. **Maximum XOR Subarray**:
```java
// Combine with Trie data structure
```

This solution demonstrates an elegant application of prefix XOR and hash maps for efficient subarray XOR calculation, handling all cases with optimal time complexity. The approach leverages fundamental XOR properties to transform the problem into a frequency counting challenge.