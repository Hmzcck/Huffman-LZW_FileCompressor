public class hfTreeNode {
    private int val;
    private String ch;
    public String byteCode;
    hfTreeNode lefhfNode;
    hfTreeNode righthfNode;

    public hfTreeNode(String car, int val) {
        this.val = val;
        this.ch = car;
        this.righthfNode = null;
        this.lefhfNode = null;
    }

    public int getValue() {
        return val;
    }

    public String getkey() {
        return ch;
    }

    public void setValue(Integer val) {
        this.val = val;
    }

    public void setKey(String ch) {
        this.ch = ch;
    }
    
}
