package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Author Mayiwei
 * Date 2020/4/15
 */
public class Graph {
    //ArrayList存储顶点
    private ArrayList<String> vertexList;
    //存储图对应的邻接矩阵
    private int[][] edges;
    //表示边的数量
    private int numOfEdges;
    //记录某个节点是否被访问
    private boolean[] isVisited;

    //初始化图
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;//边默认为0
        isVisited = new boolean[n];
    }

    /**
     * 插入节点
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);

    }

    /**
     * 插入边
     * @param v1 表示点的下标，即第几个顶点
     * @param v2
     * @param weight 权重(如果有就是1，没有就是0)
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 得到结点的个数
     * @return
     */
    public int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * 得到边的个数
     * @return
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     *返回节点下标对应的值
     * @return
     */
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    /**
     * 返回v1和v2的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    /**
     * 显示图（遍历二维数组）
     */
    public void showGraph(){
        for (int[] link: edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 得到第一个邻接节点的下标
     * @param index
     * @return 如果存在 返回对应的下标，否则返回-1
     */
    public int getFirstNeigh(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if(edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取v1的下一个邻接结点（v2之后的）
     * @param v1
     * @param v2
     * @return 存在返回下标吗，不存在返回-1
     */
    public int getNextNeigh(int v1,int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param isVisited 表示结点是否被访问到
     * @param i 为第一个结点
     */
    private void dfs(boolean[] isVisited,int i){
        //首先访问该结点，输出
        System.out.println(getValueByIndex(i) + "->");
        //将该结点设置为已访问
        isVisited[i] = true;
        //找到该结点i的第一个邻接结点w
        int w = getFirstNeigh(i);
        //如果有
        while (w != -1){
            //判断w结点有没有被访问过
            if(!isVisited[w]){
                //递归dfs
                dfs(isVisited,w);
            }else {
                //如果已经访问过，查找i的下一个结点
                w = getNextNeigh(i,w);
            }
        }
    }

    /**
     *  遍历所有的结点并进行dfs(为了防止有节点走不通进行遍历
     */
    public void dfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 广度优先算法,使用队列
     * @param isVisited 表示结点是否被访问到
     * @param i 为第一个结点
     */
    private void bfs(boolean[] isVisited,int i){
        int u;//表示出队列的头节点u
        int w;//表示结点u的邻接结点w

        //用linkedList模拟队列，入队addLast,出队removeFirst
        LinkedList<Integer> queue = new LinkedList<>();

        System.out.println(getValueByIndex(i) + "=>");
        isVisited[i] = true;

        //将结点i入队
        queue.addLast(i);
        while (!queue.isEmpty()){
            //出队列，得到头结点u
            u = queue.removeFirst();
            //查找结点u的第一个邻接结点w
            w = getFirstNeigh(u);

            while (w != -1){
                //如果w未被访问，输出，标记已被访问然后入队
                if(!isVisited[w]){
                    System.out.println(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //查找u的下一个邻接结点
                w = getNextNeigh(u,w);
            }
        }
    }

    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        String[] vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String vertex: vertexs) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);





        graph.showGraph();
        graph.bfs();

    }
}
