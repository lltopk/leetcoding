package org.lyflexi.solutions.baseAlgorithm.slippoints.winVariableSize.seekLengthMin;

/**
 * 632. 最小区间
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 你有 k 个 非递减排列 的整数列表。找到一个 最小 区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
 *
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出：[20,24]
 * 解释：
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * 示例 2：
 *
 * 输入：nums = [[1,2,3],[1,2,3],[1,2,3]]
 * 输出：[1,1]
 *
 *
 * 提示：
 *
 * nums.length == k
 * 1 <= k <= 3500
 * 1 <= nums[i].length <= 50
 * -105 <= nums[i][j] <= 105
 * nums[i] 按非递减顺序排列
 *
 */

import java.util.Arrays;
import java.util.List;

/**
 * 不定长滑动窗口求最小
 *
 * 时间复杂度为total + total*log(total) + total*k
 * 其中total为list<list>中全部元素的个数, k为list<list>中数组的个数
 *
 * 上述时间复杂度前面是二维数组初始化, 中间是合并后的数组排序, 后面是滑动窗口(其中k是窗口收缩的判断条件)
 */
public class LC632_smallestRange {
    //不定长滑动窗口求最小
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        //难题要做数组转化, 将list<list>合并排序为1个二维数组 pairs[][2], 其中pairs[i][0]是值, pairs[i][1]是该值原来所处的是哪个数组,
        int total = 0;
        for(int i = 0;i <k; i++){
            total +=nums.get(i).size();
        }
        int idxOfList = 0;//初始化list<list>的索引
        int[][]pairs = new int[total][2];
        for(int i =0; i<total; ){
            List<Integer> list = nums.get(idxOfList);
            for(int j = 0; j<list.size(); j++){
                pairs[i][0] = list.get(j);
                pairs[i][1] = idxOfList;
                i++;
            }
            idxOfList++;
        }

        //因为题目要求数字区间, 因此需要对pairs进行排序, 按照pairs[i][0]的大小进行排序
        //按第一列升序
        Arrays.sort(pairs, (pair1, pair2) -> { return pair1[0] - pair2[0] ;});

        int ansL = pairs[0][0];
        int ansR = pairs[total-1][0];
        int l = 0;
        int[] helper = new int[k];//记录每个list出现的次数, 无关具体的元素
        //对pairs进行不定长滑动窗口
        for(int r = 0; r < total; r++){
            helper[pairs[r][1]]++;

            int rV = pairs[r][0];

            //正面计算每个list出现的次数会导致while循环的判断复杂度为k
            while(shrink(helper)){
                int lV = pairs[l][0];//这行代码一定要放入循环内部, 否则窗口收缩后的lV将不受l++变化而变化
                if(rV-lV < ansR -ansL  || (rV-lV == ansR -ansL && lV<ansL)){
                    ansL = lV;
                    ansR = rV;
                }
                helper[pairs[l][1]]--;
                l++;
            }
        }

        return new int[]{ansL, ansR};
    }

    /**
     * 判断是否包含所有编号，判断所有 helper[i] 是否都大于 0
     * @param helper
     * @return
     */
    private boolean shrink(int[] helper){
        int n =helper.length;
        for(int i =0 ;i<n ;i++){
            if(helper[i] == 0){
                return false;
            }
        }
        return true;
    }
}
