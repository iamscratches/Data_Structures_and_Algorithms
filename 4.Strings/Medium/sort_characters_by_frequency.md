# 🔍 Sort Characters by Frequency - Counting and Sorting Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/sort-characters-by-frequency/description/)

Given a string `s`, sort its characters in decreasing order based on their frequency. If multiple characters have the same frequency, they can appear in any order.

**Constraints:**
- 1 <= s.length <= 5 * 10^5
- s consists of uppercase and lowercase English letters and digits

**Example 1:**
```text
Input: "tree"
Output: "eert" or "eetr"
Explanation: 'e' appears twice, 't' and 'r' appear once
```

**Example 2:**
```text
Input: "cccaaa"
Output: "cccaaa" or "aaaccc"
Explanation: Both 'c' and 'a' appear three times
```

---

## 🧠 Intuition
The solution uses frequency counting with these key insights:
1. **Frequency Array**: Count occurrences of each ASCII character
2. **Sort by Frequency**: Sort characters based on their counts
3. **String Construction**: Build result by repeating characters according to their frequency

Key Observations:
- ASCII values allow simple array indexing for counting
- Sorting by frequency gives desired ordering
- String building is efficient with StringBuilder

---

## ⚙️ Approach
### **1️⃣ Frequency Counting**
1. Initialize frequency array of size 128 (ASCII range)
2. Count occurrences of each character in input string

### **2️⃣ Sorting Preparation**
1. Create 2D array pairing frequencies with their characters
2. Sort this array in descending order of frequency

### **3️⃣ Result Construction**
1. Iterate through sorted frequency-character pairs
2. Append each character to result according to its count

---

## ✅ Code Implementation

### Optimized Solution
```java
class Solution {
    public String frequencySort(String s) {
        // Count character frequencies
        int[] freq = new int[128];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        
        // Pair characters with their frequencies
        int[][] charFreq = new int[128][2];
        for (int i = 0; i < 128; i++) {
            charFreq[i][0] = freq[i]; // frequency
            charFreq[i][1] = i;       // character
        }
        
        // Sort by frequency descending
        Arrays.sort(charFreq, (a, b) -> b[0] - a[0]);
        
        // Build the result string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 128 && charFreq[i][0] > 0; i++) {
            char c = (char) charFreq[i][1];
            int count = charFreq[i][0];
            sb.append(String.valueOf(c).repeat(count));
        }
        
        return sb.toString();
    }
}
```

Key Improvements:
1. **Clearer Naming**: `charFreq` instead of `freqSorted`
2. **Modern Java**: Uses `String.repeat()` for cleaner string building
3. **Direct Character Casting**: More readable than ASCII value manipulation
4. **Early Termination**: Stops when frequencies become zero

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n + k log k) | n = string length, k = unique characters (≤128) |
| **Space**       | O(n + k) | For frequency arrays and output string |

---

## 📊 Example Walkthrough

**Input:** "tree"

**Execution Steps:**
1. Frequency count:
   - 't':1, 'r':1, 'e':2
2. Sorted pairs:
   - [2,'e'], [1,'t'], [1,'r']
3. String construction:
   - Append 'e' twice
   - Append 't' once
   - Append 'r' once
4. Result: "eert" or "eetr"

---

## 💡 Key Features
- **Efficient Counting**: Single pass through string
- **Optimal Sorting**: Only sorts unique characters
- **Clean Construction**: Uses modern Java features
- **ASCII Optimization**: Leverages fixed character set

---

## 🚀 When to Use
- Frequency-based string transformations
- Problems requiring character counting
- When input has limited character set

## ⚠️ Edge Cases
- **Empty string**: Returns empty string
- **All same character**: Returns input unchanged
- **Mixed cases**: Handles uppercase/lowercase separately
- **Digits**: Included in ASCII range

## 🛠 Variations
1. **Case Insensitive**:
```java
// Convert to lowercase before counting
```

2. **Stable Sort**:
```java
// Maintain original order for same frequencies
```

3. **Custom Order**:
```java
// Sort by character after frequency
```