package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 应用场景-集合覆盖问题
 * <p>
 * > 假设存在下面需要付费的广播台，以及广播台信号可以覆盖的的地区，如何选择最少的广播台，让所有的地区都可以接受信号
 * <p>
 * |广播台|覆盖地区|
 * |----|----|
 * |K1 | 北京，上海，天津|
 * |K2 | 广州，北京，深圳|
 * |K3 | 成都，上海，杭州|
 * |K4 | 上海，天津|
 * |K5 | 杭州，大连|
 * Author Mayiwei
 * Date 2020/4/20
 */
public class GreedyAlgorithm {

    /**
     * 得到最少的广播台集合
     *
     * @param map
     * @return
     */
    public static ArrayList<String> getLeastBroadcast(HashMap<String, HashSet<String>> map) {
        HashSet<String> allAreasSet = getAllAreas(map);
        ArrayList<String> selects = new ArrayList<>();


        while (allAreasSet.size() != 0) {
            String maxKey = null;

            //找到最多地区的广播与全部地区取交集（就是覆盖的区域）设置为maxkey（第一次maxKey为null，就把K1设置为maxkey
            for (String key : map.keySet()) {
                HashSet<String> areas = map.get(key);
                areas.retainAll(allAreasSet);

                if (areas.size() > 0 && (maxKey == null || areas.size() > map.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                //在集合中添加
                selects.add(maxKey);
                //然后把这个电台覆盖的区域在下次比较中去掉
                allAreasSet.removeAll(map.get(maxKey));
            }
        }
        return selects;
    }


    /**
     * 得到所有的地区
     *
     * @param map 存放广播台和对应的地区
     * @return
     */
    private static HashSet<String> getAllAreas(HashMap<String, HashSet<String>> map) {
        HashSet<String> set = new HashSet<>();
        for (String key : map.keySet()) {
            set.addAll(map.get(key));
        }
        return set;
    }


    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        HashSet<String> set2 = new HashSet<>();
        set2.add("广州");
        set2.add("北京");
        set2.add("深圳");

        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        broadcasts.put("K1", set1);
        broadcasts.put("K2", set2);
        broadcasts.put("K3", set3);
        broadcasts.put("K4", set4);
        broadcasts.put("K5", set5);

        HashSet<String> allAreas = getAllAreas(broadcasts);
        System.out.print("全部地区:");
        for (String area : allAreas) {
            System.out.print(area + " ");
        }

        System.out.println();

        ArrayList<String> list = getLeastBroadcast(broadcasts);
        System.out.println("广播台" + list);

    }
}
