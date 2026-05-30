package org.lyflexi.solutions.baseAlgorithm.utils.stack.list_simulation;

/**
 * 1472. 设计浏览器历史记录
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 你有一个只支持单个标签页的 浏览器 ，最开始你浏览的网页是 homepage ，你可以访问其他的网站 url ，也可以在浏览历史中后退 steps 步或前进 steps 步。
 *
 * 请你实现 BrowserHistory 类：
 *
 * BrowserHistory(string homepage) ，用 homepage 初始化浏览器类。
 * void visit(string url) 从当前页跳转访问 url 对应的页面  。执行此操作会把浏览历史前进的记录全部删除。
 * string back(int steps) 在浏览历史中后退 steps 步。如果你只能在浏览历史中后退至多 x 步且 steps > x ，那么你只后退 x 步。请返回后退 至多 steps 步以后的 url 。
 * string forward(int steps) 在浏览历史中前进 steps 步。如果你只能在浏览历史中前进至多 x 步且 steps > x ，那么你只前进 x 步。请返回前进 至多 steps步以后的 url 。
 *
 *
 * 示例：
 *
 * 输入：
 * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 * 输出：
 * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 *
 * 解释：
 * BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 * browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
 * browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
 * browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
 * browserHistory.back(1);                   // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
 * browserHistory.back(1);                   // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
 * browserHistory.forward(1);                // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
 * browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
 * browserHistory.forward(2);                // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
 * browserHistory.back(2);                   // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
 * browserHistory.back(7);                   // 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"
 *
 *
 * 提示：
 *
 * 1 <= homepage.length <= 20
 * 1 <= url.length <= 20
 * 1 <= steps <= 100
 * homepage 和 url 都只包含 '.' 或者小写英文字母。
 * 最多调用 5000 次 visit， back 和 forward 函数。
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 栈
 */
public class LC1472_BrowserHistory2 {
    //用列表/数组模拟栈， 支持随机访问性能更高
    final List<String> history = new ArrayList<>();
    //游标卡尺
    int cur = 0;
    public LC1472_BrowserHistory2(String homepage) {
        history.add(homepage);
    }

    public void visit(String url) {
        //SubList内部持有的仍然是完整list的引用
        history.subList(++cur, history.size()).clear();
        history.add(url);
    }

    /**
     * 缩回卡尺
     * @param steps
     * @return
     */
    public String back(int steps) {
        cur = Math.max(0, cur-steps);
        return history.get(cur);
    }

    /**
     * 前进卡尺
     * @param steps
     * @return
     */
    public String forward(int steps) {
        cur = Math.min(history.size()-1, cur+steps);
        return history.get(cur);
    }
}
