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
 * 时间复杂度为total + total*log(total) + total
 * 其中total为list<list>中全部元素的个数, k为list<list>中数组的个数
 *
 * 优化了LC632_smallestRange, 省去了shrink的O(k)判断,
 *
 * 与LC632_smallestRange2优化手段相同, 区别在于LC632_smallestRange3维护的int cntCovered = 0 记录包含list的总次数, 滑动窗口有效的条件是cntCovered == k
 *
 */
public class LC632_smallestRange3 {
    //不定长滑动窗口求最小
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        //难题要做数组转化, 将list<list>合并排序为1个二维数组 pairs[][2], 其中pairs[i][0]是值, pairs[i][1]是该值原来所处的是哪个数组,
        int total = 0;
        for(int i = 0;i <k; i++){
            total +=nums.get(i).size();
        }

        int[][]pairs = new int[total][2];
        int pi = 0;//初始化pairs的过程就是把list<list>往pairs中放
        for(int i =0; i<nums.size(); i++ ){
            for(int x: nums.get(i)){
                pairs[pi][0] = x;
                pairs[pi][1] = i;
                pi++;
            }
        }

        //因为题目要求数字区间, 因此需要对pairs进行排序, 按照pairs[i][0]的大小进行排序
        //按第一列升序
        Arrays.sort(pairs, (pair1,pair2) -> { return pair1[0] - pair2[0] ;});

        int ansL = pairs[0][0];
        int ansR = pairs[total-1][0];
        int l = 0;

        int[] helper = new int[k];//记录每个list出现的次数, 无关具体的元素
        int cntCovered = 0;//记录包含list的总次数, 滑动窗口有效的条件是cntCovered == k

        //对pairs进行不定长滑动窗口
        for(int r = 0; r < total; r++){
            helper[pairs[r][1]]++;
            //首次出现某个list, cntCovered++, 需要注意不能重复++
            if(helper[pairs[r][1]]==1){
                cntCovered++;
            }

            int rV = pairs[r][0];

            while(cntCovered == k){
                int lV = pairs[l][0];
                if(rV-lV < ansR -ansL  || (rV-lV == ansR -ansL && lV<ansL)){
                    ansL = lV;
                    ansR = rV;
                }

                //同理当最后一个list即将移出窗口, cntCovered--, 需要注意不能重复--
                if(helper[pairs[l][1]] == 1){
                    cntCovered--;
                }
                helper[pairs[l][1]]--;
                l++;
            }
        }

        return new int[]{ansL, ansR};
    }
}
