https://leetcode.cn/problems/house-robber-iii/description/


以下是“打家劫舍 III”问题的两种核心解法：**递归 + 双 Map 缓存** 和 **动态规划（后序遍历）**，包含完整思路、代码实现和详细解释，方便对比学习：


### 一、解法一：递归 + 双 Map 缓存
#### 核心思路
利用两个 `Map` 分别缓存“偷当前节点”和“不偷当前节点”的最大金额，避免重复递归计算，逻辑直观易理解：
- `stealCache`：key = 节点，value = 偷该节点时，以其为根的子树能偷的最大金额。
- `notStealCache`：key = 节点，value = 不偷该节点时，以其为根的子树能偷的最大金额。

递归决策逻辑：
1. 若偷当前节点 → 左右子节点必须不偷，金额 = 当前节点值 + 左子节点不偷的金额 + 右子节点不偷的金额。
2. 若不偷当前节点 → 左右子节点可偷或不偷（取最大值），金额 = max(左子偷/不偷) + max(右子偷/不偷)。

#### 代码实现
```java
class Solution {
    // 缓存“偷当前节点”的最大金额
    Map<TreeNode, Integer> stealCache = new HashMap<>();
    // 缓存“不偷当前节点”的最大金额
    Map<TreeNode, Integer> notStealCache = new HashMap<>();

    public int rob(TreeNode root) {
        return Math.max(dfs(root, true), dfs(root, false));
    }

    private int dfs(TreeNode node, boolean steal) {
        if (node == null) {
            return 0;
        }
        // 优先从缓存中取结果
        if (steal) {
            if (stealCache.containsKey(node)) {
                return stealCache.get(node);
            }
        } else {
            if (notStealCache.containsKey(node)) {
                return notStealCache.get(node);
            }
        }
        int sum;
        if (steal) {
            // 偷当前节点 → 左右子节点都不能偷
            sum = node.val + dfs(node.left, false) + dfs(node.right, false);
            stealCache.put(node, sum); // 存入“偷”的缓存
        } else {
            // 不偷当前节点 → 左右子节点可偷或不偷（取最大值）
            sum = Math.max(dfs(node.left, true), dfs(node.left, false)) + Math.max(dfs(node.right, true), dfs(node.right, false));
            notStealCache.put(node, sum); // 存入“不偷”的缓存
        }
        return sum;
    }
}
```


### 二、解法二：动态规划（后序遍历）
#### 核心思路
通过后序遍历二叉树（先处理左右子树，再处理当前节点），为每个节点维护一个长度为 2 的 `dp` 数组，直接存储两种状态的最大金额，无需额外缓存：
- `dp[0]`：不偷当前节点时，以其为根的子树能偷的最大金额。
- `dp[1]`：偷当前节点时，以其为根的子树能偷的最大金额。

状态转移方程：
1. 不偷当前节点 → `dp[0] = max(左子dp[0], 左子dp[1]) + max(右子dp[0], 右子dp[1])`（左右子树自由选择）。
2. 偷当前节点 → `dp[1] = 当前节点值 + 左子dp[0] + 右子dp[0]`（左右子树必须不偷）。

#### 代码实现
```java
class Solution {
    public int rob(TreeNode root) {
        int[] dp = postOrderDp(root);
        // 根节点“偷”和“不偷”的最大值即为结果
        return Math.max(dp[0], dp[1]);
    }

    // 后序遍历：返回节点 node 的 dp 数组 [不偷, 偷]
    private int[] postOrderDp(TreeNode node) {
        // 终止条件：空节点的 dp 数组为 [0, 0]
        if (node == null) {
            return new int[]{0, 0};
        }

        // 递归处理左右子树（后序遍历核心：先子后父）
        int[] leftDP = postOrderDp(node.left);
        int[] rightDP = postOrderDp(node.right);

        // 计算当前节点的 dp 数组
        int[] dp = new int[2];
        // dp[0]：不偷当前节点
        dp[0] = Math.max(leftDP[0], leftDP[1]) + Math.max(rightDP[0], rightDP[1]);
        // dp[1]：偷当前节点
        dp[1] = node.val + leftDP[0] + rightDP[0];

        return dp;
    }
}
```
