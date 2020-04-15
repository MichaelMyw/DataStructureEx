package hanoi;

/**
 * Author Mayiwei
 * Date 2020/4/15
 */
public class HanoiTower {

    /**
     * 模拟汉诺塔,从A移动到C
     * @param num 层数
     * @param from A
     * @param with B
     * @param to C
     */
    public static void hanoi(int num,char from,char with, char to){
        //如果汉诺塔只有1层,那就直接从A移动到C
        if(num == 1){
            System.out.println("第1层从" + from + "->" + to);
        }else{
            //把除了最后一层汉诺塔的整体移到中间B
            hanoi(num - 1,from,to,with);
            //把最下面那层移动到C
            System.out.println("第" + num + "层从" + from + "->" + to);
            //再把除了最后一层汉诺塔的整体从B移到C
            hanoi(num - 1,with,from,to);
        }
    }

    public static void main(String[] args) {
        hanoi(2,'A','B','C');
    }
}
