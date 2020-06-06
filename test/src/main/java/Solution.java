import java.util.*;

public class Codec {

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        String str = root.val + ",";
        str += serialize(root.left);
        str += serialize(root.right);
        return str;
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] dataStr = data.split(",");
        int[] pre = new int[dataStr.length];
        int[] in = new int[dataStr.length];
        for (int i = 0; i < dataStr.length; i++) {
            pre[i] = Integer.valueOf(dataStr[i]);
            in[i] = Integer.valueOf(dataStr[i]);
        }

        Arrays.sort(in);
        return buildTree(pre, in);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }

        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>(preorder.length);
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
    }

    private TreeNode buildTree(int[] p, int pi, int pj, int[] n, int ni, int nj, Map<Integer, Integer> indexMap) {
        if (pi > pj) {
            return null;
        }

        TreeNode head = new TreeNode(p[pi]);
        int index = indexMap.get(p[pi]);
        head.left = buildTree(p, pi + 1, pi + index - ni, n, ni, index - 1, indexMap);  // index - ni表示左子树长度，pi + index - ni表示前序中左子树最大下标
        head.right = buildTree(p, pi + index - ni + 1, pj, n, index + 1, nj, indexMap);
        return head;
    }
}