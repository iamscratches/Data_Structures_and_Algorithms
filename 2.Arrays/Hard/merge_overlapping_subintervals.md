# 🔄 Merge Intervals - Efficient Interval Merging

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/merge-intervals/)

Given an array of `intervals` where `intervals[i] = [starti, endi]`, merge all overlapping intervals and return an array of the non-overlapping intervals that cover all the intervals in the input.

**Constraints:**
- 1 ≤ intervals.length ≤ 10⁴
- intervals[i].length == 2
- 0 ≤ starti ≤ endi ≤ 10⁴

**Example 1:**
```text
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
```

**Example 2:**
```text
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
```

---

## 🧠 Intuition
The optimal solution uses:
1. **Sorting**: Arrange intervals by start time
2. **Sequential Processing**: Process intervals in order
3. **Merge Condition**: Check if current interval overlaps with previous
4. **Merge Operation**: Expand end time when overlapping

Key Insights:
- Sorting makes adjacent intervals comparable
- Only need to compare with last merged interval
- Merge happens when current start ≤ previous end

---

## ⚙️ Approach
### **1️⃣ Sort and Merge**
1. **Sort Intervals**: By start time (O(nlogn))
2. **Initialize Result**: With first interval
3. **Iterate Through Sorted Intervals**:
   - If overlaps with last merged interval: merge by updating end time
   - Else: add to result as new interval
4. **Convert Result**: To array format

### **2️⃣ Merge Condition**
- Two intervals [a,b] and [c,d] overlap if c ≤ b
- Merged interval becomes [a, max(b,d)]

---

## ✅ Code Implementation

### Optimal Solution
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort intervals based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        merged.add(current);
        
        for (int[] interval : intervals) {
            int currentEnd = current[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            
            if (currentEnd >= nextStart) {
                // Merge intervals
                current[1] = Math.max(currentEnd, nextEnd);
            } else {
                // Add new interval
                current = interval;
                merged.add(current);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
}
```

Key Components:
1. **Sorting**: Ensures chronological order
2. **Current Interval Tracking**: Maintains active merge window
3. **Merge Condition**: Checks for overlap
4. **Result Building**: Dynamically constructs output

---

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(nlogn)   | Dominated by sorting |
| **Space**       | O(n)       | For storing merged intervals |

---

## 📊 Example Walkthrough

**Input:** `[[1,3],[2,6],[8,10],[15,18]]`

**Execution Steps:**
1. **Sort**: Already sorted (start times 1,2,8,15)
2. **Initialize**: current = [1,3], merged = [[1,3]]
3. **Process [2,6]**:
   - 3 ≥ 2 → merge → current becomes [1,6]
4. **Process [8,10]**:
   - 6 < 8 → add new interval → merged = [[1,6],[8,10]]
5. **Process [15,18]**:
   - 10 < 15 → add new interval → merged = [[1,6],[8,10],[15,18]]
6. **Result**: `[[1,6],[8,10],[15,18]]`

---

## 💡 Key Features
- **Sorting First**: Makes merging straightforward
- **Single Pass**: Efficient O(n) processing after sort
- **In-Place Merging**: Modifies intervals directly
- **Clear Overlap Check**: Simple mathematical condition

---

## 🚀 When to Use
- **Interval merging problems**
- **Calendar scheduling conflicts**
- **Time range optimizations**
- **Any problem requiring consolidation of overlapping ranges**

## 🔄 Comparison with Other Approaches
| Approach         | Time Complexity | Space Complexity | Advantages |
|-----------------|-----------------|------------------|------------|
| Sort + Merge (this) | O(nlogn) | O(n) | Most efficient |
| Brute Force | O(n²) | O(1) | No sorting needed but slow |
| Graph-based | O(n²) | O(n²) | Theoretical but impractical |

## ⚠️ Edge Cases
- **Single interval**: Returns itself
- **No overlaps**: Returns original intervals
- **Complete overlap**: e.g., [[1,4],[2,3]] → [[1,4]]
- **Back-to-back intervals**: e.g., [[1,4],[4,5]] → [[1,5]]

## 🛠 Variations
1. **Insert New Interval**:
```java
// Add new interval to sorted list and merge
```

2. **Find Meeting Rooms**:
```java
// Count maximum overlapping intervals
```

3. **Interval Intersection**:
```java
// Find common intervals between two lists
```

This solution demonstrates an optimal pattern for interval merging, combining efficient sorting with a single-pass merge algorithm. The approach is widely applicable to various interval-related problems.