import java.util.LinkedList;
import java.util.Queue;

/* You are given an array of binary trees that represent different cities where a certain corporation
has its branch office and the organization wishes to provide service by constructing a service center.
Building service centers at any node, i.e., a city can give service to its directly connected cities where
it can provide service to its parents, itself, and its immediate children. Returns the smallest number of
service centers required by the corporation to provide service to all connected cities. Note that: the root
node represents the head office and other connected nodes represent the branch office connected to the head
office maintaining some kind of hierarchy.
Input: tree= {0,0, null, 0, null, 0, null, null, 0}
Output: 2
Explanation: construction of two service centers denoted by black markk will be enough to provide service
to all cities.
*/
class TreeNode{
    TreeNode left;
    TreeNode right;
    int data;

    TreeNode(int data){
        this.data=data;
        this.left=this.right=null;
    }
    TreeNode(){

    }

    public TreeNode addToTree(Object[] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode((int) input[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < input.length; i += 2) {
            TreeNode curr = queue.poll();
            if (input[i] != null) {
                curr.left = new TreeNode((int) input[i]);
                queue.offer(curr.left);
            }
            if (i+1 < input.length && input[i+1] != null) {
                curr.right = new TreeNode((int) input[i+1]);
                queue.offer(curr.right);
            }
        }

        return root;
    }


}



class ConstructionServiceCenter{
    int res = 0;
    public int minCameraCover(TreeNode root) {

        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public int dfs(TreeNode root) {

        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }

    public static void main(String[] args) {
        Object[] tree= {0,0, null, 0, null, 0, null, null, 0 , 0 ,null,0};
        TreeNode root = new TreeNode().addToTree(tree);
        int ans = new ConstructionServiceCenter().minCameraCover(root);
        System.out.println(ans);


    }

}