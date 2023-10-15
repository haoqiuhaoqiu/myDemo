package com.ck.json;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;

/***
 * 有限自动机的状态
 */
public class NFANode {
    //名字
    public String name;

    private Function<String, Boolean> fun;

    private LinkedList<NFANode> nextNode = new LinkedList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NFANode nfaNode = (NFANode) o;

        return name != null ? name.equals(nfaNode.name) : nfaNode.name == null;
    }

    @Override
    public int hashCode() {
        return name.length();
    }

    public NFANode(String name, Function<String, Boolean> fun) {
        this.name = name;
        this.fun = fun;
    }

    public void addNext(NFANode... nodes) {
        this.nextNode.addAll(Arrays.asList(nodes));
    }

    public NFANode nextNode(String str) {
        final NFANode[] ret = {null};
        this.nextNode.forEach(node -> {
            //没匹配上就直接推出下一个
            if (!node.fun.apply(str)) {
                return;
            }
            //如果已经被匹配过了返回错误表示有多个都能匹配上,就不是确定的有限状态自动机了
            if (ret[0] != null) {
                throw new RuntimeException(this.name + " 匹配不确定:" + str);
            }
            ret[0] = node;
        });
        if (ret[0] == null) {
            throw new RuntimeException(this.name + "没有匹配到:" + str);
        }
        return ret[0];
    }

    public boolean match(String str) {
        NFANode node = this;
        for (Integer i = 0; i < str.length(); i++) {
            String currChar = str.charAt(i) + "";
            node = node.nextNode(currChar);
            System.out.println("数据类型:" + node.name + "  数据:" + currChar);
        }
        node = node.nextNode(null);
        System.out.println("数据类型:" + node.name + "  数据:" + null);
        if (!"程序结束节点".equals(node.name)) {
            System.out.println("没有结束!!!");
            return false;
        }
        return true;
    }
}