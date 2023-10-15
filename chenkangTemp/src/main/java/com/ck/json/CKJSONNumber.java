package com.ck.json;

import java.util.Arrays;

public class CKJSONNumber {
    private static NFANode 程序开始节点;

    static {
        程序开始节点 = new NFANode("程序开始节点", null);
        NFANode 空格开始 = new NFANode("空格开始", (obj) -> " ".equals(obj));
        NFANode 正负号 = new NFANode("正负号", (obj) -> Arrays.asList("+", "-").contains(obj));
        NFANode 整数部分 = new NFANode("整数部分", (obj) -> (obj + "").matches("\\d+"));
        NFANode 小数点 = new NFANode("小数点", (obj) -> ".".equals(obj));
        NFANode 小数部分 = new NFANode("小数部分", (obj) -> (obj + "").matches("\\d+"));
        NFANode 空格结束 = new NFANode("空格结束", (obj) -> " ".equals(obj));
        NFANode 程序结束节点 = new NFANode("程序结束节点", (obj) -> obj == null);

        //开始构建关系
        程序开始节点.addNext(空格开始, 正负号, 整数部分, 小数点);
        空格开始.addNext(空格开始, 正负号, 整数部分, 小数点);
        正负号.addNext(整数部分, 小数点);
        整数部分.addNext(整数部分, 空格结束, 小数点, 程序结束节点);
        小数点.addNext(小数部分, 程序结束节点, 空格结束);
        小数部分.addNext(小数部分, 程序结束节点, 空格结束);
        空格结束.addNext(空格结束, 程序结束节点);
    }

    public static boolean match(String str) {
        return 程序开始节点.match(str);
    }
}
