import java.util.ArrayList;

//2 element birleştir ve 2.elementen sonraki 2 elemtnide büyüklük karşılaştır ikisinden de büyükse sona at
public class SpecialQueue {
    private hfTreeNode min1 = new hfTreeNode(null, 999999999);
    private hfTreeNode min2 = new hfTreeNode(null, 999999999);
    private int size = 0;
    ArrayList<hfTreeNode> qArr = new ArrayList<>();
    ArrayList<Boolean> helper = new ArrayList<>();
    int lastIndex = 0;
    int min1Index = 0;
    int min2Index = 0;
    int startIndex = 0;

    public void enqueue(hfTreeNode item) {
        qArr.add(item);
        helper.add(true);
        lastIndex++;
        size++;
    }

    public void printer() {
        for (int i = 0; i < qArr.size(); i++) {
            if (helper.get(i) != false) {
                System.out.println(qArr.get(i).getkey() + "_" + qArr.get(i).getValue() + "==" + i);
            }
        }
    }

    public hfTreeNode getLast() {
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i) != false) {
                min1 = qArr.get(i);
                min1Index = i;
                break;
            }
        }
        for (int i = min1Index + 1; i < helper.size(); i++) {
            if (helper.get(i) != false) {
                min2 = qArr.get(i);
                break;
            }
        }
        hfTreeNode lastnd = new hfTreeNode(" ", (min1.getValue() + min2.getValue()));
        lastnd.lefhfNode = min1;
        lastnd.righthfNode = min2;
        return lastnd;
    }

    public void printerhelp() {
        for (int i = 0; i < helper.size(); i++) {
            System.out.println(helper.get(i));
        }
    }

    public hfTreeNode dequeue() {

        for (int i = 0; i < qArr.size(); i++) {
            if (qArr.get(i) != null && helper.get(i) != false) {
                helper.set(i, false);
                return qArr.get(i);
            }
        }

        size--;
        return null;
    }

    public hfTreeNode peek() {
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public hfTreeNode getMin1() {
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i) == true && qArr.get(i) != min2) {
                min1Index = i;
                min1 = qArr.get(i);
                break;
            }
        }

        for (int i = 0; i < qArr.size(); i++) {
            if (qArr.get(i) != null && helper.get(i) != false) {
                if (qArr.get(i) != null && qArr.get(i).getValue() < min1.getValue() && qArr.get(i) != min2) {
                    min1 = qArr.get(i);
                    min1Index = i;
                }
            }
        }
        hfTreeNode temp = min1;
        helper.set(min1Index, false);
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i) == true && qArr.get(i) != min2) {
                min1Index = i;
                min1 = qArr.get(i);
                break;
            }
        }

        for (int i = 0; i < qArr.size(); i++) {
            if (qArr.get(i) != null && helper.get(i) != false) {
                if (qArr.get(i) != null && qArr.get(i).getValue() < min1.getValue() && qArr.get(i) != min2) {
                    min1 = qArr.get(i);
                    min1Index = i;
                }
            }
        }
        size--;
        return temp;

    }

    public hfTreeNode getMin2() {
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i) == true && qArr.get(i) != min1) {
                min2Index = i;
                min2 = qArr.get(i);
                break;
            }
        }
        for (int i = 0; i < qArr.size(); i++) {
            if (qArr.get(i) != null && helper.get(i) != false) {
                if (qArr.get(i) != null && qArr.get(i).getValue() < min2.getValue() && qArr.get(i) != min1) {
                    min2 = qArr.get(i);
                    min2Index = i;
                }
            }
        }
        hfTreeNode temp = min2;
        helper.set(min2Index, false);
        for (int i = 0; i < helper.size(); i++) {
            if (helper.get(i) == true && qArr.get(i) != min1) {
                min2Index = i;
                min2 = qArr.get(i);
                break;
            }
        }
        for (int i = 0; i < qArr.size(); i++) {
            if (qArr.get(i) != null && helper.get(i) != false) {
                if (qArr.get(i) != null && qArr.get(i).getValue() < min2.getValue() && qArr.get(i) != min1) {
                    min2 = qArr.get(i);
                    min2Index = i;
                }
            }
        }
        size--;
        return temp;
    }

}
