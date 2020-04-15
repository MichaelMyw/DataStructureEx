package dynamicprogramme;

/**
 * Author Mayiwei
 * Date 2020/4/15
 * 背包问题
 * 假如有一个4磅的背包，现在有商品
 * 重量   价格
 * 吉他   1磅   1500
 * 音响   4磅   3000
 * 电脑   3磅   2000
 * <p>
 * 现在规定每个商品只能放一样 如何存放 使得背包的价值最大？
 */
public class BagProblem {

    /**
     * 我们定义一个表格，横坐标为背包的重量，背包重量 + 1列
     * 纵坐标为商品，一共是商品个数 + 1 行
     *
     * @param weight 商品的重量
     * @param value  商品的价值
     * @param vol    背包的最大容量
     * @return table
     */
    public static int[][] getValTable(int[] weight, int[] value, int vol) {
        //初始化一个二维数组，行为物品的数量+1，列为背包的容量+1，第一行第一列都是0
        int[][] v = new int[value.length + 1][vol + 1];

        //定义一个二维数组记录放入商品的情况
        int[][] path = new int[value.length + 1][vol + 1];


        //把第一行设置为0，意思是不管背包容量多大，没有商品放入，价值都是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        //把第一列设置为0，意思是背包容量为0，总价值为0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //第一行第一列都是0，不处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                //如果商品的重量比背包重，直接取上一行的结果
                if (weight[i - 1] > j) {//一维数组下标从0开始算，下标-1表示对应的商品
                    v[i][j] = v[i - 1][j];
                }
                //如果商品的重量比背包轻，算一下背包的剩余空间的价值加上这个商品的价值 和 上一行的结果取一个最大值
                else {//j-weight[i - 1] 表示剩余空间
                    // v[i][j] = Math.max(v[i - 1][j],value[i - 1] + v[i - 1][j - weight[i - 1]]);//一维数组下标从0开始算，下标-1表示对应的商品
                    if (v[i - 1][j] < value[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        //当要放入新东西的时候记录这个策略
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        System.out.println("path表格为：");
        printTable(path);
        System.out.println("==========");
        //求出path行和列的最大下标
        int num = path.length - 1;//商品的数量
        int curVol = path[0].length - 1; //背包的最大容量
        System.out.println("背包策略为：");
        //逆向遍历，从path的最大开始找
        while (num > 0 && curVol > 0) {
            if (path[num][curVol] == 1) {
                System.out.println("第" + num + "个商品放入背包");
                curVol -= weight[num - 1];//背包的容量 - 商品的重量
            }
            num--;//再找下一个商品
        }
        System.out.println("===========");
        return v;
    }

    /**
     * 打印二维数组
     *
     * @param table 表格
     */
    public static void printTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[] weight = new int[]{1, 4, 3};
        int[] value = new int[]{1500, 3000, 2000};
        int vol = 4;

        int[][] table = getValTable(weight, value, vol);
        printTable(table);
    }
}
