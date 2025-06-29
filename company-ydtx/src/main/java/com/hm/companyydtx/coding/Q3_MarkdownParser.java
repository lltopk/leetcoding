package com.hm.companyydtx.coding;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Q3_MarkdownParser {
    public static void main(String[] args) {
//        String mdText = "# 1Title\n## 2title\n#### 4title\n### 3title\n## 2subtitle\n# 1Title";
        String mdText = "# Title\n## Subtitle\n### Subsubtitle\n## Another subtitle\n# Another Title";
//        String mdText = "# Title\n## Subtitle\n### Subsubtitle\n## Another subtitle\n";
        Node root = parseMarkdown(mdText);
        printTree(root, 0);
    }
    static class Node {
        int level;
        String content;
        List<Node> children;

        public Node(int level, String content) {
            this.level = level;
            this.content = content;
            this.children = new ArrayList<>();
        }
    }
    public static Node parseMarkdown(String mdText) {
        String[] lines = mdText.split("\\n");
        Node root = new Node(0,"root");//level为0代表假根，最大Title其实是从1开始
        Stack<Node> stack = new Stack<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            int level = line.length() - line.replaceAll("#", "").length();
            String content = line.replaceAll("#", "").trim();
            Node node = new Node(level, content);
//            if (level == 1) {
//                root = node;
//            } else {
                //当前节点的级别更高,则stack中部分老的节点就没有价值了
                while (!stack.isEmpty() && level <= stack.peek().level ) {
                    stack.pop();
                }
                //经过上述while条件对stack栈顶元素的清除，若stack不为空，则当前节点一定是栈顶节点的子节点
                if (!stack.isEmpty()) {
                    stack.peek().children.add(node);
                } else {//经过上述while条件对stack栈顶元素的清除，若stack被完全清空了，则说明当前level=1，新的最大标题出现了
                    root.children.add(node);
                }
//            }
            stack.push(node);//辅助栈不需要返回，仅用于构造最终结果之前的栈判断
        }
        return root;
    }

    public static void printTree(Node node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println(node.content);
        for (Node child : node.children) {
            printTree(child, depth + 1);
        }
    }



}
