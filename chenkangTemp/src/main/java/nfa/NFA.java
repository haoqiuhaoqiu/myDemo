package nfa;

/***
 *
 */
public class NFA {
    private StateNode sn;

    public NFA(StateNode sn) {
        this.sn = sn;
    }

    public boolean match(String str) {
        return true;
    }

    /***
     * 是否是确定的
     * @return
     */
    public boolean isDFA() {
        return false;
    }
}
