

public class KthLargest {

    class TreeNode {

        TreeNode left, right;
        int val;
        int count;
        TreeNode(int val) {
            this.val = val;
            this.count = 1;
            this.right = this.left = null;
        }


        TreeNode insert(TreeNode root, int val) {

            TreeNode newNode = new TreeNode(val);

            //创建新的树
            if (root == null) {
                return newNode;
            }

            TreeNode node = root;

            while(true) {

                node.count++;

                if (node.val > val) { //节点插入左子树

                    if (node.left == null) { //插入左孩子
                        node.left = newNode;
                        return root;
                    }

                    node = node.left; //准备插入左子树

                } else { //节点插入右子树，在等于的情况下，插入右

                    if (node.right == null) { //插入右孩子
                        node.right = newNode;
                        return root;
                    }

                    node = node.right; //准备插入右子树
                }
            }
        }

        int searchKLar(TreeNode root, int k) {

            //无右子树，返回第1大元素
            if (k == 1 && root.right == null) {
                return root.val;
            }

            int cur;
            if (root.right == null) {
              cur = 1;
            }else {
              cur = root.right.count + 1;
            }

            TreeNode node = root;
            // System.out.println("root count: " + node.count);
            // System.out.println("root right index: " + (node.right.count+1));

            while(true) {
              // System.out.println("cur value: " + node.val);
              // System.out.println("cur index: " + cur);
              // System.out.println("cur count: " + node.count);
              // System.out.println("left node: " + node.left);
              // System.out.println("right node: " + node.right);


                if (cur == k) {
                    return node.val;
                }

                // 目标大于当前节点
                if (cur > k) { //找右子树
                    node = node.right;
                    if (node.right == null && node.left == null) {//右叶子
                      cur--;
                      continue;
                    }

                    if (node.left != null) {
                      cur--;
                      cur -= node.left.count;
                    } else {
                      cur--;
                    }

                } else if (cur < k) { //找左子树
                    node = node.left;
                    if (node.right == null && node.left == null) { //左叶子
                      cur++;
                      continue;
                    }
                    if (node.right != null) {
                      cur++;
                      cur += node.right.count;
                    } else {
                      cur++;
                    }
                }


            }

        }

    }

    TreeNode root = null;

    int k = 1;

    TreeNode invoker = new TreeNode(0);

    public KthLargest(int k, int[] nums) {

        this.k = k;
        if (nums.length == 0) {
          return;
        }

        TreeNode root;
        root = new TreeNode(nums[0]);
        if (nums.length > 1) {

            for (int i = 1; i < nums.length; i++) {
                root = root.insert(root, nums[i]);
            }
        }

        this.root = root;
    }

    public int add(int val) {

        this.root = invoker.insert(root, val);

        int result = root.searchKLar(root, this.k);

        return result;
    }


    public static void main(String[] args) {

      int[] nums = {0};
      var k = new KthLargest(2, nums);
      System.out.println(k.add(-1));
      
      System.out.println(k.add(1));

      System.out.println(k.add(-2));

      System.out.println(k.add(-4));

      System.out.println(k.add(3));


    }
}
