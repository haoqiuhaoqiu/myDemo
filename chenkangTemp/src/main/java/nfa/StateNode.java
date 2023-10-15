package nfa;

import java.util.List;
import java.util.Map;

/***
 * 状态节点
 */
public class StateNode {
    private String name;
    private Map<String, StateNode> link;
    private boolean begin;
    private boolean end;

    public boolean isItCertain(List<StateNode> p) {
        if (p.contains(this)) {
            return false;
        }

        //存在空边
        if (link.keySet().contains("")) {
            return false;
        }

        for (StateNode sn : link.values()) {
            p.add(this);
            if (sn.isItCertain(p)) {
                continue;
            }
            return false;
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, StateNode> getLink() {
        return link;
    }

    public void setLink(Map<String, StateNode> link) {
        this.link = link;
    }
}
