# ⚗️ Find the Minimum Amount of Time to Brew Potions - Dynamic Programming Approach

## 📜 Problem Statement
**Link:** [LeetCode Problem](https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions/description/?envType=daily-question&envId=2025-10-09)

Given:
- `skill[i]`: skill level of the i-th wizard (1-based)
- `mana[j]`: mana cost of the j-th potion (1-based)
- Multiple wizards can work on different potions simultaneously
- A wizard can only work on one potion at a time
- Potions must be brewed in order (potion j must be completed before starting potion j+1)

Find:
- The minimum total time to brew all potions

**Brewing Rules:**
- Wizard `i` takes `skill[i] * mana[j]` time to brew potion `j`
- Wizards can work on different potions simultaneously
- Potions must be completed in order
- Each wizard can only work on one potion at a time

**Constraints:**
- `n == skill.length`
- `m == mana.length`
- `1 <= n, m <= 500`
- `1 <= skill[i], mana[j] <= 10^5`

**Example:**
```text
Input: skill = [3,1,2], mana = [2,4,1,3]
Output: 22

Explanation:
One optimal schedule:
- Wizard 1 (skill 3): Potions [1,4] → 3×2 + 3×3 = 6 + 9 = 15
- Wizard 2 (skill 1): Potions [2] → 1×4 = 4  
- Wizard 3 (skill 2): Potions [3] → 2×1 = 2
Total time = max(15, 4, 2) = 15? Wait, actually we need to consider the sequential nature.

Actually, the total time is the sum of completion times considering dependencies.
```

## 🧠 Intuition
The solution uses:
1. **Dynamic Programming**: Track minimum completion time for each prefix of potions
2. **Parallel Scheduling**: Assign potions to wizards optimally
3. **Prefix Sums**: Efficiently calculate cumulative brewing times
4. **Optimal Assignment**: Balance workload among wizards

Key Insights:
- The problem is about optimally scheduling potions to wizards
- Faster wizards (higher skill) should handle more expensive potions (higher mana)
- Potions must be brewed in order, creating dependencies
- We need to minimize the makespan (total completion time)

## ⚙️ Approach
1. **DP State**: `done[i]` represents minimum time to complete first `i` potions using first `i` wizards

2. **Forward Pass**:
   - For each potion `j`, calculate completion times when assigned to each wizard `i`
   - `done[i+1] = max(done[i+1], done[i]) + mana[j] * skill[i]`
   - This represents assigning potion `j` to wizard `i`

3. **Backward Pass**:
   - Optimize the assignment by considering if a faster wizard could finish earlier
   - `done[i] = done[i+1] - mana[j] * skill[i]`
   - This adjusts the schedule to balance workload

## ✅ Optimized Solution
```java
class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;  // Number of wizards
        int m = mana.length;   // Number of potions
        long[] done = new long[n + 1];  // DP array: done[i] = min time for i potions
        
        // Process each potion in order
        for (int j = 0; j < m; ++j) {
            // Forward pass: assign current potion to each wizard
            for (int i = 0; i < n; ++i) {
                // Current wizard takes max(previous completion, current wizard's availability) + brewing time
                done[i + 1] = Math.max(done[i + 1], done[i]) + (long) mana[j] * skill[i];
            }
            
            // Backward pass: optimize the assignment
            for (int i = n - 1; i > 0; --i) {
                // Check if we can reduce completion time by reassigning
                done[i] = Math.min(done[i], done[i + 1] - (long) mana[j] * skill[i]);
            }
        }
        
        return done[n];  // Minimum time to complete all potions
    }
}
```

## ⏳ Complexity Analysis
| Metric          | Complexity | Description |
|-----------------|------------|-------------|
| **Time**        | O(n × m)   | Process each potion with each wizard |
| **Space**       | O(n)       | DP array of size n+1 |

## 📊 Example Walkthrough
**Input:** skill = [3,1,2], mana = [2,4,1,3]

**Initial:** done = [0,0,0,0]

**Potion 1 (mana=2):**
- Forward pass:
  - Wizard 1: done[1] = max(0,0) + 3×2 = 6
  - Wizard 2: done[2] = max(0,6) + 1×2 = 8  
  - Wizard 3: done[3] = max(0,8) + 2×2 = 12
- Backward pass: Adjusts assignments

**Potion 2 (mana=4):**
- Forward pass builds on previous completion times
- Continues for all potions...

**Final:** done[3] = 22 (minimum total time)

## 💡 Key Features
- **Dynamic Programming**: Efficiently computes optimal schedule
- **Two-Pass Optimization**: Forward assignment + backward refinement
- **Workload Balancing**: Distributes potions optimally among wizards
- **Sequential Constraints**: Respects potion brewing order

## 🚀 When to Use
- Task scheduling problems with dependencies
- Parallel processing optimization
- Load balancing among multiple workers
- Problems with sequential constraints

## ⚠️ Edge Cases
- **Single wizard**: All potions assigned to one wizard
- **Single potion**: Simple multiplication
- **Equal skills/mana**: Uniform distribution
- **Large values**: Handles 500×500 constraints efficiently

## 🛠 Variations
1. **Greedy Assignment**:
```java
// Assign most expensive potions to fastest wizards
```

2. **Binary Search**:
```java
// Binary search on total time, check feasibility
```

3. **Priority Queue**:
```java
// Use min-heap to track wizard availability
```

## Mathematical Insight
The solution leverages:
- **Optimal Substructure**: Solution for k potions builds on k-1 solution
- **Work Conservation**: Total work = Σ(skill[i] × assigned_mana[i])
- **Load Balancing**: Minimize maximum wizard completion time
- **Sequential Dependencies**: Potion order creates constraints

## Alternative Implementation (Greedy Approach)
```java
class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;
        
        // Sort wizards by skill (descending) and potions by mana (descending)
        int[] sortedSkill = skill.clone();
        Arrays.sort(sortedSkill);
        int[] sortedMana = mana.clone();
        Arrays.sort(sortedMana);
        
        // Optimal assignment: fastest wizard gets most expensive potion
        long maxTime = 0;
        long[] completionTime = new long[n];
        
        for (int j = 0; j < m; j++) {
            // Assign current potion to wizard with minimum current completion time
            int bestWizard = 0;
            for (int i = 1; i < n; i++) {
                if (completionTime[i] < completionTime[bestWizard]) {
                    bestWizard = i;
                }
            }
            completionTime[bestWizard] += (long) sortedMana[m - 1 - j] * sortedSkill[n - 1 - bestWizard];
            maxTime = Math.max(maxTime, completionTime[bestWizard]);
        }
        
        return maxTime;
    }
}
```

## Performance Comparison
| Approach | Time Complexity | Best For |
|----------|----------------|----------|
| **DP Two-Pass** | O(n × m) | Optimal solution |
| **Greedy** | O(n × m) | Good approximation |
| **Binary Search** | O(n log m × log(maxTime)) | When range is known |

The DP approach provides the optimal solution by carefully considering all possible assignments and respecting the sequential constraints of potion brewing.