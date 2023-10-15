package com.ck.json;

public class CKJSONStr {
    private static NFANode 程序开始节点;

    static {
        程序开始节点 = new NFANode("程序开始节点", null);
        NFANode 空格开始 = new NFANode("空格开始", (obj) -> " ".equals(obj));
        NFANode 开始符号 = new NFANode("开始符号", (obj) -> "\"".equals(obj));
        NFANode 普通内容 = new NFANode("普通内容", (obj) -> obj != null && !"\\".equals(obj) && !"\"".equals(obj));
        NFANode 结束符号 = new NFANode("结束符号", (obj) -> "\"".equals(obj));
        NFANode 转义符号 = new NFANode("转义符号", (obj) -> "\\".equals(obj));
        NFANode 转义内容 = new NFANode("转义内容", (obj) -> obj != null);
        NFANode 程序结束节点 = new NFANode("程序结束节点", (obj) -> obj == null);
        NFANode 空格结束 = new NFANode("空格结束", (obj) -> " ".equals(obj));


        //开始构建关系
        程序开始节点.addNext(空格开始, 开始符号);
        空格开始.addNext(空格开始, 开始符号, 程序结束节点);
        开始符号.addNext(普通内容, 转义符号, 结束符号);
        普通内容.addNext(普通内容, 转义符号, 结束符号);
        结束符号.addNext(程序结束节点, 空格结束);
        转义符号.addNext(转义内容);
        转义内容.addNext(普通内容, 转义符号, 结束符号);
    }

    public static boolean match(String str) {
        return 程序开始节点.match(str);
    }
}
